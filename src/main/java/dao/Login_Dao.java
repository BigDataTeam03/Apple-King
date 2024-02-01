package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Login_Dao {
	
	
	//field
	DataSource dataSource;
	
	
	
	//constructor
	public Login_Dao() {
		// TODO Auto-generated constructor stub
		try{
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/appleStore"); 	//[java:comp/env]는 context.xml의 위치. [jdbc/shoes]	는 이름. 		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	// Method
	
	
	//checkID that is stored inside the database. 
	public boolean checkLogin(String id, String pw) {
		Connection conn = null;
		PreparedStatement ps = null;	
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();	// this makes connection to the db.  
			
			String query = "SELECT cust_id, cust_pw FROM customer ";
			String where = " WHERE cust_id = '" + id +"' AND cust_pw = '" + pw +"'";
			
			ps = conn.prepareStatement(query+where);
			rs = ps.executeQuery();

			if(rs.next()) {
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
