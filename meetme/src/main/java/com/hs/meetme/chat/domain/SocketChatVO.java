package com.hs.meetme.chat.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Data 어노테이션은 getter, setter를 자동 생성합니다.
@Data

// AllArgsConstructor 어노테이션은 생성자를 자동 생성합니다.
@AllArgsConstructor
@NoArgsConstructor
public class SocketChatVO {
	private String email;
	
    // 유저의 이름을 저장하기 위한 변수
    private String userName;

    // 메시지의 내용을 저장하기 위한 변수
    private String content;
}
