package command;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.javalec.util.ShareVar;

import dto.customerDto;

public class aCustomerListCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {


		/*
		--------------------------------------------------------------
		* Description 	: 관리자가 보는 고객정보 리스트 출력
		* Author 		: KBS
		* Date 			: 2024.02.06
		* ---------------------------Update---------------------------		
		 	<<2024.02.06>> by KBS
			1. 리스트 출력 기능 추가
			2. 주석 설명 추가 
		*
		--------------------------------------------------------------
		*/
		
		System.out.println("aCustomerListCommand  시작.");
		
		
		response.setContentType("text/html;charset=UTF-8");  
		HttpSession session = request.getSession();
		//시작시 이름이 계속 널로표현되어 이 문장을 만들었음
		String name = "";
			if (request.getParameter("name") == null) {
			name = "";					
			}else {
			    name = request.getParameter("name") ;
			}

//		//고객 총 갯수를 나타내기 위한 변수지정
		//int totalCustomerNumber =0;
		
		// ArrayList 에 담겨 있는 데이터를 JSON 으로 변경하여 송부
		response.setContentType("application/json");
		// 가져오는 정보에 한글이 포함됨으로 인코딩 설정
		response.setCharacterEncoding("UTF-8");
		
		//out 을 사용하기위해 out 선언		
			
			// select 문에 여러가지 정보를 담아야 하니 ArrayList 를 사용해서 리스트에 담는다
		ArrayList<customerDto> customerdtoList = new ArrayList<customerDto>();
		
		//product 테이블에 있는 모든 컬럼을 불러오는 쿼리문
		String readQuery = "select " 
				+ "cust_id, "
				+ "name, "
				+ "tel, "
				+ "email, "
				+ "address, "
				+ "reg_date, "
				+ "cust_rank "				
				//   Customer 에서 name 을 검색하지만 처음에는 아무것도 안들어감으로 모두 조회함. 
				+ " from customer where name like '%"+ name + "%' and cust_id <> 'admin123'";
		System.out.println("query 실행 전 내용 :"+ readQuery);
	
		//out.flush 를 사용하기위해 out 변수 지정
		
		
		
		
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
				customerDto customerdto = new customerDto();
				customerdto.setCust_id(rs.getString("cust_id"));
				customerdto.setName(rs.getString("name"));
				customerdto.setTel(rs.getString("tel"));
				customerdto.setEmail(rs.getString("email"));
				customerdto.setReg_date(rs.getTimestamp("reg_date"));
				customerdto.setRank(rs.getInt("cust_rank"));
				
						
				//검색된 내용을 productDto 에 추가
				customerdtoList.add(customerdto);
				//고객의 총 갯수를 알수있는 변수에 ++ 
				//totalCustomerNumber++;
				
			}
			System.out.println("Json전");
			// Json 타입으로 변환하기 위한 Gson 선언
			PrintWriter out = response.getWriter();
			out.print(new Gson().toJson(customerdtoList));
			
			out.flush();
			
			
			//총 고객 숫자를 표시하기 위해 세션에 저장
//			session.setAttribute("totalProductNumber", totalCustomerNumber + 1);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
		
		
		
	
		
		
		
	

}
