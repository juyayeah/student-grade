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
<style>
.no-underline{
text-decoration:none;
}
</style>
<meta charset="UTF-8">
<title>헤더</title>
</head>
<body>
<table width="100%">
	<tr>
		<td>
			<a href="${contextPath }/main.do">
				<img src="${contextPath }/image/logo.png"/>
			</a>
		</td>
		<td>
		</td>
		<td>
			<c:choose>
				<c:when test="${isLogOn == true && student.id == 'admin' }">
					<a href="${contextPath }/logout.do" class="no-underline"><h4>로그아웃</h4></a>
					<a href="${contextPath }/admin/studentList.do" class="no-underline"><h4>관리자</h4></a>
					</c:when>
				<c:when test="${isLogOn == true && student != null}">
				<h4>${student.name }</h4>
				<a href="${contextPath }/logout.do" class="no-underline"><h4>로그아웃</h4></a>
				<a href="${contextPath}/student/studentDetail.do" class="no-underline"><h4>내 정보</h4></a>
				</c:when>
				<c:otherwise>
				<a href="${contextPath }/student/loginForm.do" class="no-underline"><h4>로그인</h4></a>
				<a href="${contextPath}/student/studentForm.do" class="no-underline"><h4>회원가입</h4></a>
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
</table>
</body>
</html>