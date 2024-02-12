package dao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.FileDto;

public class FileDao {
	/*--------------------------------------
	 * Description : FILE upload dao
	 * Author 	   : pdg
	 * Date 	   : 2024.02.12
	 * Details		
	 * 	 table  : image_upload
	 *   column : idx, name, title, cate, ofile, sfile, postdate
	 * 	 Dto    : FileDto
	 * 	 jsp    : RND_fileUploadMain
	 * Update------------------------------- 
	 * <2024.02.12> by PDG
	 *-------------------------------------- 
	 */

	// Field
	DataSource dataSource;
	
	//Constuructor
	public FileDao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/apple_store");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Method
	public int insertFile(FileDto dto) {
		
		// return value init
		int applyResult = 0 ;
		System.out.println(">> FileDao.insertFile  실행");
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = dataSource.getConnection();
			String fileInsertQuery ="INSERT into image_upload "
								  + "(name, title, cate, ofile, sfile, postdate) "
								  // 		 1 2 3 4 5 6 
								  + "values (?,?,?,?,?,now())";
			ps = conn.prepareStatement(fileInsertQuery);

			ps.setString(1, dto.getName());
			ps.setString(2, dto.getTitle());
			ps.setString(3, dto.getCate());
			ps.setString(4, dto.getOfile());
			ps.setString(5, dto.getSfile());
			// 쿼리문 실행시 적용된 행의 개수가 정수로 반환됨. 성공 -> 1
			System.out.println(">> 쿼리문 실행 :"+ps.toString());
			applyResult = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return applyResult ;
	}// InserFile end
	public List<FileDto> fileList(){
		
		//return value init
		List<FileDto> fileList = new Vector<FileDto>();
		
		System.out.println(">> FileDao.fileList  실행");
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = dataSource.getConnection();
			String listQuery ="select "
					+ "idx, "
					+ "name, "
					+ "title, "
					+ "cate, "
					+ "ofile, "
					+ "sfile, "
					+ "postdate "
					+ "from image_upload order by idx desc";
			ps = conn.prepareStatement(listQuery);
			rs = ps.executeQuery();

			System.out.println(">> 쿼리 실행 : "+ps.toString());
			while (rs.next()) {
				FileDto dto = new FileDto();
				dto.setIdx		(rs.getString(1));
				dto.setName		(rs.getString(2));
				dto.setTitle	(rs.getString(3));
				dto.setCate		(rs.getString(4));
				dto.setOfile	(rs.getString(5));
				dto.setSfile	(rs.getString(6));
				dto.setPostdate	(rs.getString(7));
				
				fileList.add(dto);
			} 
			
		}catch (Exception e) {
				e.printStackTrace();
				System.out.println(" select 시 예외 발생 ");
			} finally {
				try {
					if (ps != null)
						ps.close();
					if (conn != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		return fileList; 
	}// fileList end
}//END









