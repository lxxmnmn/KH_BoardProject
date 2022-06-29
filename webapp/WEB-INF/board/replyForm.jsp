<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
		
		<title>답변 글쓰기</title>
		
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
				let origin_content = $("#content").val();
				$("#content").val("\n\n\n\n----- Original Message -----\n" + origin_content);
			
				/* 에러 메시지 처리 */
				let msg = "${errorMsg}";
				if (msg != "") { //입력에 실패해서 다시 이 페이지로 돌아와야 errorMsg 값이 생김
					alert(msg);
				}
				
				/* 저장 버튼 클릭 시 */
				$("#boardInsert").click(function() {
					if (!chkData("#title", "제목을")) return;
					else if (!chkData("#author", "작성자를")) return;
					else if (!chkData("#content", "내용을")) return;
					else if (!chkData("#passwd", "비밀번호를")) return;
					else {
						$("#replyForm").attr({
							"method" : "post",
							"action" : "/board/insertReply.do"
						});
						$("#replyForm").submit();
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
		<div id="container">
			<div class="text-center"><h3>답변글 작성</h3></div>
			
			<div class="text-center">
				<form id="replyForm" name="replyForm">
					<input type="hidden" name="num" value="${reply.num}" />
					<input type="hidden" name="repRoot" value="${reply.repRoot}" />
					<input type="hidden" name="repStep" value="${reply.repStep}" />
					<input type="hidden" name="repIndent" value="${reply.repIndent}" />
					
					<table class="table table-bordered">
						<tr>
							<td colspan="2" class="text-left"><strong>글번호: ${reply.num}</strong> (조회수: ${reply.readCnt})</td>
						</tr>
						<tr>
							<td class="text-center">작성자</td>
							<td><input type="text" id="author" name="author" class="form-control" /></td>
						</tr>
						<tr>
							<td class="text-center">글제목</td>
							<td><input type="text" id="title" name="title" value="[RE] ${reply.title}" class="form-control" /></td>
						</tr>
						<tr>
							<td class="text-center">글내용</td>
							<td>
								<textarea id="content" name="content" maxlength="2000" rows="8" class="form-control">${reply.content}</textarea>
							</td>
						</tr>
						<tr>
							<td class="text-center">비밀번호</td>
							<td><input type="password" id="passwd" name="passwd" class="form-control" /></td>
						</tr>
					</table>
				</form>
			</div>
			
			<div class="text-center">
				<button type="button" class="btn btn-primary" id="boardInsert">저장</button>
				<button type="button" class="btn btn-primary" id="boardList">목록</button>
			</div>
		</div>
	</body>
</html>