<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
		
		<title>게시판 글쓰기</title>
		
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
				
				/* 저장 버튼 클릭 시 */
				$("#boardInsert").click(function() {
					if (!chkData("#title", "제목을")) return;
					else if (!chkData("#author", "작성자를")) return;
					else if (!chkData("#content", "내용을")) return;
					else if (!chkData("#passwd", "비밀번호를")) return;
					else {
						$("#insertForm").attr({
							"method" : "post",
							"action" : "/board/insertBoard.do"
						});
						$("#insertForm").submit();
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
			<div class="text-center"><h3>게시물 작성</h3></div>
			
			<form class="form-horizontal" id="insertForm">
				<div class="form-group">
			   		<label for="title" class="col-sm-2 control-label">제 목</label>
			   		<div class="col-sm-10">
				    	<input type="text" class="form-control" id="title" name="title" placeholder="글 제목 입력" />
				    </div>
			  	</div>
			  	<div class="form-group">
			   		<label for="author" class="col-sm-2 control-label">작성자</label>
			   		<div class="col-sm-10">
				    	<input type="text" class="form-control" id="author" name="author" placeholder="작성자 입력" maxlength="6" />
				    </div>
			  	</div>
			  	<div class="form-group">
			   		<label for="content" class="col-sm-2 control-label">내 용</label>
			   		<div class="col-sm-10">
			  	 		<textarea id="content" name="content" class="form-control" rows="8"></textarea>
			  	 	</div>
			  	</div>
				<div class="form-group">
					<label for="passwd" class="col-sm-2 control-label">비밀번호</label>
					<div class="col-sm-10">
						<input type="password" class="form-control" id="passwd" name="passwd" placeholder="비밀번호 입력" />
					</div>
				</div>
			</form>
			
			<div class="text-center">
				<button type="button" class="btn btn-primary" id="boardInsert">저장</button>
				<button type="button" class="btn btn-primary" id="boardList">목록</button>
			</div>
		</div>
	</body>
</html>