package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Cart_Dao {
	/*--------------------------------------
	 * Description : insertCart
	 * Author 	   : pdg ,kbs
	 * Date 	   : 2024.02.11
	 * Details		
	 * Update------------------------------- 
	 * <2024.02.11> by PDG
	 *-------------------------------------- 
	 * * Update------------------------------- 
	 * <2024.02.14> by KBS
	 *  1. 처음에는 그냥 insert 만 기능이 있었지만 데이터베이스에 존재하면 update, 
	 *     그렇지 않으면 insert 로 기능을 분할시키고 그 변별을하는 쿼리문을 추가함.
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
	// 해당 고객이(cust_id) 장바구니에 보유한 상품(product_code) 를 받아 조회함. 
	public boolean checkItem(String cust_id, String product_code) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean exists = false;
        try {
            conn = dataSource.getConnection();
            // 데이터베이스에 존재하면 1, 그렇지 않으면 0 으로 나오는 쿼리문
            String checkQuery = "select count(*) as count from cart where cust_id = ? and product_code = ?";
           //쿼리 실행
            ps = conn.prepareStatement(checkQuery);
            ps.setString(1, cust_id);
            ps.setString(2, product_code);
            rs = ps.executeQuery();
            //결과가 있으면 1 아니면 0 이다
            if (rs.next()) {
                int count = rs.getInt("count");
                if (count > 0) {
                    exists = true;
                } else {
                    exists = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
           //  쓰거나 안쓴자원 닫기
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // true 혹은 false 값을 반환한다
        return exists;
    }
	
} // END
