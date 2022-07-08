package com.mvc.common.controller;

public class ViewResolver {
	public String prefix; //공통 경로(폴더)
	public String suffix; //확장자
	
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	public String getView(String viewName) { //서브컨트롤러에서 반환되는 문자열 (예: "/board/getBoardList")
		return prefix + viewName + suffix; //반환값 예시: "/WEB-INF" + "/board/getBoardList" + ".jsp"
	}
}
