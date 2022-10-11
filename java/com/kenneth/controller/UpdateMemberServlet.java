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


@WebServlet("/updateMember.do")
public class UpdateMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("userId");
		System.out.println("userId: " + userId);
		
		MemberDao mDao = MemberDao.getInstance();	// 데이터 베이스 연동
		MemberVo mVo = mDao.getMember(userId);		// 데이터베이스로부터 회원정보 로딩
		
//		request.setAttribute("name", mVo.getName());
		request.setAttribute("mVo", mVo);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("member/updateMember.jsp");
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 데이터베이스에 수정된 정보를 업데이트
		
		String userid = request.getParameter("userId");
		String pwd = request.getParameter("pwd");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String nickname	= request.getParameter("nickname");
		String introduce = request.getParameter("introduce");
		String userlatlng = request.getParameter("userlatlng");
		
		
		
		MemberDao mDao = MemberDao.getInstance();	// 데이터 베이스 연동
		MemberVo mVo = new MemberVo();
		mVo.setUserid(userid);
		mVo.setPwd(pwd);
		mVo.setEmail(email);
		mVo.setPhone(phone);
		mVo.setNickname(nickname);
		mVo.setIntroduce(introduce);
		mVo.setUserlatlng(userlatlng);
		
		System.out.println(mVo.getUserid());
		
		
		
		mDao.updateMember(mVo);
		
		response.sendRedirect("login.do");
		
	}

}
