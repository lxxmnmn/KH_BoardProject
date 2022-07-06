package com.mvc.board.service;

import java.util.List;

import com.mvc.board.dao.BoardDAO;
import com.mvc.board.vo.BoardVO;

public class BoardService {
	
	/* 싱글톤 패턴으로 인스턴스 생성 */
	private static BoardService service = null;
	
	private BoardDAO dao = BoardDAO.getInstance(); //싱글톤 패턴이므로 new 생성자() 대신 getInstance() 메소드 사용
	
	private BoardService() { }
	
	public static BoardService getInstance() {
		if (service == null) {
			service = new BoardService();
		}
		return service;
	}
	
	
	/* 게시판 목록 조회(검색X)를 위해 BoardDAO 클래스의 boardList() 메서드 호출 */
//	public ArrayList<BoardVO> boardList() {
//		ArrayList<BoardVO> list = dao.boardList();
//		return list;
//	}
	
	/* 게시판 목록 조회(검색O)를 위해 BoardDAO 클래스의 boardList() 메서드 호출 */
	public List<BoardVO> boardList(BoardVO vo) {
		List<BoardVO> list = dao.boardList(vo);
		return list;
	}
	
	/* 게시물 입력을 위해 BoardDAO 클래스의 boardInsert() 메서드 호출 */
	public boolean boardInsert(BoardVO vo) {
		boolean result = dao.boardInsert(vo);
		return result;
	}
	
	/* 게시물 조회수 증가를 위해 BoardDAO 클래스의 readCount() 메서드 호출 */
	public void readCount(String num) {
		dao.readCount(num);
	}
	
	/* 게시물 상세 페이지 조회를 위해 BoardDAO 클래스의 boardDetail() 메서드 호출 */
	public BoardVO boardDetail(String num) {
		BoardVO vo = dao.boardDetail(num);
		vo.setContent(vo.getContent().toString().replaceAll("\n", "<br/>"));
		return vo;
	}

	/* 비밀번호 검사를 위해 BoardDAO 클래스의 boardPasswdCheck() 메서드 호출 */
	public int boardPasswdCheck(String num, String passwd) {
		int result = dao.boardPasswdCheck(num, passwd);
		return result;
	}

	/* 게시물 수정 form을 불러오기 위해 BoardDAO 클래스의 boardDetail() 메서드 호출 */
	public BoardVO updateForm(String num) {
		BoardVO vo = dao.boardDetail(num);
		return vo;
	}

	/* 게시물 수정을 위해 BoardDAO 클래스의 boardUpdate() 메서드 호출 */
	public boolean boardUpdate(BoardVO vo) {
		boolean result = dao.boardUpdate(vo);
		return result;
	}
	
	/* 게시물 삭제를 위해 BoardDAO 클래스의 boardDelete() 메서드 호출 */
	public void boardDelete(String num) {
		dao.boardDelete(num);
	}

	/* 답글 입력 form을 불러오기 위해 BoardDAO 클래스의 boardDetail() 메서드 호출 */
	public BoardVO replyForm(String num) {
		BoardVO vo = dao.boardDetail(num);
		return vo;
	}
	
	/* 답글 입력을 위해 BoardDAO 클래스의 replyInsert() 메서드 호출 */
	public boolean replyInsert(BoardVO vo) {
		boolean result = dao.replyInsert(vo);
		return result;
	}
}
