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
	private int userId;
	private long merchantUid;
	private long subTerm;
	private String token;
	
}
