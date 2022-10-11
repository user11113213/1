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

import com.kenneth.dao.PlanDao;
import com.kenneth.dto.PlanVo;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/writeAdminPlan.do")
public class WriteAdminPlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("member/Form3-3.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PlanVo pVo = new PlanVo();
		PlanDao pDao = PlanDao.getInstance();
		
		
			
			// 입력 양식을 통해 정보를 획득
			String name = request.getParameter("name");
			String title = request.getParameter("title");
			String description = request.getParameter("description");
			Date reg_date = Date.valueOf(request.getParameter("reg_date"));
			
			pVo.setName(name);
			pVo.setTitle(title);
			pVo.setDescription(description);
			pVo.setReg_date(reg_date);
			
			System.out.println(pVo);
			 int result = pDao.insertPlan(pVo);	//입력된 게시판 정보 삽입
			
			
			// 정상적인 게시판 등록 여부를 확인 / 정상 등록 메세지를 브라우저 출력 위해 속성값 저장
			if(result == 1) {
				// 게시판 등록 완료 시, 게시판 코드를 저장
				System.out.println("게시판 등록에 성공");
				request.setAttribute("message", "상품 등록에 성공했습니다.");
			} else {
				System.out.println("게시판 등록에 실패");
				request.setAttribute("message", "상품 등록에 실패했습니다.");
			}
			response.sendRedirect("adminPlanList.do");	// 상품 등록 후 리스트 확인 페이지 이동
//			RequestDispatcher dispatcher = request.getRequestDispatcher("product/productList.jsp");
//			dispatcher.forward(request, response);
			
		} 
	
	}


