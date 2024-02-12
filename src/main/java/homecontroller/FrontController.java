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
import command.LoginCommand;

import command.SignupCommand;
import command.aProductInsertCommand;
import command.cartCommand;
import command.productDetailCommand;
@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public FrontController() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		actionDo(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		actionDo(request, response);
	}
	private void actionDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*--------------------------------------
		 * Description: Apple King Controller 
		 * Author : PDG, KBS, LS, Diana
		 * Date : 2024.02.02
		 * Warning : 컨트롤러 수정시 슬랙에 변경(추가) 될 부분만 보내주시고 커밋은 제외 할것.
		 * Update :
		 * 		Update 2024.02.02 by PDG, KBS
		 * 			1. 주석 달음. 
		 * 			2.
		 * 		Update 2024.02.06 by pdg
		 * 			1. controller 오타 수정및 테스트코드 주석 수정. + 정리
		 * 	 	Update 2024.02.09 by pdg
		 * 			1. cGohome -> cGoHome 으로 바꿈. 
		 * 			2. 주석을 정리함.. signinup part, user, admin + admin(AJAX) part 로 나눔. 
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
		
		//Controller Start Test Code
		System.out.println("---------------------------------------");
		System.out.println(">> *****[[Controller STARTED]]*****");
		switch (com) {
		
		//--------------[ Customer Sign In & Sign Up Part (MVC) ]--------------
		// Login Page
		case ("/loginStart.do"):
			System.out.println(">> 0-1.loginSart.do 실행");
			viewPage = "login_view.jsp";
			break;
			
				
		case ("/IdSaveProcess.do"):
			System.out.println(">> IdSaveProcess.do 실행");
			viewPage = "IdSaveProcess.jsp";
			break;
		
		// Login Process Page
		case("/loginProcess.do"):
			System.out.println(">> 0-2.loginProcess.do 실행");
			command = new LoginCommand();
			command.execute(request, response);
			String loginID =  session.getAttribute("loginId").toString();
			if (loginID != null) {	
				if (loginID.equals("admin")) {
					System.out.println(">> 관리자 페이지로 이동");
					viewPage = "aProductListUpdate.jsp";
				} else { 
					System.out.println(">> 사용자 페이지로 이동");
					viewPage = "cGohome.do";	
				}
			} else { // if the login fails, then go back to login page.
				viewPage = "login_view.jsp";
			}
			System.out.println("loginID session 값 : "+loginID);
			System.out.println(" 다음 페이지로 선택은 곳은 "+viewPage);
			//session.setAttribute("viewPage", viewPage);
			break;

		// Sign Up page 
		case ("/signupStart.do"): 
			System.out.println(">>  sign up  view 실행 ");
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
			
		// log out do
		case ("/logout.do"):
			System.out.println(">> logout 됩니다.");
			viewPage = "logout.jsp";
			break;
		
		//-------------- [ USER Part ] ----------------------------
		// Go home of user
		case("/cGoHome.do"):
			System.out.println(">> " + com + "실행 ");
			viewPage ="uProductList.jsp";
			break;
			
		// Product Detail page
		case ("/productDetail.do"): 
			System.out.println(">> " + com + "실행 ");
			command = new productDetailCommand();
			command.execute(request, response);
			viewPage = "uProductDetail.jsp";
			break;
			
		//Cart Page
		case ("/cartInsert.do"): 
			System.out.println(">> " + com + "실행 ");
			command = new cartCommand();
			command.execute(request, response);
			viewPage = "uCartList.jsp";
			break;
		//-------------- [Administrator Product Part (MVC)] ----
		
			// Product insert page from top button
		case ("/aProductInsert.do"):	
			viewPage = "/aProductInsert.jsp";
			System.out.println(viewPage);
			break;	
		
		// Product insert process (image MVC)
		case("/aProductInsertProcess.do"):
			// test code
			System.out.println(">> " + com + "실행 ");
			command = new aProductInsertCommand();
			command.execute(request,response);
			viewPage = "/aProductListUpdate.jsp";
			break;
		//-------------- [ AJAX Part(Administrator)] ------------
		
			// product list update
		case("/aProductListUpdate.do"): 
			viewPage ="aProductListUpdate.jsp";
			break;
		
		// customer list 
		case("/aCustomerList.do"):		
			viewPage ="aCustomerList.jsp";	
			System.out.println("aCustmoerList.do 실행 ");
			break;
			
		// order list 
		case("/aCustomerOrderList.do"):	
			viewPage ="aCustomerOrderList.jsp";
			break;
			
		// Go home of admin
		case("/aGoHome.do"):			
			viewPage ="aCustomerList.jsp";
			break;
		default:
			break;
		}
		//-------------- [ Switch END] -------------------------
		// Controller viewPage forward
		if (viewPage != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		}else {
			System.out.println("view page 가  "+ viewPage +" 입니다. ");
		}
	}// controller
} //END
