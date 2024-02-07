package command;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class aProductInsert implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		/*
		--------------------------------------------------------------
		* Description 	: Administrator product image 
		* Author 		: PDG & KBS
		* Date 			: 2024.02.07
		* ---------------------------Update---------------------------		
		 	<<2024.02.05>> by PDG &KBS
			1. 이미지 인서트 기능 추가 
		*
		--------------------------------------------------------------
		*/
		
		try {
		    ServletContext context = request.getServletContext();
		    String realfolder = context.getRealPath("/image");
		    System.out.print("image 가 들어갈 경로" + realfolder);
		    int sizeLimit = 100 * 1024 * 1024; // 100MB 제한
		    MultipartRequest multi = new MultipartRequest(
		            request, // request
		            realfolder, // image 가 저장될 application folder
		            sizeLimit, // image size limit
		            "UTF-8", // image file name utf-8
		            new DefaultFileRenamePolicy()); // 중복시 (1). ...
		} catch (Exception e) {
		    // 예외 처리 코드 작성
		    e.printStackTrace(); // 예외 내용을 출력
		}


		
		
		
	}

}
