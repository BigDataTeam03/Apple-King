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
import command.myInfoCommand;
import command.mypageCommand;
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
		 * 	    Update 2024.02.12 by diana 
		 * 			1. mypage 이동 기능 추가
		 * 	 	Update 2024.02.12 by pdg
		 * 			1. 회원 가입 버튼 눌러서 회원가입 페이지로 이동 
		 * 		Update 2024.02.12 by pdg
		 * 			1. switch 문을 간단하게 하기 위하여 각 command 에 해당하는 method 를 만들어 실행하도록 바
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
		
		//-------------- [ USER Part (MVC)] ----------------------------
			
		// SignUp do
		case ("/signup.do"):
			command = new SignupCommand();
			executeCommand(com,command,request,response,"/loginStart.jsp");
			break;
		
		// Login Process Page
		case("/loginProcess.do"):
			command = new LoginCommand();
			loginProcess(com,command,request,response,session);
			break;
			
		//My Page (구매내역, 회원정보, 등급)
		case ("/myPage.do"): 
			command = new mypageCommand();
			executeCommand(com,command,request,response,"/myPage.jsp");
			break;
					
		//My Info (회원정보 수정, 회원탈퇴) 	
		case ("/myInfo.do"): 
			command = new myInfoCommand();
			executeCommand(com,command,request,response,"/myInfo.jsp");
			break;
			
		// Product Detail page
		case ("/productDetail.do"): 
			command = new productDetailCommand();
			executeCommand(com,command,request,response,"uProductDetail.jsp");
			break;
			
		//Cart Page
		case ("/cartInsert.do"): 
			command = new cartCommand();
			executeCommand(com,command,request,response,"uCartList.jsp");
			break;
		//-------------- [Administrator (MVC)] ----
		
		// Product insert process (image MVC)
		case("/aProductInsertProcess.do"):
			command = new aProductInsertCommand();
			executeCommand(com,command,request,response,"aProductListUpdate.jsp");
			break;
			
		//-------------- [ AJAX Part or Just ViewPage] ------------
		// Go home of user (AJAX)
		case("/cGoHome.do"):  			justGoPage(com,"uProductList.jsp", 	 	request, response);break;	
		
		// log out do
		case ("/logout.do"):			justGoPage(com,"logout.jsp", 	 		request, response);break;
		
		// Login Page
		case ("/loginStart.do"):		justGoPage(com,"login_view.jsp", 	    request, response);break;
		
		// ID cookie save jsp process
		case ("/IdSaveProcess.do"):		justGoPage(com,"IdSaveProcess.jsp", 	request, response);break;
		
		// Sign Up page 
		case ("/signUpStart.do"): 		justGoPage(com,"/USER/signup_view.jsp", request, response);break;
		
		// Go home of admin
		case("/aGoHome.do"):			justGoPage(com,"aCustomerList.jsp", 	request, response);break;	
		
		// Product insert  (AJAX)
		case("/aProductInsert.do"):		justGoPage(com,"aProductInsert.jsp", 	request, response);break;
		
		// product list update (AJAX)
		case("/aProductListUpdate.do"): justGoPage(com,"aProductListUpdate.jsp",request, response);break;
		
		// customer list  (AJAX)
		case("/aCustomerList.do"):		justGoPage(com,"aCustomerList.jsp", 	request, response);break;
		
		// order list  (AJAX)
		case("/aCustomerOrderList.do"):	justGoPage(com,"aCustomerOrderList.jsp",request, response);break;	
		
		
		default:break;
		}
	}
	private void loginProcess(String com,
							  Command command,
							  HttpServletRequest request, 
							  HttpServletResponse response,
							  HttpSession session)
							  throws ServletException, IOException {
		command.execute(request, response);
		System.out.println(">> " + com + "실행 ");
		String loginID =  session.getAttribute("loginId").toString();
		System.out.println(">> loginID session 값 : "+loginID);
		if (loginID != null) {	
			if (loginID.equals("admin")) {
				justGoPage(com,"aGoHome.jsp",request, response);
			} else { 
				justGoPage(com,"cGohome.jsp",request, response);
			}
		} else { // if the login fails, then go back to login page.
			justGoPage(com,"login_view.jsp",request, response);
		}
	}
	
	// just go some page
	private void justGoPage(String com,
							String viewPage,
							HttpServletRequest request, 
							HttpServletResponse response)
						    throws ServletException, IOException {
		System.out.println(">> " + com + "실행 ");
		// Controller viewPage forward
		if (viewPage != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
			System.out.println(viewPage + " 로 이동합니다.");
		}else {
			System.out.println("view page 가  "+ viewPage +" 입니다. ");
		}
	}
	// command execute and go to page
	private void executeCommand(String com,
								Command command,
								HttpServletRequest request,
								HttpServletResponse response, 
								String viewPage)
								throws ServletException, IOException  {
		System.out.println(">> " + com + "실행 ");
		command.execute(request, response);
		// Controller viewPage forward
		if (viewPage != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
			System.out.println(viewPage + " 로 이동합니다.");
		}else {
			System.out.println("view page 가  "+ viewPage +" 입니다. ");
		}

	}
} //END
