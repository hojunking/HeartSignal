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
	
	@PostMapping("/payment") //결제
	public String paymentInsert(PaymentVO vo) {
		
		payService.paymentInsert(vo); //결제정보 DB입력/
		System.out.println("결제정보 :"+vo);
		CoupleInfoVO oc = new CoupleInfoVO();
		String result="";
		
		int coupleId =vo.getCoupleId();
		
		if(coupleId !=0) {  //이미 커플인 경우(커플 아이디를 들고온다)
			oc.setCoupleId(coupleId);
		oc =coupleService.read(oc);  //커플의 기본정보 다 들고오기
		System.out.println("커플정보 :"+oc);
		
			if(oc.getCoupleStatus().equals("n")) { //기존 커플의 상태확인
				oc.setSubTerm(vo.getSubTerm());	//요금제 최신화
				oc.setCoupleStatus("y");		//커플의 상태 변경
				
				System.out.println("커플정보 갱신 :"+oc);
				coupleService.coupleInfoUpdate(oc); // subTerm,startDate,coupleStatus(y)로 변경
				
				System.out.println("사용자 상태변경:"+oc);
				oc.setUserId(oc.getUserRequest()); //userId에 req를 넣느다.
				coupleService.userCoupleStatusUpdate(oc); //유저테이블의 coupleStatus 둘 다 y변경
				result="기존커플갱신되었습니다.";
									//커플테이블의 상태가 n일 때 기존커플테이블 갱신
			}else {
				result="비정상적인 접근입니다."; //커플테이블의 상태가 y이거나 null인 경우의 접근(비정상)
			}
		
		}else {
			oc.setUserRequest(vo.getUserId());
			oc.setSubTerm(vo.getSubTerm());
			coupleService.coupleInfoInsert(oc); //커플테이블에 신규등록
			result="신규커플등록되었습니다.";
		}	//커플테이블에 대한 정보가 없을 때 신규테이블 생성
		return result;
	}
	
	
	@GetMapping("/refund") //환불하기
	public String refund(PaymentVO vo) {
		GetTokenAPI getToken =new GetTokenAPI();
		RefundAPI refund = new RefundAPI();
		PaymentVO pvo = new PaymentVO();
		vo= payService.latestpaid(vo);
		
		System.out.println("가장최근결제정보:"+vo);
		String json=getToken.getToken(); //토큰 받아오기 API 메소드 실행
        
		vo.setToken(json); //토큰받아오기
		
         System.out.printf("토큰여깃다! : "+json
		+"아임포트 : "+vo.getMerchantUid());
		
		String refundDb= refund.Refund(vo); //환불 API 실행 후 결과 저장
		//파싱 두번, response에서 또 get해서 value값 추출하면 db에 저장가능
		
		JSONParser parser = new JSONParser();
		JSONObject obj = null;
        try {
             obj = (JSONObject)parser.parse(refundDb); //파싱 1
             JSONObject refundParsing=(JSONObject) obj.get("response");
             System.out.println("1차 파싱="+refundParsing);
             
            
            System.out.println("1:"+refundParsing.get("buyer_email").toString());
            
           
            vo.setEmail(refundParsing.get("buyer_email").toString());
            vo.setUserName(refundParsing.get("buyer_name").toString());
            vo.setCancelAmount(Integer.parseInt(refundParsing.get("cancel_amount").toString()) );
            vo.setMerchantUid(Integer.parseInt(refundParsing.get("merchant_uid").toString()));
            System.out.println("들고온 친구들="+pvo);
             
            //vo객체 안에 바로 넣어서 DB로 넘기면 된다.
             //cancel_amount,buyer_name,buyer_email,merchant_uid
        } catch (ParseException e) {
             System.out.println("변환에 실패");
             e.printStackTrace();
        }
        
        payService.insertRefundInfo(pvo); //환불결과 DB에 저장 , 커플테이블 상태 비활성화
        
        CoupleInfoVO cvo=new CoupleInfoVO();
        cvo.setUserId(vo.getUserId());
        coupleService.userCoupleStatusRead(cvo); 
        String message ="";
	        if(cvo.getCoupleStatus().equals("w")) { //결제를 하고 커플 매칭에 실패하여 환불
	        	coupleService.deleteCoupleInfo(cvo); //커플 테이블을 삭제해줌
	        	cvo.setCoupleStatus("n"); //커플 상태를 w->n으로 다시 변경
	        	coupleService.userCoupleStatusUpdate(cvo);
	        	
	        	message="커플매칭 실패 후 환불";
	        }else{  //구독 중 커플의 환불
	        	cvo.setSubTerm(0); //구독기간 초기화
	        	cvo.setCoupleStatus("m"); //커플상태 m(커플이지만 구독기간 끝남)으로 변경
	        	coupleService.userCoupleStatusUpdate(cvo);
	        	message="커플구독 중 환불";
	        }
        
		return message;
	}
	
}
