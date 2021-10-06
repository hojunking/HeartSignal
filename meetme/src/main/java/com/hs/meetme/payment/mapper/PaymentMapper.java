package com.hs.meetme.payment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.hs.meetme.payment.domain.PaymentVO;

@Mapper
public interface PaymentMapper {
	
	public int paymentInsert(PaymentVO vo);		//결제정보 입력
	public PaymentVO latestpaid(PaymentVO vo); //가장 최근 결제정보 가져오기
	public int insertRefundInfo(PaymentVO vo); //환불정보 입력

	public List<PaymentVO> readPayment(PaymentVO vo); //결제 정보 들고오기
	public List<PaymentVO> readRefund(PaymentVO vo); //환불정보 들고오기
}