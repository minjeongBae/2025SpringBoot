<%@ page import="org.example.boards.board.DTO.CommentDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.boards.board.DTO.BoardViewDTO" %>
<%@ page import="org.example.boards.board.DTO.FileDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>게시물 보기</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            margin: 40px;
            max-width: 800px;
            color: #333;
            text-align: left;
        }
        h1, h2 {
            margin-bottom: 10px;
        }
        .section {
            margin-bottom: 20px;
        }
        .meta {
            display: flex;
            flex-direction: column;
            gap: 4px;
            font-size: 14px;
            color: #666;
        }
        .title {
            font-size: 20px;
            font-weight: bold;
        }
        .content {
            padding: 16px;
            background-color: #f5f5f5;
            border-radius: 8px;
        }
        .file-item, .comment-item {
            background-color: #f9f9f9;
            padding: 12px 16px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.05);
            margin-bottom: 10px;
            display: flex;
            flex-direction: column;
            align-items: flex-start;
        }
        .file-name {
            font-weight: 500;
            margin-bottom: 8px;
        }
        .btn {
            padding: 8px 16px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 14px;
        }
        .actions {
            display: flex;
            gap: 10px;
        }
        .comment-form {
            display: flex;
            flex-direction: column;
            gap: 10px;
        }
        .comment-form input[type="text"] {
            padding: 8px;
            font-size: 14px;
        }
    </style>
    <script>
        function goList() {
            window.location.href = '/list';
        }
        function modifyBoard(id) {
            window.location.href = '/modify-board?boardId=' + id;
        }
        function deleteBoard(id) {
            window.open("/delete-board?boardId=" + id, "deleteWindow", "width=500,height=300,left=100,top=100");
        }
    </script>
</head>
<body>
<div class="section">
    <a href="/" style="text-decoration: none; color: inherit;">
        <h1>자유 게시판</h1>
    </a>
    <h2>게시물 보기</h2>
</div>

<% if (request.getAttribute("errorMsg") != null) { %>
<script>
    alert("<%= request.getAttribute("errorMsg") %>");
    goList();
</script>
<% } %>

<% if (request.getAttribute("board") != null) {
    BoardViewDTO board = (BoardViewDTO) request.getAttribute("board");
    String updateDate = board.getUpdateDate() == null ? "-" : board.getUpdateDate() + "";
%>

<div class="section meta">
    <span>작성자: <%= board.getWriter() %></span>
    <span>등록일시: <%= board.getCreateDate() %></span>
    <span>수정일시: <%= updateDate %></span>
</div>

<div class="section title">
    [<%= board.getCategory() %>] <%= board.getTitle() %>
</div>

<div class="section content">
    <%= board.getContent() %>
</div>

<div class="section">
    <% if (request.getAttribute("files") != null) {
        List<FileDTO> files = (List<FileDTO>) request.getAttribute("files");
        for (FileDTO file : files) { %>
    <div class="file-item">
        <span class="file-name"><%= file.getName() %></span>
        <form action="/download" method="post">
            <input type="hidden" name="uuid" value="<%= file.getUuid() %>"/>
            <input type="hidden" name="name" value="<%= file.getName() %>"/>
            <button type="submit" class="btn">📥 다운로드</button>
        </form>
    </div>
    <% } } %>
</div>

<div class="section">
    <% List<CommentDTO> comments = (List<CommentDTO>) request.getAttribute("comments");
        for (CommentDTO comment : comments) { %>
    <div class="comment-item">
        <span style="font-size: small;"><%= comment.getCreateDate() %></span>
        <span><%= comment.getContent() %></span>
    </div>
    <% } %>

    <form class="comment-form" action="/view/insert-comment" method="post">
        <input type="hidden" name="boardId" value="<%= board.getBoardId() %>">
        <input type="text" name="content" placeholder="댓글을 입력하세요">
        <button type="submit" class="btn">등록</button>
    </form>
</div>

<div class="section actions">
    <button class="btn" onclick="goList()">목록</button>
    <button class="btn" onclick="modifyBoard(<%= board.getBoardId() %>)">수정</button>
    <button class="btn" onclick="deleteBoard(<%= board.getBoardId() %>)">삭제</button>
</div>

<% } %>
</body>
</html>