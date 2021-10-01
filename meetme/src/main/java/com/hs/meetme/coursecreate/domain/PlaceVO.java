package com.hs.meetme.coursecreate.domain;

import lombok.Data;

@Data
public class PlaceVO {
	
	private String placeId; // pk 
	private String placeName; // nn
	private String address;
	private String placePhone;
	private String latitude; // 경도
	private String longtitude; // 위도
	private String avgCost;
	private String description;
	private String region;
	private String thumbnailHref;
	
}
