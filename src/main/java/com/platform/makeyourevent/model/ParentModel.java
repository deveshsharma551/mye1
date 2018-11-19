package com.platform.makeyourevent.model;

import org.springframework.data.annotation.Id;

public abstract class ParentModel {
	
	@Id
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
