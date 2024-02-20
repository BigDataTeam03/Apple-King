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
@WebServlet("/purchaseServlet")
public class purchaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public purchaseServlet() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*--------------------------------------------------------------
		* Description 	: 선택된 상품을 Order table 에 추가
		* Author 		: PDD ,DK
		* Date 			: 2024.02.19
		* ---------------------------Update---------------------------		
		--------------------------------------------------------------
		*
		*/
		response.setContentType("text/html;charset=UTF-8");
		
		HttpSession session = request.getSession();
		String cust_id = (String)session.getAttribute("userId");
		System.out.println(">> purchaseServlet 실행");
		// 장바구니에서 체크된 리스트를 받아옴. 
		String [] cartCheckList = request.getParameterValues("cartCheckList");
		System.out.println("cartCheckList : " );
		if(cartCheckList !=null) {
			for(int i=0; i< cartCheckList.length; i++) {
				System.out.println(cartCheckList[i]);
			}
		}
		PrintWriter out = response.getWriter(); 
		PreparedStatement ps = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn_mysql  = DriverManager.getConnection(ShareVar.url_mysql, ShareVar.id_mysql, ShareVar.pw_mysql);
            
            String insertQuery = "INSERT into orders (product_code, cust_id) values (?,?)";

            ps = conn_mysql.prepareStatement(insertQuery);
            
         for (  int i = 0; i < cartCheckList.length; i++) {
            	 ps.setString(1, cartCheckList[i]);
            	 ps.setString(2, cust_id); 
            	 ps.executeUpdate();
         }
        } catch (Exception e) {
            e.printStackTrace();
            out.print("failure");
        } finally {
            try {       
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }// catch end
        }// finally end
    }//do post end
}//servlet end
