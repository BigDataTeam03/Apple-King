<%@page import="dto.BoardDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="dao.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ include file ="top_user.jsp" %>
<%
	/*--------------------------------------------------------------
	* Description 	: qna 게시판 dao (ver. model 1)
			Detail	:
	* Author 		: PDG
	* Date 			: 2024.02.11
	* ---------------------------Update---------------------------		
	    <<2024.02.11> by pdg
	--------------------------------------------------------------*/
%>    
<%
	//Dao -> connection pool 
	BoardDao dao =new BoardDao();
	
	//사용자가 입력한 검색 조건을 map 에 저장. 
	Map<String, Object> param = new HashMap<String, Object>();
	
	String searchField = request.getParameter("searchField");
	String searchWord = request.getParameter("searchWord");
	
	if (searchWord != null ){
		param.put("searchField", searchField);
		param.put("searchWord", searchWord);
	}
	int totalCount =dao.selectCount(param);
	List<BoardDTO> boardList = dao.selectList(param);
%>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
<body>
	 <%-- <jsp:include page="top_user.jsp">  --%>
	<h2> 목록 보기</h2>
	<form method = "post">
		<table border ="1" width ="90%">
			<tr>
				<td align ="center">
					<select name ="searchField">
						<option value ="title"> 제목 </option>
						<option value = "content">내용 </option>
					</select>		
					<input type="text" name ="searchWord"/>
					<input type ="submit" value="검색하기" />
				</td>	
			</tr>
		</table>
	</form>
	<!--  게시물 목록 테이블(표)  -->
	<table border ="1" width ="90%">
	
		<!--  각 칼럼의 이름 -->
		<tr>
			<th width="10%"> 번호 </th>
			<th width="10%"> 제목 </th>
			<th width="10%"> 작성자 </th>
			<th width="10%"> 조회수 </th>
			<th width="10%"> 작성일 </th>
		</tr>
		<!--  목록의 내용 -->
		
	</table>
	
	
</body>
</html>
















