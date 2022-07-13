package com.mvc.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.board.service.BoardService;
import com.mvc.common.controller.Controller;

public class PasswdCheckController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String num = request.getParameter("num");
		String passwd = request.getParameter("passwd");
		
		BoardService service = BoardService.getInstance();
		int result = service.boardPasswdCheck(num, passwd);
		
		request.setAttribute("resultData", result);
		
		return "/common/resultData";
	}

}
