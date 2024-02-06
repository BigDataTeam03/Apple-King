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
		// TODO Auto-generated method stub
		actionDo(request, response);// actionDo method로 보냄
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
		 *-------------------------------------- 
		 */
		request.setCharacterEncoding("utf-8");

		// Make Session
		HttpSession session = request.getSession();

		Command command = null;
		String viewPage = null;

		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());
		// com = "/signup.do";
		System.out.println("controlloe start");
		switch (com) {

		// Login Page
		case ("/loginStart.do"): // this just shows the log in page.
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
		case ("/signupStart.do"): // go to signup page.
			viewPage = "signup_view.jsp";
			break;
		// the process of making an account.
		case ("/signup.do"):
//				System.out.println(session.getAttribute("ID"));
			System.out.println(request.getParameter("id"));
			System.out.println("signup do 를 실행합니다. ");
			command = new SignupCommand();
			command.execute(request, response);
			response.sendRedirect("loginStart.do");
			break;

		case ("/checkid.do"): // checking for duplicates when signing up
			command = new IdCheckCommand();
			command.execute(request, response);
			viewPage = "signup_view.jsp";
			break;

		case ("/aProductInsert.do"):
			System.out.println("INsert do 를 실행합니다. ");
			viewPage = "aProductInsert.jsp";
			break;
		//////////// Product Part///////////////
		case ("/productDetail.do"): 
			System.out.println("상세페이지를 실행합니다");
			command = new productDetailCommand();
			command.execute(request, response);
			viewPage = "productDetail.jsp";
			break;
	
	
		case("/aProductListUpdate.do"):
			viewPage ="aProductListUpdate.jsp";
			break;
		case("/aCustomerListUpdate.do"):
			viewPage ="aCustomerListUpdate.jsp";
			break;
		case("/aCustomerOrderList.do"):
			viewPage ="aCustomerOrderList.jsp";
			break;
		case("/aGoHome.do"):
			viewPage ="aGoHome.jsp";
			break;

			
		
	
		}
		if (viewPage != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		}

	}
}
