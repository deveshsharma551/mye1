package com.platform.makeyourevent.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="db_merchants" )
public class Merchant extends ParentModel {
	
	private String merFirstName;
	private String merLastName;
	private String merMobileNumber;
	private String merEmailId;
	private List<EventAsset>  merAssetList;
	private ResourceLink selfLink;
	public ResourceLink getSelfLink() {
		return selfLink;
	}
	public void setSelfLink(ResourceLink selfLink) {
		this.selfLink = selfLink;
	}
	public String getMerFirstName() {
		return merFirstName;
	}
	public void setMerFirstName(String merFirstName) {
		this.merFirstName = merFirstName;
	}
	public String getMerLastName() {
		return merLastName;
	}
	public void setMerLastName(String merLastName) {
		this.merLastName = merLastName;
	}
	public String getMerMobileNumber() {
		return merMobileNumber;
	}
	public void setMerMobileNumber(String merMobileNumber) {
		this.merMobileNumber = merMobileNumber;
	}
	public String getMerEmailId() {
		return merEmailId;
	}
	public void setMerEmailId(String merEmailId) {
		this.merEmailId = merEmailId;
	}
	public List<EventAsset> getMerAssetList() {
		return merAssetList;
	}
	public void setMerAssetList(List<EventAsset> merAssetList) {
		this.merAssetList = merAssetList;
	}
	

}
