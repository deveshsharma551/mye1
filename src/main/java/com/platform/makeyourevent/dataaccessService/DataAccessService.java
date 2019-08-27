package com.platform.makeyourevent.dataaccessService;

import java.util.List;
import java.util.Map;

public interface DataAccessService {
	
	public <T>  T persist(T entity);
	
    public <T>  List<T> loadResources(Class<T> clss,Map<String,Object> filter);
    
    public <T> T loadResource(Class<T> clss,Map<String,Object> filter);
    
    public <T>  T  updateResources(Class<T> clss,Map<String,Object> filter);
    
    public <T>  T  removeResources(Class<T> clss,Map<String,Object> filter);
	
	}
