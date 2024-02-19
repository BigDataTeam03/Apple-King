package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Purchase_Dao {
	/*--------------------------------------
	 * Description : PurchaseDao
	 * Author 	   : LS
	 * Date 	   : 2024.02.16
	 * Details		
	 * Update------------------------------- 
	 * 
	 */
	// Field
		DataSource dataSource;
		
	// Constructor
		public Purchase_Dao() {
			try {
				Context context = new InitialContext();
				dataSource = (DataSource) context.lookup("java:comp/env/jdbc/apple_store");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}//Purchase_Dao
		
		// Method 
		public void purchase(String name, String tel, String email) {
			System.out.println(">> Purchase Dao 실행");
			Connection conn = null;
			PreparedStatement ps = null;
			try {
				conn = dataSource.getConnection();
				String purchaseQuery = "select c.name, c.tel, c.email from `order` o JOIN customer c ON o.cust_id = c.cust_id";
				ps = conn.prepareStatement(purchaseQuery);
				ps.setString(1, name);
				ps.setString(2, tel);
				ps.setString(3, email);
				System.out.println(">> purchase Query :"+ps.toString());
				ps.executeQuery();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		}
		

