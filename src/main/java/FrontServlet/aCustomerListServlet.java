package FrontServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.javalec.util.ShareVar;

import dto.MemberDto;
import dto.productDto;

/**
 * Servlet implementation class aCustomerListServlet
 */
@WebServlet("/aCustomerListServlet")
public class aCustomerListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public aCustomerListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		/*
		--------------------------------------------------------------
		* Description 	: 관리자가 보는 고객정보 리스트 출력
		* Author 		: KBS
		* Date 			: 2024.02.06
		* ---------------------------Update---------------------------		
		 	<<2024.02.06>> by KBS
			1. 리스트 출력 기능 추가
			2. 주석 설명 추가 
			3. 테이블 정렬기능을 쿼리문을 나누어서 시행
		*
		--------------------------------------------------------------
		*/
		
		System.out.println("aCustomerListServlet 시작.");
		HttpSession session =request.getSession();
		response.setContentType("text/html;charset=UTF-8");  

		//검색기능을 위한 이름변수 지정
		String name = request.getParameter("name");
		//정렬옵션 가져오는 변수 
		String sortOption = request.getParameter("sortOption");
		//시작시 선택되지 않았으니 디폴트값으로 날짜정렬
		if (sortOption == null) {
		    sortOption = "dateNew";
		}
		//쿼리문 기본값 날짜
		String orderby = "dateNew";
		
		//선택한 콤보박스값에 따라 정렬쿼리문 변경
		 if (sortOption.equals("rankHigh")) 
			 	orderby = "order by cust_rank desc";
	     if (sortOption.equals("dateNew"))
	    	 	orderby = "order by reg_date desc";
	     if (sortOption.equals("rankLow"))
	    	 	orderby = "order by cust_rank asc";
	     if (sortOption.equals("dateLate"))
	    	 	orderby = "order by reg_date asc";
	      
		  //      	String orderby = " order by reg_date desc";
	           
	        
		
//		//고객 총 갯수를 나타내기 위한 변수지정
		int totalCustomerNumber =0;
		
		// ArrayList 에 담겨 있는 데이터를 JSON 으로 변경하여 송부
		response.setContentType("application/json");
		// 가져오는 정보에 한글이 포함됨으로 인코딩 설정
		response.setCharacterEncoding("UTF-8");
		
		//out 을 사용하기위해 out 선언		
		System.out.println("js에서 가져온 name " + name);
			
			// select 문에 여러가지 정보를 담아야 하니 ArrayList 를 사용해서 리스트에 담는다
		ArrayList<MemberDto> customerdtoList = new ArrayList<MemberDto>();
		
		//product 테이블에 있는 모든 컬럼을 불러오는 쿼리문
		String Query = "select " 
				+ "cust_id, "		//1
				+ "name, "			//2
				+ "tel, "			//3
				+ "email, "			//4
				+ "address, "		//5
				+ "cust_rank, "	 	//6				
				+ "reg_date "		//7
				//   Customer 에서 name 을 검색하지만 처음에는 아무것도 안들어감으로 모두 조회함. 
				+ "from customer where name like '%" + name + "%' and cust_id <> 'admin123'" +orderby;
				//정렬에 따라 바뀐다		
		
		System.out.println("query 실행 전 내용 :"+ Query);
	
		//out.flush 를 사용하기위해 out 변수 지정
		PrintWriter out = response.getWriter();
		
		
		
		try {
			// SQL 연결
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(ShareVar.url_mysql, ShareVar.id_mysql,ShareVar.pw_mysql);
			
			//select 쿼리문을 사욜하니 Statement 를 사용
			Statement  stmt_mysql =conn_mysql.createStatement();
						
			//결과를 담는 변수 설정
			ResultSet rs = stmt_mysql.executeQuery(Query);
			while(rs.next()) {
				
				// productDto 선언
				MemberDto customerdto = new MemberDto();
				customerdto.setCust_id(rs.getString("cust_id"));
				customerdto.setName(rs.getString("name"));
				customerdto.setTel(rs.getString("tel"));
				customerdto.setEmail(rs.getString("email"));
				customerdto.setAddress(rs.getString("address"));
				customerdto.setReg_date(rs.getString("reg_date"));
				customerdto.setCust_rank(rs.getInt("cust_rank"));
									
				//검색된 내용을 productDto 에 추가
				customerdtoList.add(customerdto);
				//고객의 총 갯수를 알수있는 변수에 ++ 
				totalCustomerNumber++;
				
			//	System.out.println("쿼리문에 들어간 변수" + customerdto.getCust_rank());
			}
			
			session.setAttribute("custTot", totalCustomerNumber);
			//데이터를 json으로 담아서 보낸다
			out.print(new Gson().toJson(customerdtoList));
			
			out.flush();
			
			 
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
		
	

}
