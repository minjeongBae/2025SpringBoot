<%@ page import="org.example.boards.board.DTO.BoardDTO" %>
<%@ page import="org.example.boards.board.DTO.CommentDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>게시물</title>
</head>
<body>
    <h1>게시판 - 보기</h1>
    <%
        BoardDTO board = (BoardDTO) request.getAttribute("board");
        String updateDate = board.getUpdateDate()==null ? "-" : board.getUpdateDate()+"";
    %>
    <div style="display: flex; gap: 20px; width: 70%">
        <span><%=board.getWriter()%></span>
        <span style="margin-left: auto;">등록일시 <%=board.getCreateDate()%></span>
        <span>수정일시 <%=updateDate%></span>
    </div>
    <br/>
    <div style="display: flex; gap: 20px; width: 70%">
        <span style="font-size: large">[<%=board.getCategory()%>]</span> &nbsp;
        <span><%=board.getTitle()%></span>
    </div>
    <hr style="border: 2px solid #ccc; margin: 20px 0;">
    <div style="border: 1px; border-color: darkgray">
        <%=board.getContent()%>
    </div>

    <div style="background-color: darkgray;width: 70%; height: auto;">
        <%
            List<CommentDTO> comments = (List<CommentDTO>) request.getAttribute("comments");
            for(CommentDTO comment : comments){
        %>
        <div>
            <span style="font-size: small"><%= comment.getCreateDate()%></span><br/>
            <span><%= comment.getContent()%></span>
        </div>
        <hr style="border: 1px solid #ccc; margin: 20px 0;">
         <%
             }
         %>

        <form action="/view/insert-comment" method="post">
            <input type="hidden" name="writer" value="ADMIN">
            <input type="hidden" name="boardId" value="<%=board.getBoardId()%>">
            <input type="text" name="content">
            <button type="submit">등록</button>
        </form>
    </div>

</body>
</html>
