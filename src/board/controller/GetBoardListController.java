package com.mvc.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.board.service.BoardService;
import com.mvc.board.vo.BoardVO;
import com.mvc.common.controller.Controller;

/* 서브 컨트롤러 */
public class GetBoardListController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String search = request.getParameter("search");
		if (search == null) { //최초 요청 시 null값 처리
			search = "all";
		}
		
		BoardVO vo = new BoardVO();
		vo.setSearch(search);
		vo.setKeyword(request.getParameter("keyword"));
		
		BoardService service = BoardService.getInstance(); //싱글톤 패턴이므로 new 생성자() 대신 getInstance() 메소드 사용
		
		// 1. 검색 부분 제외하고 게시판 리스트 보여주기
		// ArrayList<BoardVO> list = service.boardList();
		
		// 2. 게시판 리스트 검색 시 검색할 대상과 단어를 인자값으로 전달
		List<BoardVO> list = service.boardList(vo);
		
		request.setAttribute("list", list);
		
		return "/board/getBoardList";
	}
	
}
