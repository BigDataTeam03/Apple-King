package command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
		 * <<2024.02.15>> by pdg
		 * 			1. 페이지 새로고침을 하면 같은 명령이 계속반복되어서 카트 인서트가 계속 반복되는문제
		 * 			
		 *----------------------------------------------------------------------  */
		HttpSession session = request.getSession();
		System.out.println(">> cartCommand 실행");
		
		// 상품 코드, 사용자 Id, 상품 수량 getParameter 보내
		String product_code  = session.getAttribute("product_code").toString();
		String cust_id 		 = session.getAttribute("userId").toString();
		String cart_qty_str = request.getParameter("cart_qty");
		int    cart_qty	= 0;
		if(cart_qty_str == null) {
			cart_qty_str = "0";
		}else {
			    cart_qty		 = Integer.parseInt(request.getParameter("cart_qty"));
		}
		
		
		String product_name  = (String)session.getAttribute("product_name"); 
		
		// "productChkMap" 속성이 세션에 없는 경우를 처리하는 부분
		Object chkObject = session.getAttribute("productChkMap");
		
		if (chkObject == null || !(chkObject instanceof Map)) {
		    // "productChkMap" 속성이 세션에 없거나 올바른 형식이 아닌 경우
		    Map<String, Boolean> chkmap = new HashMap<>(); // 새로운 맵을 생성하여 할당
		    
		    chkmap.put(product_name, true);
		    session.setAttribute("productChkMap", chkmap); // 세션에 맵을 저장
		    // product 선택여부
			Boolean chkProduct = chkmap.get(product_name);
			if (chkProduct == null) {
			    chkProduct = false;
			    System.out.println(">> chkProduct : null ->false");
			}else {
				if(chkProduct) {// 상품디테일에서 선택되어 넘어왔을때 chkProduct 는 true?-> 장바구니에서 쿼리실행
					CartQuery(cust_id, product_code, cart_qty);
					chkmap.put(product_name, true);
					session.setAttribute("productChkMap", chkmap); // 세션에 맵을 저장
					System.out.println(">> Insert query 실행 =>세션에 저장 된  값 :" +chkmap.get(product_name).toString());
				}else { // 이미 세션에 값이 이미 true 일경우에는 장바구니 추가, 수정 쿼리를 진행하지 않음. 
					System.out.println("이미 한번 선택된 상품입니다. ");
				}
			}
		} 
			
			
		
    }// execute end
	
	// 특정고객이 선택한 상품의 코드와 장바구니 넣을 상품의 수량을 받아서 쿼리를 작성하게 하는 메서드
	public void CartQuery(String cust_id, String product_code, int cart_qty) {
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
        }//else end
	}// CartQuery
}// END
