package com.mvc.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.board.service.BoardService;
import com.mvc.board.vo.BoardVO;
import com.mvc.common.controller.Controller;

public class InsertBoardController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String path = null;
		
		// 1. VO객체에 데이터 바인딩
		BoardVO vo = new BoardVO();
		vo.setTitle(request.getParameter("title"));
		vo.setAuthor(request.getParameter("author"));
		vo.setContent(request.getParameter("content"));
		vo.setPasswd(request.getParameter("passwd"));
		
		// 2. Service 객체의 메서드 호출
		BoardService service = BoardService.getInstance();
		boolean result = service.boardInsert(vo);
		
		if (result == true) { //입력 성공 시
			path = "/board/getBoardList.do"; //게시판 리스트 요청
		} else { //입력 실패 시
			path = "/board/insertForm"; //입력 화면으로 이동
			request.setAttribute("errorMsg", "게시물 등록에 문제가 발생하여 잠시 후 다시 입력해 주세요.");
		}
		
		// 3. 화면 내비게이션
		return path;
	}

}
