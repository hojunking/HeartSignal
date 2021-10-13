package com.hs.meetme.useraccess.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hs.meetme.image.domain.ImageVO;
import com.hs.meetme.image.service.ImageService;
import com.hs.meetme.useraccess.domain.AccountVO;
import com.hs.meetme.useraccess.service.AccountService;

@Controller
public class SecurityController {

	@Autowired
	AccountService accountService;
	@Autowired
	ImageService imageService;
	
	File fileDir = new File("src/main/resources/static/img/");

	@GetMapping("/signUp")
	public String signUp() {
		return "security/signUp";
	}

	@PostMapping("/signUp")
	@Transactional
	public String signUpPro(Model model, MultipartFile filepond, AccountVO vo, ImageVO imgvo) {
		System.out.println(filepond);
		System.out.println(vo);
		try {
			String path = fileDir.getAbsolutePath() + "/user/";
			MultipartFile file = filepond;
			if (!file.isEmpty() && file.getSize() > 0) {
				String filename = file.getOriginalFilename();
				UUID uuid = UUID.randomUUID();
				String imgUrl = uuid + filename;
				File uufile = new File(path, imgUrl);
				System.out.println(uufile.getPath());
				
				imgvo.setImgUrl(imgUrl);
				imageService.insertImage(imgvo);
				
				file.transferTo(uufile); //파일 옮기기
				
				vo.setImgId(String.valueOf(imgvo.getImgId()));
			}
			accountService.signUp(vo);
			model.addAttribute("signUp", true);
		} catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("signUp", false);
		}
		return "security/login";
	}
	
	@GetMapping("/findPwd")
	public String findPwd() {
		return "security/findPwd";
	}
	
	@PostMapping("/updatePwd")
	public String updatePwd(AccountVO vo) {
		System.out.println("===============");
		int result = accountService.updatePwd(vo);
		System.out.println(result);
		System.out.println("===============");
		return "redirect:/login";
	}

	@GetMapping("/login")
	public String login() {
		return "security/login";
	}
	
	@GetMapping("/naver/login")
	public String naverLogin() {
		return "security/naverLogin";
	}
	
	@PostMapping("/naver/login")
	@ResponseBody
	public Object naverLogin(@RequestBody String token, AccountVO vo, HttpServletRequest request) {

		HttpSession session = request.getSession();
		String header = "Bearer " + token; // Bearer 다음에 공백 추가
		String apiURL = "https://openapi.naver.com/v1/nid/me";

		Map<String, String> requestHeaders = new HashMap<>();
		requestHeaders.put("Authorization", header);
		String responseBody = get(apiURL, requestHeaders);
		
		JSONParser parser = new JSONParser();
		JSONObject obj = null;
		try {
			obj = (JSONObject) parser.parse(responseBody);
			obj = (JSONObject) obj.get("response");
			System.out.println(obj);
			vo.setEmail((String) obj.get("email"));
			vo.setPassword((String) obj.get("id"));
			vo.setName((String) obj.get("name"));
			vo.setNickname((String) obj.get("nickname"));
			vo.setPhoneNum((String) obj.get("mobile"));
			vo.setBirthYear((String) obj.get("birthyear"));
			vo.setBirthDay((String) obj.get("birthday"));
			
			if(accountService.emailCheck(vo.getEmail()) == 0) {
				accountService.signUp(vo);
			}
	
			session.setAttribute("userSession", accountService.loadUserByUsername(vo.getEmail()));
			
			System.out.println(((AccountVO) session.getAttribute("userSession")).toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return obj.get("response");
	}
	

	private static String get(String apiUrl, Map<String, String> requestHeaders) {
		HttpURLConnection con = connect(apiUrl);
		try {
			con.setRequestMethod("GET");
			for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
				con.setRequestProperty(header.getKey(), header.getValue());
			}

			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
				return readBody(con.getInputStream());
			} else { // 에러 발생
				return readBody(con.getErrorStream());
			}
		} catch (IOException e) {
			throw new RuntimeException("API 요청과 응답 실패", e);
		} finally {
			con.disconnect();
		}
	}
	

	private static HttpURLConnection connect(String apiUrl) {
		try {
			URL url = new URL(apiUrl);
			return (HttpURLConnection) url.openConnection();
		} catch (MalformedURLException e) {
			throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
		} catch (IOException e) {
			throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
		}
	}
	

	private static String readBody(InputStream body) {
		InputStreamReader streamReader = new InputStreamReader(body);

		try (BufferedReader lineReader = new BufferedReader(streamReader)) {
			StringBuilder responseBody = new StringBuilder();

			String line;
			while ((line = lineReader.readLine()) != null) {
				responseBody.append(line);
			}

			return responseBody.toString();
		} catch (IOException e) {
			throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
		}
	}

}
