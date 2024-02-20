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
import dto.MemberDto;

/**
 * Servlet implementation class aQuestionListServlet
 */
@WebServlet("/aQuestionListServlet")
public class aQuestionListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public aQuestionListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		
				
		/*
		--------------------------------------------------------------
		* Description 	: 관리자가 보는 답변이 필요한 문의 내역 페이지 출력
		* Author 		: KBS
		* Date 			: 2024.02.16
		* ---------------------------Update---------------------------		
		 	<<2024.02.16>> by KBS
			1. 리스트 출력 기능 추가
			2. 주석 설명 추가 
			3. 리스트를 출력 할 때, 답변이 안 달려있는 문의만 출력한다
			4. join 문 사용하여 상품이름 출력
			
		*
		--------------------------------------------------------------
		*/
		
		HttpSession session =request.getSession();
		response.setContentType("text/html;charset=UTF-8");  
		
//		//고객 총 갯수를 나타내기 위한 변수지정
		int questionCount =0;
		
		// ArrayList 에 담겨 있는 데이터를 JSON 으로 변경하여 송부
		response.setContentType("application/json");
		// 가져오는 정보에 한글이 포함됨으로 인코딩 설정
		response.setCharacterEncoding("UTF-8");

		// select 문에 여러가지 정보를 담아야 하니 ArrayList 를 사용해서 리스트에 담는다
		ArrayList<InquireDto> questionList = new ArrayList<InquireDto>();
		
		// product 테이블에서 product_name을 가져와서 출력하고, inquire 테이블과 join 후 출력
		String Query = "select " 
		        + "inquire.inquire_code, "          //1
		        + "inquire.cust_id, "               //2
		        + "inquire.product_code, "          //3
		        + "inquire.inquire_date, "          //4
		        + "inquire.inquire_content, "      //5
		        + "product.product_name "           // 6
		        
		        + "from inquire "
		        
		        //  product 테이블과 조인
		        + "inner join product on inquire.product_code = product.product_code "  
		        // 답글이 아직 안 달린 문의 내역만 가져온다
		        + "where inquire.answer_content is null order by inquire.inquire_date desc";
				
	
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
			while(rs.next()) {
				
				// Dto 선언
				InquireDto dto = new InquireDto();
				dto.setInquire_code(rs.getInt("inquire_code"));
				dto.setCust_id(rs.getString("cust_id"));
				dto.setProduct_code(rs.getString("product_code"));
				dto.setInquire_date(rs.getString("inquire_date"));
				dto.setInquire_content(rs.getString("inquire_content"));
			    dto.setProduct_name(rs.getString("product_name")); 					
				//검색된 내용을 Dto 에 추가
				questionList.add(dto);
				//답변이 필요한 문의의 총 갯수를 알수있는 변수에 ++ 
				questionCount++;
				
			
			}
			
			session.setAttribute("questionTot", questionCount);
			//데이터를 json으로 담아서 보낸다
			out.print(new Gson().toJson(questionList));
			
			out.flush();
			
			 
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
	}

}
