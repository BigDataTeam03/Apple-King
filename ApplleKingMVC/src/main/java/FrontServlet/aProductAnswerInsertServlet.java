package FrontServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javalec.util.ShareVar;

/**
 * Servlet implementation class aProductAnswerInsertServlet
 */
@WebServlet("/aProductAnswerInsertServlet")
public class aProductAnswerInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public aProductAnswerInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		--------------------------------------------------------------
		* Description 	: 상품 문의에 관리자가 답변을 다는 기능
		* Author 		: KBS
		* Date 			: 2024.02.20
		* ---------------------------Update---------------------------		
		 	<<2024.02.20>> by KBS 
			1. 문의 테이블에 답변 컬럼을 업데이트 해준다
			
		*
		--------------------------------------------------------------
		*/
		HttpSession session = request.getSession();
		
		//js 에서 받은 값을 변수로 지정한다
		String answer = request.getParameter("answer");
		String inquire_code = request.getParameter("code");
		//변수중에 한글이 포함됨으로 인코딩설정을 한다
		response.setContentType("text/html;charset=UTF-8");
		
		 //? 를 사용해서 쿼리문을 사용하기 위해 프리페어를 사용한다
		PreparedStatement ps = null;
		try {
			//js는 서버를 모르기 때문에 데이터베이스 커넥션을 지정해줘야한다
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(ShareVar.url_mysql, ShareVar.id_mysql,ShareVar.pw_mysql);
	
			//업데이트를 위한 쿼리문을 작성
			String query ="update inquire set answer_content=? where inquire_code=?";
			
			//쿼리문을 실행할 변수를 지정한다
			ps = conn_mysql.prepareStatement(query);
			
			//지정해준 변수에 입력된 값을 넣는다
			ps.setString(1, answer);
			ps.setString(2, inquire_code);
					
			System.out.println("수정된 디비에 들어가는 쿼리" +ps.toString());
			//입력된 값을 데이터베이스에 업데이트를 실행한다
			ps.executeUpdate();

		}catch(Exception e) {
			e.printStackTrace();
	
		}finally {
			try {
				if (ps != null) {
	                ps.close();
	            }
			} catch (Exception e2) {
					e2.printStackTrace();
			}
		}

		
	}

}
