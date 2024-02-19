package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.MemberDto;
import dto.OrderDto;

public class Order_Dao {
	/*--------------------------------------
	 * Description : Order Dao <<customer >>
	 * Author 	   : DK
	 * Date 	   : 2024.02.18
	 * Update	   :
	 *-------------------------------------- 
	 */
	
DataSource dataSource;
	
	public Order_Dao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/apple_store");
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
	}
	
	//구매내역 조회
	// Method for retrieving order list based on customer ID
	public ArrayList<OrderDto> list(String cust_id) {
        ArrayList<OrderDto> orderList = new ArrayList<OrderDto>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            String query = "SELECT product_code, order_code, payment_method, used_point FROM orders WHERE cust_id = ?";
            
            preparedStatement = connection.prepareStatement(query);
            // Setting customer ID as a parameter in the query
            preparedStatement.setString(1, cust_id); 	
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
            	OrderDto dto = new OrderDto();
            	dto.setProduct_code(resultSet.getString("product_code"));
                dto.setOrder_code(resultSet.getString("order_code"));
                dto.setPayment_method(resultSet.getString("payment_method"));
                dto.setUsed_point(resultSet.getInt("used_point"));
                // Adding the OrderDto object to the list
                orderList.add(dto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return orderList;
    }
	
	
	
}//END 
