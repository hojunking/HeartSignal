package com.hs.meetme.coupleinfo.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestTestAPI {
	HttpURLConnection con = null;
	StringBuilder sb;
	
	public String getToken() {
		
		String imp_key = "3776906865835153";
		String imp_secret= "fe482934383aee2c35e87ca125160098b4ea4161967bf80c4ba354418b7b9678daed5d2af48fc97a";
		
		try {
			
			String apiURL = "https://api.iamport.kr/users/getToken"; // url 집어넣기
			System.out.println(apiURL);
			URL url = new URL(apiURL);
			con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("POST"); // POST
//			con.setRequestProperty("Authorization", token); //해더
			
			
			
			// post request
            String postParams = "imp_key=" +imp_key+ "&imp_secret=" +imp_secret;
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();
            System.out.println(wr);
		
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if(responseCode == 200) { // 정상 호출
				System.out.println(responseCode);
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                
            } else {  // 에러 발생
            	System.out.println(responseCode);
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                
            }
			
			sb = new StringBuilder();
            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            
            System.out.println(sb.toString());

			br.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return sb.toString();
	}
}
