package command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Cart_Dao;
import dao.purchaseDao;
import dto.cartDto;

public class purchaseCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
				

		/*
		--------------------------------------------------------------
		* Description 	: 장바구니에서 구매페이지로 이동하는 커맨드
		* Author 		: KBS, L S
		* Date 			: 2024.02.13
		* ---------------------------Update---------------------------		
		 	<<2024.02.13>> by KBS
			1. 
			
		--------------------------------------------------------------
	
		*
		*/
		
		HttpSession session = request.getSession();
	    String cust_id = (String)session.getAttribute("user_Id");
	    
	    
	    
							
		//String suct_id = request.getParameter("cust_id");
		
			purchaseDao dao = new purchaseDao();
			ArrayList<cartDto> purchaseInfo = new ArrayList<cartDto>();
			dao.purchaseInfo(cust_id);
			
			request.setAttribute("purchaseInfo", purchaseInfo);
//			
//			 RequestDispatcher dispatcher = request.getRequestDispatcher("purchasePage.jsp");
//		        try {
//					dispatcher.forward(request, response);
//				} catch (ServletException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
		
		
	}

}
