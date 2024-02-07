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
import com.javaproject.util.ShareVar;

import dto.productDto;

/**
 * Servlet implementation class uProductListServlet
 */
@WebServlet("/uProductListServlet")
public class uProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public uProductListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("uProductListServlet 을 실행합니다.");
		
		response.setContentType("text/html;charset=UTF-8");  
		HttpSession session = request.getSession();

		String product_name = "";
			if (request.getParameter("name") == null) {
				product_name = "";					
			}else {
			    product_name = request.getParameter("name") ;
			}
		
		// ArrayList 에 담겨 있는 데이터를 JSON 으로 변경하여 송부
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		

		// uProductList에서 productList로 넘어감 
		ArrayList<productDto> productdtoList = new ArrayList<productDto>();
		
		//product 테이블에 있는 모든 컬럼을 불러오는 쿼리문
		String select = "SELECT product_name, price, origin, size, weight, product_image_names FROM product";
	
		PrintWriter out = response.getWriter();
		
		// 상품 총수량 정보 
		
		try {
			// SQL 연결
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(ShareVar.url_mysql, ShareVar.id_mysql,ShareVar.pw_mysql);
			
			//select 쿼리문을 사욜하니 Statement 를 사용
			Statement  stmt_mysql =conn_mysql.createStatement();
			
			//결과를 담는 변수 설정
			ResultSet rs = stmt_mysql.executeQuery(select);
			while(rs.next()) {
				
			// productDto 선언
			productDto productdto = new productDto();
			
			productdto.setProduct_name		 	(rs.getString("product_name")); 	  // 1
			productdto.setPrice		 			(rs.getInt("price")); 				  // 2
			productdto.setOrigin	 			(rs.getString("origin")); 			  // 3
			productdto.setSize	 				(rs.getString("size")); 			  // 4
			productdto.setWeight	 			(rs.getInt("weight")); 				  // 5
			productdto.setProduct_image_names	(rs.getString("product_image_names"));// 6 
			
			
			//검색된 내용을 productDto 에 추가
			productdtoList.add(productdto);
				
			}
			System.out.println("Json전");
			// Json 타입으로 변환하기 위한 Gson 선언
			out.print(new Gson().toJson(productdtoList));
			
			//Json 이 뭔지 한번 출력해봅시다. 

			out.flush();
			// 어레이리스트 하나에 에스 코드 에스 디티로 싸여있겟지 이것이 대괄호에애스코드1 번 최문국 등으로 
			// 키벨류값으로 리스트가 만들어진다. 그게 제이슨이다. 
			System.out.println("실행 완료");
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	}

