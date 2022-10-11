package com.kenneth.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kenneth.dao.MemberDao;
import com.kenneth.dto.MemberVo;

@WebServlet("/multiDelete.do")
public class MultiDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원 삭제 코드: 데이터베이스에서 상품 삭제
		MemberDao mDao = MemberDao.getInstance();
		MemberVo mVo = new MemberVo();
				
		String[] chk = request.getParameterValues("chk");
				
		int result = mDao.multiDelete(chk);
		System.out.println(result);		
		
		// 삭제 후 목록 페이지로 이동
		response.sendRedirect("memberList.do");
	}

}
