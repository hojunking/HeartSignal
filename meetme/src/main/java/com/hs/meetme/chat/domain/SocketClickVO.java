package com.hs.meetme.chat.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocketClickVO {
	
	// 메시지의 내용을 저장하기 위한 변수
    private String clickContent;
}
