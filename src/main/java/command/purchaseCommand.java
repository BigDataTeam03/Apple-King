package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Cart_Dao;
import dao.Purchase_Dao;

public class purchaseCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		/*----------------------------------------------------------------------
		 * Description: Purchase command
		 * Author : LS
		 * Date : 2024.02.16
		 * Update :
		 * 	
		 * 			
		 *----------------------------------------------------------------------  */
		HttpSession session = request.getSession();
		System.out.println(">> purchasecommand 실행");	
		
		// 상품 코드, 사용자 Id, 상품 수량 getParameter 보내
		String product_code = session.getAttribute("product_code").toString();
		String cust_id = session.getAttribute("userId").toString();
		
		Purchase_Dao purchaseDao = new Purchase_Dao();
	}

}// END
