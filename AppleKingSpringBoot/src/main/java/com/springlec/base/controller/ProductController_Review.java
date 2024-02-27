package com.springlec.base.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springlec.base.model.ProductListDto;
import com.springlec.base.model.ReviewDto;
import com.springlec.base.service.ProductDetailDaoService;
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
	
	@Autowired
	ProductDetailDaoService servic2;
	
		
	@GetMapping("/gotoProductReview")
	public String review (HttpServletRequest request,HttpSession session,
						  HttpServletResponse response, Model model) throws Exception{
		   String product_code = (String) session.getAttribute("product_code");
			
		   	//String product_code = "1";
		 
		   	List<ReviewDto> ListReview = service.reviewList(product_code);
		   	model.addAttribute("ListReview", ListReview);
		  
		   	
			return "ProductPart/uProductReview";		 			
	}
	@PostMapping("/upGood")
	public ResponseEntity<String> upGood(HttpServletRequest request,HttpSession session) {
		if(session.getAttribute("userId") == null) {
			   return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                       .body("로그인 후 이용해주세요.");
		}
	    String reviewCode = request.getParameter("review");
	    try {
	        service.upHelpful(reviewCode);
	        return ResponseEntity.ok("도움이 되요 카운트가 증가되었습니다.");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body("도움이 되요 카운트 증가 중 오류가 발생했습니다.");
	    }
	}
	
	@GetMapping("/insertReview")
	public String insertReview(HttpSession session,HttpServletRequest request,
							   HttpServletResponse response, RedirectAttributes redirectAttributes, Model model) throws Exception {
			// 로그인 안된 상태에서의 예외처리
			if(session.getAttribute("userId") == null) {
				redirectAttributes.addFlashAttribute("errorMessage", "로그인 후 이용해주세요.");
		        // 로그인 페이지로 리다이렉트
		        return "redirect:/login";
			}
		
				String cust_id = (String) session.getAttribute("userId");
				String product_code = (String)session.getAttribute("product_code");
				//String product_code = request.getParameter("product_code");
				String rating = request.getParameter("rating");
				String content = request.getParameter("content");
				String image = request.getParameter("image");
				String product_name = (String) session.getAttribute("product_name");
				System.out.println(" 상품코드" + product_code);
	System.out.println(" 가져온 값들" + cust_id + content + image + product_code + product_name + rating);		
		 service.insertReview(cust_id, product_code, rating, 
			    			  content, image, product_name);
		  ProductListDto listDao = servic2.productDetailDao(product_code);
		  model.addAttribute("listDao", listDao);
		 System.out.println("리스트 목록" + listDao);
		 //session.setAttribute("product_code", product_code);
		 return "ProductPart/productDetail";
	}
	
	
	
	
}
