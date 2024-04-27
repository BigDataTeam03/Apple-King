package com.springlec.base.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.springlec.base.dao.AdminDao;
import com.springlec.base.model.InquireDto;
import com.springlec.base.model.MemberDto;
import com.springlec.base.model.ProductListDto;


/*
 * Description 	: adminDao service implement 
 * Date 		: 2024.02.27
 * Author 		: kbs, pdg , ls 		
 * Detail 		: 관리자 crud
 * Update		: 
 * 	<<2024.02.27 by pdg>>
 * 		1.multipart request 를 사용하여 이미지 저장 성공 (다중이미지.)
 * 		2. 이미지 이름이 이상하게 저장됨? => 상관없나?? => 그럴듯한 이미지 제목으로 바꾸어서 저장필요??
 * 
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class AdminDaoServiceImpl implements AdminDaoService {
	@Autowired
	AdminDao dao;
	
	// 고객 리스트를 출력, 정렬, 검색
	@Override
	public List<MemberDto> custList(String name,String notThis, String orderby) throws Exception {
		// TODO Auto-generated method stub
		//System.out.println(" 갖져온거" + name + notThis +orderby);
		//System.out.println("asdasdsd " +dao.custList(name,notThis,orderby).get(0));
		return dao.custList(name,notThis,orderby);
		
		
	}

	// 상품 리스트 출력
	@Override
	public List<ProductListDto> productList(String product_name, String selected, String orderby2) throws Exception {
		// TODO Auto-generated method stub		
		return dao.productList(product_name, selected, orderby2);
	}

	// 상품 수정
	@Override
	public void updateProduct(String product_name, String product_qty, String origin, String manufacture_date,
			String weight, String size, String detail_image, String view_count, String product_reg_date,
			String kind, String product_image, String product_code) throws Exception {
		
			dao.updateProduct(product_name, product_qty, origin, manufacture_date, weight, size, detail_image,
					view_count, product_reg_date, kind, product_image, product_code);
			
		
	}
	// 상품 문의내역 출력
	@Override
	public List<InquireDto> questionList(String Not) throws Exception {
		// TODO Auto-generated method stub
		return dao.questionList(Not);
	}
	// 상품 문의 답변 입력
	@Override
	public void updateQuestion(String answer, String inquire_code) throws Exception {
		// TODO Auto-generated method stub
			dao.updateQuestion(answer, inquire_code);
			
			
	}

	@Override
	public void productInsertDao(	
			// detail_image_names 는 comma 로 연결됨.
			String product_code 	,//1
			String product_name 	,//2
			String product_rank		,//3	
			String product_qty  	,
			String origin  			,
			String manufacture_date ,
			String weight  			,
			String size  			,
			String product_reg_date ,
			String kind 			,
			MultipartFile product_image, 
			MultipartFile detail_image,  	
			String view_count  		,
			String price,
			String sold_qty,
			String starred
			) throws Exception {
		
		// 파일 업로드 후 고유한 파일 이름을 받아옴( imageUpload function 은 밑에 생성함.)
		String detail_image_name = imageUpload(detail_image);
		String product_image_name = imageUpload(product_image);
		
		// DB 에는 이미지 파일이아니라 이미지의 이름을 넣어야함. 
		dao.productInsertDao(
				product_code, 	//1
				product_name, 	//2
				product_rank,	//3
				product_qty, 	//4
				origin,			//5
				manufacture_date,//6
				weight, 		//7
				size, 			//8
				product_reg_date,//9
				kind,			//10
				product_image_name,	//11
				detail_image_name,	//12
				view_count,		//13
				price,			//14
				sold_qty,		//15
				Double.parseDouble(starred)		//16
				);
		
	}// END
		

	// 이미지의 고유 파일 이름을 저장하는 변수
	String uniqueFileName = "";// 이미지를 업로드하기전의 사진의 이름 

	// property에서 설정한 파일 업로드 경로를 주입받음
	
	@Value("${productimage.upload.directory}")// image 저장할 경로를 설정함. 
	private String uploadDirectory;
	
	// imageUpload 함수
	public String imageUpload(MultipartFile file) throws Exception {
		System.out.println(">> imageUpload 함수 실행");
		
		// 파일 고유 이름 생성
		uniqueFileName = generateUniqueFileName(file.getOriginalFilename());
		
		System.out.println(">> original file name : "+uniqueFileName);
		
		//directory 
		System.out.println(">> uploadDirectory : "+ uploadDirectory);
		
		// 파일 경로 설정 for 저장을 위한
		Path fileNameAndPath = Paths.get(uploadDirectory, uniqueFileName);
		
		// 파일을 해당 경로에 저장
		Files.write(fileNameAndPath, file.getBytes());
		
		// 업로드된 파일 고유한 이름을 반환
		return uniqueFileName;
	}

	private String generateUniqueFileName(String originalFileName) {
		return UUID.randomUUID().toString() + "_" + originalFileName;
	}
}
