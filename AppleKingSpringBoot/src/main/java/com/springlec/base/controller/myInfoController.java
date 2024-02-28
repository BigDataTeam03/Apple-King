package com.springlec.base.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springlec.base.dao.MyInfoDao;
import com.springlec.base.model.MyInfoDto;
import com.springlec.base.service.MyInfoDaoService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

@Controller
public class myInfoController {

	/*--------------------------------------
	 * Description: myInfo 컨트롤러 
	 * Author :  DK 
	 * Date : 2024.02.23
	 * ----------------------------------------
	 * Update : 2024.02.23 DK
  	 *   1. MyInfoDaoService를 사용하여 사용자와 관련된 회원정보를 가져옴
     *   2. 세션에서 사용자 userId 를 가져와 해당하는 myInfo 를 검색. 
     *   3. Controller 를 하나 사용하기 위해서 myPageController 에 "service2" 추가 --> 에러걸림.짜증남.  
     * Update : 2024.02.26 DK
  	 *   1. ModifyDao/DeactivateDao 를 사용하여 회원정보수정/회원탈퇴 기능 구현. 
  	 * Update : 2024.02.28 DK
  	 * 	 1. 서비스 레이어를 통해 데이터베이스에서 해당 사용자의 비밀번호를 가져옴
	 *	 2.	클라이언트가 제공한 이전 비밀번호(oldPassword)와 데이터베이스에서 가져온 비밀번호를 비교
	 *	 3.	비밀번호가 일치하면 "match"를 반환하고, 일치하지 않으면 "not match"를 반환함.
	 *	 4. 서비스 레이어를 통해 데이터베이스에서 해당 사용자의 새로운 비밀번호를 업데이트.
	 *-------------------------------------- 
	 */

	@Autowired 
	MyInfoDaoService service;
	
	@GetMapping("/myInfo")
	public String myInfo (HttpSession session, HttpServletRequest request, Model model) throws Exception{
	//(TEMPORARY)set user's Id after th;ey have logged in.
	String userId = (String)session.getAttribute("userId");
	String userName = (String)session.getAttribute("userName");
	
	System.out.println("userId : " + userId);
	System.out.println("userName : " + userName);
	
	//get the user's information using MyInfoDao.
	List<MyInfoDto> myInfo = service.MyInfoDao(userId);
	model.addAttribute("myInfo", myInfo);
		
	return "/MyPagePart/myInfo";	
	}
	
	@PostMapping("modify")
	public String modify (HttpSession session, HttpServletRequest request) throws Exception{
    boolean modificationSuccess = service.ModifyDao(request.getParameter("name"),request.getParameter("cust_pw")
			,request.getParameter("tel"), request.getParameter("email"), request.getParameter("useraddress"), request.getParameter("cust_id"));
    if (modificationSuccess) {
        return "redirect:/MyPage"; 
	    } else {
	        return "errorPage"; 
	    }
	}
	
	@PostMapping("deactivate")
	public String deactivate(HttpSession session, HttpServletRequest request) throws Exception {
	    String deact_date = request.getParameter("deact_date");
	    System.out.println("가져온 날짜" + deact_date);
	    if (deact_date == null) {
	        deact_date = ""; 
	    }
	    String cust_id = (String) session.getAttribute("userId");
	    System.out.println(" 가져온 아이디" + cust_id);
	    service.DeactivateDao(deact_date, cust_id);
	    session.invalidate();
	    return "UserCheckPart/login_view";
	}
	
	@GetMapping("/passwordReset")
	public String passwordReset () throws Exception{
		return "/MyPagePart/passwordReset";
	}
	
		
	@PostMapping("/validateOldPassword")
	@ResponseBody 
	public String validateOldPassword(@RequestParam("oldPassword") String oldPassword, HttpSession session) {
		System.out.println("기존비번 : " + oldPassword);
	    String cust_id = (String) session.getAttribute("userId");
	    System.out.println("유저아이디 : " + cust_id);
	    try {
	        // Retrieve the database password using the service layer method
	        String dbPassword = service.getUserPwById(cust_id);
	        System.out.println("DB에있는 비번 : " + dbPassword);
	        // Compare the old password with the database password
	        if (oldPassword.equals(dbPassword)) {
	        	System.out.println("match");
	            return "match";
	        } else {
	        	System.out.println("notmatch");
	            return "not match";
	        }
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "error";
	    }
	}
	
	@PostMapping("updatePassword")
	public String updatePassword(@RequestParam("confirmPassword") String confirmPassword,HttpSession session) throws Exception {
	    String cust_id = (String) session.getAttribute("userId");
	    service.updatePassword( cust_id, confirmPassword);
	    return "redirect:/myInfo";
	}
	
	
}//CONTROLLER END
