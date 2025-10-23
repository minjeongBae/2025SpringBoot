<%@ page import="org.example.boards.board.Entity.Board" %>
<%@ page import="org.example.boards.board.Entity.Comment" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>게시물</title>
</head>
<body>

    <h1>게시판 - 보기</h1>
    <%
        Board board = (Board) request.getAttribute("board");
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

    <div style="margin-top:20px; background-color: darkgray;width: 70%; height: auto;">
        <%
            List<Comment> comments = (List<Comment>) request.getAttribute("comments");
            for(Comment comment : comments){
        %>
        <div>
            <span style="font-size: small"><%= comment.getCreateDate()%></span><br/>
            <span><%= comment.getContent()%></span>
        </div>
        <hr style="border: 1px solid #ccc; margin: 20px 0;">
         <%
             }
         %>

        <form style="padding: 15px" action="/view/insert-comment" method="post">
            <input type="hidden" name="writer" value="ADMIN">
            <input type="hidden" name="boardId" value="<%=board.getBoardId()%>">
            <input type="text" name="content">
            <button type="submit">등록</button>
        </form>
    </div>

    <div style="width: 80%; margin-top: 20px; justify-content: center; align-content: center; display: flex">
        <button style="margin-right: 10px" onclick="goList()">목록</button>
        <button style="margin-right: 10px" onclick="modifyBoard(<%= board.getBoardId()%>)">수정</button>
        <button onclick="deleteBoard(<%= board.getBoardId()%>)">삭제</button>
    </div>

</body>
</html>

<script type="text/javascript">
    function modifyBoard(id) {
        window.location.href = '/go-modify-board?boardId=' + id;
    }

    function deleteBoard(id) {
        //window.location.href = '/go-delete-board?boardId=' + id;
        window.open("/go-delete-board?boardId="+ id, "deleteWindow", "width=500,height=300,left=100,top=100");

    }

    function goList() {
        window.location.href = '/';
    }
</script>