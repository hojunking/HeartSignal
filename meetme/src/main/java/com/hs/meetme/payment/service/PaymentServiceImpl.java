package com.hs.meetme.payment.service;

import java.util.List;

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
	public PaymentVO latestpaid(PaymentVO vo) {
		return payMap.latestpaid(vo);
	}
	@Override
	public int insertRefundInfo(PaymentVO vo) {
		// TODO Auto-generated method stub
		return payMap.insertRefundInfo(vo);
	}
	@Override
	public List<PaymentVO> readRefund(PaymentVO vo) {
		// TODO Auto-generated method stub
		return payMap.readRefund(vo);
	}
	@Override
	public List<PaymentVO> readPayment(PaymentVO vo) {
		// TODO Auto-generated method stub
		return payMap.readPayment(vo);
	}
	
	
		
}
