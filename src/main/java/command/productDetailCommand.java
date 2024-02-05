package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Detail_Dao;
import dto.productDto;

public class productDetailCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		// product_name 값을 가져와서 String값으로 변환
				String product_name = request.getParameter("product_name");
				// dao와 dto 준비
				Detail_Dao dao = new Detail_Dao();
				productDto dto = dao.Detail(product_name);
				System.out.println("상세페이지" + product_name);
				request.setAttribute("Detail", dto);
				
				// session에 dto값을 저장
				//session.setAttribute("detailSession", dto);
				
				



				
				
			
	}

}
