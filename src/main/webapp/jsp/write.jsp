<%@ page import="java.util.List" %>
<%@ page import="org.example.boards.board.DTO.CategoryDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        th, td {
            padding: 8px;
            border: 1px solid #ccc;
        }
    </style>
    <title>등록</title>
</head>
<body>
    <% if(request.getAttribute("errorMsg") != null) { %>
    <script>
        alert("${errorMsg}");
    </script>
    <% } %>
    <h1>게시판 - 등록</h1>
    <br/>
    <div style="margin: 10px; padding: 10px">
        <form id="insertForm" action="/insert-board" style="width: 70%"
              method="post" enctype="multipart/form-data">
            <table style="border-collapse: collapse; padding: 10px">
                <tr>
                    <td style="background-color: darkgray">카테고리</td>
                    <td>
                        <select name="category" size="1">
                            <option value="0">카테고리 선택</option>
                            <%
                                List<CategoryDTO> categories = (List<CategoryDTO>) request.getAttribute("categories");
                                for(CategoryDTO category : categories) {
                            %>
                            <option value="<%=category.getCd()%>"><%= category.getName()%></option>
                            <%
                                }
                            %>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td style="background-color: darkgray">작성자</td>
                    <td><input type="text" name="writer" id="writer"></td>
                </tr>
                <tr>
                    <td style="background-color: darkgray">비밀번호</td>
                    <td><input type="password" name="password" placeholder="비밀번호"> &nbsp;
                        <input type="password" name="passwordCheck" placeholder="비밀번호 확인">
                        <button type="button" onclick="checkPW(password, passwordCheck)">확인</button>
                    </td>
                </tr>
                <tr>
                    <td style="background-color: darkgray">제목</td>
                    <td><input type="text" name="title" id="title"></td>
                </tr>
                <tr style="height: 200px">
                    <td style="background-color: darkgray">내용</td>
                    <td>
                        <textarea name="content" style="width: 100%" rows ="15" id="content"></textarea>
                    </td>
                </tr>
                <tr>
                    <td style="background-color: darkgray">파일첨부</td>
                    <td>
                        <input type="file" name="files"><br><br>
                        <input type="file" name="files"><br><br>
                        <input type="file" name="files">
                    </td>

                </tr>
            </table>
            <br/>
            <button type="submit" id="submitBtn" style="width: 15%">저장</button><br>
            <button onclick="cancel()" style="width: 15%; margin-top: 20px">취소</button>
        </form>
    </div>
</body>

<script>
    function cancel(){
        window.location.href = "/list.jsp";
    }

    function checkPW(pw,pwCheck){
        if(pw.value === pwCheck.value){
            alert("확인되었습니다.");
            document.getElementById("submitBtn").disabled = false;
        } else {
            alert("비밀번호가 일치하지 않습니다.");
            document.getElementById("submitBtn").disabled = true;

        }
    }

    window.onload = function(){
        document.getElementById("submitBtn").disabled = true;
    }

    document.getElementById("submitBtn").addEventListener("submit", function(event) {
        // event 인 submit 을 막으려면 파라미터로 받아야 함
        if(document.getElementById("writer")==null
            || document.getElementById("writer").value === ""
            || document.getElementById("title")==null
            || document.getElementById("title").value === ""
            || document.getElementById("content")==null
            || document.getElementById("content").value === ""
            || document.getElementById("password")==null
            || document.getElementById("password").value === "") {
            alert("필수항목 누락");
            document.getElementById("submitBtn").disabled = true;
            event.preventDefault(); // 제출 막기
        }
    });

</script>
</html>
