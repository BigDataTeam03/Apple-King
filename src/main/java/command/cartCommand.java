package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CartUpdate_Dao;
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
		 * 
		 *<< 2024.02.14>> by KBS
		 *			1. 이미 장바구니에 존재하는 상품을 장바구니에 다시 추가할때 수량을 업데이트하는 기능추가
		 *          2. command 에서 두개의 Dao 를 조율
		 * 			
		 *----------------------------------------------------------------------  */
		HttpSession session = request.getSession();
		System.out.println(">> cartCommand 실행");
		
		// 상품 코드, 사용자 Id, 상품 수량 getParameter 보내
		String product_code = session.getAttribute("product_code").toString();
		String cust_id = session.getAttribute("userId").toString();
		int cart_qty = Integer.parseInt(request.getParameter("cart_qty"));

		// 장바구니 채크와 인서트기능의 다오
		Cart_Dao cartInsertDao = new Cart_Dao();
		// 장바구니에 담긴 상품의 수량을 업데이트하는 다오
		CartUpdate_Dao updao = new CartUpdate_Dao();
		
		// 재고를 체크하는 메소드에 보내서 채크를 실행한다
		boolean  check = cartInsertDao.checkItem(cust_id, product_code);
		
		if (check == true) {
            // 이미 해당 상품이 장바구니에 있는 경우, 수량만 업데이트
            updao.updateCartQty(cust_id, product_code, cart_qty);
        } else {
            // 장바구니에 해당 상품이 없는 경우, 새로운 상품으로 추가
            cartInsertDao.insertCart(cust_id, product_code, cart_qty);
        }
    }
		

}// END
