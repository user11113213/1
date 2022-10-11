package com.kenneth.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kenneth.dao.MemberDao;
import com.kenneth.dto.MemberVo;


@WebServlet("/searchMember.do")
public class SearchMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDao mDao = MemberDao.getInstance();

		String userid = "userid";
		String name = "";
		
		String t_userid = request.getParameter("userid");
		String t_name = request.getParameter("name");
	
		// null 값이 아닌 경우,
			if(t_userid != null)
				userid = t_userid;
			if(t_name != null)
				name = t_name;
		
		System.out.println("u: " + userid);
		System.out.println("n: " + name);
		
		List<MemberVo> memberList = mDao.searchMember(userid, name);
		request.setAttribute("memberList", memberList);
		
		request.getRequestDispatcher("member/Form.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	

}
