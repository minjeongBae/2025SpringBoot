<%@ page import="java.util.List" %>
<%@ page import="org.example.boards.board.DTO.CategoryDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>게시판 - 등록</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            margin: 40px;
            max-width: 800px;
            color: #333;
            text-align: left;
        }
        h1 {
            margin-bottom: 20px;
        }
        form {
            display: flex;
            flex-direction: column;
            gap: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
            border: 1px solid #ccc;
            vertical-align: top;
            text-align: left;
        }
        td.label {
            background-color: #eee;
            width: 150px;
            font-weight: bold;
        }
        input[type="text"],
        input[type="password"],
        textarea,
        select {
            width: 100%;
            padding: 8px;
            font-size: 14px;
        }
        .file-inputs input {
            margin-bottom: 10px;
        }
        .btn {
            width: 120px;
            padding: 8px 12px;
            font-size: 14px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .btn.cancel {
            background-color: #999;
        }
    </style>
</head>
<body>

<% if(request.getAttribute("errorMsg") != null) { %>
<script>
    alert("<%= request.getAttribute("errorMsg") %>");
</script>
<% } %>

<h1>게시판 - 등록</h1>

<form id="insertForm" action="/insert-board" method="post" enctype="multipart/form-data">
    <table>
        <tr>
            <td class="label">카테고리</td>
            <td>
                <select name="category">
                    <option value="0">카테고리 선택</option>
                    <%
                        List<CategoryDTO> categories = (List<CategoryDTO>) request.getAttribute("categories");
                        for(CategoryDTO category : categories) {
                    %>
                    <option value="<%=category.getCd()%>"><%= category.getName()%></option>
                    <% } %>
                </select>
            </td>
        </tr>
        <tr>
            <td class="label">작성자</td>
            <td><input type="text" name="writer" id="writer"></td>
        </tr>
        <tr>
            <td class="label">비밀번호</td>
            <td>
                <input type="password" name="password" id="password" placeholder="비밀번호">
                <br><br>
                <input type="password" name="passwordCheck" id="passwordCheck" placeholder="비밀번호 확인">
                <button type="button" class="btn" onclick="checkPW(password, passwordCheck)">확인</button>
            </td>
        </tr>
        <tr>
            <td class="label">제목</td>
            <td><input type="text" name="title" id="title"></td>
        </tr>
        <tr>
            <td class="label">내용</td>
            <td><textarea name="content" rows="10" id="content"></textarea></td>
        </tr>
        <tr>
            <td class="label">파일첨부</td>
            <td class="file-inputs">
                <input type="file" name="files">
                <input type="file" name="files">
                <input type="file" name="files">
            </td>
        </tr>
    </table>

    <div style="display: flex; gap: 10px; margin-top: 20px;">
        <button type="submit" id="submitBtn" class="btn">저장</button>
        <button type="button" class="btn cancel" onclick="cancel()">취소</button>
    </div>
</form>

<script>
    function cancel() {
        window.location.href = "/list.jsp";
    }

    function checkPW(pw, pwCheck) {
        if (pw.value === pwCheck.value) {
            alert("확인되었습니다.");
            document.getElementById("submitBtn").disabled = false;
        } else {
            alert("비밀번호가 일치하지 않습니다.");
            document.getElementById("submitBtn").disabled = true;
        }
    }

    window.onload = function() {
        document.getElementById("submitBtn").disabled = true;
    }

    document.getElementById("insertForm").addEventListener("submit", function(event) {
        const writer = document.getElementById("writer");
        const title = document.getElementById("title");
        const content = document.getElementById("content");
        const password = document.getElementById("password");

        if (!writer.value || !title.value || !content.value || !password.value) {
            alert("필수항목 누락");
            document.getElementById("submitBtn").disabled = true;
            event.preventDefault();
        }
    });
</script>

</body>
</html>