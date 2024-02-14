package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Cart_Dao;
import dao.Detail_Dao;
import dto.productDto;

public class cartCommand implements Command {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		/*----------------------------------------------------------------------
		 * Description:  Cart Insert command
		 * Author : PDG
		 * Date : 2024.02.09
		 * Update :
		 * 	<<2024.02.09>> by pdg
		 * 			1. 주석 생성.
		 * 			2.카트 디비에 장바구니 추가 버튼 클릭 시에 저장된 세션값으로 dao 하는 기능 
		 *----------------------------------------------------------------------  */
		HttpSession session = request.getSession();
		System.out.println(">> cartCommand 실행");
		
		// 상품 코드, 사용자 Id, 상품 수량 getParameter 보내
		String product_code = session.getAttribute("product_code").toString();
		String cust_id = session.getAttribute("userId").toString();
		int cart_qty = Integer.parseInt(request.getParameter("cart_qty"));
		System.out.println("\t product 코드 세션 값: " + session.getAttribute("product_code"));
		System.out.println("\t user id : "		+cust_id);
		System.out.println("\t product code : "+product_code);
		System.out.println("\t cart qty :" 	+ request.getParameter("cart_qty"));
		
		Cart_Dao cartInsertDao = new Cart_Dao();
		
		cartInsertDao.insertCart(cust_id, product_code, cart_qty);
		
		
	}
}// END
