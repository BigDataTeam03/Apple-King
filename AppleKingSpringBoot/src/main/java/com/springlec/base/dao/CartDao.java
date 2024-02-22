package com.springlec.base.dao;

import java.util.List;

import com.springlec.base.model.CartDto;

public interface CartDao {
	/*--------------------------------------
	 * Description: cartList  Dao
	 * Author :  KBS
	 * Date : 2024.02.22
	 * Update : 2024.02.22 KBS 
	 * 		1. 리스트 출력과 수량 업데이트 다오 추가 
	 *    
	 *-------------------------------------- 
	 */
	
	public List<CartDto> cartlistdao(String userId) throws Exception;
	public void updateqty(String cart_code, String cart_qty) throws Exception;
	
}
