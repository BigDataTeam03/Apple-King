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
	 * 
	 * Update : 2024.02.28 KBS 
	 * 		 1. 도움이 되요 카운트 증가기능 완료
	 *       2. 리뷰 인서트 기능 추가(인서트하면 상품상세가 값이 null 뜸. 해결 필)
	 * Update : 2024.02.29 KBS 
	 * 		 1. 인서트시 문제나던거 해결
	 *-------------------------------------- 
	
	*/
	
	
	@Autowired
	ProductReviewDaoService service;
	// 디테일 다오를 받아야 하는 이유
	// 상품 상세페이지에서 정보를 띄울때에는 상품 리스트에서 해당 상품을 클릭 할 때 
	// 상품 코드를 세션으로 받아서 그 세션값으로 정보를 출력한다. 
	// 하지만 리뷰를 작성하고 나서 상세페이지로 이동하면 세션을 받지 못하여 리스트가 null 값이 된다
	// 그래서 리뷰 인서트시, 다시 다오를 받아서 ProductDto 에서 값을 가져와서 다시 상품 상세 페이지의 ${listDao} 에 값을 뿌려준다
	@Autowired
	ProductDetailDaoService servic2;
	
	//리뷰 리스트를 출력하는 메서드	
	@GetMapping("/gotoProductReview")
	public String review (HttpServletRequest request,HttpSession session,
						  HttpServletResponse response, Model model) throws Exception{
		   // 해당 상품의 코드세션값을 받는다
		   String product_code = (String) session.getAttribute("product_code");
			// 테스트용 값
		   	//String product_code = "1";
		    // List 타입으로 받는다
		   	List<ReviewDto> ListReview = service.reviewList(product_code);
		   	//  JSP 페이지의 ${ListReview}로 뿌려주기 위해 model 사용
		   	model.addAttribute("ListReview", ListReview);
		  
		   	
			return "ProductPart/uProductReview";		 			
	}
	// 도움이 되요를 +1 시키는 메서드
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
	// 리뷰를 등록하는 메서드
	@GetMapping("/insertReview")
	public String insertReview(HttpSession session,HttpServletRequest request,
							   HttpServletResponse response, RedirectAttributes redirectAttributes, Model model) throws Exception {
			// 로그인 안된 상태에서의 예외처리
			if(session.getAttribute("userId") == null) {
				model.addAttribute("errorMessage", "로그인 후 이용해주세요.");
		
			}
				// 세션과 리퀘스트로 값을 받는다
				String cust_id = (String) session.getAttribute("userId");
				String product_code = (String)session.getAttribute("product_code");	
				String rating = request.getParameter("rating");
				String content = request.getParameter("content");
				//String image = request.getParameter("image");
				String product_name = (String) session.getAttribute("product_name");
				System.out.println(" 상품코드" + product_code);
	//System.out.println(" 가져온 값들" + cust_id + content + image + product_code + product_name + rating);	
		 // 리뷰 인서트
		 service.insertReview(cust_id, product_code, rating, 
			    			  content, product_name);
		 // 상품의 평점 업데이트
		 service.rateUpdate(product_code,rating); 
		 // 다시 상품 상세 페이지에 사용되는 상품에 정보를 다시 받아준다
		 ProductListDto listDao = servic2.productDetailDao(product_code);
		 model.addAttribute("listDao", listDao);
		 //System.out.println("리스트 목록" + listDao);
		 //session.setAttribute("product_code", product_code);
		 return "ProductPart/uProductDetail";
	}
	
	
	
	
}
