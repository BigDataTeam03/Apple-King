package FrontServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.util.ShareVar;

/**
 * Servlet implementation class aProductDeleteServlet
 */
@WebServlet("/aProductDeleteServlet")
public class aProductDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public aProductDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		--------------------------------------------------------------
		* Description 	: Administrator product delete 
		* Author 		: PDG & KBS
		* Date 			: 2024.02.05
		* ---------------------------Update---------------------------		
		 	<<2024.02.05>> by PDG &KBS
			1. 삭제 기능 추가 
		*
		--------------------------------------------------------------
		*/
		
		
		String productCode = request.getParameter("code");
		
		System.out.println("삭제될 상품의 code  : "+ productCode);
		//변수중에 한글이 포함됨으로 인코딩설정을 한다
		response.setContentType("text/html;charset=UTF-8");
				
		PrintWriter out = response.getWriter(); // try 바깥에서 선언해라. 
		 
		//? 를 사용해서 쿼리문을 사용하기 위해 프리페어를 사용한다
			PreparedStatement ps = null;

	        try {
	            // 데이터베이스 연결
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection conn_mysql = DriverManager.getConnection(ShareVar.url_mysql, ShareVar.id_mysql, ShareVar.pw_mysql);

	            // 상품 코드를 이용하여 상품의 Primary Key 값을 가져오는 쿼리
	            
	            String deleteQuery = "DELETE FROM product WHERE product_code = ?";
                ps = conn_mysql.prepareStatement(deleteQuery);
	            ps.setString(1, productCode);
	            ps.executeUpdate();
	            // 쿼리 실행
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	            out.print("failure");
	        } finally {
	            try {       
	                if (ps != null) {
	                    ps.close();
	                }
	            } catch (Exception ex) {
	                ex.printStackTrace();
	            }
	        }
	    }
	

}
