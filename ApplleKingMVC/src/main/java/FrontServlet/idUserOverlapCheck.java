package FrontServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.javalec.util.ShareVar;

/**
 * Servlet implementation class idUserOverlapCheck
 */
@WebServlet("/idUserOverlapCheck")
public class idUserOverlapCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public idUserOverlapCheck() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("idUserOverlapCheck 을 실행합니다.");
		
		
		String id = request.getParameter("id");

		
		response.setContentType("text/html;charset=UTF-8");
		String code = request.getParameter("code");// html 에 텍스트타입으로 만들어서 보내려고 이걸쓰는것이다. 
		HttpSession session = request.getSession();
	
		// ArrayList 에 담겨 있는 데이터를 JSON 으로 변경하여 송부
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		
		PreparedStatement ps = null; 
		ResultSet rs = null;	
		boolean result ;
				
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(ShareVar.url_mysql, ShareVar.id_mysql,ShareVar.pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			String query= " SELECT cust_id FROM customer ";
			String query1= "WHERE cust_id = '" + id + "'";
		
			ps = conn_mysql.prepareStatement(query+query1);
			rs = ps.executeQuery();
		
			if(rs.next()) {
				// result 아이디의 중복여부의 결과물을 Json으로 반환
				result = true; // 중복됐을 경우 true

				
				System.out.println("중복된 아이디가 있습니다.");	
				
			}else {
				result = false; // 중복되지 않았을 경우 false 
				
				System.out.println("중복된 아이디가 없습니다.");
			}
			
			out.print(new Gson().toJson(result));
			System.out.println(result);
			out.flush();
			} catch(Exception e) {
				e.printStackTrace();
				
			}
			
		
		
		
		
			
		
	
			
			
		
		
		
		
		
		


	
	}
}

