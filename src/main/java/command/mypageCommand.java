package command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Order_Dao;
import dto.OrderDto;

public class mypageCommand implements Command {
	
	/*--------------------------------------
	 * Description: MyPage Command 
	 * Author : DK
	 * Date : 2024.02.15
	 * Update: 2024.02.18
	 * 1. Made access to Order_Dao to retrieve order list 
	 * 2.Invoking the list to retrieve order information for the given customer ID
	 *-------------------------------------- 
	 */

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println(">>Initiating MyPageCommand");
		
		// Accessing the session to retrieve ID
        HttpSession session = request.getSession();  
        String cust_id = (String)session.getAttribute("userId"); 
        System.out.println(cust_id); 
        
        // Accessing the session to retrieve Name
        String name = (String)session.getAttribute("userName");
        System.out.println(name);
        
        // Accessing the session to retrieve Register Date
        String reg_date = (String)session.getAttribute("regDate");
        System.out.println(reg_date);
        
        
       
        // Accessing Order_Dao to retrieve order list
        Order_Dao dao = new Order_Dao(); 
        dao.list(cust_id); 
        
        // Storing the retrieved order list in an ArrayList
        ArrayList<OrderDto> orderList = dao.list(cust_id); 
        
        // Storing the order list in the session for access in JSP pages
        session.setAttribute("list", orderList); 
        
		}
	}


