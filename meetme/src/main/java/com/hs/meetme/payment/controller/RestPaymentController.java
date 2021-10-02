package com.hs.meetme.payment.controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hs.meetme.coupleinfo.domain.CoupleInfoVO;
import com.hs.meetme.coupleinfo.service.CoupleInfoService;
import com.hs.meetme.payment.api.GetTokenAPI;
import com.hs.meetme.payment.api.RefundAPI;
import com.hs.meetme.payment.domain.PaymentVO;
import com.hs.meetme.payment.service.PaymentService;


@RestController
public class RestPaymentController {
	@Autowired PaymentService payService;
	@Autowired CoupleInfoService coupleService;
	
	@PostMapping("/payment")
	public String paymentInsert(PaymentVO vo,CoupleInfoVO cvo) {
		
		payService.paymentInsert(vo); //결제정보 DB입력/
		System.out.println("결제정보 :"+vo);
		CoupleInfoVO oc = new CoupleInfoVO();
		System.out.println("입력정보확인 :"+cvo);
		String result="";
		
		if(cvo.getCoupleId()!=0) {  //이미 커플인 경우(커플 아이디를 들고온다)
		oc =coupleService.read(cvo);
		System.out.println("커플정보 :"+oc);
		cvo.setUserRequest(vo.getUserId());
		cvo.setSubTerm(vo.getSubTerm());
		
		if(oc.getCoupleStatus().equals("n")) {
			
			coupleService.coupleInfoUpdate(cvo);
			result="기존커플갱신되었습니다.";
			//커플테이블의 상태가 n일 때 기존커플테이블 갱신
		}else {
			
			result="비정상적인 접근입니다.";
		}
			//커플테이블의 상태가 y이거나 null인 경우의 접근
					
		}else {
			
			oc.setUserRequest(vo.getUserId());
			oc.setSubTerm(vo.getSubTerm());
//			coupleService.coupleInfoInsert(oc); //커플테이블에 신규등록
			result="신규커플등록되었습니다.";
		}	//커플테이블에 대한 정보가 없을 때 신규테이블 생성
		return result;
	}
	@GetMapping("/refund")
	public String refund(PaymentVO vo) {
		GetTokenAPI getToken =new GetTokenAPI();
		RefundAPI refund = new RefundAPI();
		
		vo= payService.readPayInfo(vo);
		
		System.out.println("결제정보조회결과:"+vo);
		String json=getToken.getToken();
        
		vo.setToken(json);
		
         System.out.printf("토큰여깃다! : "+json
		+"아임포트 : "+vo.getMerchantUid());
		
		String refundDb= refund.Refund(vo);
		//파싱 두번, response에서 또 get해서 value값 추출하면 db에 저장가능
		
		JSONParser parser = new JSONParser();
		JSONObject obj = null;
		String resultRefund = "";
        try {
             obj = (JSONObject)parser.parse(refundDb);
             JSONObject refundParsing=(JSONObject) obj.get("response");
             resultRefund = refundParsing.get("").toString();
             //vo객체 안에 바로 넣어서 DB로 넘기면 된다.
             //cancel_amount,buyer_name,buyer_email,merchant_uid
             System.out.println("파싱최종="+resultRefund);
        } catch (ParseException e) {
             System.out.println("변환에 실패");
             e.printStackTrace();
        }
		return resultRefund;
	}
	
}
