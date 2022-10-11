package com.kenneth.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kenneth.dao.PlanDao;
import com.kenneth.dto.PlanVo;

@WebServlet("/adminPlanList.do")
public class AdminPlanList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PlanDao pDao = PlanDao.getInstance();
		
		List<PlanVo> planList = pDao.selectAllPlan();
		request.setAttribute("planList", planList);
		
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("member/Form3.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
