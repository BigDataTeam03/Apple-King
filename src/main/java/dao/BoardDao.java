package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.BoardDTO;
import dto.productDto;

public class BoardDao {
	
	/*--------------------------------------
	 * Description : Q&A board Dao << qna_board,customer >>
	 * Author 	   : pdg
	 * Date 	   : 2024.02.11
	 * Details		
	 * 		DTO -> BoardDto
	 * 		** qna_board **
	 * 			board_num , title,content,id, postdate,vistcount
	 * 			Map<String, Object> => 게시물 검색을 위한 검색어가 담겨잇음.
	 * Update------------------------------- 
	 * <2024.02.11> by PDG
	 *-------------------------------------- 
	 */
	// Field
	
	DataSource dataSource;
	public BoardDao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/apple_store");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 게시물을 가져오는 메서드 
	public List<BoardDTO> selectList(Map<String, Object> map){
		//return value init
		
		List<BoardDTO> searchedContents = new Vector<BoardDTO>();
		System.out.println(">> BoardDao.selectList 실행");
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		String query = "select "
				+ "board_num ,"
				+ "title, "
				+ "name, "
				+ "content, "
				+ "id, "
				+ "postdate, "
				+ "vistcount from board ";
		if (map.get("searchWord")!=null) {
			query += "where "+ map.get("searchField")+ " "+
					"like '%" + map.get("searchWord")+ "%' ";
		}
		query += " order by board_num desc";
		try {
			// data 베이스 연결
			connection = dataSource.getConnection();
			// 작성한 쿼리를 데이터 connection 을 사용하여 실행
			preparedStatement = connection.prepareStatement(query);
			System.out.println("쿼리문: " + query);
			// 실행한 쿼리문을 resultset에 삽입
			resultset = preparedStatement.executeQuery();
			while(resultset.next()) {
				BoardDTO dto  =new BoardDTO();
				
				// 한행(게시물 하나) 의 내용을 dto 에 저장.
				dto.setBoard_num (resultset.getString("board_num"));// 일련번호
				dto.setTitle	 (resultset.getString("title"));	// 제목
				dto.setName		 (resultset.getString("name"));		// 이름
				dto.setContent	 (resultset.getString("content"));	// 내용
				dto.setId		 (resultset.getString("id"));		// 작성자 아이디
				dto.setPostdate	 (resultset.getDate  ("postdate"));	// 작성일
				dto.setVisitcount(resultset.getString("vistcount"));// 조회수
				
				//결과 목록에 저장.
				searchedContents.add(dto);
			}
		} catch (Exception e) {
			System.out.println("게시물 수를 구하는 중 예외 발생");
			e.printStackTrace();
		}
		return searchedContents;
	}
	
	
	
	// 게시된 글의 개수를 세주는 기능 .
	public int selectCount(Map<String, Object> map) {
		
		//Retrun value init
		int totalCount = 0;
		System.out.println(">> BoardDao.selectCount 실행");
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		String countQuery ="select count(bourd_num) from qna_board";
		if(map.get("searchWord") !=null) {
			countQuery += "where"  + map.get("searchField")+" "+
					      "like '%"+ map.get("searchWord")+ "%'";
		}
		try {
			// data 베이스 연결
			connection = dataSource.getConnection();
			// 작성한 쿼리를 데이터 connection 을 사용하여 실행
			preparedStatement = connection.prepareStatement(countQuery);
			System.out.println("쿼리문: " + countQuery);
			// 실행한 쿼리문을 resultset에 삽입
			resultset = preparedStatement.executeQuery();
			if(resultset.next()) {
				totalCount =resultset.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("게시물 수를 구하는 중 예외 발생");
			e.printStackTrace();
		}
		return totalCount;
	}
} // END
