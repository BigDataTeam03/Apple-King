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
			
		//js 에서 받은 값을 변수로 지정한다
		//String name = request.getParameter("name");  
		String productCode = request.getParameter("code");
		
		//변수중에 한글이 포함됨으로 인코딩설정을 한다
		response.setContentType("text/html;charset=UTF-8");
				
		PrintWriter out = response.getWriter(); // try 바깥에서 선언해라. 
		 //? 를 사용해서 쿼리문을 사용하기 위해 프리페어를 사용한다
		 PreparedStatement ps = null;
	        ResultSet rs = null;

	        try {
	            // 데이터베이스 연결
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection conn_mysql = DriverManager.getConnection(ShareVar.url_mysql, ShareVar.id_mysql, ShareVar.pw_mysql);

	            // 상품 코드를 이용하여 상품의 Primary Key 값을 가져오는 쿼리
	            String selectQuery = "SELECT product_code FROM product WHERE product_name = ?";
	            ps = conn_mysql.prepareStatement(selectQuery);
	            ps.setString(1, productCode);

	            // 쿼리 실행
	             ps.executeQuery();

	            if (rs.next()) {
	                // Primary Key 값을 받아옴
	                int primaryKey = rs.getInt("product_code");

	                // 상품 및 Primary Key 삭제 쿼리 작성
	                String deleteQuery = "DELETE FROM product WHERE product_code = ?";
	                ps = conn_mysql.prepareStatement(deleteQuery);
	                ps.setInt(1, primaryKey);

	                // 쿼리 실행
	                ps.executeUpdate();

	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	            out.print("failure");
	        } finally {
	            try {
	                if (rs != null) {
	                    rs.close();
	                }
	                if (ps != null) {
	                    ps.close();
	                }
	            } catch (Exception ex) {
	                ex.printStackTrace();
	            }
	        }
	    }
	

}
