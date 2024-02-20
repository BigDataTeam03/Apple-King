package FrontServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.javalec.util.ShareVar;

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
		
		// Field 
		System.out.println(">> (login.js ->) 1.login check Servelet 실행");
		
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession(); // Session  
		
		// User Id , Password fetch from login.js
		String id = (String)request.getParameter("userID"); 
		String pw = (String)request.getParameter("userPW"); 

		// id check
		System.out.println("\t 1-1. Fetched id from loginview.jsp : " + id);
		String idPwCheckQuery = "SELECT cust_id, cust_pw FROM customer "
					 		  + " WHERE cust_id = ? AND cust_pw =?";
		boolean checkResult =false;

		
		// jsp printing 
		PrintWriter out = response.getWriter();
		try {
			// SQL 연결
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(ShareVar.url_mysql, ShareVar.id_mysql,ShareVar.pw_mysql);
			PreparedStatement ps = conn_mysql.prepareStatement(idPwCheckQuery);
		    ps.setString(1, id);
		    ps.setString(2, pw);
		    // Query check
		    System.out.println("\t 1-2. idPwCheckQuery 실행 :"+ ps.toString());
		    ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				System.out.println("\t 1-3. DB 에 존재하는 사용자입니다");
				checkResult = true;
			}
			if(checkResult) { // check ->dB custmor 에 있을때
				System.out.println("\t 1.4 Json전송 ");
				
				// session 에 아이디 패스워드 저장
				session.setAttribute("id",id);
				session.setAttribute("pw",pw);
				//System.out.print("\t전송된 Json : ");
				//System.out.println(new Gson().toJson(checkResult));
				// Json 타입으로 변환하여 jsp 에 뿌림
				out.print(new Gson().toJson(checkResult));
				out.flush();
			}else {
				System.out.println("DB 에 문제가생겼습니다.");
			}
		    ps.close(); // PreparedStatement 닫기
		    conn_mysql.close(); // 연결 닫기
		}catch(Exception e) {
			e.printStackTrace();
		}

	
	}
}
