package command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Myinfo_Dao;
import dto.MemberDto;

public class myInfoCommand implements Command {
	/*--------------------------------------
	 * Description: MyInfo Command 
	 * Author : Diana
	 * Date : 2024.02.10
	 * Update: 		
	 *-------------------------------------- 
	 */


	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

			HttpSession session = request.getSession();
			String cust_id = (String)session.getAttribute("userId");
			System.out.println(cust_id);
		
			
			
			Myinfo_Dao dao = new Myinfo_Dao();
			ArrayList<MemberDto> userInfo = dao.list(cust_id);
		   
			session.setAttribute("userInfo", userInfo);
		
	}

}
