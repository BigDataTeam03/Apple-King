package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.productDto;

public class Detail_Dao {
	/*--------------------------------------
	 * Description : Product detail page DAO << product >>
	 * Author 	   : pdg, LS
	 * Date 	   : 2024.02.11
	 * Details		
	 * Update------------------------------- 
	 * <2024.02.11> by PDG
	 *  
	 *  <2024.02.15> by LS
	 *  1. detailimagename list 처리 
	 *-------------------------------------- 
	 */
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
		System.out.println(">>Detail_Dao.detail 실행");
		
		// return 변수 생성
		productDto dto = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;

		try {
			// data 베이스 연결
			connection = dataSource.getConnection();

			// 쿼리 작성
			// 상품코드, 상품이름, 상품가격, 상품색깔, 상품설명, 상품이미지경로, 상품사이즈, 상품수량을 select
			String select = "select product_name, product_code, product_qty, origin, "
							+ "weight, size, detail_image_name, "
							+ "kind, price from product WHERE product_name = ?";
			
			// 작성한 쿼리를 데이터 connection 을 사용하여 실행
			preparedStatement = connection.prepareStatement(select);
			preparedStatement.setString(1, product_name); // 상품명 매개변수 설정
			System.out.println("쿼리문: " + select);

			// 실행한 쿼리문을 resultset에 삽입
			resultset = preparedStatement.executeQuery();
			
			// detail_image_name을 ArrayList에 담을 변수 생성
			ArrayList<String> detailImageNames = new ArrayList<>(); 
			
			while (resultset.next()) {
				String pdetailimage = resultset.getString("detail_image_name");
				String pname 		= resultset.getString("product_name"); 
				String porigin 		= resultset.getString("origin");
				int    pprice		= resultset.getInt	 ("price");
				String psize 		= resultset.getString("size");
				int    pweight 		= resultset.getInt	 ("weight");
				
				// 불러온 데이터들을 dto 객체에 추가
				dto = new productDto(pdetailimage, pname, porigin, pprice, psize, pweight);
				
				// detail_image_name을 ArrayList에 추가
                detailImageNames.add(pdetailimage);
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
				 e.printStackTrace();
			}
		}
		return dto;
	} // detail()
}// End
