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
import com.hs.meetme.post.service.PostService;
import com.hs.meetme.useraccess.domain.AccountVO;

import lombok.extern.java.Log;

@Controller
@RequestMapping("/post")
@Log
public class PostController {

	@Autowired
	PostService pService;

	File fileDir = new File("src/main/resources/static/img/");

	// 커뮤니티 리스트 조회... 페이징 해야함!
	@GetMapping("/community_list")
	public String community_list(Model model, Criteria cri) {

		int total = pService.getTotalCmNum(cri);

		model.addAttribute("count", pService.getCmNum());

		List<PostVO> list = new ArrayList<>();
		list = pService.getCMList(cri);
		model.addAttribute("list", list);
		model.addAttribute("pageMaker", new PageVO(cri, total));
		return "post/community_list";
	}

	// 커뮤니티 글 상세보기
	@GetMapping("/get_community/{postId}")
	public String get_community(@PathVariable long postId, Model model) {
		PostVO post = pService.getPost(postId);
		String courseId = post.getCourseId();
		model.addAttribute("list", pService.getPost(postId));
		model.addAttribute("cmt", pService.commentCM(postId));
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
	public String community_insert() {
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
	public String updateCommunity(Model model, PostVO vo) {
		model.addAttribute("list", pService.getPost(Long.parseLong(vo.getPostId())));
		return "post/community_update";
	}

	// 커뮤니티 수정
	@PostMapping("/updateCommunity")
	public String update_community(@ModelAttribute PostVO vo) {

		pService.postUpdate(vo);
		return "redirect:/post/community_list";
	}

	// 커뮤니티, 추천 모두 삭제가능한데.. redirect페이지가?!
	@PostMapping("/postDelete")
	public String postDelete(PostVO vo) {
		pService.postDelete(vo);
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
		vo.setImgId(accountVO.getImgId());
		return vo;
	}

	// 커뮤니티 댓글 수정
	@ResponseBody
	@PutMapping("/commentUpdate")
	public CommentVO commentUpdate(@RequestBody CommentVO vo) {
		pService.commentUpdate(vo);
		return vo;
	}

	// 커뮤니티 댓글 삭제
	@ResponseBody
	@DeleteMapping("/delCMComment/{commentId}")
	public int delCMComment(@PathVariable long commentId) {
		return pService.commentDelete(commentId);
	}

	// 글 좋아요(스크랩)
	@ResponseBody
	@PostMapping("/postLike")
	public int postLike(@RequestBody long postId, long userId) {
		return pService.postLike(postId, userId);
		/* return "redirect:/post/get_community/{postId}"; */
	}

	// 내 코스 모달에 불러오기
	@ResponseBody
	@GetMapping("/getMyCourse")
	public List<MyPageCourseVO> getMyCourse(@ModelAttribute MyPageCourseVO vo) {

		/* model.addAttribute("course", pService.getCourseList(vo)); */
		List<MyPageCourseVO> list = pService.getCourseList(vo);
		System.out.println(vo);
		return list;
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
			String path = fileDir.getAbsolutePath() + "/ckImage/";
			// fileDir는 전역 변수라 그냥 이미지 경로 설정해주면 된다.
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
		String path = fileDir.getAbsolutePath() + "/ckImage/";
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
