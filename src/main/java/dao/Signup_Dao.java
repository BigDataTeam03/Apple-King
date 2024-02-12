package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Signup_Dao {
	/*--------------------------------------
	 * Description : Sign Up DAO << customer >>
	 * Author 	   : pdg
	 * Date 	   : 2024.02.11
	 * Details		
	 * Update------------------------------- 
	 * <2024.02.11> by PDG
	 *-------------------------------------- 
	 */
	
	// Field
	DataSource dataSource;

	// Constructor
	public Signup_Dao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/apple_store");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Adding UserInfo into the database.
	public void insertUser(String cust_id, String pw, String name, String tel, String email, String domain,
			String address) {
		System.out.println(">> SinupDao.insertUser 실행");
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = dataSource.getConnection();
			String query = "INSERT into customer (cust_id, cust_pw, name, tel, email, address, reg_date) values (?,?,?,?,?,?,NOW()) ";
			ps = conn.prepareStatement(query);
			ps.setString(1, cust_id);
			ps.setString(2, pw);
			ps.setString(3, name);
			ps.setString(4, tel);
			ps.setString(5, email + domain);
			ps.setString(6, address);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}// END
