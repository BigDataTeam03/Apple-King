package com.springlec.base.service;

import java.util.List;

import com.springlec.base.model.CartDto;

public interface CartDaoService {

	/*--------------------------------------
	 * Description: cartList  Service
	 * Author :  KBS
	 * Date : 2024.02.22
	 * Update : 2024.02.22 KBS 
	 * 		1. 리스트 출력과 수량 업데이트 서비스 추가 
	 *    
	 *-------------------------------------- 
	 */
	
			public List<CartDto> cartlistdao(String userId) throws Exception;
			public void updateqty(String cart_code, String cart_qty) throws Exception;
}
