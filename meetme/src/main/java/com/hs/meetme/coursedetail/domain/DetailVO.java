package com.hs.meetme.coursedetail.domain;

import lombok.Data;

@Data
public class DetailVO {
	private String placeName;
	private String courseId;
	private String courseName;
	private String courseComment;
	private String latitude;
	private String longtitude;
	private String region;
	private String avgCost;
	private String thumbnailHref;
	private String courseOrder;
	private String nickname;
	private String description;

}
