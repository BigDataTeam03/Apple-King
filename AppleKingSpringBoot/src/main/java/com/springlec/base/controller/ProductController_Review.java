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

	@Autowired
	ProductReviewDaoService service;
		
	@GetMapping("/gotoProductReview")
	public String review (HttpServletRequest request,HttpSession session,
						  HttpServletResponse response, Model model) throws Exception{
		   //String product_code = (String) sesstion.getAttribute("product_code");
			System.out.println( " d컨트롤러 입장" );
		   	String product_code = "1";
		 
		   	List<ReviewDto> ListReview = service.reviewList(product_code);
		   	model.addAttribute("ListReview", ListReview);
		   	System.out.println(" 가져온 리뷰목록" + ListReview);
		   	
			return "ProductPart/uProductReview";		 			
	}
			
	
}
