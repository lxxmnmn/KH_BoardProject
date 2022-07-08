package com.mvc.common.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DispatcherServlet
 */

public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private HandlerMapping handlerMapping;
	private ViewResolver viewResolver;

	@Override
	public void init() throws ServletException {
		handlerMapping = new HandlerMapping();
		viewResolver = new ViewResolver();
		viewResolver.setPrefix("/WEB-INF"); //공통경로
		viewResolver.setSuffix(".jsp"); //확장자
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 1. 클라이언트의 요청 path 정보를 추출한다.
		// *** 요청 URL: http://localhost:8080/webProject/board/getBoardList.do
		// uri인 "/webProject/board/getBoardList.do"를 얻음
		// String uri = request.getRequestURI();
		// uri에서 "/webProject"를 찾아 잘라내고 나머지 "/board/getBoardList.do"를 path에 대입
		// String path = uri.substring(request.getContextPath().length());
		
		// *** 요청 URL: http://localhost:8080/board/getBoardList.do
		String path = request.getRequestURI(); //String path = /board/getBoardList.do
		
		// 2. HandlerMapping을 통해 path에 해당하는 Controller를 검색한다.
		Controller ctrl = handlerMapping.getController(path); //new GetBoardListController()의 주소값 반환
		
		// 3. 검색된 Controller를 실행한다.
		String viewName = ctrl.execute(request, response); //GetBoardListController 클래스(서브컨트롤러)의 execute() 메소드 호출
		
		// 4. ViewResolver를 통해 viewName에 해당하는 화면을 검색한다.
		String view = null;
		
		if (!viewName.contains(".do")) {
			// 5. 검색된 화면으로 이동한다. (포워드)
			view = viewResolver.getView(viewName); //viewResolver.getView("/board/getBoardList") -> /WEB-INF/board/getBoardList.jsp
			try {
				RequestDispatcher dispatch = request.getRequestDispatcher(view);
				dispatch.forward(request, response);
			} catch (Exception e) {
				System.out.println("Forward ERROR: " + e);
			}
		} else { //
			// 5. 검색된 화면으로 이동한다. (웹브라우저에 재요청)
			view = viewName;
			response.sendRedirect(view);
		}
	}
	
}
