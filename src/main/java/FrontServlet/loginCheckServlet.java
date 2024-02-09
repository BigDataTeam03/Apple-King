package FrontServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Login_Dao;

@WebServlet("/loginCheckServlet")
public class loginCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public loginCheckServlet() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		--------------------------------------------------------------
		* Description 	: Login id password check
		* Author 		: pdg
		* Date 			: 2024.02.09
		* ---------------------------Update---------------------------		
		 	<<2024.02.09>> by pdg
			1. login.js 에서 보내주는 userID, userPW 를 가지고 DB 와 비교함. 
		*
		--------------------------------------------------------------
		*/	
		System.out.println(">> login Servelet 실행");

		// Field 
		
		// Session
		HttpSession session = request.getSession();  
		
		// User Id , Password fetch from login.js
		String id = (String)request.getParameter("userID"); 
		String pw = (String)request.getParameter("userPW"); 
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		// Login DAO => DB customer id  comparison
		Login_Dao dao = new Login_Dao();
		System.out.println(">> Fetched  id from loginview.jsp :" + id);
		
		// If login is successful after going through Login_Dao, insert it into session.
		if(dao.checkLogin(id, pw)) {					 
			session.setAttribute("loginID", id); // loginID <-- id
			session.setAttribute("loginPW", pw); // loginPW <-- pw
		}else {
			System.out.println(">> login 실패 ");
			session.setAttribute("loginID", null);
		}
	
	
	}

}
