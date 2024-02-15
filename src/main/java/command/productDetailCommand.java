package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Detail_Dao;
import dto.productDto;

public class productDetailCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		/*
		--------------------------------------------------------------
		* Description 	: 상세페이지 리스트 출력
		* Author 		: LS
		* Date 			: 2024.02.15
		* ---------------------------Update---------------------------		
		 	<<2024.02.15>> by LS
			1. 주석 설명 추가 
		*
		--------------------------------------------------------------
		*/
		HttpSession session = request.getSession();
		System.out.println(">> productDetail command 실행");
		
		// product_name을 세션에서 가져옵니다.
 		String productName = (String) session.getAttribute("product_name");
		
		// 상품이름으로 db조회
		Detail_Dao dao = new Detail_Dao();
		System.out.println(">> 상세페이지 :" + productName);
		productDto dto = dao.Detail(productName);
		
		// request에 상품상세정보 저장
		request.setAttribute("Detail", dto);
		
		// session에 상품상세정보 저장
		session.setAttribute("detailSession", dto);
	}

}
