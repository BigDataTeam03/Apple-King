package com.springlec.base.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springlec.base.dao.CartDao;
import com.springlec.base.model.CartDto;
import com.springlec.base.model.OrderDto;
@Service
public class CartDaoServiceImpl implements CartDaoService {

	/*--------------------------------------
	 * Description: cartList Service 에서 다오로 연결해주는 클라스
	 * Author :  KBS
	 * Date : 2024.02.22
	 * Update : 2024.02.22 KBS 
	 * 		1. 리스트 출력과 수량 업데이트 기능두개 추가
	 * Update : 2024.02.23 KBS 
	 * 		1. 장바구니 삭제기능 추가
	 *-------------------------------------- 
	 */
	
	@Autowired
	CartDao dao;
	
	@Override
	// 장바구니 리스트 출력
	public List<CartDto> cartlistdao(String userId) throws Exception {
		// TODO Auto-generated method stub
		return dao.cartlistdao(userId);
	}

	@Override
	// 장바구니 상품 수량 수정
	public void updateqty(String cart_qty, String cart_code) throws Exception {
		// TODO Auto-generated method stub
	 dao.updateqty(cart_qty, cart_code);
		
		
	}

	@Override
	// 장바구니 상품 삭제
	public void deleteCart(String selected) throws Exception {
		// TODO Auto-generated method stub
		dao.deleteCart(selected);
		
	}
	// 장바구니 결제에 필요한 정보를 수집하는 cartdao
	@Override
	public OrderDto orderInfoFromCart(Integer cart_code) throws Exception {
		
		return dao.orderInfoFromCart(cart_code);
	}

}
