package FrontServlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/checkedProductsServlet")
public class checkedProductsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public checkedProductsServlet() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		--------------------------------------------------------------
		* Description 	: 상품 디테일에서 장바구니담기 버튼 누를 때 세션 정보 저장위한 서블
		* 		Detail  : 장바구니에서 페이지를 새로고침했을때 update 가 되는 버그 해결을 위해 세션값 활용
		* Author 		: Diana & PDG
		* Date 			: 2024.02.16
		* ---------------------------Update---------------------------		
		 	<<2024.02.05>> by KBS & PDG
			1.사용자가 입력한 정보를 가져와서 업데이트 실행
		*
		--------------------------------------------------------------
		*/
		
		String productName = request.getParameter("productName");
		boolean productSelectedCheck =  Boolean.parseBoolean(request.getParameter("productSelectedCheck"));
			
		Map<String, Boolean> productChkMap = new HashMap<String, Boolean>(); 
		productChkMap.put(productName, true);
		
		HttpSession session = request.getSession();
		session.setAttribute ("productChkMap", productChkMap);
		
		
	
	}

}
