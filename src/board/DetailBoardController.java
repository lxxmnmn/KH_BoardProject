package com.mvc.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.board.service.BoardService;
import com.mvc.board.vo.BoardVO;
import com.mvc.common.controller.Controller;

public class DetailBoardController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String num = request.getParameter("num"); //getBoardList.jsp에서 hidden type으로 넘어온 글 번호
		
		BoardService service = BoardService.getInstance();
		service.readCount(num); //조회수 증가
		
		BoardVO data = service.boardDetail(num);
		request.setAttribute("detail", data);
		
		return "/board/detailBoard";
	}

}
