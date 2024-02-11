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
import javax.servlet.http.HttpSession;

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
		
		
		HttpSession session = request.getSession();
		/*
		--------------------------------------------------------------
		* Description 	: 장바구니 수량 수정 기능
		* Author 		: KBS
		* Date 			: 2024.02.09
		* ---------------------------Update---------------------------		
		 	<<2024.02.09>> by KBS
			1.사용자가 변경한 수량을 가져와서 데이터베이스에 즉각 반영
		*
		*
		*<<2024.02.11>> by KBS
			1. 수량을 수정할 때 상품 테이블에서 정보를 가져와 그 이상 수정할 수 없게 한다
			2. 해당 상품의 재고를 세션으로 받아야함
			3. 재고 초과시 예외처리 완
			
		--------------------------------------------------------------
		*/
		
		
		
		
		
		//js 에서 받은 값을 변수로 지정한다
		String cartCode 	= request.getParameter("cartCode");
		String quantity 	= request.getParameter("quantity");
		// 상품 테이블에 존재하는 재고 값을 세션으로 받아야함
		String	product_qty	= "5";
		//String product_qty = (String)session.getAttribute("product_qty");
		
		System.out.println(" 상품 코드와 수량 그리고 재고량" + cartCode + quantity + product_qty);
		//변수중에 한글이 포함됨으로 인코딩설정을 한다
		response.setContentType("text/html;charset=UTF-8");
		
		
		PrintWriter out = response.getWriter(); // try 바깥에서 선언해라. 
		 //? 를 사용해서 쿼리문을 사용하기 위해 프리페어를 사용한다
		PreparedStatement ps = null;
		//		사용자가 수정한 수량이         해당 상품의 재고보다 작을 때 
		//System.out.println("비교문 츨력" + ( Integer.parseInt(quantity) < Integer.parseInt(product_qty)));
   if (Integer.parseInt(quantity) < Integer.parseInt(product_qty)) {
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
	
 
			out.print("success");
			
		}catch(Exception e) {
			e.printStackTrace();
			
			out.print("failure");;
		}
		
		// 수량이 초과될 때  보내는 Json 값
	}else {
		out.print("수량초과");
	}

		
	}

}
