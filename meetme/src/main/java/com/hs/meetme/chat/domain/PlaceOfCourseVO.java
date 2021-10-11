package com.hs.meetme.chat.domain;

import lombok.Data;

@Data
public class PlaceOfCourseVO {
	private String subTitle;
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
