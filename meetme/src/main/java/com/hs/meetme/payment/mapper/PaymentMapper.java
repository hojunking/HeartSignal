package com.hs.meetme.payment.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.hs.meetme.payment.domain.PaymentVO;
@Mapper
public interface PaymentMapper {
	
	public int paymentInsert(PaymentVO vo);
	public String test(PaymentVO vo);
	

}
