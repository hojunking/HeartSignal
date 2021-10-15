package com.hs.meetme.mypage.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTagsVO {

	private String userId;
	private String tagId;

}
