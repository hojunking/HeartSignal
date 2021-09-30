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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hs.meetme.mypage.domain.PostVO;
import com.hs.meetme.post.service.PostService;

import lombok.extern.java.Log;


@Controller
@RequestMapping("/post")
@Log
public class PostController {
	@Autowired PostService pService;
	
	//커뮤니티 리스트 조회... 페이징 해야함!
	@GetMapping("/community_list")
	public String community_list(Model model, HttpServletRequest request) {
		
		List<PostVO> list = new ArrayList<>();
		list = pService.getCMList();
		model.addAttribute("list",list);
		
		return "post/community_list";
	}
	
	//커뮤니티 글 상세보기
	@GetMapping("/get_community")
	public String get_community(@RequestParam(value="idx", required=false)Long idx, Model model) {
		return null;
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
	
	//커뮤니티 글 입력
	@GetMapping("/community_insert")
	public String community_insert() {
		return "post/community_insert";
	}
	
//	@RequestMapping(value="/mine/imageUpload.do", method = RequestMethod.POST) 
//	public void imageUpload(HttpServletRequest request, HttpServletResponse response, MultipartHttpServletRequest multiFile , @RequestParam MultipartFile upload)
//			throws Exception{
//			// 랜덤 문자 생성 
//			UUID uid = UUID.randomUUID(); OutputStream out = null; PrintWriter printWriter = null; 
//			//인코딩 
//			response.setCharacterEncoding("utf-8"); response.setContentType("text/html;charset=utf-8"); 
//				try{ 
//						//파일 이름 가져오기 
//						String fileName = upload.getOriginalFilename(); 
//						byte[] bytes = upload.getBytes(); 
//					
//				//이미지 경로 생성 
//					String path = fileDir.getPath() + "ckImage/";
//					// fileDir는 전역 변수라 그냥 이미지 경로 설정해주면 된다. 
//					String ckUploadPath = path + uid + "_" + fileName; 
//					File folder = new File(path); 
//					//해당 디렉토리 확인 
//					if(!folder.exists()){ try{ folder.mkdirs(); 
//						// 폴더 생성 
//						}catch(Exception e){
//							e.getStackTrace(); 
//							}
//					} 
//					out = new FileOutputStream(new File(ckUploadPath)); 
//					out.write(bytes); out.flush(); 
//					// outputStram에 저장된 데이터를 전송하고 초기화 
//					String callback = request.getParameter("CKEditorFuncNum"); 
//					printWriter = response.getWriter(); 
//					String fileUrl = "/mine/ckImgSubmit.do?uid=" + uid + "&fileName=" + fileName; 
//					// 작성화면
//					// 업로드시 메시지 출력 
//					printWriter.println("{\"filename\" : \""+fileName+"\", \"uploaded\" : 1, \"url\":\""+fileUrl+"\"}"); 
//					printWriter.flush()
//					; 
//				}catch(IOException e){ 
//					e.printStackTrace(); 
//				} finally { 
//					try { 
//						if(out != null) { 
//							out.close(); 
//							} 
//						if(printWriter != null) {
//							printWriter.close(); 
//							} 
//						} catch(IOException e) { 
//							e.printStackTrace(); 
//							}
//					} return; 
//					
//				}
//		
//	@RequestMapping(value="/mine/ckImgSubmit.do") 
//	public void ckSubmit(@RequestParam(value="uid") String uid , @RequestParam(value="fileName") String fileName , HttpServletRequest request, HttpServletResponse response) 
//			throws ServletException, IOException{ 
//		//서버에 저장된 이미지 경로 
//		String path = fileDir.getPath() + "ckImage/"; 
//		String sDirPath = path + uid + "_" + fileName; 
//		File imgFile = new File(sDirPath); 
//		//사진 이미지 찾지 못하는 경우 예외처리로 빈 이미지 파일을 설정한다. 
//		if(imgFile.isFile()){ 
//			byte[] buf = new byte[1024]; 
//			int readByte = 0; int length = 0; byte[] imgBuf = null; 
//			FileInputStream fileInputStream = null; 
//			ByteArrayOutputStream outputStream = null; 
//			ServletOutputStream out = null; 
//		try{ 
//			fileInputStream = new FileInputStream(imgFile); 
//			outputStream = new ByteArrayOutputStream(); 
//			out = response.getOutputStream(); 
//			while((readByte = fileInputStream.read(buf)) != -1){ 
//				outputStream.write(buf, 0, readByte); 
//			} 
//			imgBuf = outputStream.toByteArray(); 
//			length = imgBuf.length; 
//			out.write(imgBuf, 0, length); 
//			out.flush(); 
//		}catch(IOException e){ 
//			logger.info(e); 
//			}
//		finally { 
//				outputStream.close;
//				fileInputStream.close; 
//				out.close;
//				} 
//		}
//	}
}

	
