package com.mvc.common.controller;

import java.util.HashMap;
import java.util.Map;

import com.mvc.board.controller.DeleteBoardController;
import com.mvc.board.controller.DetailBoardController;
import com.mvc.board.controller.GetBoardListController;
import com.mvc.board.controller.InsertBoardController;
import com.mvc.board.controller.InsertFormController;
import com.mvc.board.controller.InsertReplyController;
import com.mvc.board.controller.PasswdCheckController;
import com.mvc.board.controller.ReplyFormController;
import com.mvc.board.controller.UpdateBoardController;
import com.mvc.board.controller.UpdateFormController;

public class HandlerMapping {
	private Map<String, Controller> mappings;
	
	// 생성자
	public HandlerMapping() {
		mappings = new HashMap<String, Controller>();
		
		// 답변형 게시판 처리
		mappings.put("/board/getBoardList.do", new GetBoardListController());
		mappings.put("/board/insertForm.do", new InsertFormController());
		mappings.put("/board/insertBoard.do", new InsertBoardController());
		mappings.put("/board/detailBoard.do", new DetailBoardController());
		mappings.put("/board/passwdCheck.do", new PasswdCheckController());
		mappings.put("/board/updateForm.do", new UpdateFormController());
		mappings.put("/board/updateBoard.do", new UpdateBoardController());
		mappings.put("/board/deleteBoard.do", new DeleteBoardController());
		mappings.put("/board/replyForm.do", new ReplyFormController());
		mappings.put("/board/insertReply.do", new InsertReplyController());
	}
	
	// 인스턴스 반환
	public Controller getController(String path) { //path = "/board/getBoardList.do"
		return mappings.get(path); //new GetBoardListController()의 주소값 반환
	}
}
