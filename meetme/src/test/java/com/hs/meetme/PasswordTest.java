package com.hs.meetme;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class PasswordTest {
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Test
	public void testEncode() {
		// 세션에 담긴 비밀번호(암호화 된 비밀번호)
		String sessionPw = encoder.encode("admin");
		// 사용자 입력한 비밀번호
		String inputPw = "admin";
		// 사용자가 바꾸려고 하는 비밀번호
		String changePw= "example";
		
		// 1. sessionPw와 inputPw를 비교한다 encoder.matches()
		// 2. 비교 결과가 true일 경우 changePw를 암호화(encoder.encode(changePw))
		// 3. 암호화된 changePw로 사용자의 비밀번호를 update하는 쿼리를 쓴다.

	}
}
