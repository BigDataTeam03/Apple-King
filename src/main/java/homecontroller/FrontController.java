package homecontroller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import command.Command;
import command.IdCheckCommand;
import command.LoginCommand;

import command.SignupCommand;
import command.aProductInsert;
import command.productDetailCommand;


/**
 * Servlet implementation class FrontController
 */
@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FrontController() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		actionDo(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*--------------------------------------
		 * Description: Apple store controller 
		 * Author : PDG, KBS, LS, Diana
		 * Date : 2024.02.02
		 * Warning : 컨트롤러 수정시 슬랙에 변경(추가) 될 부분만 보내주시고 커밋은 제외 할것.
		 * Update :
		 * 		Update 2024.02.02 by PDG, KBS
		 * 			1. 주석 달음. 
		 * 			2.
		 * 		Update 2024.02.06 by pdg
		 * 			1. controller 오타 수정및 테스트코드 주석 수정. + 정리
		 * 
		 *-------------------------------------- 
		 */
		
		// Request character encoding
		request.setCharacterEncoding("utf-8");

		// Session & Query String => URI => com 
		HttpSession session = request.getSession();
		Command command = null;
		String viewPage = null;
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());
		
		//Controller Start Test code
		System.out.println(">> Controller start =>"+ com );
		
		switch (com) {
		
		//-------------- Customer Management Part (MVC) --------------
		// Login Page
		case ("/loginStart.do"):
			viewPage = "login_view.jsp";
			break;

		// Login Action
		case ("/login.do"):
			command = new LoginCommand();
			command.execute(request, response);
			String loginID = (String) session.getAttribute("loginID");
			if (loginID != null) {
				if (loginID.equals("admin")) { // if "admin" is input as ID, move to admin's product list.
					viewPage = "aProductList.do";
				} else { // if the input ID is customer's id, move to customer's product list.
					viewPage = "productList.do";
				}
			} else { // if the login fails, then go back to login page.
				viewPage = "login_view.jsp";
			}
			break;
			
		// Sign Up page 
		case ("/signupStart.do"): 
			viewPage = "/USER/signup_view.jsp";
			break;
			
		// SignUp do
		case ("/signup.do"):
			
			// test code
			System.out.println(">> Rq_parameter_id = "+request.getParameter("id"));
			System.out.println(">> " + com + "실행 ");
			command = new SignupCommand();
			command.execute(request, response);
			response.sendRedirect("loginStart.do");
			break;
			
		// ID overlap check
		case ("/checkid.do"): // checking for duplicates when signing up
			command = new IdCheckCommand();
			command.execute(request, response);
			viewPage = "signup_view.jsp";
			break;
			
		// Go home of user
		case("/cGohome.do"):
			System.out.println(">> " + com + "실행 ");
			viewPage ="uProductList.jsp";
		break;
		
		
			
		//-------------- Product Part (MVC) --------------
		// Product insert 
		case ("/aProductInsert.do"):
			
			// test code
		
			System.out.println(">> " + com + "실행 ");

			command = new aProductInsert();
			//viewPage = "/ADMIN/aProductInsert.jsp";

			viewPage = "/aProductInsert.jsp";
			System.out.println(viewPage);

			break;	
		
		// Product Detail page
		case ("/productDetail.do"): 
			
			// test code
			System.out.println(">> " + com + "실행 ");
			command = new productDetailCommand();
			command.execute(request, response);
			viewPage = "productDetail.jsp";
			break;
			
		//-------------- AJAX Part --------------
		// product list update
		case("/aProductListUpdate.do"):
			viewPage ="aProductListUpdate.jsp";
			break;
			
		// customer list 
		case("/aCustomerList.do"):		
			System.out.println("aCustmoerList.do 실행 ");
			viewPage ="aCustomerList.jsp";
			break;
			
		// order list 
		case("/aCustomerOrderList.do"):
			viewPage ="aCustomerOrderList.jsp";
			break;
			
		// Go home of admin
		case("/aGoHome.do"):
			viewPage ="aGoHome.jsp";
			break;
		}
		// Controller viewPage forward
		if (viewPage != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		}

	}
}
