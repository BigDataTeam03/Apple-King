package command;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalTime;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javalec.util.ShareVar;
import com.javaproject.util.ParameterPrintOut;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class aProductInsertCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		/*
		 * -------------------------------------------------------------- Description :
		 * Administrator product image (MVC version) Author : PDG & KBS Date :
		 * 2024.02.07 ---------------------------Update---------------------------
		 * <<2024.02.05>> by PDG &KBS 1. 이미지 인서트 기능 추가
		 *
		 * --------------------------------------------------------------
		 */
		System.out.println(" Product INsert Command  실행...");
		try {
			ServletContext context = request.getServletContext();
			String realfolder = context.getRealPath("/image");
			System.out.println("image 가 들어갈 경로" + realfolder);
			int sizeLimit = 100 * 1024 * 1024; // 100MB 제한
			MultipartRequest multi = new MultipartRequest(request, // request
					realfolder, // image 가 저장될 application folder
					sizeLimit, // image size limit
					"UTF-8", // image file name utf-8
					new DefaultFileRenamePolicy()); // 중복시 (1). ...
			System.out.println("--getParameterNames");
//			Enumeration enumeration02 = multi.getParameterNames();
//			while (enumeration02.hasMoreElements()) {
//				String element = enumeration02.nextElement() + ":";
//				System.out.println(multi.getParameter(element));
//
//			}
			// js 에서 받은 값을 변수로 지정한다
			String code 		= multi.getParameter("product_code");
			String name 		= multi.getParameter("product_name");
			String qty 			= multi.getParameter("product_qty");
			String origin 		= multi.getParameter("origin");
			String manufacture 	= (multi.getParameter("manufacture_date")!= null) ? multi.getParameter("manufacture"): "2024-02-02";
			String weight 		= multi.getParameter("weight");
			String size 		= multi.getParameter("size");
			String detailImage 	= multi.getParameter("detail_image_name");
			String view 		= (multi.getParameter("view")!= null) ? multi.getParameter("view"): "0";
			String regDate 		= (multi.getParameter("product_reg_date")!= null) ? multi.getParameter("regDate"): "now()";
			String kind 		= multi.getParameter("kind");
			String productImage = (multi.getParameter("productImage")!= null) ? multi.getParameter("productImage"): "null.png";
			String price 		= multi.getParameter("price");

			// 변수중에 한글이 포함됨으로 인코딩설정을 한다
			response.setContentType("text/html;charset=UTF-8");

			System.out.println(" price : " + price);
			// parameter 출력 확인
			String[] parms = { code, name, qty, origin, manufacture, weight, size, detailImage, view, regDate, kind,
					productImage, price };
			ParameterPrintOut parmOut = new ParameterPrintOut();
			parmOut.pp("js 에서 받는 변수 : ", parms);
			PreparedStatement ps = null;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn_mysql = DriverManager.getConnection(ShareVar.url_mysql, ShareVar.id_mysql,
						ShareVar.pw_mysql);
				String query = "insert into product " // 1
						+ "(product_code,product_name," // 2
						+ "product_qty," // 3
						+ "origin," // 4
						+ "manufacture_date," // 5 -> null
						+ "weight," // 6
						+ "size," // 7
						+ "detail_image_name," // 8
						+ "view_count," // 9
						+ "product_reg_date," // 10
						+ "kind," // 11
						+ "product_image_names," // 12
						+ "price)" // 13
						// 1 2 3 4 5 6 7 8 9 10 11 12 13
						+ "values(?,?,?,?,?,?,?,?,?,now(), ?, ?, ?)";

				// 쿼리문을 실행할 변수를 지정한다
				ps = conn_mysql.prepareStatement(query);
				// 지정해준 변수에 입력된 값을 넣는다
				ps.setString(1, code);
				ps.setString(2, name);
				ps.setString(3, qty);
				ps.setString(4, origin);
				ps.setString(5, manufacture);
				ps.setString(6, weight);
				ps.setString(7, size);
				ps.setString(8, detailImage);
				ps.setString(9, view);
				// now 가 있던자리...
				ps.setString(10, kind);
				ps.setString(11, productImage);
				ps.setString(12, price);
				System.out.println("입력된 디비에 들어가는 쿼리" + ps.toString());
				// 입력된 값을 데이터베이스에 업데이트를 실행한다
				ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();

			}

		} catch (Exception e) {
			// 예외 처리 코드 작성
			e.printStackTrace(); // 예외 내용을 출력
		}

	}
}
