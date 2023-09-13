<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<%
request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
    <script>
        function fn_check(){
            alert("로그인 후 이용해 주세요");
        }
    </script>
<style>
.no-underline{
text-decoration:none;
}
</style>
<meta charset="UTF-8">
<title>사이드 메뉴</title>
</head>
<body>
<h1>사이드 메뉴</h1>
<h1>
<c:choose>
<c:when test="${side_menu == 'admin_mode'}">
<a href="${contextPath }/admin/gradeList.do" class="no-underline">성적 목록</a><br>
<a href="${contextPath }/admin/studentList.do" class="no-underline">학생 목록</a><br>
<a href="${contextPath }/admin/studentForm.do" class="no-underline">학생 등록</a><br>
</c:when>
<c:when test="${isLogOn == true && student != null}">
<a href="${contextPath }/student/gradeList.do" class="no-underline">성적 확인</a><br>
</c:when>
<c:otherwise>
<a href="" onClick="fn_check()" class="no-underline">성적 확인</a>
</c:otherwise>
</c:choose>
</h1>
</body>
</html>