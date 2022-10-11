package com.kenneth.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kenneth.dao.BoardDao;
import com.kenneth.dto.BoardVo;

@WebServlet("/boardList.do")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDao bDao = BoardDao.getInstance();
		
		List<BoardVo> boardList = bDao.selectAllBoard();
		request.setAttribute("boardList", boardList);
		
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("board/boardList.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
