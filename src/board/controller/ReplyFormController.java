package com.mvc.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.board.service.BoardService;
import com.mvc.board.vo.BoardVO;
import com.mvc.common.controller.Controller;

public class ReplyFormController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String num = request.getParameter("num");
		String code = request.getParameter("code"); //답변 처리 실패 시 내부적으로 사용할 변수
		
		BoardService service = BoardService.getInstance();
		BoardVO vo = service.replyForm(num);
		
		request.setAttribute("reply", vo); //기존글 정보를 보여주기 위한 속성 설정
		
		if (code != null && code.equals("1")) { //답변 처리 실패 시 속성값 설정 후 답변 입력 화면으로 다시 포워드
			request.setAttribute("errorMsg", "답변 처리에 문제가 발생하여 잠시 후 다시 입력해 주세요.");
		}
		
		return "/board/replyForm";
	}

}
