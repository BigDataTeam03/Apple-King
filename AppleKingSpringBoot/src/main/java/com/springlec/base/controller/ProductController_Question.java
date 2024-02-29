package com.springlec.base.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.springlec.base.model.InquireDto;

import com.springlec.base.service.productQuestionDaoService;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class ProductController_Question {

	/*--------------------------------------
	 * Description: 상세페이지의 문의 컨트롤러
	 * Author :  KBS
	 * Date : 2024.02.25
	 * Update : 2024.02.25 KBS 
	 * 		1.  테이블이 안나옴
	 *      2. 인서트 기능 완료
	 * Update : 2024.02.26 KBS
	 * 		1. 테이블 나옴
	 *      2. 관리자가 답변단 내용 확인 가능  
	 *-------------------------------------- 
	
	*/
	@Autowired
	productQuestionDaoService service;
	// 리스트를 출력하는 메서드
	@GetMapping("/goProductQuestion")
	public ResponseEntity<List<InquireDto>> goQuestion(HttpSession session, HttpServletResponse response,
							 HttpServletRequest request)throws Exception {
		System.out.println(" 문의 테이블 생성");
		// 세션으로 해당 상품의 코드를 받는다
		String product_code = (String)session.getAttribute("product_code");
		//String product_code = "1";
		// 배열에 결과값들을 넣는다
		List<InquireDto> QuestionList = service.ShowList(product_code);
		System.out.println("문의 리스트" + QuestionList);
		// Json  형식으로 response 를 보낸다
	return	ResponseEntity.ok().body(QuestionList);
	}
	// include 를 위한 경로 설정
	@GetMapping("/gotoProductQuestion")
	public String gogo()throws Exception {
		return "ProductPart/uProductQuestion";
	}
	// 사용자가 문의를 입력 하는 메서드
	@PostMapping("/insertQuestion")
	public String insertQ(HttpServletRequest request, HttpSession session,
						  HttpServletResponse response)throws Exception  {
		// 세션으로 로그인 할 때 받는 아이디값, 해당 상품의 코드값을 받는다
		String product_code = (String) session.getAttribute("product_code"); // 세션에서 상품 이름 가져오기
		//String product_code = "1";
        String cust_id = (String)session.getAttribute("userId");
        // 작성한 문의 내용을 가져온다
        String question = request.getParameter("question");
        // 서비스로 내용을 보낸다
        service.insertQuestion(cust_id, product_code,question);
        // 기능이 끝나면 다시 테이블을 불러온다
        return"redirect:/goProductQuestion";
	}
}
