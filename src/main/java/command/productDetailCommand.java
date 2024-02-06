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

		// product_name 값을 가져와서 String값으로 변환
				String product_name = request.getParameter("product_name");
				String price 		= request.getParameter("price");
				String origin 		= request.getParameter("origin");
				String rating 		= request.getParameter("rating");
				String size 		= request.getParameter("size");
				String weight 		= request.getParameter("weight");
				
				
	     System.out.println("------------------------");			
				System.out.println(product_name);
				System.out.println(origin);
				System.out.println(price);
				System.out.println(rating);
				System.out.println(size);
				System.out.println(weight);
				
				// dao와 dto 준비
				Detail_Dao dao = new Detail_Dao();
				System.out.println("상세페이지" + product_name);
				productDto dto = dao.Detail(product_name,price,origin,rating,size,weight);
			
				request.setAttribute("Detail", dto);
				HttpSession session = request.getSession();
				
				// session에 dto값을 저장
				session.setAttribute("detailSession", dto);
				
				



				
				
			
	}

}
