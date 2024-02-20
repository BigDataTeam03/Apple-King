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

public class purchaseCustInfo {
	/*--------------------------------------
	 * Description : Order Dao <<customer >>
	 * Author 	   : DK
	 * Date 	   : 2024.02.18
	 * Update	   :
	 *-------------------------------------- 
	 */
	
DataSource dataSource;
	
	public purchaseCustInfo() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/apple_store");
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
	}
	
	//구매내역 조회
	// Method for retrieving order list based on customer ID
	public ArrayList<MemberDto> lists(String cust_id) {
		System.out.println("purchase dao 실행");
        ArrayList<MemberDto> custinfo = new ArrayList<MemberDto>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            String query = "select name, tel, email from customer where cust_id=?";
            
            preparedStatement = connection.prepareStatement(query);
            // Setting customer ID as a parameter in the query
            preparedStatement.setString(1, cust_id); 	
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
            	MemberDto dto = new MemberDto();
            	dto.setName(resultSet.getString("name"));
                dto.setTel(resultSet.getString("tel"));
                dto.setEmail(resultSet.getString("email"));
                
                
                custinfo.add(dto);
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

        return custinfo;
    }
	
	
}
