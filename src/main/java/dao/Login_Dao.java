package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Login_Dao {
	// Field
	DataSource dataSource;
	// Constructor
	public Login_Dao() {
		try{
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/apple_store"); 	
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	// Method
	//checkID that is stored inside the database. 
	public boolean checkLogin(String id, String pw) {
		System.out.println(">> Login_dao 실행");
		Connection conn = null;
		PreparedStatement ps = null;	
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();	// this makes connection to the db.  
			String query = "SELECT cust_id, cust_pw FROM customer ";
			String where = " WHERE cust_id = '" + id +"' AND cust_pw = '" + pw +"'";
			ps = conn.prepareStatement(query+where);
			System.out.println("CheckLogin 쿼리문 : "+ps.toString());
			rs = ps.executeQuery();
			if(rs.next()) {
				System.out.println("db에 존재하는 사용자입니다");
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {	
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}//checkID()
	
	

}
