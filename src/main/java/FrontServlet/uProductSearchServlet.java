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
    public uProductSearchServlet() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(">> uProductSearchServlet 을 실행합니다.");
		/*
		--------------------------------------------------------------
		* Description 	: 상품 목록, 검색, 정렬 서블릿. 
		* 		Detail  :   
		* 					1. 상품을DB 에서 검색함 uProduct.jsㅅ에서 요청됨 ,   
		* 					2. 검색 단어는uProductList.jsp 에서 변수가져옴
		* 					3. 상품 정렬 기능 
		* 
		*  js 에서 가져오는 파라미터 들
		*  		classifyOption,searchContent,startIndex,endIndex
		* Author 		: DK, pdg
		* Date 			: 2024.02.13
		* ---------------------------Update---------------------------		
		*<<2024.02.13>> by pdg,dk
		*			1.상품 정렬기능 수정
		*			2. 페이징을 위해서 uProduct.js 에서 startIndex, endIndex 를 가져옴
		--------------------------------------------------------------
		*/
		// Field 
		String 	classifyOption 	= request.getParameter("classifyOption");
		String 	searchContent 	= request.getParameter("searchContent");
		
		
		int pageSize =10;
		if (request.getParameter("pageSize") != null) {
			pageSize = Integer.parseInt(request.getParameter("pageSize"));	
		}
		
		int startIndex =0; 
		if(request.getParameter("startIndex") != null ) {
			startIndex = Integer.parseInt(request.getParameter("startIndex"));
		}
		
		System.out.println(">>pageSize : "+pageSize);
		System.out.println(">>startIndex : "+startIndex);
		// ArrayList 에 담겨 있는 데이터를 JSON 으로 변경하여 송부
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		//session 
		HttpSession session = request.getSession();
		
		// 시작시 classifyOption -> highPrice
		if (classifyOption == null) {
			classifyOption = "highprice";
		}
		// 쿼리문 기본값 highPrice
		String orderby = "";
		
		// 선택한 옵션값에 따라 정렬쿼리문 변경
		if (classifyOption.equals("highprice")) orderby = " order by price desc";
	    if (classifyOption.equals("lowprice" )) orderby = " order by price asc";
	    if (classifyOption.equals("product_code" )) orderby = " order by product_code asc";
	    
	    // 검색버튼 눌렀을때 검색내용 저장 
		if (request.getParameter("searchContent") == null) {
			searchContent = "";
		}else {
			searchContent = request.getParameter("searchContent") ;
		}
		
		// 상품 총 갯수를 나타내기 위한 변수지정
		int totalProductNumber =0;
		
		// 쿼리를 실행 결과 담을 객체 생성.
		ArrayList<productDto> productdtoList = new ArrayList<productDto>();
				
		String query = "select " 
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
				+ " from product2 where product_name like '%"
				+ searchContent + "%'" 
				+ orderby
				+ " limit "+ pageSize + " offset "+startIndex
				;
		System.out.println("query 실행 전 내용 :"+ query);
		PrintWriter out = response.getWriter();
		
		// 상품 총수량 정보 
		
		try {
			// SQL 연결
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(ShareVar.url_mysql, ShareVar.id_mysql,ShareVar.pw_mysql);
			
			//select 쿼리문을 사욜하니 Statement 를 사용
			Statement  stmt_mysql =conn_mysql.createStatement();
			
			//결과를 담는 변수 설정
			ResultSet rs = stmt_mysql.executeQuery(query);
			while(rs.next()) {
				
				// productDto 선언
				productDto productdto = new productDto();
				productdto.setProduct_code		 (rs.getString(	"product_code")); 		// 1
				productdto.setProduct_name		 (rs.getString(	"product_name")); 		// 2
				productdto.setProduct_qty		 (rs.getInt(	"product_qty")); 		// 3
				productdto.setOrigin			 (rs.getString( "origin")); 			// 4
				productdto.setManufacture_date	 (rs.getString(	"manufacture_date")); 	// 5 
				productdto.setWeight			 (rs.getInt(	"weight")); 				// 6
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




