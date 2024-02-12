package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import dto.MemberDto;

public class Login_Dao {
	/*--------------------------------------
	 * Description : Login Dai <<customer >>
	 * Author 	   : pdg
	 * Date 	   : 2024.02.11
	 * Details		
	 * Update------------------------------- 
	 * <2024.02.11> by PDG
	 *-------------------------------------- 
	 */
	
	
	// Field
	DataSource dataSource;
	// Constructor
	public Login_Dao() {
		// Connection pool 을 사용하여 DB 연결함. 
		try{
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/apple_store"); 	
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// Method
	//checkID that is stored inside the database. 
	public MemberDto checkLogin(String id, String pw) {
		// Return value
		MemberDto dto = new MemberDto();
		
		System.out.println(">> Login_dao 실행");
		Connection conn = null;
		PreparedStatement ps = null;	
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();	// this makes connection to the db.  
			String select = "SELECT cust_id, name, cust_rank FROM customer ";
			String where = " WHERE cust_id = '" + id +"' AND cust_pw = '" + pw +"'";
			ps = conn.prepareStatement(select+where);
			System.out.println(">> CheckLogin Query : "+ps.toString().split(":")[1]);
			rs = ps.executeQuery();
			if(rs.next()) {
				System.out.println(">> DBcheck :존재하는 사용자입니다");
				dto.setCust_id(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setCust_rank(rs.getInt(3));
			}else {
				System.out.println(">> DBcheck :존재하지 않는 사용자 입니다.");
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {	
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}return dto;
	}//checkID()
	
	

}
