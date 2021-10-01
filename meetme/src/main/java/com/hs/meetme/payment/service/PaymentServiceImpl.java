package com.hs.meetme.payment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hs.meetme.payment.domain.PaymentVO;
import com.hs.meetme.payment.mapper.PaymentMapper;


@Service
public class PaymentServiceImpl implements PaymentService{

	@Autowired PaymentMapper payMap;
	@Override
	public int paymentInsert(PaymentVO vo) {
		return payMap.paymentInsert(vo);
	}
	@Override
	public PaymentVO readPayInfo(PaymentVO vo) {
		return payMap.readPayInfo(vo);
	}
	
	
		
}
