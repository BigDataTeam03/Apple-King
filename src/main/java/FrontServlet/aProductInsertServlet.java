package FrontServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalTime;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javalec.util.ShareVar;
import com.javaproject.util.ParameterPrintOut;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

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
			<<2024.02.06>> by PDG
			1. 이 페이지는 필요가 없습니다. 왜냐하면 js 로 이미지를 로드하는게 빌어먹게 안됬기때문.!
		*
		--------------------------------------------------------------
		*/
		//image 등록 
		ServletContext context = getServletContext();
		String realfolder = context.getRealPath("/image");
		System.out.print("image 가 들어갈 경로"+realfolder);
		int sizeLimit = 100*1024*1024;	
		MultipartRequest multi = new MultipartRequest(			 
				//<< Multi Part parameters >>
				request	  ,	 // request
				realfolder,	 // image 가 저장될 application folder 
	            sizeLimit ,	 // image size limit
	            "UTF-8"   ,	 // image file name utf-8 
				new DefaultFileRenamePolicy()); // 중복시 (1). ...
		
		
		
		// image 이외의 값들 등록
		System.out.println(" Product INsert servlet 실행...");
		
		HttpSession session = request.getSession();
		String time_now = LocalTime.now().toString();
		//js 에서 받은 값을 변수로 지정한다
		String code 		= request.getParameter("product_code");
		String name 		= request.getParameter("product_name");
		String qty 			= request.getParameter("product_qty");
		String origin 		= request.getParameter("origin");
		String manufacture 	= (request.getParameter("manufacture_date")!= null) ? request.getParameter("manufacture"): "2024-02-02";
		String weight 		= request.getParameter("weight");
		String size 		= request.getParameter("size");
		String detailImage 	= request.getParameter("detail_image_name");
		String view 		= (request.getParameter("view")!= null) ? request.getParameter("view"): "0";
		String regDate 		= (request.getParameter("product_reg_date")!= null) ? request.getParameter("regDate"): "now()";
		String kind 		= request.getParameter("kind");
		String productImage = (request.getParameter("productImage")!= null) ? request.getParameter("productImage"): "null.png";
		String price 		= request.getParameter("price");
		
		//변수중에 한글이 포함됨으로 인코딩설정을 한다
		response.setContentType("text/html;charset=UTF-8");
		
		System.out.println(" price : " +price );
		// parameter  출력 확인
		String[] parms = {code, name, qty, origin, manufacture, weight, size, detailImage, view, regDate, kind
				, productImage, price};
		ParameterPrintOut parmOut = new ParameterPrintOut();
		parmOut.pp("js 에서 받는 변수 : ",parms);
		
		
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
					+ "manufacture_date,"				//5 -> null
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
