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
@WebServlet("/uProductListServlet")
public class uProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public uProductListServlet() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		--------------------------------------------------------------
		* Description 	: 상품 목록 servlet 
		* 		Detail  :   
		* 					1. 상품을DB 에서 검색함 uProduct.jsㅅ에서 요청됨 ,   
		* 					2. 검색 단어는uProductList.jsp 에서 변수가져옴
		* 					3. 상품 정렬 기능 
		* Author 		: DK, pdg
		* Date 			: 2024.02.13
		* ---------------------------Update---------------------------		
		*<<2024.02.13>> by pdg,dk
		*			1.상품 정렬기능 수정
		*			2. 
		--------------------------------------------------------------
		*/
		System.out.println(">> uProductListServlet 을 실행");
		response.setContentType("text/html;charset=UTF-8");  
		HttpSession session = request.getSession();
			
		// 정렬 옵션 가져오는 변수 -> 높은 가격순, 낮은가격순. 
		String classifyOption = request.getParameter("classifyOption");
		
		// 시작시 classifyOption 이 선택되지 않았으니 디폴트값으로 highPrice 지정
		if (classifyOption == null) {
			classifyOption = "highprice";
		}
		// 쿼리문 기본값 highPrice
		String orderby = "highprice";
		
		//선택한 옵션값에 따라 정렬쿼리문 변경
		 if (classifyOption.equals("highprice")) 
			 	orderby = " order by price desc";
	     if (classifyOption.equals("lowprice"))
	    	 	orderby = " order by price asc";
	
		String product_name = "";
		
			if (request.getParameter("name") == null) {
				product_name = "";					
			}else {
			    product_name = request.getParameter("name") ;
			}
		
		// ArrayList 에 담겨 있는 데이터를 JSON 으로 변경하여 송부
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		ArrayList<productDto> productdtoList = new ArrayList<productDto>();
		String select =   "SELECT"
						+ " product_code,"  	//1
						+ " product_name,"  	//2
						+ " price, origin,"  	//3
						+ " size,"  			//4
						+ " weight,"  			//5
						+ " product_image_names,"//6
						+ " detail_image_name"//7 ,detailimagenames 추가
						+ " FROM product" + orderby; 
		PrintWriter out = response.getWriter();
		try {
			// SQL 연결
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(ShareVar.url_mysql,
																ShareVar.id_mysql,
																ShareVar.pw_mysql);
			Statement  stmt_mysql =conn_mysql.createStatement();
			ResultSet rs = stmt_mysql.executeQuery(select);
			while(rs.next()) {
				productDto productdto = new productDto();
				productdto.setProduct_code		 	(rs.getString("product_code")); 	  // 1
				productdto.setProduct_name		 	(rs.getString("product_name")); 	  // 2
				productdto.setPrice		 			(rs.getInt("price")); 				  // 3
				productdto.setOrigin	 			(rs.getString("origin")); 			  // 4
				productdto.setSize	 				(rs.getString("size")); 			  // 5
				productdto.setWeight	 			(rs.getInt("weight")); 				  // 6
				productdto.setProduct_image_names	(rs.getString("product_image_names"));// 7 
				productdto.setDetail_image_name		(rs.getString("detail_image_name"));// 8, detailimagenames 추가
				productdtoList.add(productdto);
			}
			System.out.println("Json전");
			System.out.println("-------");
			out.print(new Gson().toJson(productdtoList));
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}// END
