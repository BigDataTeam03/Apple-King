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
	public List<ProductListDto> productlist(String product_name, String selected, String orderby2) throws Exception {
		// TODO Auto-generated method stub		
		return dao.productlist(product_name, selected, orderby2);
	}

	// 상품 수정
	@Override
	public void updateProduct(String product_name, String product_qty, String origin, String manufacture_date,
			String weight, String size, String detail_image_name, String view_count, String product_reg_date,
			String kind, String product_image_names, String product_code) throws Exception {
		
			dao.updateProduct(product_name, product_qty, origin, manufacture_date, weight, size, detail_image_name,
					view_count, product_reg_date, kind, product_image_names, product_code);
			
		
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
			String product_code,
			String product_name,
			String product_qty,
			String origin,
			String manufacture_date,
			String weight,
			String size, 		
			MultipartFile detail_images, // DB : detail_image_names ( 상세이미지)
			String view_count,
			String kind,
			String product_reg_date, 	 
			MultipartFile product_image, // DB : product_image_name(섬네일 이미지)
			String price) throws Exception {
		
		// 파일 업로드 후 고유한 파일 이름을 받아옴
		String detail_image_names = imageUpload(detail_images);
		String product_image_name = imageUpload(product_image);

		dao.productInsertDao(
				product_code,
				product_name,
				product_qty,
				origin,
				manufacture_date,
				weight,
				size,
				detail_images,
				view_count,
				kind,
				product_reg_date,
				product_image,
				price
				);
	}

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
		
		System.out.println(">> uploadDirectory : "+ uploadDirectory);
		
		// 파일 경로 설정
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
