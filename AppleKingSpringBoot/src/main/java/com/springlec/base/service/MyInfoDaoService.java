package com.springlec.base.service;

import java.util.List;

import com.springlec.base.model.MyInfoDto;

public interface MyInfoDaoService {
	public List<MyInfoDto> MyInfoDao(String cust_id) throws Exception;
	public boolean ModifyDao(String name, String cust_pw, String tel, String email, String address, String cust_id) throws Exception;
	public void DeactivateDao(String deact_date,String cust_id) throws Exception;
	String getUserPwById(String cust_id) throws Exception;
	void updatePassword(String cust_id, String confirmPassword) throws Exception;

}
         