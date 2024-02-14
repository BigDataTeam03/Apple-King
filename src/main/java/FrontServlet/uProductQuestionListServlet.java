package FrontServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.javalec.util.ShareVar;

import dto.InquireDto;

/**
 * Servlet implementation class uProductquestionListServlet
 */
@WebServlet("/uProductquestionListServlet")
public class uProductQuestionListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public uProductQuestionListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		//--------------------------------------------------------------
		//* Description 	: 사용자가 보는 상품문의 리스트
		//*  Detail		: 문의 리스트를출력
		//* Author 		: KBS
		//* Date 			: 2024.02.14
		//* ---------------------------Update---------------------------		
		//* <<2024.02.14>> by KBS
		//*		 1. 주석  및 정리
		//       2. 리스트 불러오고 Json 으로 보내기
		//         
		//--------------------------------------------------------------
		
		
		
		HttpSession session =request.getSession();
		response.setContentType("text/html;charset=UTF-8");  

		//검색기능을 위한 이름변수 지정
		//String product_code = request.getParameter("product_code");
		String product_code = (String) session.getAttribute("product_code");
		//System.out.println(" 세션으로 가져온 상품 코드" + product_code);
		
	
		
		
		// ArrayList 에 담겨 있는 데이터를 JSON 으로 변경하여 송부
		response.setContentType("application/json");
		// 가져오는 정보에 한글이 포함됨으로 인코딩 설정
		response.setCharacterEncoding("UTF-8");
			
      	// select 문에 여러가지 정보를 담아야 하니 ArrayList 를 사용해서 리스트에 담는다
		ArrayList<InquireDto> inquiredto = new ArrayList<InquireDto>();
		
		//product 테이블에 있는 모든 컬럼을 불러오는 쿼리문
		String Query = "select cust_id, product_code, inquire_date, inquire_content, answer_content from inquire where product_code='" + product_code +"'";
				//정렬에 따라 바뀐다		
		
		System.out.println("query 실행 전 내용 :"+ Query);
	
		//out.flush 를 사용하기위해 out 변수 지정
		PrintWriter out = response.getWriter();
		
		
		
		try {
			// SQL 연결
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(ShareVar.url_mysql, ShareVar.id_mysql,ShareVar.pw_mysql);
			
			//select 쿼리문을 사욜하니 Statement 를 사용
			Statement  stmt_mysql =conn_mysql.createStatement();
						
			//결과를 담는 변수 설정
			ResultSet rs = stmt_mysql.executeQuery(Query);
		    while (rs.next()) {
                InquireDto dto = new InquireDto();
                dto.setCust_id(rs.getString("cust_id"));            
                dto.setProduct_code(rs.getString("product_code"));
                dto.setInquire_date(rs.getString("inquire_date"));
                dto.setInquire_content(rs.getString("inquire_content"));
                dto.setAnswer_content(rs.getString("answer_content"));
                
                inquiredto.add(dto);
            }

            out.print(new Gson().toJson(inquiredto));
            out.flush();
			
		    rs.close();
            conn_mysql.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		

		
		
		
		
		
		
		
		
		
	}

}
