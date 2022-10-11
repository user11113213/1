package com.kenneth.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kenneth.dao.MemberDao;
import com.kenneth.dto.MemberVo;


@WebServlet("/userSearch.do")
public class UserSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		System.out.println("name: " + name);
		
		MemberDao mDao = MemberDao.getInstance();	// 데이터 베이스 연동
		MemberVo mVo = mDao.getMember(name);		// 데이터베이스로부터 회원정보 로딩
		
//		request.setAttribute("name", mVo.getName());
		request.setAttribute("mVo", mVo);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("member/Form.jsp");
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
