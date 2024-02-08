package FrontServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.util.ShareVar;

/**
 * Servlet implementation class aProductUpdateServlet
 */
@WebServlet("/aProductUpdateServlet")
public class aProductUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public aProductUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		/*
		--------------------------------------------------------------
		* Description 	: 상품 수정 기능
		* Author 		: KBS & PDG
		* Date 			: 2024.02.05
		* ---------------------------Update---------------------------		
		 	<<2024.02.05>> by KBS & PDG
			1.사용자가 입력한 정보를 가져와서 업데이트 실행
		*
		--------------------------------------------------------------
		*/
		
		
		
		
		
		//js 에서 받은 값을 변수로 지정한다
		String code 		= request.getParameter("code");
		String name 		= request.getParameter("name");
		String qty 			= request.getParameter("qty");
		String origin 		= request.getParameter("origin");
		String manufacture 	= request.getParameter("manufacture");
		String weight 		= request.getParameter("weight");
		String size 		= request.getParameter("size");
		String detailImage 	= request.getParameter("detailImage");
		String view 		= request.getParameter("view");
		String regDate 		= request.getParameter("regDate");
		String kind 		= request.getParameter("kind");
		String productImage = request.getParameter("productImage");
		//변수중에 한글이 포함됨으로 인코딩설정을 한다
		response.setContentType("text/html;charset=UTF-8");
		
		
		PrintWriter out = response.getWriter(); // try 바깥에서 선언해라. 
		 //? 를 사용해서 쿼리문을 사용하기 위해 프리페어를 사용한다
		PreparedStatement ps = null;
		try {
			//js는 서버를 모르기 때문에 데이터베이스 커넥션을 지정해줘야한다
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(ShareVar.url_mysql, ShareVar.id_mysql,ShareVar.pw_mysql);
			//Statement stmt_mysql =conn_mysql.createStatement();
			//업데이트를 위한 쿼리문을 작성한다, 그 중에 date=(now) 가 있어 rs. 는 하나가 줄어든다
			String query ="UPDATE product "
					+ "SET"
					+ "    product_name=?,"  
					+ "    product_qty=?,"		
					+ "    origin=?,"			
					+ "    manufacture_date=?,"	
					+ "    weight=?,"			
					+ "    size=?,"				
					+ "    detail_image_name=?,"
					+ "    view_count=?,"		
					+ "    product_reg_date=now(),"
					+ "    kind=?,"					
					+ "    product_image_names=? WHERE product_code=?";
			//쿼리문을 실행할 변수를 지정한다
			ps = conn_mysql.prepareStatement(query);
			//지정해준 변수에 입력된 값을 넣는다
			ps.setString(1, name);
			ps.setString(2, qty);
			ps.setString(3, origin);
			ps.setString(4, manufacture);
			ps.setString(5, weight);
			ps.setString(6, size);
			ps.setString(7, detailImage);
			ps.setString(8, view);
			ps.setString(9, kind);
			ps.setString(10, productImage);
			ps.setString(11, code);
			
			System.out.println("수정된 디비에 들어가는 쿼리" +ps.toString());
			//입력된 값을 데이터베이스에 업데이트를 실행한다
			ps.executeUpdate();
			out.print("success");
			


			
			
		}catch(Exception e) {
			e.printStackTrace();
			
			out.print("failure");;
		}
		
		
	}

		
		
		
		
	

}
