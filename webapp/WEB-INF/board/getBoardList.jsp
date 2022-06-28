<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
		
		<title>게시판 리스트</title>
		
		<link rel="shortcut icon" href="../image/icon.png" />
		<link rel="apple-touch-icon" href="../image/icon.png" />
		
		<!--[if lt IE 9]>
		<script src="../js/html5shiv.js"></script>
		<![endif]-->
		
		<link rel="stylesheet" type="text/css" href="/include/dist/css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="/include/dist/css/bootstrap-theme.css" />
		<style type="text/css">
			#boardSearch {margin-bottom: 20px;}
			#f_search label {margin-right: 5px;}
		</style>
		
		<script type="text/javascript" src="/include/dist/js/jquery-1.12.4.min.js"></script>
		<script type="text/javascript" src="/include/dist/js/common.js"></script>
		<script type="text/javascript" src="/include/dist/js/bootstrap.min.js"></script>
		<script type="text/javascript">
			$(function() {
				/* 제목 클릭 시 상세 페이지로 이동하기 위한 이벤트 */
				$(".goDetail").click(function() {
					// let num = $(this).parents("tr").children().eq(0).html();
					// 위처럼 하면 번거로우니 미리 글 번호를 각 tr의 속성으로 부여하고 그 속성 값을 가져옴
					//		  [이벤트가 발생한 자기 자신].[자신을 포함한 부모요소 중 tr].[tr의 속성 data-num]
					let num = $(this).parents("tr").attr("data-num");
					$("#num").val(num);
					$("#detailForm").attr({
						"method" : "post",
						"action" : "/board/detailBoard.do"
					});
					$("#detailForm").submit();
				});
				
				/* 글쓰기 버튼 클릭 시 처리 이벤트 */
				$("#writeForm").click(function() {
					location.href = "/board/insertForm.do";
				});
				
				/* 검색 입력양식 enter 제거 */
				$("#keyword").bind("keydown", function(event) {
					if (event.keyCode == 13) {
						event.preventDefault();
						//$("#searchBtn").click();
					}
				});
				
				/* 게시물 검색 버튼 클릭 시 처리 이벤트 */
				$("#searchBtn").click(function() {
					if ($("#search").val() == "all") { //검색조건이 "전체"일 경우
						$("#keyword").val(""); //검색어 값 필요없음
					} else { //검색조건이 "전체"가 아닐 경우
						if (!chkData("#keyword", "검색어를")) return;
					}
					
					$("#f_search").attr({
						"method" : "post",
						"action" : "/board/getBoardList.do"
					});
					$("#f_search").submit();
				});
				
				/* 검색 후 검색대상과 검색단어 출력 */
				if ('${param.keyword}' != "") {
					$("#search").val("${param.search}");
					$("#keyword").val("${param.keyword}");
				}
			});
		</script>
	</head>
	<body>
		<div class="container">
			<div class="text-center"><h3>게시판 리스트</h3></div>
			
			<%-- 상세 페이지 이동을 위한 form --%>
			<form id="detailForm" name="detailForm">
				<input type="hidden" id="num" name="num" />
			</form>
			
			<%-- 게시물 검색 form --%>
			<div id="boardSearch" class="text-right">
				<form id="f_search" name="f_search" class="form-inline">
					<div class="form-group">
						<label>검색조건</label>
						<select id="search" name="search" class="form-control">
							<option value="all">전체</option>
							<option value="title">제목</option>
							<option value="author">작성자</option>
							<option value="content">내용</option>
						</select>
						<input type="text" id="keyword" name="keyword" placeholder="검색어를 입력하세요" class="form-control" />
						<button type="button" id="searchBtn" class="btn btn-primary">검색</button>
					</div>
				</form>
			</div>
			
			<%-- 게시판 리스트 --%>
			<div id="boardList">
				<table summary="게시판 리스트" class="table">
					<thead>
						<tr>
							<th class="col-md-1 text-center">번호</th>
							<th class="col-md-6 text-center">제목</th>
							<th class="col-md-2 text-center">작성자</th>
							<th class="col-md-2 text-center">날짜</th>
							<th class="col-md-1 text-center">조회수</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${not empty list}">
								<c:forEach var="vo" items="${list}">
									<tr class="text-center" data-num="${vo.num}">
										<td>${vo.num}</td>
										<td class="text-left"><span class="goDetail">${vo.title}</span></td>
										<td>${vo.author}</td>
										<td>${vo.writeDay}</td>
										<td>${vo.readCnt}</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="5" class="text-center">등록된 게시물이 존재하지 않습니다.</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>
			
			<div class="text-right">
				<button type="button" id="writeForm" class="btn btn-primary btn-sm">글쓰기</button>
			</div>
		</div>
	</body>
</html>
