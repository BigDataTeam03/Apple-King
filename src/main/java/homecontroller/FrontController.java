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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);// actionDo method로 보냄 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);
	}
	
	
	
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
	
		// Make Session
		HttpSession session =request.getSession();
		Command command = null; 
		String viewPage = null;
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());
		System.out.println("controller start");
		switch(com) {
		
		//Login Page
		case("/loginStart.do"):						
			viewPage = "login_view.jsp";
			break;	
				
		//Login Action 	
		case("/login.do"):
			System.out.println("logindo 실행s");
			command = new LoginCommand();
			command.execute(request, response);
			
			String loginID = (String) session.getAttribute("loginID");
			System.out.println("세션에 저장되어있는 ID :"+ loginID );
			if (loginID != null) {
				if (loginID.equals("admin123")) {
					viewPage = "adminMain.jsp";
				} else {
					viewPage = "main.jsp";
				}
			} else {
				viewPage = "login_view.jsp";
			}
			break;
		case ("/signupStart.do"):				
				viewPage = "signup_view.jsp";
				break;
				
		case ("/signup.do"):

				System.out.println(request.getParameter("id"));
				System.out.println("signup do 를 실행합니다. ");
				command = new SignupCommand();
				command.execute(request, response);
				response.sendRedirect("loginStart.do");
				break;
		
		case ("/checkid.do"):					
				command = new IdCheckCommand();
				command.execute(request, response);
				viewPage = "signup_view.jsp";
				break;
			
			
		
		default:
			break;
		}
		if(viewPage != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		}
	
	}
}
