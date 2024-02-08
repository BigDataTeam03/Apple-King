package FrontServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.util.ShareVar;

/**
 * Servlet implementation class uCartDeleteServlet
 */
@WebServlet("/uCartDeleteServlet")
public class uCartDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public uCartDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		--------------------------------------------------------------
		* Description 	: cart delete 
		* Author 		: KBS
		* Date 			: 2024.02.08
		* ---------------------------Update---------------------------		
		 	<<2024.02.08>> by KBS
			1. 삭제 기능 추가 
		*
		--------------------------------------------------------------
		*/
		
		 
		String [] selected = request.getParameterValues("code");
		
		System.out.println("삭제될 상품의 code  : "+ selected);
		//변수중에 한글이 포함됨으로 인코딩설정을 한다
		response.setContentType("text/html;charset=UTF-8");
				
		PrintWriter out = response.getWriter(); // try 바깥에서 선언해라. 
		 
		//? 를 사용해서 쿼리문을 사용하기 위해 프리페어를 사용한다
			PreparedStatement ps = null;

	        try {
	            // 데이터베이스 연결
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection conn_mysql = DriverManager.getConnection(ShareVar.url_mysql, ShareVar.id_mysql, ShareVar.pw_mysql);

	            
	            
	            String deleteQuery = "delete from cart where cart_code = ?";
                ps = conn_mysql.prepareStatement(deleteQuery);
               // 배열을 사용함으로 배열의 길이만큼 시행한다
             for (  int i = 0; i < selected.length; i++) {
            	 			//배열의 첫번째는 0 으로 시작한다
            	 	
	            	 ps.setString(1, selected[i]);
	            	 ps.executeUpdate();
             }
              
	            
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
