<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	isELIgnored="false"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%
request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html >
<html>
    <head>
        <meta charset="utf-8">
        <title>성적 리스트</title>
        <script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>
        <script>
            function fn_enable(obj, num, id){
                document.getElementById("i_kor"+num).disabled = false;
                document.getElementById("i_eng"+num).disabled = false;
                document.getElementById("i_math"+num).disabled = false;
                document.getElementById("i_history"+num).disabled = false;
                document.getElementById("mod"+num).setAttribute("value", "수정반영");
                document.getElementById("mod"+num).setAttribute("onclick", "fn_modify_grade(frm_grade,"+id+")");
            }
            function fn_able(obj, num){
                document.getElementById("i_kor"+num).disabled = true;
                document.getElementById("i_eng"+num).disabled = true;
                document.getElementById("i_math"+num).disabled = true;
                document.getElementById("i_history"+num).disabled = true;
                document.getElementById("mod"+num).setAttribute("value", "수정");
                document.getElementById("mod"+num).setAttribute("onclick", "fn_enable(this.form,"+num+")");
            }
            function fn_modify_grade(obj, id){
                obj.action = "${contextPath}/admin/modGrade.do?id="+id;
                obj.submit();
            }
        </script>
        <style>
            table{
                margin:0 auto;
            }
        </style>
    </head>
    <body>
        <h3>그린고등학교 성적 리스트</h3>
        <form name="frm_grade" method="post">
        <table>
            <tr>
                <td>학번</td>
                <td>이름</td>
                <td>등수</td>
                <td>국어</td>
                <td>영어</td>
                <td>수학</td>
                <td>역사</td>
                <td>총점</td>
                <td>평균</td>
                <td>수정</td>
                <td>취소</td>
            </tr>
            <c:forEach var="grade" items="${gradeList}" varStatus="num">
                <tr id="mini${num}">
                    <td><input type="text" value="${grade.id}" disabled size="3" name="id" id="i_id"></td>
                    <td><input type="text" value="${grade.name}" disabled size="3" name="name"></td>
                    <td><input type="text" value="${grade.rank}" disabled size="1"></td>
                    <td><input type="text" value="${grade.kor}" disabled size="3" name="kor" id="i_kor${num.count}"></td>
                    <td><input type="text" value="${grade.eng}" disabled size="3" name="eng" id="i_eng${num.count}"></td>
                    <td><input type="text" value="${grade.math}" disabled size="3" name="math" id="i_math${num.count}"></td>
                    <td><input type="text" value="${grade.history}" disabled size="3" name="history" id="i_history${num.count}"></td>
                    <td><input type="text" value="${grade.sum}" disabled size="3"></td>
                    <td><input type="text" value="${grade.avg}" disabled size="3"></td>
                    <td><input type="button" value="수정" style="width:70px;" onClick="fn_enable(this.form, '${num.count}', '${grade.id}')" id="mod${num.count}"></td>
                    <td><input type="button" value="취소" style="width:60px;" onClick="fn_able(this.form, '${num.count}')" id="back${num.count}"></td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="11"><button type="button" onClick="location.href='${contextPath}/admin/gradeForm.do'">성적 등록</td>
            </tr>
        </table>
    </form>
    </body>
</html>