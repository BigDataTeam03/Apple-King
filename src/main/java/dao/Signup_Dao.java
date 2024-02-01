package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Signup_Dao {
	
	// Field
	DataSource dataSource;
	
	// Constructor
	public Signup_Dao() {
		// TODO Auto-generated constructor stub
		try{
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/appleStore"); 	//[java:comp/env]는 context.xml의 위치. [jdbc/shoes]	는 이름. 		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}	
		
	// Method
	//checkID
		public boolean checkID(String id) {
					Connection conn = null;
					PreparedStatement ps = null;	
					ResultSet rs = null;
					
					try {
						conn = dataSource.getConnection();	// DB연결. 
						
						String query = "SELECT cust_id FROM customer ";
						String where = " WHERE cust_id = '" + id +"'";
						
						ps = conn.prepareStatement(query+where);
						rs = ps.executeQuery();

						if(rs.next()) {
							return false;
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
					return true;
		}//checkID()
	
		// 회원가입시 아이디 중복체크 후 바로 입력.
			public boolean insertID(String id) {
				Connection conn = null;
				PreparedStatement ps = null;
				
				try {
					conn = dataSource.getConnection();
					
					String query = "INSERT INTO customer (cust_id) VALUES (?)";
					
					ps = conn.prepareStatement(query);
					
					ps.setString(1, id);
					
					ps.executeUpdate();
					
				}catch(Exception e){
					e.printStackTrace();
					return false;
				}finally {	
					try {
						if(ps != null) ps.close();
						if(conn != null) conn.close();
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
				return true;
		}//insertID()
			
			
			// 입력된 아이디에 나머지 정보 추가.
			public void insertUser(String cust_id, String pw, String name, String tel, String email, String address) {
				System.out.println("insert customer info ");
				
				Connection conn = null;
				PreparedStatement ps = null;	

				try {
					conn = dataSource.getConnection();	
					
					String query = "INSERT into customer (cust_id, cust_pw, name, tel, email, address) values (?,?,?,?,?,?) ";
					
					
					ps = conn.prepareStatement(query);
					
					ps.setString(1, cust_id);
					ps.setString(2, pw);
					ps.setString(3, name);
					ps.setString(4, tel);
					ps.setString(5, email);
					ps.setString(6, address);
					
					ps.executeUpdate();
					
					
					System.out.println("회원가입 실행");
					
				}catch(Exception e){
					e.printStackTrace();
				}finally {	
					try {
						if(ps != null) ps.close();
						if(conn != null) conn.close();
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
			}//updateID()

}

