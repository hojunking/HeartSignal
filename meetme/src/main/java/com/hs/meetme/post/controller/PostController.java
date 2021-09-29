package com.hs.meetme.post.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		if(idx==null)
		{
			//올바르지 않은 접근
			return "post/community_lis";
		}
		
		PostVO post = pService.getPost();
		if(board==null || "Y".equals(board.getDeleteYn())) {
			//없는 게시글 or 이미 삭제된 게시글
			return "redirect:/board/list.do";
		}
		model.addAttribute("board",board);
		
		return "board/view";
	}
	
	//커뮤니티 글 입력
	@GetMapping("/community_insert")
	public String community_insert() {
		return "post/community_insert";
	}
}
