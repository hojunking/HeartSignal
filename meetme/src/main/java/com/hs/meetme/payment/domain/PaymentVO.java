package com.hs.meetme.payment.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentVO {
	
	private Date payDate;
	private int payCost;
	private long subTerm;
	private String token;
	//공동
	private long merchantUid;
	private int userId;
	//환불VO
	private String email;
	private String userName;
	private long cancelAmount;
	//coupleId 확인
	private int coupleId;
	
}
