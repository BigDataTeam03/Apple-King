package com.springlec.base.dao;

import java.util.List;

import com.springlec.base.model.MyInfoDto;

public interface MyInfoDao {

	public List<MyInfoDto> MyInfoDao(String cust_id) throws Exception;
	public boolean ModifyDao(String name, String cust_pw, String tel, String email, String address, String cust_id) throws Exception;
	public void DeactivateDao( String deact_date, String cust_id) throws Exception;
}
 