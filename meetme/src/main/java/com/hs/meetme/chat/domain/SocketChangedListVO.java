package com.hs.meetme.chat.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocketChangedListVO extends PlaceOfCourseVO{
	
	private List<PlaceOfCourseVO> changedList;
	
}
