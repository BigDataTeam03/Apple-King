package com.springlec.base.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.springlec.base.model.MyInfoDto;

public interface MyInfoDao {

	public List<MyInfoDto> MyInfoDao(String cust_id) throws Exception;
	public boolean ModifyDao(String name, String cust_pw, String tel, String email, String address, String cust_id) throws Exception;
	public void DeactivateDao( String deact_date, String cust_id) throws Exception;
	String getUserPwById(String cust_id) throws Exception;
	public void updatePassword(@Param("cust_id") String cust_id, @Param("confirmPassword") String confirmPassword) throws Exception;
}

 