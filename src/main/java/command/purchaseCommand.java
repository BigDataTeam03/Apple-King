package command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Cart_Dao;
import dao.Purchase_Dao;
import dao.purchaseCustInfo;
import dao.purchaseProductInfo;
import dto.MemberDto;
import dto.productDto;

public class purchaseCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		/*----------------------------------------------------------------------
		 * Description: Purchase command
		 * 		Detail : 
		 * Author : LS ,pdg, dk
		 * 
		 * Date : 2024.02.16
		 * Update : 2024.02.19
		 * 	1. 장바구니에 체크된 상품을불러와서 화면에 뿌리고 order table 에 insert
		 * 	2. 구매가 완료되었으므로 product table 에서 구매된 상품 개수가 차감됨. 
		 * 	
		 * 			
		 *----------------------------------------------------------------------  */
		HttpSession session = request.getSession();
		System.out.println(">> purchaseCommand 실행");	
		
		// 상품 코드, 사용자 Id, 상품 수량 getParameter 로 받음. 
		String product_code 	= (String)session.getAttribute ("product_code"); //<- saveProductInfoServlet
		String cust_id 			= (String) session.getAttribute("userId");
		String product_qty_str  = (String)session.getAttribute ("product_qty"); // 선택한 상품의 총수량. 
		int product_qty = 0;
		if (product_qty_str != null) {
			product_qty= Integer.parseInt(product_qty_str);
		}
		System.out.println(">> product qty : "+ product_qty);
		System.out.println(">> product_code value :"+product_code);
		
		
		purchaseCustInfo purchaseCustInfo = new purchaseCustInfo();
		ArrayList<MemberDto> custinfo = purchaseCustInfo.lists(cust_id);
		
		session.setAttribute("lists", custinfo);
		//String a = (String) session.getAttribute("lists");
		//System.out.println("고객정보"+ a);
		purchaseProductInfo purchaseProductInfo = new purchaseProductInfo();
		ArrayList<productDto> pdto = purchaseProductInfo.list(product_code);
		
		request.setAttribute("productlnfo", pdto);
		
	}

}// END
