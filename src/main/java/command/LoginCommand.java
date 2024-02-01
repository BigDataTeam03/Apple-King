package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Login_Dao;

public class LoginCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String id = (String)request.getParameter("id");  //fetch id that is input by the user. 
		String pw = (String)request.getParameter("pw");	 //fetch pw that is input by the user. 
		HttpSession session = request.getSession();
		
		Login_Dao dao = new Login_Dao();
		
		if(dao.checkLogin(id, pw)) {					// If login is successful after going through Login_Dao, insert it into session. 
			session.setAttribute("loginID", id);
		}
			request.setAttribute("id", id);
	}


	}


