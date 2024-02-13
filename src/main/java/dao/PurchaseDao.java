package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;



import dto.cartDto;

public class purchaseDao {
	
	/*
	--------------------------------------------------------------
	 * Description 	: 장바구니에 담긴 상품을 가져와 구매 준비를 한다
	 * Author 		: KBS
	 * Date 			: 2024.02.13
	 * ---------------------------Update---------------------------		
	 	<<2024.02.13>> by KBS
		1. 리스트 출력 기능 추가
		
	--------------------------------------------------------------

	 *
	 */
	//Field
			DataSource dataSource;
	//Constructor
			public purchaseDao() {
					try {
						Context context = new InitialContext();
						dataSource = (DataSource)context.lookup("java:comp/env/jdbc/apple_store");
					}catch(Exception e) {
						e.printStackTrace();						
					}
			}
			// 전체 검색
			public ArrayList<cartDto> purchaseInfo(String cust_id) {
				ArrayList<cartDto> cartdtos = new ArrayList<cartDto>();
				Connection connection = null;
				PreparedStatement preparedStatement = null;
				ResultSet resultSet =null;
				
				try {
					connection = dataSource.getConnection();
					String query ="select cart.cart_code, cart.cust_id, customer.cust_point, cart.product_code,"
							//상품 테이블에 있는 이름,수량,가격을 선택
							+ " product.product_name, cart.cart_qty, product.price"
							// cart 테이블에서 가져온다
							+ " from cart"
							// 고객이 보유중인 포인트를 보여주기 위한 join 문
							+ " join customer ON cart.cust_id = customer.cust_id"
							// 상품 이름,수량,가격을 보여주기 위한 join 문
							+ " join product ON cart.product_code = product.product_code"
							// 로그인 한 유저의 아이디의 카트만 보여줘야함
							+ " where cart.cust_id='" + cust_id + "'";
					preparedStatement = connection.prepareStatement(query);
					resultSet =  preparedStatement.executeQuery();
							
					while(resultSet.next()) {
						  cartDto cartDto = new cartDto();
		                    cartDto.setCart_code(resultSet.getString("cart_code"));
		                    cartDto.setCust_id(resultSet.getString("cust_id"));
		                    cartDto.setCust_point(resultSet.getInt("cust_point"));
		                    cartDto.setProduct_code(resultSet.getString("product_code"));
		                    cartDto.setProduct_name(resultSet.getString("product_name"));
		                    cartDto.setCart_qty(resultSet.getString("cart_qty"));
		                    cartDto.setPrice(resultSet.getInt("price"));

		                    cartdtos.add(cartDto);
							 
					}
					
				}catch (Exception e) {
					e.printStackTrace();
					
				}finally {
					try {
						if (resultSet != null) 	resultSet.close();
						if (preparedStatement != null) preparedStatement.close();
						if (connection != null) connection.close();
						
					
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
				return cartdtos;
			}//list
}
    // Field
