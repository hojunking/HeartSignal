package com.hs.meetme.coupleinfo.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hs.meetme.coupleinfo.domain.CoupleInfoVO;
import com.hs.meetme.coupleinfo.service.CoupleInfoService;
import com.hs.meetme.image.service.ImageService;
import com.hs.meetme.notice.domain.NoticeVO;
import com.hs.meetme.notice.service.NoticeService;
import com.hs.meetme.useraccess.domain.AccountVO;

@RestController

public class RestCoupleInfoController {
	@Autowired CoupleInfoService coupleService;
	@Autowired ImageService imageService;
	@Autowired NoticeService noticeService;
	
	File fileDir = new File("src/main/resources/static/img/");

	@PostMapping("/validation")
	public CoupleInfoVO checkExpiration(CoupleInfoVO vo) { // 커플아이디,유저아이디
		CoupleInfoVO cvo = new CoupleInfoVO();
		cvo = coupleService.read(vo); // 커플정보 들고오기
		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(cvo.getStartDate());
		cal.add(Calendar.MONTH, cvo.getSubTerm()); // 시작일에 구독기간(월) 더하기 => 만료일
		Date endDate = cal.getTime();
		Date sysdate = new Date(); // 현재 날짜

		System.out.println("커플로그 만료기간은" + simpleDate.format(endDate) + "입니다");

		cal.add(Calendar.DATE, -3); //
		Date endDateMinus3 = cal.getTime(); // 만료일 -3 구하기

		if (endDate.compareTo(sysdate) < 0) {
			vo.setSubTerm(0);
			vo.setCoupleStatus("n");
			coupleService.coupleInfoUpdate(vo);
			System.out.println("couple_status changed");

			vo.setCoupleStatus("e"); // 커플 유저 개별 커플상태 e로 변경
			coupleService.userCoupleStatusUpdate(vo);
			System.out.println("userCouple_status changed");
			vo.setEndDate(endDate); // 프론트에서 만료일자를 보여주기 위함
			vo.setCoupleStatus("n");
			return vo;
		} else if (endDateMinus3.compareTo(sysdate) < 0) { // 만료 3일 전 부터 alert
			System.out.println("만료 3일 전");
			cvo.setName(vo.getName());
			cvo.setEndDate(endDate);
			cvo.setCoupleStatus("n");

			return cvo;
		}
		System.out.println("정상적인 입장");
		return cvo;
	}
	@GetMapping("/endDate")
	public CoupleInfoVO readEndDate(CoupleInfoVO vo) {
		vo =coupleService.read(vo);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(vo.getStartDate());
		cal.add(Calendar.MONTH, vo.getSubTerm()); // 시작일에 구독기간(월) 더하기 => 만료일
		Date endDate = cal.getTime();
		vo.setEndDate(endDate);
		
		return vo;
	}
	
	
	// 커플 디폴트 이미지로 바꾸기
	@GetMapping("/defaultImage")
	public String defaultImage(@RequestParam int coupleId) {
		coupleService.couplePhotoDefault(coupleId);

		return "기본사진으로 변경되었습니다";
	}

	@PutMapping("/matching")
	public String matchingUpdate(CoupleInfoVO vo, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		AccountVO accountVO = (AccountVO) session.getAttribute("userSession");
		
		System.out.println("매칭하는 중 "+ vo);
		
		coupleService.coupleMatching(vo); //received 커플창에 업데이트
		
		vo.setUserId(vo.getUserReceived()); 
		vo.setCoupleStatus("y");
		coupleService.userCoupleStatusUpdate(vo); //유저들 상태 y로 업데이트
		
		NoticeVO nvo =new NoticeVO();
		
		String coupleId= String.valueOf(vo.getCoupleId());
		
		System.out.println("커플아이디는 "+coupleId);
		nvo.setCoupleId(coupleId);
		noticeService.deleteNotice(nvo);
		
		accountVO.setCoupleId(coupleId); //세션아이디 최신화
		
		return "커플매칭이 되었습니다!";
	}

	@GetMapping("/coupleInfo/{id}") // 커플테이블정보와 나의 정보 불러오기
	public CoupleInfoVO coupleMainInfo(@PathVariable int id) {
		CoupleInfoVO vo = new CoupleInfoVO();
		vo.setUserId(id);
		vo = coupleService.coupleInfoSelect(vo);
		return vo;
	}

	@GetMapping("/myloveInfo") // 내 여친정보 불러오기
	public CoupleInfoVO myLoveInfo(CoupleInfoVO vo) {
		if (vo.getCoupleStatus().equals("y")) {
			vo = coupleService.myLoverInfo(vo);
		}
		System.out.println(vo);
		return vo;
	}
	@GetMapping("/findLove") //내 여친찾기
	public CoupleInfoVO findLove(CoupleInfoVO vo) {
		System.out.println("누굴 찾나요?"+vo);
		vo=coupleService.findLove(vo);
		System.out.println("어딧니 내여친 "+vo);
		return vo;
	}

	@PostMapping("/coupleImage")
	public CoupleInfoVO updateCoupleImage(MultipartFile uploadFile, CoupleInfoVO vo)
			throws IllegalStateException, IOException {
		String path = fileDir.getAbsolutePath() + "/couple/";
		MultipartFile ufile = uploadFile;
		if (!ufile.isEmpty() && ufile.getSize() > 0) {
			String filename = ufile.getOriginalFilename();
			UUID uuid = UUID.randomUUID();
			String imgUrl = uuid + filename + ".jpg";
			File file = new File(path, imgUrl);
			ufile.transferTo(file); //파일 옮기기
			vo.setImgUrl(imgUrl);
			System.out.println("vo 확인하기 " + vo);
			imageService.insertImage(vo);  // 이미지 테이블에 등록
			coupleService.updateCoupleImage(vo); //이미지 커플테이블에 업뎃
		}
		return vo;
	}
	@PostMapping("/updateDday") //dday 업데이트하기
	public void updateDday(CoupleInfoVO vo) {
		System.out.println("커플디데이=" + vo.getCoupleDate());
		coupleService.updateDday(vo); // 커플 테이블 이미지, 디데이 업뎃
	}

}