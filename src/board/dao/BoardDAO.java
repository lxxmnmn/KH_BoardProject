package com.mvc.board.dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mvc.board.vo.BoardVO;

import static com.mvc.common.util.JDBCTemplate.*; //static으로 import

public class BoardDAO {
	
	/* 싱글톤 패턴 */
	private static BoardDAO instance = null;
	
	public static BoardDAO getInstance() {
		if (instance == null) {
			instance = new BoardDAO();
		}
		return instance;
	}
	
	private BoardDAO() { }
	
	
	/********** boardList(): 게시판 목록 조회 (검색X) 메서드 **********/
//	public ArrayList<BoardVO> boardList() {
//		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			conn = getConnection();
//			
//			StringBuffer query = new StringBuffer();
//			query.append("SELECT num, author, title, ");
//			query.append("TO_CHAR(writeday, 'YYYY/MM/DD') AS writeday, ");
//			query.append("readcnt, reproot, repstep, repindent FROM board ");
//			query.append("ORDER BY reproot DESC, repstep ASC");
//			
//			pstmt = conn.prepareStatement(query.toString());
//			rs = pstmt.executeQuery();
//			
//			while (rs.next()) {
//				BoardVO data = new BoardVO();
//				data.setNum(rs.getInt("num"));
//				data.setAuthor(rs.getString("author"));
//				data.setTitle(rs.getString("title"));
//				data.setWriteDay(rs.getString("writeday"));
//				data.setReadCnt(rs.getInt("readcnt"));
//				data.setRepRoot(rs.getInt("reproot"));
//				data.setRepStep(rs.getInt("repstep"));
//				data.setRepIndent(rs.getInt("repindent"));
//				
//				list.add(data);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			close(rs);
//			close(pstmt);
//			close(conn);
//		}
//		
//		return list;
//	}
	
	/********** boardList(): 게시판 목록 조회 (검색O) 메서드 **********/
	public List<BoardVO> boardList(BoardVO vo) {
		List<BoardVO> list = new ArrayList<BoardVO>(); //인터페이스<타입> 참조변수 = new 구현클래스<타입>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			StringBuffer query = new StringBuffer();
			query.append("SELECT num, author, title, ");
			query.append("TO_CHAR(writeday, 'YYYY/MM/DD') AS writeday, ");
			query.append("readcnt, reproot, repstep, repindent ");
			query.append("FROM board ");
			
			if ("title".equals(vo.getSearch())) { //검색대상이 "제목"일 경우
				query.append("WHERE title LIKE ? ");
			} else if ("author".equals(vo.getSearch())) { //검색대상이 "작성자"일 경우
				query.append("WHERE author LIKE ? ");
			} else if ("content".equals(vo.getSearch())) { //검색대상이 "내용"일 경우
				query.append("WHERE content LIKE ? ");
			}
			query.append("ORDER BY reproot DESC, repstep ASC");
			
			pstmt = conn.prepareStatement(query.toString());
			if (!"all".equals(vo.getSearch())) { //검색대상이 "전체"가 아닐 경우
				pstmt.setString(1, "%"+vo.getKeyword()+"%");
			}
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				BoardVO data = new BoardVO();
				data.setNum(rs.getInt("num"));
				data.setAuthor(rs.getString("author"));
				data.setTitle(rs.getString("title"));
				data.setWriteDay(rs.getString("writeday"));
				data.setReadCnt(rs.getInt("readcnt"));
				data.setRepRoot(rs.getInt("reproot"));
				data.setRepStep(rs.getInt("repstep"));
				data.setRepIndent(rs.getInt("repindent"));
				
				list.add(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(conn);
		}
		
		return list;
	}
	
	/********** boardInsert(): 게시물 등록 처리 메서드 **********/
	public boolean boardInsert(BoardVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try {
			conn = getConnection();
			
			StringBuffer query = new StringBuffer();
			query.append("INSERT INTO board(num, author, title, content, reproot, repstep, repindent, passwd) ");
			query.append("VALUES (board_seq.nextval, ?, ?, ?, board_seq.currval, 0, 0, ?)");
			
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, vo.getAuthor());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContent());
			pstmt.setString(4, vo.getPasswd());
			
			int value = pstmt.executeUpdate();
			if (value == 1) result = true;
			
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			close(pstmt);
			close(conn);
		}
		
		return result;
	}
	
	/********** readCount(): 게시물 조회수 증가 처리 메서드 **********/
	public void readCount(String num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			StringBuffer query = new StringBuffer();
			query.append("UPDATE board SET readcnt = readcnt + 1 ");
			query.append("WHERE num = ?");
			
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setInt(1, Integer.parseInt(num));
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(conn);
		}
	}
	
	/********** boardDetail(): 게시물 상세 페이지 조회 메서드 **********/
	public BoardVO boardDetail(String num) {
		BoardVO data = new BoardVO();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			StringBuffer query = new StringBuffer();
			query.append("SELECT num, author, title, content, ");
			query.append("TO_CHAR(writeday, 'YYYY-MM-DD HH24:MI:SS') AS writeday, ");
			query.append("readcnt, reproot, repstep, repindent FROM board ");
			query.append("WHERE num = ?");
			
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setInt(1, Integer.parseInt(num));
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				data.setNum(rs.getInt("num"));
				data.setTitle(rs.getString("title"));
				data.setAuthor(rs.getString("author"));
				data.setContent(rs.getString("content"));
				data.setWriteDay(rs.getString("writeday"));
				data.setReadCnt(rs.getInt("readcnt"));
				data.setRepRoot(rs.getInt("reproot"));
				data.setRepStep(rs.getInt("repstep"));
				data.setRepIndent(rs.getInt("repindent"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(conn);
		}
		
		return data;
	}
	
	/********** boardPasswdCheck(): 비밀번호 검사 메서드 **********/
	public int boardPasswdCheck(String num, String passwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			conn = getConnection();
			StringBuffer query = new StringBuffer();
			query.append("SELECT NVL((SELECT 1 FROM board WHERE num = ? AND passwd = ?), 0) AS result FROM DUAL");
			
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setInt(1, Integer.parseInt(num));
			pstmt.setString(2, passwd);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt("result"); //비밀번호 일치 시 1, 불일치 시 0 반환
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(conn);
		}
		
		return result;
	}
	
	/********** boardUpdate(): 게시물 수정 처리 메서드 **********/
	public boolean boardUpdate(BoardVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try {
			conn = getConnection();
			
			StringBuffer query = new StringBuffer();
			query.append("UPDATE board SET title = ?, content = ? "); //제목과 내용은 변경하면 변경된 내용을, 변경하지 않으면 기존값 그대로 가져옴
			// 필수항목이 아닐 때는 제어가 필요함
			if (vo.getPasswd() != "") { //비밀번호는 수정할 수도 있고 하지 않을 수도 있음
				query.append(", passwd = ? ");
			}
			query.append("WHERE num = ?");
			
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			
			if (vo.getPasswd() != "") {
				pstmt.setString(3, vo.getPasswd());
				pstmt.setInt(4, vo.getNum());
			} else {
				pstmt.setInt(3, vo.getNum());
			}
			
			int value = pstmt.executeUpdate();
			if (value == 1) result = true;
			
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			close(pstmt);
			close(conn);
		}
		
		return result;
	}
	
	/********** boardDelete(): 게시물 삭제 처리 메서드 **********/
	public void boardDelete(String num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			StringBuffer query = new StringBuffer();
			query.append("DELETE FROM board WHERE num = ?");
			
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setInt(1, Integer.parseInt(num));
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(conn);
		}
	}
	
	/********** makeReply(): 답변글의 기존 repStep(위치) 증가 처리 메서드 **********/
	public void makeReply(int root, int step) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			StringBuffer query = new StringBuffer();
			query.append("UPDATE board SET repstep = repstep + 1 ");
			query.append("WHERE reproot = ? AND repstep > ?"); //가장 최근에 달린 답변일수록 상단에 표시하기 위해 그 전 답변은 repstep+1 해야 함
			
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setInt(1, root);
			pstmt.setInt(2, step);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(conn);
		}
	}

	/********** replyInsert(): 답변글 등록 처리 메서드 **********/
	public boolean replyInsert(BoardVO vo) {
		makeReply(vo.getRepRoot(), vo.getRepStep());
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try {
			conn = getConnection();
			
			StringBuffer query = new StringBuffer();
			query.append("INSERT INTO board(num, author, title, content, reproot, repstep, repindent, passwd) ");
			query.append("VALUES (board_seq.nextval, ?, ?, ?, ?, ?, ?, ?)");
			
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, vo.getAuthor());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContent());
			pstmt.setInt(4, vo.getRepRoot());
			pstmt.setInt(5, vo.getRepStep() + 1);
			pstmt.setInt(6, vo.getRepIndent() + 1);
			pstmt.setString(7, vo.getPasswd());
			
			int count = pstmt.executeUpdate();
			if (count == 1) result = true;
			
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			close(pstmt);
			close(conn);
		}
		
		return result;
	}
}
