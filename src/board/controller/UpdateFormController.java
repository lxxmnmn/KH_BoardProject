package com.mvc.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.board.service.BoardService;
import com.mvc.board.vo.BoardVO;
import com.mvc.common.controller.Controller;

public class UpdateFormController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String num = request.getParameter("num");
		String code = request.getParameter("code");
		
		BoardService service = BoardService.getInstance();
		BoardVO data = service.updateForm(num);
		
		request.setAttribute("updateData", data);
		
		if (code != null && code.equals("1")) { //수정 처리 시 문제가 발생하여 다시 돌아오면 code값 전달받음
			request.setAttribute("errorMsg", "게시물 수정에 문제가 발생하여 잠시 후 다시 입력해 주세요.");
		}
		
		return "/board/updateForm";
	}

}
