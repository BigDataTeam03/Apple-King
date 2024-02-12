package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Cart_Dao {
	/*--------------------------------------
	 * Description : Sign Up DAO << product, customer, order >>
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
	public Cart_Dao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/apple_store");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// Cart_Dao
	
	// Method 
	public void insertCart(String cust_id, String product_code, int cart_qty) {
		System.out.println(">> InsertCart Dao 실행");
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = dataSource.getConnection();
			String insertCartQuery = "insert into cart (cust_id,product_code,cart_qty) "
									+"values (?,?,?)";
			ps = conn.prepareStatement(insertCartQuery);
			ps.setString(1, cust_id);
			ps.setString(2, product_code);
			ps.setInt(3, cart_qty);
			System.out.println(">> Cart Insert Query :"+ps.toString());
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
} // END
