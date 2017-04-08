package com.platform.makeyourevent.model;

public enum AssetType {
	
	MARIAGEGARDEN("marriage.garden"),TENTHOUSE("tent.house"),CATERERS("caterers"),BEAUTYPARLERS("beauty.parlers");
	
	private String values;

	private AssetType(String values) {
		this.values = values;
	}
	

}
