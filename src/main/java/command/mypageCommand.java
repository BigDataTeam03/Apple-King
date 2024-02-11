package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class mypageCommand implements Command {
	
	/*--------------------------------------
	 * Description: MyPage Command 
	 * Author : Diana
	 * Date : 2024.02.10
	 * Update: 		
	 *-------------------------------------- 
	 */

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println(">>Initiating MyPageCommand");
		
		// Session
		HttpSession session = request.getSession();  
		
		
		
		
		
		
		
		
		

	}

}
