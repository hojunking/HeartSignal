package com.hs.meetme.main.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hs.meetme.main.domain.MainVO;

@Mapper
public interface MainMapper {
	List<MainVO> tagList();
}
