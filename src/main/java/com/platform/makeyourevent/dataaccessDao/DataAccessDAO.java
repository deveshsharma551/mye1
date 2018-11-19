package com.platform.makeyourevent.dataaccessDao;

import java.util.List;
import java.util.Map;

public interface DataAccessDAO {
	
	public <T> T persist(T object);
	
	  public <T>  List<T> loadResources(Class<T> clss,Map<String,Object> filter);
	  
	  public <T>  T updateResources(Class<T> clss,Map<String,Object> filter);
	  
	  public <T>  T removeResources(Class<T> clss,Map<String,Object> filter);

}
