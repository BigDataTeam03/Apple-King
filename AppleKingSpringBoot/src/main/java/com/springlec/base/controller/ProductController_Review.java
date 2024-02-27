package com.springlec.base.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.springlec.base.model.ReviewDto;
import com.springlec.base.service.ProductReviewDaoService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller	
public class ProductController_Review {

	/*--------------------------------------
	 * Description: 상세페이지 리뷰 목록 컨트롤러
	 * Author :  KBS
	 * Date : 2024.02.27
	 * Update : 2024.02.27 KBS 
	 * 	     1. 리스트 출력기능 완료 
	 *-------------------------------------- 
	
	*/
	
	
	@Autowired
	ProductReviewDaoService service;
		
	@GetMapping("/gotoProductReview")
	public String review (HttpServletRequest request,HttpSession session,
						  HttpServletResponse response, Model model) throws Exception{
		   //String product_code = (String) sesstion.getAttribute("product_code");
			
		   	String product_code = "1";
		 
		   	List<ReviewDto> ListReview = service.reviewList(product_code);
		   	model.addAttribute("ListReview", ListReview);
		  
		   	
			return "ProductPart/uProductReview";		 			
	}
			
	
}
