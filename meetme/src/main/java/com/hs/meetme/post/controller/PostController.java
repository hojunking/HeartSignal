package com.hs.meetme.post.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hs.meetme.mypage.domain.CommentVO;
import com.hs.meetme.mypage.domain.Criteria;
import com.hs.meetme.mypage.domain.MyPageCourseVO;
import com.hs.meetme.mypage.domain.PageVO;
import com.hs.meetme.mypage.domain.PostVO;
import com.hs.meetme.notice.domain.NoticeVO;
import com.hs.meetme.notice.service.NoticeService;
import com.hs.meetme.post.service.PostService;
import com.hs.meetme.useraccess.domain.AccountVO;

import lombok.extern.java.Log;

@Controller
@RequestMapping("/post")
@Log
public class PostController {

	@Autowired
	PostService pService;
	@Autowired
	NoticeService noticeService;

	File fileDir = new File("src/main/resources/static/img/");

	// 커뮤니티 리스트 조회... 페이징 해야함!
	@GetMapping("/community_list")
	public String community_list(Model model, Criteria cri) {
//		cri.setAmount(20);
		int total = pService.getTotalCmNum(cri);
//		System.out.println("토탈 ==== "+total);
		model.addAttribute("count", pService.getCmNum());

		List<PostVO> list = new ArrayList<>();
		list = pService.getCMList(cri);
//		System.out.println("리스트 넘어온거"+list);
		model.addAttribute("list", list);
		model.addAttribute("pageMaker", new PageVO(cri, total));
		return "post/community_list";
	}

	// 커뮤니티 글 상세보기
	@GetMapping("/get_community/{postId}")
	public String get_community(@PathVariable long postId, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		AccountVO accountVO = (AccountVO) session.getAttribute("userSession");
		
		if(accountVO == null) {
			return "redirect:/login";
		}
		
		String userId = accountVO.getUserId();

		PostVO post = pService.getPost(postId);
		post.setUserId(userId);
				
		// 좋아요 했으면 1 안했으면 0
		int isLike = pService.getPostLike(post);
		model.addAttribute("like", isLike);
		
		// 스크랩 했으면 1 안했으면 0
		int isScraped = pService.getCourseScrap(post);
		model.addAttribute("scrap", isScraped);
		
		String courseId = post.getCourseId();
		model.addAttribute("list", pService.getPost(postId));
		model.addAttribute("cmt", pService.commentCM(postId));
		int count = pService.countCM(postId);
		model.addAttribute("count",count);
		if (courseId != null) {
			model.addAttribute("course", pService.getCourse(courseId));
		}

		pService.countHit(postId);

		return "post/community_get";
		/*
		 * if(idx==null) { //올바르지 않은 접근 return "post/community_lis"; }
		 * 
		 * PostVO post = pService.getPost(); if(board==null ||
		 * "Y".equals(board.getDeleteYn())) { //없는 게시글 or 이미 삭제된 게시글 return
		 * "redirect:/board/list.do"; } model.addAttribute("board",board);
		 * 
		 * return "board/view";
		 */
	}
	
	

	// 커뮤니티 글 입력
	@GetMapping("/community_insert")
	public String community_insert(Model model,HttpServletRequest request, MyPageCourseVO vo) {
		HttpSession session = request.getSession();
		AccountVO accountVO = (AccountVO) session.getAttribute("userSession");
		vo.setUserId(accountVO.getUserId());
		model.addAttribute("places", pService.getPlaceList(vo));
		model.addAttribute("list", pService.getCourseList(vo));

		return "post/community_insert";
	}

	// 입력하면 부르는 함수
	@GetMapping("/insertCommunity")
	public String insertCommunity(@ModelAttribute PostVO vo, HttpServletResponse insert/* , HttpSession session */) {
		pService.CMInsert(vo);
		return "redirect:/post/community_list";
	}

	// 커뮤니티 수정 할 페이지
	@GetMapping("/updateCommunity")
	public String updateCommunity(Model model, PostVO vo,  MyPageCourseVO pVo) {
		PostVO list = pService.getPost(Long.parseLong(vo.getPostId()));
		model.addAttribute("list", list);
		String courseId = list.getCourseId();
		
		if (courseId != null) {
			model.addAttribute("course", pService.getCourse(courseId));
		}
		String userId = list.getUserId();
		pVo.setUserId(userId);
		model.addAttribute("cList", pService.getCourseList(pVo));
		model.addAttribute("places", pService.getPlaceList(pVo));
		
		return "post/community_update";
	}

	// 커뮤니티 수정
	@PostMapping("/updateCommunity")
	public String update_community(@ModelAttribute PostVO vo, HttpServletResponse update/* , HttpSession session */) {
		System.out.println("vo =="+vo);
		pService.postUpdate(vo);
		int postId = Integer.parseInt(vo.getPostId());
		System.out.println("postId == "+postId);
		String page = "redirect:/post/get_community/"+postId;
		return page;
	}

	// 커뮤니티, 추천 모두 삭제가능한데.. redirect페이지가?!
	@PostMapping("/postDelete")
	public String postDelete(@ModelAttribute PostVO vo) {
		pService.postDelete(vo);
		System.out.println(vo);
		return "redirect:/post/community_list";
	}

	/*
	 * //커뮤니티 댓글 불러오기
	 * 
	 * @GetMapping("/commentCM") public String commentCM(CommentVO cVo) {
	 * pService.commentCM(Long.parseLong(cVo.getPostId())); return
	 * "post/community_get"; }
	 */

	// 커뮤니티 댓글 작성
	@ResponseBody
	@PostMapping("/insertCMComment")
	public CommentVO insertCMComment(@RequestBody CommentVO vo, HttpServletRequest request) {
		
		
		HttpSession session = request.getSession();
		pService.insertCMComment(vo);
		AccountVO accountVO = (AccountVO) session.getAttribute("userSession");
		vo.setNickname(accountVO.getNickname());
		vo.setImgUrl(accountVO.getImgUrl());
		
		//notice INSERT 
		System.out.println("보낸사람"+vo.getUserId()+"+"+"게시글만든 "+vo.getPostUserId());
		if(!vo.getUserId().equals(vo.getPostUserId())) { 		//댓글을 작성하면 게시글
			NoticeVO nVo = new NoticeVO();
			nVo.setUserSent(vo.getUserId()); 			//댓글 쓴 사람
			nVo.setUserReceived(vo.getPostUserId()); 	//게시글 쓴 사람
			nVo.setPostId(vo.getPostId()); 				// 게시글 번호 넣기
			nVo.setNoticeContent("\""+vo.getPostTitle()+"\"에 "+accountVO.getNickname()+"님이 댓글을 달았습니다.");
			noticeService.insertNotice(nVo);
			System.out.println("댓글달고 댓글정보 INSERT"+nVo);
		}
		//notice INSERT end

		return vo;
	}

	//커뮤니티 댓글 수정
	@ResponseBody
	@PutMapping("/commentUpdate")
	public CommentVO commentUpdate(@RequestBody CommentVO vo) {
		pService.commentUpdate(vo);
		return vo;
	}

	//커뮤니티 댓글 삭제
	@ResponseBody
	@DeleteMapping("/delCMComment/{commentId}")
	public int delCMComment(@PathVariable long commentId) {
		return pService.commentDelete(commentId);
	}

	//글 좋아요(스크랩)
	@ResponseBody
	@PostMapping("/postLike")
	public int postLike(@RequestBody Map<String, Long> map) {
		long postId = map.get("postId");
		long userId = map.get("userId");
		return pService.postLike(postId, userId);
	}
	
	
	//코스 스크랩
	@ResponseBody
	@PostMapping("/scrapCourse")
	public int scrapCourse(@RequestBody Map<String, Long> map) {
		long courseId = map.get("courseId");
		long userId = map.get("userId");
		return pService.scrapCourse(courseId, userId);
	}
	

	//글 좋아요 삭제
	@ResponseBody
	@DeleteMapping("/postLikeCancel")
	public int postLikeCancel(@RequestBody Map<String, Long> map) {
		long postId = map.get("postId");
		long userId = map.get("userId");
		return pService.postLikeCancle(postId, userId);
	}
	
	//코스 스크랩 삭제
	@ResponseBody
	@DeleteMapping("/scrapCourseCancel")
	public int scrapCourseCancel(@RequestBody Map<String, Long> map) {
		long courseId = map.get("courseId");
		long userId = map.get("userId");
		return pService.scrapCancel(courseId, userId);
	}

	
	@RequestMapping(value = "/ckeditor/fileUpload", method = RequestMethod.POST)
	public void imageUpload(HttpServletRequest request, HttpServletResponse response,
			MultipartHttpServletRequest multiFile, @RequestParam MultipartFile upload) throws Exception {
		// 랜덤 문자 생성
		UUID uid = UUID.randomUUID();
		OutputStream out = null;
		PrintWriter printWriter = null;
		// 인코딩
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		try {
			// 파일 이름 가져오기
			String fileName = upload.getOriginalFilename();
			byte[] bytes = upload.getBytes();

			
			// 이미지 경로 생성
//			String path = fileDir.getAbsolutePath() + "/ckImage/";
			// fileDir는 전역 변수라 그냥 이미지 경로 설정해주면 된다.
			
			String path = request.getSession().getServletContext().getRealPath("/img/ckImage");
			String ckUploadPath = path + uid + "_" + fileName;
			File folder = new File(path);
			// 해당 디렉토리 확인
			if (!folder.exists()) {
				try {
					folder.mkdirs();
					// 폴더 생성
				} catch (Exception e) {
					e.getStackTrace();
				}
			}
			out = new FileOutputStream(new File(ckUploadPath));
			out.write(bytes);
			out.flush();
			// outputStram에 저장된 데이터를 전송하고 초기화
			String callback = request.getParameter("CKEditorFuncNum");
			printWriter = response.getWriter();
			String fileUrl = "/post/ckImgSubmit?uid=" + uid + "&fileName=" + fileName;
			// 작성화면
			// 업로드시 메시지 출력
			printWriter.println("{\"filename\" : \"" + fileName + "\", \"uploaded\" : 1, \"url\":\"" + fileUrl + "\"}");
			printWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (printWriter != null) {
					printWriter.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return;

	}

	@RequestMapping(value = "/ckImgSubmit")
	public void ckSubmit(@RequestParam(value = "uid") String uid, @RequestParam(value = "fileName") String fileName,
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 서버에 저장된 이미지 경로
//		String path = fileDir.getAbsolutePath() + "/ckImage/";
		String path = request.getSession().getServletContext().getRealPath("/img/ckImage");
		String sDirPath = path + uid + "_" + fileName;
		File imgFile = new File(sDirPath);
		// 사진 이미지 찾지 못하는 경우 예외처리로 빈 이미지 파일을 설정한다.
		if (imgFile.isFile()) {
			byte[] buf = new byte[1024];
			int readByte = 0;
			int length = 0;
			byte[] imgBuf = null;
			FileInputStream fileInputStream = null;
			ByteArrayOutputStream outputStream = null;
			ServletOutputStream out = null;
			try {
				fileInputStream = new FileInputStream(imgFile);
				outputStream = new ByteArrayOutputStream();
				out = response.getOutputStream();
				while ((readByte = fileInputStream.read(buf)) != -1) {
					outputStream.write(buf, 0, readByte);
				}
				imgBuf = outputStream.toByteArray();
				length = imgBuf.length;
				out.write(imgBuf, 0, length);
				out.flush();
			} catch (IOException e) {
				log.info(e.toString());
			} finally {
				outputStream.close();
				fileInputStream.close();
				out.close();
			}
		}
	}
}
