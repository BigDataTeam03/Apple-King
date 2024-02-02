package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Login_Dao;

public class LoginCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println(" Login command 실행 ");
		String id = (String)request.getParameter("id");  //fetch id that is input by the user. 
		String pw = (String)request.getParameter("pw");	 //fetch pw that is input by the user. 
		HttpSession session = request.getSession();
		
		Login_Dao dao = new Login_Dao();
		
		System.out.println("login view 에서 가져온 id :" + id);
		
		if(dao.checkLogin(id, pw)) {					// If login is successful after going through Login_Dao, insert it into session. 
			session.setAttribute("loginID", id);
			session.setAttribute("loginPW", pw);
		}else {
			System.out.println("checklogin에서 떨어진거잖아");
			session.setAttribute("loginID", null);
		}
//			request.setAttribute("id", id);
//			request.setAttribute("pw", pw);
	}


	}


