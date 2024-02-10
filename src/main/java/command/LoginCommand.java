package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Login_Dao;

public class LoginCommand implements Command {
	/*--------------------------------------
	 * Description: Login Command 
	 * Author : PDG,LS, Diana
	 * Date : 2024.02.02
	 * Update :
	 * 		Update 2024.02.04 by LS, Diana
	 * 			1. Dao 작성
	 * 		Update 2024.02.09 by pdg
	 * 			1. 주석 생성. 
	 * 			
	 * 
	 *-------------------------------------- 
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println(">> loginCommand 실행");

		// Field 
		
		// Session
		HttpSession session = request.getSession();  
		
		// User Id , Password fetch from loginview.jsp
		String id = (String)request.getParameter("id"); 
		String pw = (String)request.getParameter("pw"); 
		
		// Login DAO => DB customer id  comparison
		Login_Dao dao = new Login_Dao();
		System.out.println(">> Fetched  id from loginview.jsp :" + id);
		
		// If login is successful after going through Login_Dao, insert it into session.
		if(dao.checkLogin(id, pw)[0].equals(id)) {				
			System.out.println("loginId 와 loginPw 를 세션에 저장합니다. Id:"+id);
			session.setAttribute("loginId", id); // loginID <-- id
			session.setAttribute("loginPw", pw); // loginPW <-- pw
		}else {
			System.out.println(">> login 실패 ");
			session.setAttribute("loginID", null);
		}
	}
}


