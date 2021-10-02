package com.hs.meetme.coursecreate.domain;

import lombok.Data;

@Data
public class CourseCreateVO {
	
	private String courseId;
	private String courseName;
	private String userId;
	private String courseLikes;
	private String courseState;
	private String courseOpen;
	private String region;
	
}
