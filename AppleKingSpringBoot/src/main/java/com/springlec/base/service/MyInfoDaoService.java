package com.springlec.base.service;

import java.util.List;

import com.springlec.base.model.MyInfoDto;

public interface MyInfoDaoService {
	public List<MyInfoDto> MyInfoDao(String cust_id) throws Exception;

}
         