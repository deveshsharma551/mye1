package com.platform.makeyourevent.dataaccessDao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.mongodb.WriteResult;


@Component
public class DataAccessDAOImpl implements DataAccessDAO{
	
	@Autowired
	private MongoTemplate mongoTemp;

	public <T> T  persist(T object) {
		// TODO Auto-generated method stub
		if(object!=null)
		{
		  mongoTemp.save(object);
		return object;
		}
		return null;
	}

	@Cacheable(value="Mongo-cache", key="#p0")
	public <T> List<T> loadResources(Class<T> clss, Map<String, Object> filter) {
		// TODO Auto-generated method stub
		if(filter!=null) {
		 Query q = new Query();
		 for(String key : filter.keySet()){
		 q.addCriteria(Criteria.where(key).is(filter.get(key)));}
		}
		//return mongoTemp.findOne(q,clss);
		 return mongoTemp.findAll(clss);
	}
	
	public <T> T loadResource(Class<T> clss, Map<String, Object> filter) {
		// TODO Auto-generated method stub
		 Query q = new Query();
		 for(String key : filter.keySet()){
		 q.addCriteria(Criteria.where(key).is(filter.get(key)));}
		return mongoTemp.findOne(q,clss);
		// return mongoTemp.findAll(clss);
	}

	@CachePut(value="Mongo-cache", key="#Mongo-cache.p0")
	public <T> T updateResources(Class<T> clss, Map<String, Object> filter) {
		// TODO Auto-generated method stub
		Query q = new Query();
		Update u = new Update();
		WriteResult writeResult = null;
		 for(String key : filter.keySet()){
		 if(key.equals("id"))
		 q.addCriteria(Criteria.where(key).is(filter.get(key)));
		 if(!key.equals("id"))
		 u.set(key, filter.get(key));
		 } 
		 writeResult  = mongoTemp.upsert(q,u,clss);
		 return mongoTemp.findOne(q, clss);
	}

	public <T> T removeResources(Class<T> clss, Map<String, Object> filter) {
		// TODO Auto-generated method stub
		 Query q = new Query();
		 for(String key : filter.keySet()){
		 q.addCriteria(Criteria.where(key).is(filter.get(key)));}
		 WriteResult writeResult = mongoTemp.remove(q, clss);
		return null;
	}
	
	@CacheEvict(value="Mongo-cache" ,allEntries=true)
	public void cacheClear()
	{
		
	}


}
