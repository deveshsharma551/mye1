package com.platform.makeyourevent.model;

public class ResourceLink {
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	private String name;
	private String linkUrl;
	
	public ResourceLink(String name,String linkUrl)
	{
		this.name = name;
		this.linkUrl = linkUrl;
	}

}
