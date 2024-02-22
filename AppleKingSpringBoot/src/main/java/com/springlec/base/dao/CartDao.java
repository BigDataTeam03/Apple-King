package com.springlec.base.dao;

import java.util.List;

import com.springlec.base.model.CartDto;

public interface CartDao {

	
	public List<CartDto> cartlistdao(String userId) throws Exception;
	
	
}
