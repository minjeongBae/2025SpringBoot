<%@ page import="org.example.boards.board.BoardDTO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: ssii6
  Date: 2025-09-26
  Time: AM 3:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>목록</title>
</head>
<body>
    <%
        List<BoardDTO> list = (List<BoardDTO>) request.getAttribute("list");
        for(BoardDTO board : list){

    %>

        <li><%= board.getTitle()%></li>

    <%
        }

    %>
</body>
</html>
