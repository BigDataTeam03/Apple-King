package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.productDto;

public class ProductDao {
	
	/*--------------------------------------
	 * Description : Product DAO<< product >>
	 * Author 	   : pdg
	 * Date 	   : 2024.02.13
	 * Details		
	 * Update------------------------------- 
	 * <2024.02.11> by PDG
	 *-------------------------------------- 
	 */
	// Field
	DataSource dataSource;
	
	// Constructor
	public ProductDao() {
		try {
			Context context =new InitialContext();
			dataSource =(DataSource)context.lookup("java:comp/env/jdbc/apple_store");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public int productCount() {
		System.out.println(">> ProductDao.ProductCount 실행");
		// retrun value 
		int productCount = 0; 
		
		
		Connection connection =null;
		PreparedStatement ps =null;
		ResultSet rs =null;
		try {
			connection = dataSource.getConnection();
			
			String Query = "select count(product_code) as totalCount from product";
			
			ps =connection.prepareStatement(Query);
			rs  = ps.executeQuery();
			if (rs.next()){
				productCount = rs.getInt("totalCount");
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally { // 데이터 정리하는 용도로 쓰임 (만든 순서 거꾸로 정리해야함)
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
			}
		}
		return productCount;
	}
	public List getProductList(int startRow, int pageSize, String searchContent) {
		// retrun value 
		List<productDto> productList = new ArrayList<productDto>();
		System.out.println(">> ProductDao.getProductList 실행");
		Connection connection =null;
		PreparedStatement ps =null;
		ResultSet rs =null;
		try {
			connection = dataSource.getConnection();
			
			String Query = "select * from product "
					+ "where product_name like '%"+ searchContent + "%'"
					+ " order by product_code desc ,price asc limit ?,? ";
			
			
			ps =connection.prepareStatement(Query);
			ps.setInt(1, startRow -1); // 시작 행 -1  ( 시작 row index 변호 ) 
			ps.setInt(2, pageSize) ; // 페이지 크기 (한번에 출력되는 수)
			System.out.println(">> Query : "+ps.toString());
			rs  = ps.executeQuery();
			while  (rs.next()){
				productDto pdto  =new productDto();
				
				pdto.setProduct_code		(rs.getString("product_code"));
				pdto.setProduct_name		(rs.getString("product_name"));
				pdto.setProduct_qty			(rs.getInt	 ("product_qty"));
				pdto.setOrigin				(rs.getString("origin"));
				pdto.setManufacture_date	(rs.getString("manufacture_date"));
				pdto.setWeight				(rs.getInt	 ("weight"));
				pdto.setSize			 	(rs.getString("size"));
				pdto.setDetail_image_name	(rs.getString("detail_image_name"));
				pdto.setView_count			(rs.getInt	 ("view_count"));
				pdto.setProduct_reg_date	(rs.getString("product_reg_date"));
				pdto.setKind				(rs.getString("kind"));
				pdto.setProduct_image_names	(rs.getString("product_image_names"));
				pdto.setPrice				(rs.getInt	 ("price"));
				
				productList.add(pdto);
				
			}
			System.out.println(" >> 상품 정보 dto 불러와서 저장 개수 :"+ productList.size());
		}catch(Exception e) {
			e.printStackTrace();
		}finally { // 데이터 정리하는 용도로 쓰임 (만든 순서 거꾸로 정리해야함)
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
			}
		}
		return productList;
	}
}
