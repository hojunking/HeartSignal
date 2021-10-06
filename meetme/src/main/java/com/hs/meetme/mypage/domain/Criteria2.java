package com.hs.meetme.mypage.domain;

import lombok.Data;

@Data
public class Criteria2 {
  private int pageNum;
  private int amount;
  
  private String type;
  private String keyword;
  private String category;
  
  public Criteria2( ){
	  this(1, 5);
  }
  
  public Criteria2(int pageNum, int amount) {
	  this.pageNum = pageNum;
	  this.amount = amount;
  }
  
  public String[] getTypeArr() {
	  return type == null ? new String[] {} : type.split("");
  }
  
}