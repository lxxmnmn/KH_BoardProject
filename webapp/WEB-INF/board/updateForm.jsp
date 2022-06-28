<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
		
		<title>게시물 수정</title>
		
		<link rel="shortcut icon" href="../image/icon.png" />
		<link rel="apple-touch-icon" href="../image/icon.png" />
		
		<!--[if lt IE 9]>
		<script src="../js/html5shiv.js"></script>
		<![endif]-->
		
		<link rel="stylesheet" type="text/css" href="/include/dist/css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="/include/dist/css/bootstrap-theme.css" />
		
		<script type="text/javascript" src="/include/dist/js/jquery-1.12.4.min.js"></script>
		<script type="text/javascript" src="/include/dist/js/common.js"></script>
		<script type="text/javascript" src="/include/dist/js/bootstrap.min.js"></script>
		<script type="text/javascript">
			$(function() {
				/* 에러 메시지 처리 */
				let msg = "${errorMsg}";
				if (msg != "") { //입력에 실패해서 다시 이 페이지로 돌아와야 errorMsg 값이 생김
					alert(msg);
				}
				
				/* 수정 버튼 클릭 시 */
				$("#boardUpdate").click(function() {
					if (!chkData("#title", "제목을")) return;
					else if (!chkData("#content", "내용을")) return;
					else {
						$("#f_update").attr({
							"method" : "post",
							"action" : "/board/updateBoard.do"
						});
						$("#f_update").submit();
					}
				});
				
				/* 목록 버튼 클릭 시 */
				$("#boardList").click(function() {
					location.href = "/board/getBoardList.do";
				});
			});
		</script>
	</head>
	<body>
		<div class="container">
			<div class="text-center"><h3>게시물 수정</h3></div>
			
			<div class="text-center">
				<form id="f_update" name="f_update">
					<input type="hidden" id="num" name="num" value="${updateData.num}" />
					<table class="table table-bordered">
						<tr>
							<td class="text-center">글번호</td>
							<td class="text-left">${updateData.num} <label>(조회수: ${updateData.readCnt})</label></td>
							<td class="text-center">작성일</td>
							<td class="text-left">${updateData.writeDay}</td>
						</tr>
						<tr>
							<td class="text-center">작성자</td>
							<td colspan="3" class="text-left">${updateData.author}</td>
						</tr>
						<tr>
							<td class="text-center">글제목</td>
							<td colspan="3">
								<input type="text" class="form-control" id="title" name="title" value="${updateData.title}" />
							</td>
						</tr>
						<tr>
							<td class="text-center">글내용</td>
							<td colspan="3">
								<textarea id="content" name="content" class="form-control" rows="8">${updateData.content}</textarea>
							</td>
						</tr>
						<tr>
							<td class="text-center">비밀번호</td>
							<td colspan="3">
								<input type="password" class="form-control" id="passwd" name="passwd" placeholder="기존 비밀번호가 아닌 수정할 비밀번호를 입력해 주세요." />
							</td>
						</tr>
					</table>
				</form>
			</div>
			
			<div class="text-right">
				<button type="button" class="btn btn-primary btn-sm" id="boardUpdate">수정</button>
				<button type="button" class="btn btn-primary btn-sm" id="boardList">목록</button>
			</div>
		</div>
	</body>
</html>
