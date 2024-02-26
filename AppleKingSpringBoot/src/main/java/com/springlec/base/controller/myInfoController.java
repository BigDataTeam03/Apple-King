package com.springlec.base.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.springlec.base.model.MyInfoDto;
import com.springlec.base.service.MyInfoDaoService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

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
	
}//CONTROLLER END
