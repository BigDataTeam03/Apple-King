package FrontServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.javalec.util.ShareVar;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dto.productDto;

/**
 * Servlet implementation class aProductList2
 */
@WebServlet("/aProductListServlet")
public class aProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public aProductListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		--------------------------------------------------------------
		* Description 	: 상품리스트를 출력하고 상품이름을 검색한다
		* Author 		: KBS
		* Date 			: 2024.02.06
		* ---------------------------Update---------------------------		
		 	<<2024.02.06>> by KBS
			1. 리스트 출력 기능 추가
			2. 주석 설명 추가 
			3. 테이블 정렬기능을 쿼리문을 나누어서 시행
		*
		--------------------------------------------------------------
		 	<<2024.02.07>> by KBS
			1.사용자가 선택한 조건에 맞는 정렬기능 추가
		*
		*/
		
		System.out.println("aProductListServlet 을 실행합니다.");
		response.setContentType("text/html;charset=UTF-8");  
		HttpSession session = request.getSession();

		String product_name = "";
			if (request.getParameter("name") == null) {
				product_name = "";					
			}else {
			    product_name = request.getParameter("name") ;
			}

		//상품 총 갯수를 나타내기 위한 변수지정
		int totalProductNumber =0;
		
		// ArrayList 에 담겨 있는 데이터를 JSON 으로 변경하여 송부
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		// AJAX 에서 가져온 값을 변수에 받아온다	
		// 라디오 버튼으로 선택한 상세 검색 값
		String origin = request.getParameter("origin");
		String size = request.getParameter("size");
		String kind = request.getParameter("kind");
		String selected = "";
		
		// 데이터 값 확인 
		System.out.println(">> 원산지 :" + origin);
		
		// 콤보박스로 선택된 정렬값
		String sorting = request.getParameter("sorting");
		
		//기본값은 재고순으로
		if (sorting == null) {
			sorting = "stokHigh";
		}
		//쿼리문 기본값 		
		String orderby = "";
		
		//선택한 콤보박스값에 따라 정렬쿼리문 변경
		//재고순
		 if (sorting.equals("stokHigh")) 
			 	orderby = " order by product_qty desc";
	     if (sorting.equals("stokLow"))
	    	 orderby = " order by product_qty asc";
	    	 
	    //생산일자순	 
	     if (sorting.equals("makeHigh")) 
			 	orderby = " order by manufacture_date desc";
	     if (sorting.equals("makeLow"))
	    	 orderby = " order by manufacture_date asc";
	    	 
	    //무게순	 
	     if (sorting.equals("weightHigh")) 
			 	orderby = " order by weight desc";
	     if (sorting.equals("weightLow"))
	    	 orderby = " order by weight asc";
	    	 
	    //조회수순	 
	     if (sorting.equals("viewHigh")) 
			 	orderby = " order by view_count desc";
	     if (sorting.equals("viewLow"))
	    	 orderby = " order by view_count asc";
	    	 
	    //등록일순	 
	     if (sorting.equals("insertHigh")) 
			 	orderby = " order by product_reg_date desc";
	     if (sorting.equals("insertLow"))
	    	 orderby = " order by product_reg_date asc";
	    	 
	    //가격순	 
	     if (sorting.equals("priceHigh")) 
			 	orderby = " order by price desc";
	     if (sorting.equals("priceLow"))
	    	 	orderby = " order by price asc";
	     
	    
	     // 선택된 라디오 버튼 값에 따라 쿼리 조건 설정
//	     if (origin != null && !origin.isEmpty()) {
//	         selected += " origin = '" + origin + "' and ";
//	     }
//	     if (size != null && !size.isEmpty()) {
//	         selected += " size = '" + size + "' and ";
//	     }
//	     if (kind != null && !kind.isEmpty()) {
//	         selected += " kind = '" + kind + "' and ";
//	     }

	     // and 로 연결된 조건들 중 마지막 and 제거
//	     if (!selected.isEmpty() && selected.length() > 5) {
//	         selected = selected.substring(0, selected.length() - 5);
//	     }
			
	     
	     if (origin != null && !origin.isEmpty()) {
	         selected += " and origin = '" + origin +"'";
	     }
	     if (size != null && !size.isEmpty()) {
	         selected += " and size = '" + size + "'";
	     }
	     if (kind != null && !kind.isEmpty()) {
	         selected += " and kind = '" + kind + "'";
	     }
	     
	     
		
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
				+ " from product where product_name like '%"+ product_name + "%'" + selected + orderby;
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



