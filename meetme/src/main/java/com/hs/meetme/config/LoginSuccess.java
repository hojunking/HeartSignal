package com.hs.meetme.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.hs.meetme.useraccess.domain.AccountVO;

public class LoginSuccess implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication auth) throws IOException, ServletException {
		List<String> roleNames = new ArrayList<>();
		
		auth.getAuthorities().forEach(authority -> {
			roleNames.add(authority.getAuthority());
		});
		
		System.out.println("====================================================");
		System.out.println("session : "+auth.getPrincipal());
		HttpSession session = request.getSession();
		session.setAttribute("userSession", auth.getPrincipal());
		AccountVO vo = (AccountVO) session.getAttribute("userSession");
		System.out.println("session info : "+vo.getUserId());

		response.sendRedirect("/");
	}

}
