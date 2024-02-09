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
		System.out.println("uProductListServlet 을 실행합니다.");
		response.setContentType("text/html;charset=UTF-8");  
		HttpSession session = request.getSession();
		
		//검색기능을 위한 이름변수 지정
			
				//정렬옵션 가져오는 변수 
				String classifyOption = request.getParameter("classifyOption");
				//시작시 선택되지 않았으니 디폴트값으로 날짜정렬
				if (classifyOption == null) {
					classifyOption = "highprice";
				}
				//쿼리문 기본값 날짜
				String orderby = "highprice";
				
				//선택한 콤보박스값에 따라 정렬쿼리문 변경
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
						+ " product_image_names"//6
						+ " FROM product" + orderby; ;
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
				productdto.setProduct_name		 	(rs.getString("product_name")); 	  // 1
				productdto.setPrice		 			(rs.getInt("price")); 				  // 2
				productdto.setOrigin	 			(rs.getString("origin")); 			  // 3
				productdto.setSize	 				(rs.getString("size")); 			  // 4
				productdto.setWeight	 			(rs.getInt("weight")); 				  // 5
				productdto.setProduct_image_names	(rs.getString("product_image_names"));// 6 
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
