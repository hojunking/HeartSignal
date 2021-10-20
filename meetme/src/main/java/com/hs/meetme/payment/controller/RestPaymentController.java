package com.hs.meetme.payment.controller;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hs.meetme.coupleinfo.domain.CoupleInfoVO;
import com.hs.meetme.coupleinfo.service.CoupleInfoService;
import com.hs.meetme.notice.domain.NoticeVO;
import com.hs.meetme.notice.service.NoticeService;
import com.hs.meetme.payment.api.GetTokenAPI;
import com.hs.meetme.payment.api.RefundAPI;
import com.hs.meetme.payment.domain.PaymentVO;
import com.hs.meetme.payment.service.PaymentService;
import com.hs.meetme.useraccess.domain.AccountVO;


@RestController
public class RestPaymentController {
	@Autowired PaymentService payService;
	@Autowired CoupleInfoService coupleService;
	@Autowired NoticeService noticeService;
	//Account VO는 다 session update
	@PostMapping("/payment") //결제
	public String paymentInsert(PaymentVO vo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		AccountVO accountVO = (AccountVO) session.getAttribute("userSession");
		payService.paymentInsert(vo); //결제정보 DB입력/
		System.out.println("결제정보 :"+vo);
		CoupleInfoVO oc = new CoupleInfoVO(); //커플정보 들고오기
		String result="";
		
		int coupleId =vo.getCoupleId();
		
		if(coupleId !=0) {  //이미 커플인 경우(커플 아이디를 들고온다)
			oc.setCoupleId(coupleId);
		oc =coupleService.read(oc);  //커플의 기본정보 다 들고오기
		System.out.println("커플정보 :"+oc);
		
			if(oc.getCoupleStatus().equals("n")) { //커플의 상태확인
				oc.setSubTerm(vo.getSubTerm());	//요금제 최신화
				oc.setCoupleStatus("y");		//커플의 상태 변경
				
				System.out.println("커플정보 갱신 :"+oc);
				coupleService.coupleInfoUpdate(oc); // subTerm,startDate,coupleStatus(y)로 변경
				
				System.out.println("사용자 상태변경:"+oc);
				oc.setUserId(oc.getUserRequest()); //userId에 req를 넣느다.
				coupleService.userCoupleStatusUpdate(oc); //유저테이블의 coupleStatus 둘 다 y변경
				accountVO.setCoupleStatus(oc.getCoupleStatus());
				result="커플로그기간이 새롭게 갱신되었습니다.\n"
						+ "감사합니다.";
									//커플테이블의 상태가 n일 때 기존커플테이블 갱신
			
			}else if(oc.getCoupleStatus().equals("y")){ //커플 연장하기
				Calendar cal = Calendar.getInstance();
				cal.setTime(oc.getStartDate());
				cal.add(Calendar.MONTH, oc.getSubTerm()); //연장 기간 더하기
				Date endDate= cal.getTime();
				
				oc.setSubTerm(vo.getSubTerm());	//요금제 최신화
				oc.setStartDate(endDate); //startDate를 endDate로 초기화
				coupleService.coupleInfoUpdate(oc); //커플테이블 최신화
				
			
				result="커플로그기간 연장되었습니다.\n"
						+ "감사합니다."; //기존 커플의 연장 성공
			}else {
				result="비정상적인 접근입니다.";
			}
		
		}else {
			oc.setUserRequest(vo.getUserId());
			oc.setSubTerm(vo.getSubTerm());
			coupleService.coupleInfoInsert(oc); //커플테이블에 신규등록
			
			oc =coupleService.read(oc);
			accountVO.setCoupleId(String.valueOf(oc.getCoupleId()));
			accountVO.setCoupleStatus(oc.getCoupleStatus());
			result="커플로그가 시작되었습니다. \n"
					+ "감사합니다.";
		}	//커플테이블에 대한 정보가 없을 때 신규테이블 생성
		
		return result;
	}
	
	
	@PostMapping("/refund") //환불하기
	public String refund(PaymentVO vo, HttpServletRequest request) { //결제정보 merchantUid를 들고 옵니다
		GetTokenAPI getToken =new GetTokenAPI();
		RefundAPI refund = new RefundAPI();
		HttpSession session = request.getSession();
		AccountVO accountVO = (AccountVO) session.getAttribute("userSession");
		
		System.out.println("가장최근결제정보:"+vo);
		String json=getToken.getToken(); //토큰 받아오기 API 메소드 실행
        
		vo.setToken(json); //토큰받아오기
		
         System.out.printf("토큰여깃다! : "+json
		+"아임포트 : "+vo.getMerchantUid());
		
		String refundDb= refund.Refund(vo); //환불 API 실행 후 결과 저장
		// 환불할 때 필요한 정보는 merchantUid랑 tokken이다
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
            vo.setMerchantUid(refundParsing.get("merchant_uid").toString());
            System.out.println("들고온 친구들="+vo);
             
            //vo객체 안에 바로 넣어서 DB로 넘기면 된다.
             //cancel_amount,buyer_name,buyer_email,merchant_uid
        } catch (ParseException e) {
             System.out.println("변환에 실패");
             e.printStackTrace();
        }
        payService.insertRefundInfo(vo); //환불결과 DB에 저장 , 커플테이블 상태 비활성화
        
        
        //커플 상태, 유저 커플상태 변경
        CoupleInfoVO cvo=new CoupleInfoVO();
        NoticeVO nvo =new NoticeVO();
        cvo.setUserId(vo.getUserId());
        cvo= coupleService.userCoupleStatusRead(cvo); 
        String message ="";
	        if(cvo.getCoupleStatus().equals("w")) { //결제를 하고 커플 매칭에 실패하여 환불
	        	
	        	coupleService.deleteCoupleInfo(cvo); //커플 테이블을 삭제해줌
	        	
	        	cvo.setCoupleStatus("n"); //커플 상태를 w->n으로 다시 변경
	        	accountVO.setCoupleStatus("n");
	        	coupleService.userCoupleStatusUpdate(cvo);
	        	
	        	message="환불되었습니다!";
	        }else if(cvo.getCoupleStatus().equals("s")) {
	        	 nvo.setUserSent(String.valueOf(cvo.getUserId()));
	        	noticeService.deleteNotice(nvo); //커플 신청한 정보까지 제거
	        	
	        	coupleService.deleteCoupleInfo(cvo); //커플 테이블을 삭제해줌
	        	cvo.setCoupleStatus("n"); //커플 상태를 w->n으로 다시 변경
	        	accountVO.setCoupleStatus("n");
	        	coupleService.userCoupleStatusUpdate(cvo);
	        }else{  //구독 중 커플의 환불
	        	cvo.setSubTerm(0); //구독기간 초기화
	        	cvo.setCoupleStatus("e"); //커플상태 m(커플이지만 구독기간 끝남)으로 변경
	        	accountVO.setCoupleStatus("e");
	        	coupleService.userCoupleStatusUpdate(cvo);
	        	message="환불되었습니다! \n"
	        			+ "더 나은 우리 오늘 뭐해?가 되도록 노력하겠습니다.";
	        }
	        
		return message;
	}
	
}
