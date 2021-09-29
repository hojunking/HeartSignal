package com.hs.meetme.payment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hs.meetme.coupleinfo.mapper.CoupleInfoMapper;
import com.hs.meetme.payment.domain.PaymentVO;
import com.hs.meetme.payment.mapper.PaymentMapper;


@Service
public class PaymentServiceImpl implements PaymentService{

	@Autowired PaymentMapper payMap;
	@Autowired CoupleInfoMapper coupleMap;
	@Override
	public int paymentInsert(PaymentVO vo) {
		return payMap.paymentInsert(vo);
	}
	@Override
	public String test(PaymentVO vo) {
		
		return "연결이요";
	}
	
	
}
