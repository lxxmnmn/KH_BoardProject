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
</table>
