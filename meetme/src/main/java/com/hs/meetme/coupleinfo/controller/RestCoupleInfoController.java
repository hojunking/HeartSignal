package com.hs.meetme.coupleinfo.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hs.meetme.coupleinfo.domain.CoupleInfoVO;
import com.hs.meetme.coupleinfo.service.CoupleInfoService;

@RestController

public class RestCoupleInfoController {
		@Autowired CoupleInfoService coupleService;
		
		
		@GetMapping("/matching/{id}")
		public String matchingUpdate(@PathVariable int id) {
			return null;
		}
		
		@GetMapping("/coupleInfo/{id}") //커플테이블정보와 나의 정보 불러오기
		public CoupleInfoVO coupleMainInfo(@PathVariable int id) {
			CoupleInfoVO vo = new CoupleInfoVO();
			vo.setUserId(id);
			vo =coupleService.coupleInfoSelect(vo);
			return vo;
		}
		@GetMapping("/myloveInfo") //내 여친정보 불러오기
		public CoupleInfoVO myLoveInfo(CoupleInfoVO vo) {
			if(vo.getCoupleStatus().equals("y")) {
			vo = coupleService.myLoverInfo(vo);
			}
			System.out.println(vo);
			return vo;
		}
		@PostMapping("/coupleImage")
		public CoupleInfoVO updateCoupleImage(MultipartFile uploadFile, CoupleInfoVO vo)
				throws IllegalStateException, IOException {
			String path="d:/download";
			MultipartFile ufile = uploadFile;
			if(!ufile.isEmpty() && ufile.getSize() > 0) {
				String filename = ufile.getOriginalFilename();
				UUID uuid = UUID.randomUUID();
				File file = new File(path, uuid + filename+".jpg");
				ufile.transferTo(file);
				vo.setImgName(filename);
			}
			
			System.out.println(vo.getImgName());
			return vo;
		}
		
		
		
}