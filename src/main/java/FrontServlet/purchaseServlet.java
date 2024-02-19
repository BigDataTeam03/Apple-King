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

import dto.PurchaseDto;


@WebServlet("/purchaseServlet")
public class purchaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public purchaseServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("purchaseServlet 을 실행합니다.");
		response.setContentType("application/html;charset=UTF-8");  
		HttpSession session = request.getSession();
	
		//실제는 세션으로 받아서 이걸 사용한다
		//	String cust_id = (String) session.getAttribute("cust_id");
			//테스트용 값
			String cust_id = (String)session.getAttribute("userId");
			//String	cust_id	= request.getParameter("cust_id");		
			System.out.println("고객 아이디" + cust_id);
	
			// ArrayList 에 담겨 있는 데이터를 JSON 으로 변경하여 송부
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
				
			
			ArrayList<PurchaseDto> PurchaseDtos = new ArrayList<>();
			
			//구매 테이블은 구매 아이디, 고객 아이디, 상품 아이디, 사용된 포인트만 존재한다
			//  아이디값은 로그인 한 아디를 세션에 넣었으니 그 세션값으로 가져와서 그 아이디에 해당하느 카트값만 가져온다
			String readQuery =
					//구매 테이블에 존재하는 컬럼
					"SELECT `order`.cust_id, `order`.product_code, `order`.order_code, payment_method, used_point, "
					//customer에서 가져오는 정보
					+ "customer.name, customer.tel, customer.email "
					//선택한 테이블
					+ "FROM `order` "
					// 선택한 상품 을 조회하기 위한 조인문
					+ "JOIN product ON `order`.product_code = product.product_code "
					// 고객 을 조회하기 위한 조인문
					+ "JOIN customer ON `order`.cust_id = customer.cust_id "
					// 로그인 한 아이디의 카트를 가져온다
					+ "WHERE cust_id= '" + cust_id + "'";

			
			
			System.out.println("query 실행  내용 :"+ readQuery);
			
			//Gson 사용을 위한 out선언
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
					
					// cartDto 선언
					PurchaseDto PurchaseDto = new PurchaseDto();
					//값 추가
//					PurchaseDto.setOrder_code(rs.getString("order_code"));
//					PurchaseDto.setCust_id(rs.getString("cust_id"));
//					PurchaseDto.setProduct_code(rs.getString("product_code"));
//					PurchaseDto.setPayment_method(rs.getString("payment_method"));
//					PurchaseDto.setUsed_method(rs.getInt("used_method"));
//					PurchaseDto.setPrice(rs.getInt("price"));
					PurchaseDto.setName(rs.getString("name"));
					PurchaseDto.setEmail(rs.getString("email"));
					PurchaseDto.setTel(rs.getString("tel"));
//					PurchaseDto.setCust_point(rs.getInt("cust_point"));
					//검색된 내용을 cartDto 에 추가
					PurchaseDtos.add(PurchaseDto);
//					cartTot++;
					
				}
				System.out.println("Json전");
				// Json 타입으로 변환하기 위한 Gson 선언
				out.print(new Gson().toJson(PurchaseDtos));
				
				//Json 이 뭔지 한번 출력해봅시다. 
//					System.out.println(new Gson().toJson(cartDtoList));
				out.flush();
			
					System.out.println("쿼리문 정상 작동!");
				//입력시 코드를 생성하기위한 세션
//				session.setAttribute("cartTot", cartTot );
				//System.out.println("총 상품 갯수" + session.getAttribute("totalProductNumber"));
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
			
		

	}
	



