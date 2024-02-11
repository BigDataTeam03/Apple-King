package FrontServlet;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class uCartQtyUpdateServlet
 */
@WebServlet("/uCartQtyUpdateServlet")
public class uCartQtyUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public uCartQtyUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		/*
		--------------------------------------------------------------
		* Description 	: 장바구니 수량 수정 기능
		* Author 		: KBS
		* Date 			: 2024.02.09
		* ---------------------------Update---------------------------		
		 	<<2024.02.09>> by KBS
			1.사용자가 변경한 수량을 가져와서 데이터베이스에 즉각 반영
		*
		--------------------------------------------------------------
		*/
		
		
		
		
		
		//js 에서 받은 값을 변수로 지정한다
		String cartCode 		= request.getParameter("cartCode");
		String quantity 		= request.getParameter("quantity");
		System.out.println(" 상품 코드와 수량" + cartCode + quantity);
		//변수중에 한글이 포함됨으로 인코딩설정을 한다
		response.setContentType("text/html;charset=UTF-8");
		
		
		PrintWriter out = response.getWriter(); // try 바깥에서 선언해라. 
		 //? 를 사용해서 쿼리문을 사용하기 위해 프리페어를 사용한다
		PreparedStatement ps = null;
		try {
			//js는 서버를 모르기 때문에 데이터베이스 커넥션을 지정해줘야한다
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(ShareVar.url_mysql, ShareVar.id_mysql,ShareVar.pw_mysql);
		
			
			String query = "update cart set cart_qty = ? where cart_code = ?";

			//쿼리문을 실행할 변수를 지정한다
			ps = conn_mysql.prepareStatement(query);
			//지정해준 변수에 입력된 값을 넣는다
			ps.setString(1, quantity);
			ps.setString(2, cartCode);
			
			
			System.out.println("수정된 디비에 들어가는 쿼리" +ps.toString());
			//입력된 값을 데이터베이스에 업데이트를 실행한다
			ps.executeUpdate();
			out.print("success");
			
			//conn_mysql.close();
 
			
			
		}catch(Exception e) {
			e.printStackTrace();
			
			out.print("failure");;
		}
		
		
	}

		
	

}
