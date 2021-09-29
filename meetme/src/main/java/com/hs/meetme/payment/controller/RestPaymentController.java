package com.hs.meetme.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hs.meetme.coupleinfo.domain.CoupleInfoVO;
import com.hs.meetme.coupleinfo.service.CoupleInfoService;
import com.hs.meetme.payment.domain.PaymentVO;
import com.hs.meetme.payment.service.PaymentService;


@RestController
public class RestPaymentController {
	@Autowired PaymentService payService;
	@Autowired CoupleInfoService coupleService;
	
	@GetMapping("/payment")
	public String paymentInsert(PaymentVO vo,CoupleInfoVO cvo) {
		
		String re="";
		payService.paymentInsert(vo);
		System.out.println("결제정보 :"+vo);
		CoupleInfoVO oc = new CoupleInfoVO();
		System.out.println("입력정보확인 :"+cvo);
		
		if(cvo.getCoupleId()!=0) {
		oc =coupleService.read(cvo);
		System.out.println("커플정보 :"+oc);
		cvo.setUserRequest(vo.getUserId());
		cvo.setSubTerm(vo.getSubTerm());
		
		if(oc.getCoupleStatus().equals("n")) {
			
			coupleService.coupleInfoUpdate(cvo);
			re="기존커플갱신되었습니다.";
			//커플테이블의 상태가 n일 때 기존커플테이블 갱신
		}else {
			
			re="비정상적인 접근입니다.";
		}
			//커플테이블의 상태가 y이거나 null인 경우의 접근
					
		}else {
			
			oc.setUserRequest(vo.getUserId());
			oc.setSubTerm(vo.getSubTerm());
			coupleService.coupleInfoInsert(oc);
			re="신규커플등록되었습니다.";
		}	//커플테이블에 대한 정보가 없을 때 신규테이블 생성
		return re;
	}
	
}
