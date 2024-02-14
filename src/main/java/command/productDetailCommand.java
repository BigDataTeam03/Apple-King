package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Detail_Dao;
import dto.productDto;

public class productDetailCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		System.out.println(">>productDetail command 실행");
		// product_name 값을 가져와서 String값으로 변환
//				if(session.getAttribute("product_name").toString()==null) {
//					System.out.println("product name 이 널입니다. ");
//				}
//		
		
				// product_name을 세션에서 가져옵니다.
		 		String productName = (String) session.getAttribute("product_name");
				//String product_name = session.getAttribute("product_name").toString();
				//System.out.println(product_name + "입니다");
				String price 		= session.getAttribute("price").toString();
				String origin 		= session.getAttribute("origin").toString();
				//String rating 		= session.getAttribute("rating").toString();
				String size 		= session.getAttribute("size").toString();
				String weight 		= session.getAttribute("weight").toString();
				
				
				
				
				
				System.out.println("product 코드 세션 값: " + session.getAttribute("product_code"));
				
				
				
				System.out.println("------------------------");			
				//System.out.println(product_name);
				System.out.println(origin);
				System.out.println(price);
				String rating ="왓";
				//System.out.println(rating);
				System.out.println(size);
				System.out.println(weight);
				System.out.println("------------------------");
				
				// dao와 dto 준비
//				String product_name = "";
				Detail_Dao dao = new Detail_Dao();
				System.out.println("상세페이지" + productName);
				productDto dto = dao.Detail(productName);
			
				request.setAttribute("Detail", dto);
	
				
				// session에 dto값을 저장
				session.setAttribute("detailSession", dto);
	
		          



				
				
			
	}

}
