package command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Signup_Dao;

public class SignupCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println(" signUp Command 실행합니다. ");
		
		String id = (String)request.getParameter("id");
//		System.out.println(" customer 의 id : "+ id);
		String pw = (String)request.getParameter("pw");
		String name = (String)request.getParameter("name");
		String tel = (String)request.getParameter("tel");
		String email = (String)request.getParameter("email");
		String domain = (String)request.getParameter("domain");
		String address = (String)request.getParameter("useraddress");
		
	
		Signup_Dao dao = new Signup_Dao();
		dao.insertUser(id, pw, name, tel, email, domain, address);
	}

}
