package com.springlec.base.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springlec.base.dao.CartDao;
import com.springlec.base.model.CartDto;
@Service
public class CartDaoServiceImpl implements CartDaoService {

	/*--------------------------------------
	 * Description: cartList Service 에서 다오로 연결해주는 클라스
	 * Author :  KBS
	 * Date : 2024.02.22
	 * Update : 2024.02.22 KBS 
	 * 		1. 리스트 출력과 수량 업데이트 기능두개 추가
	 *    
	 *-------------------------------------- 
	 */
	
	@Autowired
	CartDao dao;
	
	@Override
	public List<CartDto> cartlistdao(String userId) throws Exception {
		// TODO Auto-generated method stub
		return dao.cartlistdao(userId);
	}

	@Override
	public void updateqty(String cart_code, String cart_qty) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
