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
 * Servlet implementation class aProductFindServlet
 */
@WebServlet("/aProductFindServlet")
public class aProductFindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public aProductFindServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		--------------------------------------------------------------
		* Description 	: 상세검색 기능
		* Author 		: KBS
		* Date 			: 2024.02.06
		* ---------------------------Update---------------------------		
		 	<<2024.02.07>> by KBS
			1.상세 검색 요소들을 가져와서 상세검색 실행
		*
		--------------------------------------------------------------
		알림. 
		
		서블릿을 하나로 합치는 시도중....
		
		
		
		*/
		System.out.println("aProductFindServlet 을 실행합니다.");
		response.setContentType("text/html;charset=UTF-8");  
		HttpSession session = request.getSession();

		

//		//상품 총 갯수를 나타내기 위한 변수지정
		int totalSearchNumber =0;
		// ArrayList 에 담겨 있는 데이터를 JSON 으로 변경하여 송부
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		//out 을 사용하기위해 out 선언		
//		String sorting = request.getParameter("sorting");
//		String name = request.getParameter("name");
		String origin = request.getParameter("origin");
		String size = request.getParameter("size");
		String kind = request.getParameter("kind");
		String selected = "";
			System.out.println("오리진" + origin);
			// 선택된 라디오 버튼 값에 따라 쿼리 조건 설정
			if (origin != null && !origin.isEmpty()) {
			    selected += "origin = '" + origin + "' and ";
			}
			if (size != null && !size.isEmpty()) {
			    selected += "size = '" + size + "' and ";
			}
			if (kind != null && !kind.isEmpty()) {
			    selected += "kind = '" + kind + "' and ";
			}		
			
			    // and 로 연결된 조건들 중 마지막 and 제거
			    selected = selected.substring(0, selected.length() - 5);
			

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
				
				+ " from product where " 	+ selected;
			
	
		
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
				totalSearchNumber++;
				
			}
			System.out.println("Json전");
			// Json 타입으로 변환하기 위한 Gson 선언
			out.print(new Gson().toJson(productdtoList));
			
			out.flush();
			System.out.println("실행ㄱ된 쿼리문" +readQuery.toString());
			
			//입력시 코드를 생성하기위한 세션
			session.setAttribute("totalProductNumber", totalSearchNumber );
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
		
		
	

}
