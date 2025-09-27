<%@ page import="org.example.boards.board.DTO.BoardDTO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>게시글 - 수정</title>
</head>

<body>
<h4>기본정보</h4>

<%
    BoardDTO board = (BoardDTO) request.getAttribute("board");
%>
<form action="/modify-board" method="post">
    <table style="width: 80%; height: auto; margin: 20px; padding: 10px">
        <tr>
            <th>카테고리</th>
            <th><%=board.getCategory()%></th>
        </tr>

        <tr>
            <th>등록일시</th>
            <th><%=board.getCreateDate()%></th>
        </tr>

        <tr>
            <th>수정일시</th>
            <th><%=board.getUpdateDate()%></th>
        </tr>

        <tr>
            <th>조회수</th>
            <th><%=board.getViews()%></th>
        </tr>

        <tr>
            <th>작성자</th>
            <th>
                <input type="text" value="<%=board.getWriter()%>" id="new_writer">
            </th>
        </tr>
        <tr>
            <th>비밀번호</th>
            <th><input type="password" id="new_password"/></th>
        </tr>
        <tr>
            <th>제목</th>
            <th><input type="text" value="<%=board.getTitle()%>" id="new_title"></th>
        </tr>
        <tr>
            <th>내용</th>
            <th><textarea value="<%=board.getContent()%>" id="new_content"></textarea>
            </th>
        </tr>
        <tr>
            <th>파일첨부</th>
            <th>
                <p> 파일 1 </p>
                <p> 파일 2 </p>
                <p> 파일 3 </p>
            </th>
        </tr>
    </table>
</form>
<div>
    <button onclick="location.href = '/'">취소</button>
    <button onclick="updatePost()">수정</button>
</div>

</body>
</html>
