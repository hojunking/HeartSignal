package com.hs.meetme.chat.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.hs.meetme.chat.domain.PlaceOfCourseVO;
import com.hs.meetme.chat.domain.SocketChangedListVO;
import com.hs.meetme.chat.domain.SocketChatVO;
import com.hs.meetme.chat.domain.SocketInsertCourseVO;
import com.hs.meetme.chat.domain.SocketRegisterVO;
import com.hs.meetme.chat.domain.SocketSearchByTagVO;
import com.hs.meetme.chat.domain.SocketTagVO;
import com.hs.meetme.config.WSHandshakeInterceptor;
import com.hs.meetme.useraccess.domain.AccountVO;

@Controller
public class SocketController {
	
	// 커플 코스 만들기 끝내기
	@MessageMapping("/registerReceive")
    @SendTo("/send")
    public SocketRegisterVO SocketChangedListHandler(SocketRegisterVO vo) {
		String registerConfirmValue = vo.getRegisterConfirmValue();
 
        SocketRegisterVO result = new SocketRegisterVO(registerConfirmValue);
        return result;
    }
	
	// 수정한 코스 보내기
	@MessageMapping("/changeListReceive")
    @SendTo("/send")
    public SocketChangedListVO SocketChangedListHandler(SocketChangedListVO vo) {
        List<PlaceOfCourseVO> changedList = vo.getChangedList();
 
        SocketChangedListVO result = new SocketChangedListVO(changedList);
        return result;
    }
	
	// 코스에 장소 추가
	@MessageMapping("/insertCourseReceive")
    @SendTo("/send")
    public SocketInsertCourseVO SocketInsertCourseHandler(SocketInsertCourseVO vo) {
        String InsertCourse = vo.getInsertCourse();
 
        SocketInsertCourseVO result = new SocketInsertCourseVO(InsertCourse);
        return result;
    }
	
	// 태그를 기준 검색 클릭
	@MessageMapping("/searchByTagReceive")
    @SendTo("/send")
    public SocketSearchByTagVO SocketClickHandler(SocketSearchByTagVO vo) {
        String searchByTag = vo.getSearchByTag();

        SocketSearchByTagVO result = new SocketSearchByTagVO(searchByTag);
        return result;
    }
	
	// 태그 클릭
	@MessageMapping("/tagReceive")
    @SendTo("/send")
    public SocketTagVO SocketClickHandler(SocketTagVO vo) {
        String tagId = vo.getTagId();

        SocketTagVO result = new SocketTagVO(tagId);
        return result;
    }
	
    // /receive를 메시지를 받을 endpoint로 설정합니다.
    @MessageMapping("/chatReceive")
    
    // /send로 메시지를 반환합니다.
    @SendTo("/send")
    
    // SocketHandler는 1) /receive에서 메시지를 받고, /send로 메시지를 보내줍니다.
    // 정의한 SocketVO를 1) 인자값, 2) 반환값으로 사용합니다.
    public SocketChatVO SocketHandler(SocketChatVO socketVO) {
        // vo에서 getter로 userName을 가져옵니다.
//    	String currUserName = ((AccountVO) session.getAttribute("userSession")).getNickname();
//    	socketVO.setUserName(currUserName);
        String email = socketVO.getEmail();
        // vo에서 getter로 content를 가져옵니다.
        String sendUserName = WSHandshakeInterceptor.nameMap.get(email);
        String content = socketVO.getContent();

        // 생성자로 반환값을 생성합니다.
        SocketChatVO result = new SocketChatVO(email, sendUserName, content);
        // 반환
        return result;
    }
}