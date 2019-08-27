package com.platform.makeyourevent.dataaccessService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.platform.makeyourevent.dataaccessDao.DataAccessDAO;

@Component
public class DataAccessServiceImpl implements DataAccessService{
	
	@Autowired
	private DataAccessDAO dataAccessDao;

	public <T> T persist(T entity) {
		// TODO Auto-generated method stub
		return dataAccessDao.persist(entity);
	}

	public <T> List<T> loadResources(Class<T> clss, Map<String, Object> filter) {
		// TODO Auto-generated method stub
		return dataAccessDao.loadResources(clss, filter);
	}

	public <T> T loadResource(Class<T> clss, Map<String, Object> filter) {
		// TODO Auto-generated method stub
		return dataAccessDao.loadResource(clss, filter);
	}
	
	public <T> T updateResources(Class<T> clss, Map<String, Object> filter) {
		// TODO Auto-generated method stub
		return dataAccessDao.updateResources(clss,filter);
	}

	public <T> T removeResources(Class<T> clss, Map<String, Object> filter) {
		// TODO Auto-generated method stub
		return dataAccessDao.removeResources(clss,filter);
	}
	
	

}
