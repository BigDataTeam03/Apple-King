package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.productDto;

public class Detail_Dao {

	// Field
		DataSource dataSource;

		// Constructor
		public Detail_Dao() {
			try {
				Context context = new InitialContext();
				dataSource = (DataSource) context.lookup("java:comp/env/jdbc/apple_store");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// Method
		// detail_view에 들어갈 dto 만들기 (select)
		public productDto Detail(String product_name) {
			productDto dto = null;
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultset = null;
			
			try {
				// data 베이스 연결
				connection = dataSource.getConnection();
				// 쿼리 작성
				// 상품코드, 상품이름, 상품가격, 상품색깔, 상품설명, 상품이미지경로, 상품사이즈, 상품수량을 select
				String select = "SELECT p.detail_image_name, p.product_name, p.origin, r.rating, p.price, p.size, p.weight";
				String from = " FROM product p, review r";
				String where = " WHERE p.product_code = r.product_code ";

				// 작성한 쿼리를 데이터 connection을 사용하여 실행
				preparedStatement = connection.prepareStatement(select + from + where);
				preparedStatement.setString(1, product_name);
				System.out.println("상세페이지 실행");
				
				// 실행한 쿼리문을 resultset에 삽입
				resultset = preparedStatement.executeQuery();
				
				if (resultset.next()) {
					// 데이터 불러오기
					
					String pdetailimage = resultset.getString("p.detail_image_name");
					String pname = resultset.getString("p.product_name"); // 칼럼 이름을 넣어야함
					String porigin = resultset.getString("p.origin"); 
					String rating = resultset.getString("r.rating");
					int pprice = resultset.getInt("p.price");
					String psize = resultset.getString("p.size");
					int pweight = resultset.getInt("p.weight");
				
					// 불러온 데이터들을 dto 객체에 추가
					dto = new productDto(pdetailimage, pname, porigin, rating, pprice, psize, pweight);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally { // 데이터 정리하는 용도로 쓰임 (만든 순서 거꾸로 정리해야함)
				try {
					if (resultset != null) {
						resultset.close();
					}
					if (preparedStatement != null) {
						preparedStatement.close();
					}
					if (connection != null) {
						connection.close();
					}
				} catch (Exception e) {

				}
			}

			return dto;
			
		} //detail()

	}// End

