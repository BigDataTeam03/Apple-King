package FrontServlet;
//Java function
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
// javax servlet 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
// gson 
import com.google.gson.Gson;
// From Other package
import com.javalec.util.ShareVar;
@WebServlet("/idUserOverlapCheck")
public class idUserOverlapCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public idUserOverlapCheck() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(">> idUserOverlapCheck 을 실행");
		response.setContentType("text/html;charset=UTF-8");
		
		//return value
		boolean result ;
		
		// Parameters from signup.js 
		String id = request.getParameter("id");
	
		// ArrayList 에 담겨 있는 데이터를 JSON 으로 변경하여 송부
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter(); // For Gson result output
		
		// My SQL connection 
		PreparedStatement ps = null; 
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(ShareVar.url_mysql, ShareVar.id_mysql,ShareVar.pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			// SQL query
			String query= " SELECT cust_id FROM customer ";
			String query1= "WHERE cust_id = '" + id + "'";
			
			ps = conn_mysql.prepareStatement(query+query1);
			rs = ps.executeQuery();
			if(rs.next()) {
				result = true; 	// ID 중복 -> true
				System.out.println(">> 중복된 아이디가 있습니다.");	
			}else {
				result = false; // 중복 x -> false 
				System.out.println(">> 중복된 아이디가 없습니다.");
			}
			out.print(new Gson().toJson(result));
			//System.out.println(result);
			out.flush();
			} catch(Exception e) {
				e.printStackTrace();
			}
	}
}

