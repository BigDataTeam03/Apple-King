package com.springlec.base.dao;

import java.util.List;

import com.springlec.base.model.MyInfoDto;

public interface MyInfoDao {

	public List<MyInfoDto> MyInfoDao(String cust_id) throws Exception;
	
}
