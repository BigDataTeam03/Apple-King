package com.springlec.base.service;

import java.util.List;

import com.springlec.base.model.CartDto;

public interface CartDaoService {

			public List<CartDto> cartlistdao(String userId) throws Exception;
	
}
