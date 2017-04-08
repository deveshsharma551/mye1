package com.platform.makeyourevent.DataBaseAccessUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
/*import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;*/

@Configuration
public class DataAccessProvider {
	
	
	@Autowired
	private Environment env;
	
	/*
     * mongoclientoption to provide detault settings and 
     * here max wait time  a thread  will wait for connection
     */
	/*@Bean 
	public MongoClientOptions mongoOptions()
	{
	  return new MongoClientOptions.Builder().maxWaitTime(1000 * 60 * 5).build();	
	}
	
	
	@Bean
	public MongoDbFactory mongoDbFactory()
	{
		String mongodbUrl = env.getProperty("mongodb.url");
		String mongoDatabase = env.getProperty("mongodb.db");
		MongoClient mongoClient = new MongoClient(mongodbUrl, mongoOptions());
		return new SimpleMongoDbFactory(mongoClient, mongoDatabase);
	}
	
	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		return new MongoTemplate(mongoDbFactory());

	}*/
	
	
	


}
