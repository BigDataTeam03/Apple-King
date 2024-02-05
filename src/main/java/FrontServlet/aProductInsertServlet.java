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
import javax.servlet.http.HttpSession;

import com.javalec.util.ShareVar;

/**
 * Servlet implementation class aProductInsertServlet
 */
@WebServlet("/aProductInsertServlet")
public class aProductInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public aProductInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		--------------------------------------------------------------
		* Description 	: Administrator product insert
		* Author 		: PDG & KBS
		* Date 			: 2024.02.05
		* ---------------------------Update---------------------------		
		 	<<2024.02.05>> by PDG &KBS
			1. 삽입 기능 추가 
		*
		--------------------------------------------------------------
		*/
		
		System.out.println(" Product INsert servlet 실행...");
		
		
		HttpSession session = request.getSession();
		
		
		
		//js 에서 받은 값을 변수로 지정한다
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		String qty = request.getParameter("qty");
		String origin = request.getParameter("origin");
		String manufacture = request.getParameter("manufacture");
		String weight = request.getParameter("weight");
		String size = request.getParameter("size");
		String detailImage = request.getParameter("detailImage");
		String view = request.getParameter("view");
		String regDate = request.getParameter("regDate");
		String kind = request.getParameter("kind");
		String productImage = request.getParameter("productImage");
		String price = request.getParameter("price");
		
		//변수중에 한글이 포함됨으로 인코딩설정을 한다
		response.setContentType("text/html;charset=UTF-8");
		
		System.out.println(" price : " +price );
		
		
		PrintWriter out = response.getWriter(); // try 바깥에서 선언해라. 
		 //? 를 사용해서 쿼리문을 사용하기 위해 프리페어를 사용한다
		PreparedStatement ps = null;
		try {
			//js는 서버를 모르기 때문에 데이터베이스 커넥션을 지정해줘야한다
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(ShareVar.url_mysql, ShareVar.id_mysql,ShareVar.pw_mysql);
			//Statement stmt_mysql =conn_mysql.createStatement();
			//인서트를 위한 쿼리문을 작성한다, 그 중에 date=(now) 가 있어 rs. 는 하나가 줄어든다
			String query ="insert into product " 		//1
					+ " (product_code,product_name," 	//2
					+ "product_qty,"					//3
					+ "origin,"							//4
					+ "manufacture_date,"				//5
					+ "weight,"							//6
					+ "size,"							//7
					+ "detail_image_name,"				//8
					+ "view_count,"						//9
					+ "product_reg_date,"				//10
					+ "kind,"							//11
					+ "product_image_names,"			//12
					+ "price)"							//13
							//1 2 3 4 5 6 7 8 9 10 	   11 12 13
					+ "values(?,?,?,?,?,?,?,?,?,now(), ?, ?, ?)";
					
			//쿼리문을 실행할 변수를 지정한다
			ps = conn_mysql.prepareStatement(query);
			//지정해준 변수에 입력된 값을 넣는다
			ps.setString(1, code);
			ps.setString(2, name);
			ps.setString(3, qty);
			ps.setString(4, origin);
			ps.setString(5, manufacture);
			ps.setString(6, weight);
			ps.setString(7, size);
			ps.setString(8, detailImage);
			ps.setString(9, view);
			// now 가 있던자리...
			ps.setString(10, kind);
			ps.setString(11, productImage);
			ps.setString(12, price);
			
			
			System.out.println("입력된 디비에 들어가는 쿼리" +ps.toString());
			//입력된 값을 데이터베이스에 업데이트를 실행한다
			ps.executeUpdate();
			out.print("success");
			
			

			
			
		}catch(Exception e) {
			e.printStackTrace();
			
			out.print("failure");;
		}
		
		// Thumnail
		
		
		
	}


		
}
