package com.mvc.board.vo;

public class BoardVO {
	private int num; //글 번호
	private String author; //글 작성자
	private String title; //글 제목
	private String content; //글 내용
	private String writeDay; //등록일
	private int readCnt; //조회수
	private int repRoot; //답변글 작성 시 사용 - 원글의 번호 참조
	private int repStep; //답변글 작성 시 사용 - 답글의 들여쓰기 지정
	private int repIndent; //답변글 작성 시 사용 - 답글의 순서 지정
	private String passwd; //비밀번호
	
	// 게시물 검색 시 사용할 속성
	private String search = ""; //기본값을 null이 아니라 ""(빈문자)로 제어
	private String keyword = "";
	
	public BoardVO() { }

	public BoardVO(int num, String author, String title, String content, String writeDay, int readCnt, int repRoot,
			int repStep, int repIndent, String passwd) {
		this.num = num;
		this.author = author;
		this.title = title;
		this.content = content;
		this.writeDay = writeDay;
		this.readCnt = readCnt;
		this.repRoot = repRoot;
		this.repStep = repStep;
		this.repIndent = repIndent;
		this.passwd = passwd;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriteDay() {
		return writeDay;
	}

	public void setWriteDay(String writeDay) {
		this.writeDay = writeDay;
	}

	public int getReadCnt() {
		return readCnt;
	}

	public void setReadCnt(int readCnt) {
		this.readCnt = readCnt;
	}

	public int getRepRoot() {
		return repRoot;
	}

	public void setRepRoot(int repRoot) {
		this.repRoot = repRoot;
	}

	public int getRepStep() {
		return repStep;
	}

	public void setRepStep(int repStep) {
		this.repStep = repStep;
	}

	public int getRepIndent() {
		return repIndent;
	}

	public void setRepIndent(int repIndent) {
		this.repIndent = repIndent;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public String toString() {
		return "BoardVO [num=" + num + ", author=" + author + ", title=" + title + ", content=" + content
				+ ", writeDay=" + writeDay + ", readCnt=" + readCnt + ", repRoot=" + repRoot + ", repStep=" + repStep
				+ ", repIndent=" + repIndent + ", passwd=" + passwd + "]";
	}
}
