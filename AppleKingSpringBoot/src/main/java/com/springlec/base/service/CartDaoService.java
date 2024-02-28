package com.springlec.base.service;

import java.util.List;
import java.util.Map;

import com.springlec.base.model.CartDto;
import com.springlec.base.model.OrderDto;

public interface CartDaoService {

	/*--------------------------------------
	 * Description: cartList  Service
	 * Author :  KBS
	 * Date : 2024.02.22
	 * Update : 2024.02.22 KBS 
	 * 		1. 리스트 출력과 수량 업데이트 서비스 추가 
	 *    Update : 2024.02.23 KBS 
	 * 		1. 장바구니 삭제 서비스 추가 
	 *-------------------------------------- 
	 */
			// 장바구니 리스트 출력
			public List<CartDto> cartlistdao(String userId) throws Exception;
			// 장바구니 상품 수량 수정
			public void updateqty(String cart_qty, String cart_code) throws Exception;
			// 장바구니 상품 삭제
			public void deleteCart(String selected) throws Exception;
			
			//장바구니 결제를 위한 결제정보 불러오기
			public OrderDto orderInfoFromCart(Integer cart_code) throws Exception; 
}
