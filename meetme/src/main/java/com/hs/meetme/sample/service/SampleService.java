package com.hs.meetme.sample.service;

import java.util.List;

import com.hs.meetme.coursecreate.domain.TagVO;
import com.hs.meetme.sample.domain.SampleVO;


public interface SampleService {
	List<SampleVO> getList();
	List<TagVO> getTagList();
}
