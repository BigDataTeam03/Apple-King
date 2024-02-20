package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CartUpdate_Dao {

	
	/*--------------------------------------
	 * Description : CartUpdate , 장바구니에 추가 할 경우, 만약 장바구니에 해당 상품이 이미 있으면 업데이트로 실행한다
	 * Author 	   : kbs
	 * Date 	   : 2024.02.14
	 * Details		
	 * Update------------------------------- 
	 * <2024.02.14> by KBS
	 *  1. 장바구니에 상품을 추가 할 때 이미 존재한다면 수량만 업데이트하는 기능 완료
	 *-------------------------------------- 
	 */
	// Field
	DataSource dataSource;
	
	// Constructor
	public CartUpdate_Dao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/apple_store");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// CartUpdate_Dao
	
	// Method 
	public void updateCartQty(String cust_id, String product_code, int cart_qty) {
	      Connection conn = null;
	        PreparedStatement ps = null;
	        try {
	            conn = dataSource.getConnection();
	            // 로그인 한 고객이 보유한 장바구니의 상품에 수량을 업데이트한다
	            String updateQuery = "update cart set cart_qty = cart_qty + ? where cust_id = ? and product_code = ?";
	            ps = conn.prepareStatement(updateQuery);
	            ps.setInt(1, cart_qty);
	            ps.setString(2, cust_id);
	            ps.setString(3, product_code);
	            ps.executeUpdate();
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (ps != null) ps.close();
	                if (conn != null) conn.close();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    }
	
	
}
