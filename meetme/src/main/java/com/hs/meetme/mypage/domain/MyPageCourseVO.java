package com.hs.meetme.mypage.domain;

import lombok.Data;

@Data
public class MyPageCourseVO {
	private String userId;
	private String courseId;
	private String courseName;
	private String courseState;
	private String courseOpen;
	private String region;
	private String courseOrder;
	private String placeId;
	private String placeName;
	private String address;
	private String placePhone;
	private String sumCost;
	private String description;
	private String region2;
	private String thumbnailHref;
	private String avgCost; //평균비용(인영)
	private String courseComment; //코스 comment(인영) 
	private String longtitude; //위도 (인영)
	private String latitude; //경도 (인영)
}
