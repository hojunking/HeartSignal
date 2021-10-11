package com.hs.meetme.useraccess.controller;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@RestController
public class CertificationController {
	
	@PostMapping("/api/certify")
	public String certificate(Model model, 
					@RequestBody Map<String, String> certiInfo) { 
		String to = certiInfo.get("to");
		String from = certiInfo.get("from");

		return sendSMS(to, from);
	}
	
	private String sendSMS(String to, String from) {
		String api_key = "NCSBKJDBS8TQSIMZ";
	    String api_secret = "IVW9NICH2HSWLLCWLKKYE9YYF2ACH3YW";
	    String certiNum = String.valueOf((int) (Math.random() * 1000000));
	    
	    Message coolsms = new Message(api_key, api_secret);

	    // 4 params(to, from, type, text) are mandatory. must be filled
	    HashMap<String, String> params = new HashMap<String, String>();
	    params.put("to", to);
	    params.put("from", from);
	    params.put("type", "SMS");
	    params.put("text", "인증번호는 "+certiNum+"입니다.");

	    try {
	      JSONObject obj = (JSONObject) coolsms.send(params);
	      System.out.println(obj.toString());
	    } catch (CoolsmsException e) {
	      System.out.println(e.getMessage());
	      System.out.println(e.getCode());
	    }
	    return certiNum;
	}
}
