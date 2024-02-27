package com.springlec.base.dao;

import java.util.List;

import com.springlec.base.model.MemberDto;
import com.springlec.base.model.PurchaseDto;

public interface PurchaseDao {

	public MemberDto memberInfoDao(String userId) throws Exception ;
	public PurchaseDto productInfoDao(String totalProductPrice, String cust_point, String totalprice);
	}
