package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {

	/*--------------------------------------
	 * Description:
	 * Author :
	 * Date :
	 * Warning :
	 * Update :
	 * 		Update 2024.02.02 by PDG, KBS
	 * 			1. 
	 * 			2.
	 *-------------------------------------- 
	 */
	
	public void execute(HttpServletRequest request, HttpServletResponse response);
	
	
	
	
}
