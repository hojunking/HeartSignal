package com.hs.meetme.payment.api;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.hs.meetme.payment.domain.PaymentVO;

public class RefundAPI {
	
	HttpURLConnection con = null;
	StringBuilder sb;
	
	public String Refund(PaymentVO vo) {
		
		String token = vo.getToken() ;
		String merchant_uid = vo.getMerchantUid();
		
		System.out.println("토근="+token+" merchant="+merchant_uid);
		try {
			
			String apiURL = "https://api.iamport.kr/payments/cancel"; // url 집어넣기
			System.out.println(apiURL);
			URL url = new URL(apiURL);
			con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("POST"); // POST
			con.setRequestProperty("Authorization", token); //해더
			
			// post request
            String postParams = "merchant_uid=" +merchant_uid;
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
