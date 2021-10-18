package com.hs.meetme.coupleCourse.domain;

import java.util.Date;

import lombok.Data;

@Data
public class CoupleCourseVO {
	private String courseId;
	private String courseName;
	private String imgUrl;
	private String nickName;
	private String userId;
	private String courseState;
	private String courseOpen;
	private String region;
	private String sumCost;
	private int coupleId;
	private Date courseCreated;
	private String courseOrder;
	private int placeId;
	private String placeName;
	private String address;
	private String placePhone;
	private String avg_cost;
	private String description;
	private String thumbnailHref;

}
