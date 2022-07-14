package com.mvc.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.board.service.BoardService;
import com.mvc.board.vo.BoardVO;
import com.mvc.common.controller.Controller;

public class InsertReplyController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String path = "";
		
		// 1. VO 객체에 데이터 바인딩
		BoardVO vo = new BoardVO();
		vo.setNum(Integer.parseInt(request.getParameter("num")));
		vo.setTitle(request.getParameter("title"));
		vo.setAuthor(request.getParameter("author"));
		vo.setContent(request.getParameter("content"));
		vo.setRepRoot(Integer.parseInt(request.getParameter("repRoot")));
		vo.setRepStep(Integer.parseInt(request.getParameter("repStep")));
		vo.setRepIndent(Integer.parseInt(request.getParameter("repIndent")));
		vo.setPasswd(request.getParameter("passwd"));
		
		// 2. Service 객체의 메서드 호출
		BoardService service = BoardService.getInstance();
		boolean result = service.replyInsert(vo);
		
		if (result) { //답변글 입력 처리 성공 시
			path = "/board/getBoardList.do"; //게시판 리스트로 이동
		} else { //답변글 입력 처리 실패 시
			path = "/board/replyForm.do?num=" + vo.getNum() + "&code=1"; //다시 답변글 입력 화면으로 이동
		}
		
		return path;
	}

}
