package com.hs.meetme.mypage.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hs.meetme.image.domain.ImageVO;
import com.hs.meetme.image.service.ImageService;
import com.hs.meetme.mypage.domain.MyPageCourseVO;
import com.hs.meetme.mypage.domain.MyPageUserInfoVO;
import com.hs.meetme.mypage.domain.UserTagsVO;
import com.hs.meetme.mypage.service.MypageService;
import com.hs.meetme.notice.domain.NoticeVO;
import com.hs.meetme.notice.service.NoticeService;
import com.hs.meetme.useraccess.domain.AccountVO;

@RestController
@RequestMapping("/mypage/*")
public class MypageRestController {

	@Autowired
	MypageService mypageService;
	@Autowired
	NoticeService noticeService;
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	ImageService imageService;

//	File fileDir = new File("src/main/resources/static/img/");

	//수정하기(프로필이미지)
	@PostMapping("/imgUpdate")
	@Transactional
	public boolean imgUpdate(Model model, MultipartFile updateImage, MyPageUserInfoVO mypageUserInfoVO, ImageVO imgvo, HttpServletRequest request) {
        //유저 세션 불러오기
		HttpSession session = request.getSession();
		AccountVO accountVO = (AccountVO) session.getAttribute("userSession");
		int r = 0;
		
		String path = request.getSession().getServletContext().getRealPath("/img/user");
		File filePath = new File(path);
		if(!filePath.exists()) {
			filePath.mkdirs();
		}
		try {
            //String path = fileDir.getAbsolutePath() + "/user/";
			MultipartFile file = updateImage;
			if (!file.isEmpty() && file.getSize() > 0) {
				String filename = file.getOriginalFilename();
				UUID uuid = UUID.randomUUID();
				String imgUrl = uuid + "_" + filename;
				File uufile = new File(path, imgUrl);
				System.out.println(uufile.getPath());

				imgvo.setImgUrl(imgUrl);
				r = imageService.insertImage(imgvo);
				
				file.transferTo(uufile); // 파일 옮기기

				mypageUserInfoVO.setImgId(String.valueOf(imgvo.getImgId()));
				accountVO.setImgUrl(imgUrl);
			}
			r += mypageService.userUpdateImage(mypageUserInfoVO);
		} catch (Exception e) {
			e.printStackTrace();
			r = 0;
		}
		return r == 2 ? true : false;
	}

	@DeleteMapping("/deleteNotice")
	public boolean deleteNotice(NoticeVO noticeVO) {

		int r = noticeService.deleteNotice(noticeVO);

		return r == 1 ? true : false;
	}

	@DeleteMapping("/deleteCourse")
	@Transactional
	public boolean deleteCourse(@RequestBody MyPageCourseVO myPageCourseVO, HttpServletRequest request) {

		// 세션 쓰는법
		HttpSession session = request.getSession();
		AccountVO accountVO = (AccountVO) session.getAttribute("userSession");
		String userId = accountVO.getUserId();
		String courseUserId = myPageCourseVO.getUserId();

		int r = 0;
		if (userId.equals(courseUserId)) {
			r = mypageService.deleteCourse(myPageCourseVO);
			mypageService.deleteCourseLike2(myPageCourseVO);
		} else {
			myPageCourseVO.setUserId(userId);
			r = mypageService.deleteCourseLike(myPageCourseVO);
		}
		return r == 1 ? true : false;
	}

	// 현재 비밀번호 확인
	@PostMapping("/passwordRead")
	public boolean passwordRead(@RequestBody MyPageUserInfoVO vo) {
		String currentPassword = vo.getPassword();
		String dbPassword = mypageService.userSelectPassword(vo).getPassword();

		return encoder.matches(currentPassword, dbPassword);
	}

	// 수정하기 (비밀번호)
	@PutMapping("/passwordUpdate")
	public boolean passwordUpdate(@RequestBody MyPageUserInfoVO vo) {
		String newPassword = vo.getPassword();// 새로 입력한 비밀번호

		// 암호화 된 비밀번호
		String encodedPw = encoder.encode(newPassword);

		vo.setPassword(encodedPw);

		int r = mypageService.userUpdatePassword(vo);

		return r == 1 ? true : false;
	}

	// 닉네임 중복 확인
	@PostMapping("/nickNameRead")
	public boolean nickNameRead(@RequestBody MyPageUserInfoVO vo) {
		if (mypageService.userSelectNickName(vo) == null) {
			return true;
		} else {
			return false;
		}
	}

	// 수정하기 (닉네임)
	@PutMapping("/nickNameUpdate")
	public MyPageUserInfoVO nickNameUpdate(@RequestBody MyPageUserInfoVO myPageUserInfoVO, HttpServletRequest request) {
		HttpSession session = request.getSession();
		((AccountVO) session.getAttribute("userSession")).setNickname(myPageUserInfoVO.getNickName());
		mypageService.userUpdateNickName(myPageUserInfoVO);

		return myPageUserInfoVO;
	}

	//수정하기 (주소)
	@PutMapping("/addressUpdate")
	public MyPageUserInfoVO addressUpdate(@RequestBody MyPageUserInfoVO myPageUserInfoVO, HttpServletRequest request) {
		HttpSession session = request.getSession();
		((AccountVO) session.getAttribute("userSession")).setAddrzonecode(myPageUserInfoVO.getAddrzonecode());
		((AccountVO) session.getAttribute("userSession")).setAddrfull(myPageUserInfoVO.getAddrfull());
		((AccountVO) session.getAttribute("userSession")).setAddrdetail(myPageUserInfoVO.getAddrdetail());
		mypageService.updateAddress(myPageUserInfoVO);

		return myPageUserInfoVO;
	}

	
	//수정하기 (생년월일)
	@PutMapping("/birthDayUpdate")
	public MyPageUserInfoVO birthDayUpdate(@RequestBody MyPageUserInfoVO myPageUserInfoVO) {
		
		mypageService.userUpdateBirthDay(myPageUserInfoVO);
		
		return myPageUserInfoVO;
	}

	@PostMapping("/saveUserTags")
	@Transactional
	public List<UserTagsVO> tagsUpdate(String tags, String userId) {
		mypageService.deleteUserTags(userId);
		
		List<UserTagsVO> tagList = new ArrayList<UserTagsVO>();
		
		String[] str = tags.split(" ");
		System.out.println(str.toString());
		
		for(int i=0; i<str.length; i++) {
			UserTagsVO vo = new UserTagsVO();
			vo.setUserId(userId);
			vo.setTagId(str[i]);
			tagList.add(vo);
			System.out.println(tagList);
		}
		mypageService.insertUserTags(tagList);
		
		return tagList;
	}

}