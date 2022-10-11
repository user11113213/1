package com.kenneth.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kenneth.dao.BoardDao;
import com.kenneth.dto.BoardVo;

@WebServlet("/deleteBoard.do")
public class DeleteBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 쿼리스트링으로 전달 받은 code 획득
		String code = request.getParameter("code");
				
		// 게시판 삭제 링크 클릭시 삭제할 상품 정보를 표시
		BoardDao bDao = BoardDao.getInstance();
		BoardVo bVo = new BoardVo();
				
		// 데이터베이스에서 삭제할 데이터 정보 확인
		bVo = bDao.selectBoardByCode(code);
				
		request.setAttribute("board", bVo);
				
		// 페이지 이동 : 삭제 페이지
		RequestDispatcher dispatcher = request.getRequestDispatcher("board/deleteBoard.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 게시판 삭제 코드: 데이터베이스에서 상품 삭제
		BoardDao bDao = BoardDao.getInstance();
		BoardVo bVo = new BoardVo();
				
		String code = request.getParameter("code");
				
		bDao.deleteBoard(code);
				
		// 삭제 후 목록 페이지로 이동
		response.sendRedirect("boardList.do");
	}

}
