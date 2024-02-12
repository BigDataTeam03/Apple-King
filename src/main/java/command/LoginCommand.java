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
	 * 	<<2024.02.11>>
	 * 		1. 일단 jsp <-> db 연동으로 체크 기능 실현되는지 알아보고 mvc 로 바꿀때 다시 사용하기 위해 주요기능 주석처리함. 
	 * 
	 * <<2024.02.11 
	 * 		1. mvc 를 살려야하는데 귀찮다.. 그냥 이대로 쓰고 나중에 고치기로함. 
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
		String id = (String)request.getParameter("userId"); 
		String pw = (String)request.getParameter("userPw"); 
		
		// Login DAO => DB customer id  comparison
		Login_Dao dao = new Login_Dao();
		System.out.println(">> Fetched  id from loginview.jsp :" + id);
		//dao.checkLogin(id, pw);
		
		// If login is successful after going through Login_Dao, insert it into session.
//		if(dao.checkLogin(id, pw)[0].equals(id)) {				
//			System.out.println("loginId 와 loginPw 를 세션에 저장합니다. Id:"+id);
//			session.setAttribute("loginId", id); // loginID <-- id
//			session.setAttribute("loginPw", pw); // loginPW <-- pw
//		}else {
//			System.out.println(">> login 실패 ");
//			session.setAttribute("loginID", null);
//		}
	}
}


