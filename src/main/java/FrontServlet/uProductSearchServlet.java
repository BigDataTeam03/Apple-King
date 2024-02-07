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
 * Servlet implementation class uProductSearchServlet
 */
@WebServlet("/uProductSearchServlet")
public class uProductSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public uProductSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		System.out.println("aProductListServlet 을 실행합니다.");
		response.setContentType("text/html;charset=UTF-8");  
		HttpSession session = request.getSession();

		String product_name = "";
			if (request.getParameter("name") == null) {
				product_name = "";					
			}else {
			    product_name = request.getParameter("name") ;
			}

//		//상품 총 갯수를 나타내기 위한 변수지정
		int totalProductNumber =0;
		
		// ArrayList 에 담겨 있는 데이터를 JSON 으로 변경하여 송부
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		//out 을 사용하기위해 out 선언		
			
		
		ArrayList<productDto> productdtoList = new ArrayList<productDto>();
		
		//product 테이블에 있는 모든 컬럼을 불러오는 쿼리문
		String readQuery = "select " 
				+ "product_code, "
				+ "product_name, "
				+ "product_qty, "
				+ "origin, "
				+ "manufacture_date, "
				+ "weight, "
				+ "size, "
				+ "detail_image_name, "
				+ "view_count, "
				+ "product_reg_date, "
				+ "kind, "
				+ "product_image_names, "
				+ "price"
				
				//   product 에서 product name 을 검색하지만 처음에는 아무것도 안들어감으로 모두 조회함. 
				+ " from product where product_name like '%"+ product_name + "%'";
		System.out.println("query 실행 전 내용 :"+ readQuery);
		PrintWriter out = response.getWriter();
		
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
				productdto.setOrigin			 (rs.getString( "origin")); 			// 4
				productdto.setManufacture_date	 (rs.getString(	"manufacture_date")); 	// 5 
				productdto.setWeight			 (rs.getInt("weight")); 				// 6
				productdto.setSize  			 (rs.getString(	"size")); 				// 7
				productdto.setDetail_image_name  (rs.getString(	"detail_image_name")); 	// 8 
				productdto.setView_count  		 (rs.getInt(	"view_count")); 		// 9
				productdto.setProduct_reg_date 	 (rs.getString(	"product_reg_date")); 	// 10 
				productdto.setKind  			 (rs.getString(	"kind")); 				// 11
				productdto.setProduct_image_names(rs.getString(	"product_image_names"));// 12 
				productdto.setPrice				 (rs.getInt(	"price"));				// 13 
				//검색된 내용을 productDto 에 추가
				productdtoList.add(productdto);
				totalProductNumber++;
				
			}
			System.out.println("Json전");
			// Json 타입으로 변환하기 위한 Gson 선언
			out.print(new Gson().toJson(productdtoList));
			
			//Json 이 뭔지 한번 출력해봅시다. 
//				System.out.println(new Gson().toJson(productdtoList));
			out.flush();
			
			
			//입력시 코드를 생성하기위한 세션
			session.setAttribute("totalProductNumber", totalProductNumber );
			//System.out.println("총 상품 갯수" + session.getAttribute("totalProductNumber"));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	}




