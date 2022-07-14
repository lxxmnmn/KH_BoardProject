# KH_BoardProject
게시판 만들기


## MVC 구조
<table>
  <tr>
    <th></th>
    <th>매핑 정보 (key)</th>
    <th>서브 컨트롤러 (value)</th>
    <th>뷰 (보여줄 jsp 파일)</th>
  </tr>
  <tr>
    <th>게시판 리스트</th>
    <td>/board/getBoardList.do</td>
    <td>GetBoardListController</td>
    <td>/WEB-INF/board/getBoardList.jsp</td>
  </tr>
  <tr>
    <th>입력 화면</th>
    <td>/board/insertForm.do</td>
    <td>InsertFormController</td>
    <td>/WEB-INF/board/insertForm.jsp</td>
  </tr>
  <tr>
    <th>입력 처리</th>
    <td>/board/insertBoard.do</td>
    <td>InsertBoardController</td>
    <td>/board/getBoardList.do</td>
  </tr>
  <tr>
    <th>상세 페이지</th>
    <td>/board/detailBoard.do</td>
    <td>DetailBoardController</td>
    <td>/WEB-INF/board/detailBoard.jsp</td>
  </tr>
  <tr>
    <th>비밀번호 확인</th>
    <td>/board/passwdCheck.do</td>
    <td>PasswdCheckController</td>
    <td>/WEB-INF/common/resultData.jsp</td>
  </tr>
  <tr>
    <th>수정 화면</th>
    <td>/board/updateForm.do</td>
    <td>UpdateFormController</td>
    <td>/WEB-INF/board/updateForm.jsp</td>
  </tr>
  <tr>
    <th>수정 처리</th>
    <td>/board/updateBoard.do</td>
    <td>UpdateBoardController</td>
    <td>/board/detailBoard.do</td>
  </tr>
  <tr>
    <th>삭제 처리</th>
    <td>/board/deleteBoard.do</td>
    <td>DeleteBoardController</td>
    <td>/board/getBoardList.do</td>
  </tr>
  <tr>
    <th>답글 입력 화면</th>
    <td>/board/replyForm.do</td>
    <td>ReplyFormController</td>
    <td>/WEB-INF/board/replyForm.jsp</td>
  </tr>
  <tr>
    <th>답글 입력 처리</th>
    <td>/board/insertReply.do</td>
    <td>InsertReplyController</td>
    <td>/board/getBoardList.do</td>
  </tr>
</table>
