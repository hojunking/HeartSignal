package com.hs.meetme;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hs.meetme.coursecreate.mapper.CourseCreateMapper;

@SpringBootTest
public class CreateCourseTest {
	
	@Autowired CourseCreateMapper courseCreateMapper;
	
	@Test
	public void client() {
//		String[] arr1 = {"21", "3", "1", "첫번째 코스", "50000"};
//		String[] arr2 = {"22", "3", "2", "두번째 코스", "50000"};
//		String[] arr3 = {"23", "3", "3", "", "50000"};
//		
//		List<String[]> list = new ArrayList<String[]>();
//		
//		list.add(arr1);
//		list.add(arr2);
//		list.add(arr3);
//		
//		System.out.println(list);
//		
//		int result = courseCreateMapper.createCourseOrder(list);
//		
//		System.out.println(result);
	}
	
}
