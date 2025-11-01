<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>삭제</title>
    <style>
        table {
            width: 80%;
            border-collapse: collapse;
        }
        th, td {
            text-align: center;
            padding: 8px;
            border: 1px solid #ccc;
        }
    </style>

    <% String msg = (String) request.getAttribute("msg"); %>
    <% if (msg != null) { %>
    <script>
        alert("<%= msg %>");
        window.close();
    </script>
    <% } %>
</head>
<body>
    <form action="/delete-board" method="post">
        <input type="hidden" name="boardId" value="<%= request.getAttribute("boardId")%>">
        <table>
            <tr>
                <td>비밀번호</td>
                <td><input type="password" name="password"></td>
            </tr>
        </table>
        <button type="button" onclick="window.close()">취소</button>
        <button type="submit">삭제</button>
    </form>
</body>
</html>
