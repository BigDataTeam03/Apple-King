package FrontServlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javalec.util.ShareVar;

/**
 * Servlet implementation class aProductInquerySurvlet
 */
@WebServlet("/aProductInquerySurvlet")
public class aProductInquerySurvlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public aProductInquerySurvlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		--------------------------------------------------------------
		* Description 	: 상품문의글을 작성한걸 데이터베이스에 넣는다
		* Author 		: KBS
		* Date 			: 2024.02.13
		* ---------------------------Update---------------------------		
		 	<<2024.02.13>> by KBS
			1. 인서트 기능 추가
			
		--------------------------------------------------------------
	
		*
		*/
		 
		response.setContentType("text/html;charset=UTF-8");  
		HttpSession session = request.getSession();
		
		String question = request.getParameter("question");
        String productName = (String) session.getAttribute("product_name"); // 세션에서 상품 이름 가져오기
        String cust_id = (String)session.getAttribute("userId");
        	System.out.println(" 문의 인서트에서 세션으로 받은 유저아이디" + cust_id);
        
        PreparedStatement ps = null;
        
        try {
			//js는 서버를 모르기 때문에 데이터베이스 커넥션을 지정해줘야한다
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(ShareVar.url_mysql, ShareVar.id_mysql,ShareVar.pw_mysql);
			//Statement stmt_mysql =conn_mysql.createStatement();
			//인서트를 위한 쿼리문을 작성한다, 그 중에 date=(now) 가 있어 rs. 는 하나가 줄어든다
			String query ="insert into inquire "
					+ " (cust_id, inquire_date, inquire_content) "
					+ " values(?,now(),?)"; 		
							

			//쿼리문을 실행할 변수를 지정한다
			ps = conn_mysql.prepareStatement(query);
			//지정해준 변수에 입력된 값을 넣는다
			ps.setString(1, cust_id);
			ps.setString(2, question);
		
			System.out.println("입력된 디비에 들어가는 쿼리" +ps.toString());
			//입력된 값을 데이터베이스에 업데이트를 실행한다
			ps.executeUpdate();
			
			
		}catch(Exception e) {
			e.printStackTrace();
			
			
		}		
	}
}
