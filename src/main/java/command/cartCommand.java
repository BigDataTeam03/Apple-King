package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Detail_Dao;
import dto.productDto;

public class cartCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		System.out.println(">>cartCommand initiated");
		// 상품 코드, 사용자 Id, 상품 수량 getParameter 보내
		
		String productcode = (String)request.getParameter("product_code");
		String custid = (String)request.getParameter("cust_id");
		String cartqty = (String)request.getParameter("cart_qty");
		
		System.out.println("product 코드 세션 값: " + session.getAttribute("product_code"));

		System.out.println(productcode);
	}

}
