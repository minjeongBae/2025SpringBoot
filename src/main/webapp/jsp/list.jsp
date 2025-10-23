<%@ page import="java.util.List" %>
<%@ page import="org.example.boards.board.DTO.CategoryDTO" %>
<%@ page import="org.example.boards.board.DTO.BoardDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <style>
        table {
            width: 70%;
            border-collapse: collapse;
        }
        th, td {
            text-align: center;
            padding: 8px;
            border: 1px solid #ccc;
        }
    </style>

    <title>목록</title>
</head>
<body>
    <h1>자유 게시판 - 목록</h1>

    <form action="/list" method="get">
        등록일  <input type="date" name = "headDate" value="">
        <input type="date" name = "tailDate">
        <input type="text" name = "word">
        <select name="category" size="1">
            <option value="0">선택하세요.</option>
            <%
                List<CategoryDTO> categories = (List<CategoryDTO>) request.getAttribute("categories");
                for(CategoryDTO category : categories) {
            %>
            <option value="<%=category.getCd()%>"><%= category.getName()%></option>
            <%
                }
            %>
        </select>
        <button type="submit">검색</button>
    </form>

    <div style="margin: 15px">
        <%
            List<BoardDTO> boards = (List<BoardDTO>) request.getAttribute("boards");
        %>
        <span>총 <%=boards.size()%>건</span>
        <table style="margin: 10px">
            <thead>
                <tr>
                    <th>카테고리</th>
                    <th>파 일</th>
                    <th>제 목</th>
                    <th>작성자</th>
                    <th>조회수</th>
                    <th>등록일시</th>
                    <th>수정일시</th>
                </tr>
            </thead>
            <tbody>
            <%
                for(BoardDTO board : boards){
                    String updateDate = board.getUpdateDate() == null ? "-" : board.getUpdateDate()+"" ;
            %>
                <tr onclick="showDetail(<%=board.getBoardId()%>)">
                    <td><%= board.getCategory()%></td>
                    <td><%=board.getBoardId()%></td>
                    <td><%= board.getTitle()%></td>
                    <td><%= board.getWriter()%></td>
                    <td><%= board.getViews()%></td>
                    <td><%= board.getCreateDate()%></td>
                    <td><%= updateDate%></td>
                </tr>
            <%
                }

            %>
            </tbody>
        </table>
    </div>
    <br/>
    <button style="margin: 10px" onclick="insertBoard()">등록</button>
</body>
<script>
    function showDetail(boardId) {
        window.location.href = '/view?boardId=' + boardId;
    }

    function insertBoard(){
        window.location.href = '/write';
    }
</script>

</html>
