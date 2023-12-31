<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	 isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<c:if test='${not empty message }'>
<script>
window.onload=function()
{
  result();
}

function result(){
	alert("아이디나  비밀번호가 틀립니다. 다시 로그인해주세요");
}
</script>
</c:if>
<style>
#logFrm{
margin:0 auto;
}
</style>
</head>
<body>
	<H3>회원 로그인 창</H3>
	<form action="${contextPath}/student/login.do" method="post">
		<TABLE id="logFrm">
			<TBODY>
				<TR class="dot_line">
					<TD class="fixed_join">학번</TD>
					<TD><input name="id" type="text" size="20" /></TD>
				</TR>
				<TR class="solid_line">
					<TD class="fixed_join">비밀번호</TD>
					<TD><input name="pwd" type="password" size="20" /></TD>
				</TR>
			</TBODY>
		</TABLE>
		<br><br>
		<INPUT	type="submit" value="로그인"> 
		<INPUT type="button" value="초기화">
		<button type="button" onClick="location.href='${contextPath}/student/studentForm.do'">회원가입</button>
	</form>		
</body>
</html>