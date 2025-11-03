<%@ page import="java.util.List" %>
<%@ page import="org.example.boards.board.DTO.CategoryDTO" %>
<%@ page import="org.example.boards.board.DTO.BoardListDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>자유 게시판 - 목록</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            margin: 40px;
            max-width: 1000px;
            color: #333;
            text-align: left;
        }
        h1 {
            margin-bottom: 10px;
        }
        form {
            margin-bottom: 20px;
            display: flex;
            flex-wrap: wrap;
            gap: 10px;
            align-items: center;
        }
        form input[type="date"],
        form input[type="text"],
        form select {
            padding: 6px;
            font-size: 14px;
        }
        form button {
            padding: 6px 12px;
            font-size: 14px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .summary {
            margin: 10px 0;
            font-weight: bold;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
        }
        th, td {
            text-align: left;
            padding: 10px;
            border: 1px solid #ccc;
            font-size: 14px;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #f9f9f9;
            cursor: pointer;
        }
        .register-btn {
            margin-top: 20px;
            padding: 8px 16px;
            font-size: 14px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
    </style>
    <script>
        function showDetail(boardId) {
            window.location.href = '/view?boardId=' + boardId;
        }
        function insertBoard() {
            window.location.href = '/write';
        }
    </script>
</head>
<body>

<a href="/" style="text-decoration: none; color: inherit;">
    <h1>자유 게시판 - 목록</h1>
</a>

<% if(request.getAttribute("errorMsg") != null) { %>
<script>
    alert("<%= request.getAttribute("errorMsg") %>");
</script>
<% } %>

<form action="/list" method="get">
    <label>등록일</label>
    <input type="date" name="headDate" value="">
    <input type="date" name="tailDate">
    <input type="text" name="word" placeholder="검색어 입력">
    <select name="category">
        <option value="0">선택하세요.</option>
        <%
            List<CategoryDTO> categories = (List<CategoryDTO>) request.getAttribute("categories");
            for(CategoryDTO category : categories) {
        %>
        <option value="<%=category.getCd()%>"><%= category.getName()%></option>
        <% } %>
    </select>
    <button type="submit">검색</button>
</form>

<%
    List<BoardListDTO> boards = (List<BoardListDTO>) request.getAttribute("boards");
%>

<div class="summary">총 <%= boards.size() %>건</div>

<table>
    <thead>
    <tr>
        <th>카테고리</th>
        <th>파일</th>
        <th>제목</th>
        <th>작성자</th>
        <th>조회수</th>
        <th>등록일시</th>
        <th>수정일시</th>
    </tr>
    </thead>
    <tbody>
    <%
        for(BoardListDTO board : boards){
            String updateDate = board.getUpdateDate() == null ? "-" : board.getUpdateDate().toString();
    %>
    <tr onclick="showDetail(<%= board.getBoardId() %>)">
        <td><%= board.getCategory() %></td>
        <td><%= board.getBoardId() %></td>
        <td><%= board.getTitle() %></td>
        <td><%= board.getWriter() %></td>
        <td><%= board.getViews() %></td>
        <td><%= board.getCreateDate() %></td>
        <td><%= updateDate %></td>
    </tr>
    <% } %>
    </tbody>
</table>

<button class="register-btn" onclick="insertBoard()">등록</button>

</body>
</html>