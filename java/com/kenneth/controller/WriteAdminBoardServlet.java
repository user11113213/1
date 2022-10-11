package com.kenneth.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kenneth.dao.BoardDao;
import com.kenneth.dto.BoardVo;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/writeAdminBoard.do")
public class WriteAdminBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("member/Form2-3.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		BoardVo bVo = new BoardVo();
		BoardDao bDao = BoardDao.getInstance();
		
		int result = -1;
		String savePath = "upload";
		ServletContext context = getServletContext();			// 실행 서블릿의 컨택스트 가져오기
		String uploadFilePath = context.getRealPath(savePath);	// 실제 경로를 리턴
		System.out.println("저장파일 서버경로: " + uploadFilePath);
		int uploadFileSizeLimit = 5 * 1024 * 1024;
		String encType = "UTF-8";
	
		try {
			MultipartRequest multi = new MultipartRequest(
					request, 						// request 객체
					uploadFilePath, 				// 서버상의 실제 디렉토리
					uploadFileSizeLimit, 			// 최대 업로드 파일 크기
					encType,						// 인코딩 방식
					new DefaultFileRenamePolicy()	// 정책 : 동일 이름 존재시, 새로운 이름 부여
					);
			
			// 입력 양식을 통해 정보를 획득
			String name = multi.getParameter("name");
			String title = multi.getParameter("title");
			String description = multi.getParameter("description");
			String pictureurl = multi.getFilesystemName("pictureurl");
			Date reg_date = Date.valueOf(multi.getParameter("reg_date"));
			
			bVo.setName(name);
			bVo.setTitle(title);
			bVo.setDescription(description);
			bVo.setPictureurl(pictureurl);
			bVo.setReg_date(reg_date);
			
			System.out.println(bVo);
			result = bDao.insertBoard(bVo);	//입력된 상품 정보 삽입
			
			
			// 정상적인 상품 등록 여부를 확인 / 정상 등록 메세지를 브라우저 출력 위해 속성값 저장
			if(result == 1) {
				// 상품 등록 완료 시, 상품 코드를 저장
				System.out.println("게시판 등록에 성공");
				request.setAttribute("message", "게시판 등록에 성공했습니다.");
			} else {
				System.out.println("게시판 등록에 실패");
				request.setAttribute("message", "게시판 등록에 실패했습니다.");
			}
			response.sendRedirect("adminBoardList.do");	// 게시판 등록 후 리스트 확인 페이지 이동
			
		} catch(Exception e) {
			System.out.println("파일 업로드간 예외 발생: " + e);
		}
	
	
	}

}
