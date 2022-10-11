package com.kenneth.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kenneth.dao.MemberDao;
import com.kenneth.dto.MemberVo;
import com.kenneth.dto.ProductVo;


@WebServlet("/updateMemberAdmin.do")
public class UpdateMemberAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		
		MemberDao mDao = MemberDao.getInstance();	// 데이터 베이스 연동
		MemberVo mVo = new MemberVo();
		
		mVo = mDao.selectMemberByName(userid);
		
		request.setAttribute("member", mVo);
			
		RequestDispatcher dispatcher = request.getRequestDispatcher("member/Form-1.jsp");
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int result = -1;
		ServletContext context = getServletContext();			// 실행 서블릿의 컨택스트 가져오기
		
		
		// 데이터베이스에 수정된 정보를 업데이트
		
		String userid = request.getParameter("userid");
		String pwd = request.getParameter("pwd");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String nickname =request.getParameter("nickname");
		String introduce =request.getParameter("introduce");
		String userlatlng =request.getParameter("userlatlng");		
				
				
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
				
				
				
		result = mDao.updateMember(mVo);
				
		if(result == 1) {
			System.out.println("회원 정보 수정에 성공했습니다.");
		} else {
			System.out.println("회원 정보 수정에 실패했습니다.");
		}
				
		response.sendRedirect("memberList.do");
	}

}
