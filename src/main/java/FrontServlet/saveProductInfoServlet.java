package FrontServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/saveProductInfoServlet")
public class saveProductInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public saveProductInfoServlet() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		   /*--------------------------------------------------------------
			* Author 		: pdg
			* Date 			: 2024.02.08
			* ---------------------------Update---------------------------		
			*
			--------------------------------------------------------------*/
				
			System.out.println(">> saveproductinfoServlet을 실행");
		 	String product_code = request.getParameter("productCode");
		 	String product_name = request.getParameter("productName");
	        String price 		= request.getParameter("price");
	        String origin 		= request.getParameter("origin");
	        String size 		= request.getParameter("size");
	        String weight		= request.getParameter("weight");	
	        
	        // 세션을 가져옴. 세션이 없으면 새로 생성함
	        HttpSession session = request.getSession(true);
	        
	        // 세션에 상품 정보를 저장
	        session.setAttribute("product_code", product_code);
	        session.setAttribute("product_name", product_name);
	        session.setAttribute("price"	, price);
	        session.setAttribute("origin"	, origin);
	        session.setAttribute("size"		, size);
	        session.setAttribute("weight"	, weight);

	        // 클라이언트에게 성공적인 응답을 반환
	        response.setContentType("text/plain");
	        response.setCharacterEncoding("UTF-8");
	        response.getWriter().write("상품 정보가 세션에 저장되었습니다.");
	}

}
