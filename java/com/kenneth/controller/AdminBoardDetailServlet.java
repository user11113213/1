package com.kenneth.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kenneth.dao.BoardDao;
import com.kenneth.dto.BoardVo;

@WebServlet("/adminBoardDetail.do")
public class AdminBoardDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String code = request.getParameter("code");
//		out.println(code);
		
		BoardDao bDao = BoardDao.getInstance();
		BoardVo bVo = new BoardVo();
		
		bVo = bDao.selectBoardByCode(code);
//		System.out.println(bVo);
		
		request.setAttribute("board", bVo);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("member/Form2-4.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
