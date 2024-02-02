package FrontServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.javalec.util.ShareVar;


import dto.productDto;

/**
 * Servlet implementation class aProductList
 */
@WebServlet("/aProductList")
public class aProductList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public aProductList() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8"); // html 에 텍스트타입으로 만들어서 보내려고 이걸쓰는것이다. 
	
		HttpSession session = request.getSession();
		System.out.println(" js 에서 parameter 를 가져온다.  ");
		
		//Request 로 받아온 값을 변수로 지정한다
		String 	 product_code 			= request.getParameter("product_code");
		String 	 product_name			= request.getParameter("product_name");
		int    	 product_qty 			= Integer.parseInt(request.getParameter("product_qty"));
		String 	 origin 				= request.getParameter("origin");
		String	 manufacture_date 		= request.getParameter("manufacture_date");
		int		 weigtht 				= Integer.parseInt(request.getParameter("weigtht"));
		int		 size 					= Integer.parseInt(request.getParameter("size"));
		String 	 detail_image_name 		= request.getParameter("detail_image_name");
		int 	 view_count 			=Integer.parseInt(request.getParameter("view_count"));
		String 	 product_reg_date 		= request.getParameter("product_reg_date");
		String 	 kind 					= request.getParameter("kind");
		String 	 product_image_names 	= request.getParameter("product_image_names");
		//상품 총 갯수를 나타내기 위한 변수지정
		int totalProductNumber =0;
		
		// ArrayList 에 담겨 있는 데이터를 JSON 으로 변경하여 송부
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		//out 을 사용하기위해 out 선언		
			PrintWriter out = response.getWriter();
			
		
		ArrayList<productDto> productdtoList = new ArrayList<productDto>();
		
		//product 테이블에 있는 모든 컬럼을 불러오는 쿼리문
		String readQuery = "select *"
				+ "product_code"
				+ "product_name"
				+ "product_qty"
				+ "origin"
				+ "manufacture_date"
				+ "weigtht"
				+ "size"
				+ "detail_image_name"
				+ "view_count"
				+ "product_reg_date"
				+ "kind"
				+ "product_image_names"
				
				//   product 에서 product name 을 검색하지만 처음에는 아무것도 안들어감으로 모두 조회함. 
				+ " from product where product_name like '%"+ product_name +"%'";
		System.out.println("query 실행 전 내용 :"+ readQuery);
		
		// 상품 총수량 정보 
		
		try {
			// SQL 연결
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(ShareVar.url_mysql, ShareVar.id_mysql,ShareVar.pw_mysql);
			
			//select 쿼리문을 사욜하니 Statement 를 사용
			Statement  stmt_mysql =conn_mysql.createStatement();
			
			//결과를 담는 변수 설정
			ResultSet rs = stmt_mysql.executeQuery(readQuery);
			while(rs.next()) {
				
				// productDto 선언
				productDto productdto = new productDto();
				productdto.setProduct_code		 (rs.getString(	"product_code")); 		// 1
				productdto.setProduct_name		 (rs.getString(	"product_name")); 		// 2
				productdto.setProduct_qty		 (rs.getInt(	"product_qty")); 		// 3
				productdto.setManufacture_date	 (rs.getString(	"manufacture_date")); 	// 4 
				productdto.setWeigtht			 (rs.getInt(	"weigtht")); 			// 5
				productdto.setSize  			 (rs.getString(	"size")); 				// 6
				productdto.setDetail_image_name  (rs.getString(	"detail_image_name")); 	// 7 
				productdto.setView_count  		 (rs.getInt(	"view_count")); 		// 8
				productdto.setProduct_reg_dat 	 (rs.getString(	"product_reg_date")); 	// 9 
				productdto.setKind  			 (rs.getString(	"kind")); 				// 10
				productdto.setProduct_image_names(rs.getString(	"product_image_names"));// 11 
				
				//검색된 내용을 productDto 에 추가
				productdtoList.add(productdto);
				totalProductNumber++;
				// Json 타입으로 변환하기 위한 Gson 선언
				
				
			}
			out.print(new Gson().toJson(productdtoList));
			
			//Json 이 뭔지 한번 출력해봅시다. 
			System.out.println(new Gson().toJson(productdtoList));
			out.flush();
			// 어레이리스트 하나에 에스 코드 에스 디티로 싸여있겟지 이것이 대괄호에애스코드1 번 최문국 등으로 
			// 키벨류값으로 리스트가 만들어진다. 그게 제이슨이다. 
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
