package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.OrderDto;
import dto.productDto;

public class purchaseProductInfo {
	/*--------------------------------------
	 * Description : Order Dao <<customer >>
	 * Author 	   : DK
	 * Date 	   : 2024.02.18
	 * Update	   :
	 *-------------------------------------- 
	 */
	
DataSource dataSource;
	
	public purchaseProductInfo(){
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/apple_store");
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
	}
	
	//구매내역 조회
	// Method for retrieving order list based on customer ID
	public ArrayList<productDto> list(String product_code) {
	System.out.println("product info 실행");
        ArrayList<productDto>  productinfo = new ArrayList<productDto>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            String query = "select product_name, product_qty, price from product where product_code=?";
            
            preparedStatement = connection.prepareStatement(query);
            // Setting customer ID as a parameter in the query
            preparedStatement.setString(1, product_code); 	
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
            	productDto dto = new productDto();
            	
            	
            	dto.setProduct_name(resultSet.getString("product_name"));
            	dto.setProduct_qty(resultSet.getInt("product_qty"));
            	dto.setPrice(resultSet.getInt("price"));
            	
            	
            	
            
                // Adding the OrderDto object to the list
            	productinfo.add(dto);
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

        return productinfo;
    }
	
	
}
