<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
		
		<title>상세 페이지</title>
		
		<link rel="shortcut icon" href="../image/icon.png" />
		<link rel="apple-touch-icon" href="../image/icon.png" />
		
		<!--[if lt IE 9]>
		<script src="../js/html5shiv.js"></script>
		<![endif]-->
		
		<link rel="stylesheet" type="text/css" href="/include/dist/css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="/include/dist/css/bootstrap-theme.css" />
		<style type="text/css">
			.table-height .text-vertical {vertical-align: middle;}
			#boardCtrl {margin-bottom: 20px;}
			#msg {
				margin-left: 10px;
				font-size: 0.9em;
			}
		</style>
		
		<script type="text/javascript" src="/include/dist/js/jquery-1.12.4.min.js"></script>
		<script type="text/javascript" src="/include/dist/js/common.js"></script>
		<script type="text/javascript" src="/include/dist/js/bootstrap.min.js"></script>
		<script type="text/javascript">
			let buttonCheck = 0; //수정버튼과 삭제버튼을 구별하기 위한 변수
			
			$(function() {
				$("#pwdChk").css("visibility", "hidden"); //영역은 그대로 두고 보이지 않게만 하기 위해 display 속성이 아니라 visibility 속성을 사용
				
				/* 에러 메시지 처리 */
				let msg = "${errorMsg}";
				if (msg != "") { //입력에 실패해서 다시 이 페이지로 돌아와야 errorMsg 값이 생김
					alert(msg);
				}
				
				/* 수정 버튼 클릭 시 처리 이벤트 */
				$("#updateForm").click(function() {
					$("#pwdChk").css("visibility", "visible");
					$("#msg").text("작성 시 입력한 비밀번호를 입력해 주세요.").css("color", "#000099");
					buttonCheck = 1;
				});
				
				/* 삭제 버튼 클릭 시 처리 이벤트 */
				$("#deleteBoard").click(function() {
					$("#pwdChk").css("visibility", "visible");
					$("#msg").text("작성 시 입력한 비밀번호를 입력해 주세요.").css("color", "#000099");
					buttonCheck = 2;
				});
				
				/* 비밀번호 확인 버튼 클릭 시 처리 이벤트 */
				$("#pwdBtn").click(function(event) {
					boardPwdConfirm();
				});
				
				/* 비밀번호 취소 버튼 클릭 시 처리 이벤트 */
				$("#pwdBtnCancel").click(function() {
					$("#pwdChk").css("visibility", "hidden");
					buttonCheck = 0; //buttonCheck = "";
				});
				
				/* 답글 버튼 클릭 시 처리 이벤트 */
				$("#replyBoard").click(function() {
					$("#f_data").attr({
						"method" : "post",
						"action" : "/board/replyForm.do"
					});
					$("#f_data").submit();
				});
				
				/* 목록 버튼 클릭 시 처리 이벤트 */
				$("#boardListBtn").click(function() {
					location.href = "/board/getBoardList.do";
				});
			});
			
			/* 비밀번호 확인 버튼 클릭 시 처리 함수 */
			function boardPwdConfirm() {
				//if (!dataCheck("#passwd", "#msg", "비밀번호를")) return;
				if (!chkData("#passwd", "비밀번호를")) return;
				else {
					$.ajax({
						url : "/board/passwdCheck.do",    //전송 url
						type : "post",					  //전송방식
						dataType : "text",				  //응답받을 문서 타입 (text/json/xml)
						data : $("#f_pwd").serialize(),   //form 전체 데이터 전송 "num=" + $("#num").val() + "&passwd=" + $("#passwd").val()
						error : function() {			  //실행 시 오류가 발생했을 경우
							alert("시스템 오류입니다. 관리자에게 문의하세요.");
						},
						success : function(resultData) {  //정상적으로 실행되었을 경우 (여기서 resultData는 request 속성으로 넘어온 것, 0 or 1)
							var goUrl = ""; //이동할 경로
							if (resultData == 0) {		  //비밀번호가 일치하지 않을 경우
								$("#msg").text("작성 시 입력한 비밀번호와 일치하지 않습니다.").css("color", "red");
								$("#passwd").select();
							} else if (resultData == 1) { //비밀번호가 일치할 경우
								$("#msg").text("");
								//console.log("비밀번호 일치");
								
								if (buttonCheck == 1) { //수정 버튼을 클릭한 경우
									goUrl = "/board/updateForm.do";
									$("#f_data").attr("action", goUrl);
									$("#f_data").submit();
								} else if (buttonCheck == 2) { //삭제 버튼을 클릭한 경우
									if (confirm("정말 삭제하시겠습니까?")) {
										goUrl = "/board/deleteBoard.do";
										$("#f_data").attr("action", goUrl);
										$("#f_data").submit();
									}
								}
							}
						}
					});
				}
			}
		</script>
	</head>
	<body>
		<div class="container">
			<div class="text-center"><h3>게시판 상세 페이지</h3></div>
			
			<%-- 수정/삭제 작업을 위한 form --%>
			<form id="f_data" name="f_data" method="post">
				<input type="hidden" name="num" value="${detail.num}" />
			</form>
			
			<%-- 비밀번호 검사 form과 게시판 제어 버튼 --%>
			<div id="boardCtrl" class="row text-center">
				<div id="pwdChk" class="col-md-9 text-left">
					<form id="f_pwd" name="f_pwd" class="form-inline">
						<input type="hidden" id="num" name="num" value="${detail.num}" />
						<label for="passwd" id="l_pwd">비밀번호: </label>
						<input type="password" id="passwd" name="passwd" class="form-control" />
						
						<button type="button" id="pwdBtn" class="btn btn-default btn-sm">확인</button>
						<button type="button" id="pwdBtnCancel" class="btn btn-default btn-sm">취소</button>
						<span id="msg"></span>
					</form>
				</div>
				<div class="col-md-3 text-right">
					<button type="button" id="updateForm" class="btn btn-primary btn-sm">수정</button>
					<button type="button" id="deleteBoard" class="btn btn-primary btn-sm">삭제</button>
					<button type="button" id="replyBoard" class="btn btn-primary btn-sm">답글</button>
					<button type="button" id="boardListBtn" class="btn btn-primary btn-sm">목록</button>
				</div>
			</div>
			
			<div class="text-center">
				<table class="table table-bordered">
					<tbody>
						<tr>
							<td class="col-md-3">글번호</td>
							<td class="col-md-3 text-left">${detail.num} <label>(조회수: ${detail.readCnt})</label></td>
							<td class="col-md-3">작성일</td>
							<td class="col-md-3 text-left">${detail.writeDay}</td>
						</tr>
						<tr>
							<td class="col-md-3">작성자</td>
							<td colspan="3" class="col-md-9 text-left">${detail.author}</td>
						</tr>
						<tr>
							<td class="col-md-3">글제목</td>
							<td colspan="3" class="col-md-9 text-left">${detail.title}</td>
						</tr>
						<tr class="table-height">
							<td class="text-valign">글내용</td>
							<td colspan="3" class="text-left">${detail.content}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</body>
</html>