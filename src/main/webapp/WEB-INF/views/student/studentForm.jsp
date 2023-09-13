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
<meta charset="EUC-KR">
<title>회원가입 창</title>
<script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>
<script type="text/javascript">
function readURL(input){
	if(input.files && input.files[0]){
		var reader = new FileReader();
		reader.onload = function(e){
			$('#preview').attr('src', e.target.result);
		}
		reader.readAsDataURL(input.files[0]);
	}
}

function backToList(obj){
	obj.action = "${contextPath }/main.do";
	obj.submit();
}
</script>
<style>
	#stdFrm{
		margin:0 auto;
	}
</style>
</head>
<body>
<form name="stdFrm" action="${contextPath }/student/addStudent.do" method="post" enctype="multipart/form-data">
<table  id="stdFrm">
<tr>
<td align="right">학번</td>
<td colspan="2"><input type="text" name="id" size="20"></td>
</tr>
<tr>
<td align="right">비밀번호</td>
<td colspan="2"><input type="password" name="pwd" size="20"></td>
</tr>
<tr>
<td align="right">이름</td>
<td colspan="2"><input type="text" name="name" size="20"></td>
</tr>
<tr>
<td align="right">프로필 사진: </td>
<td><input type="file" name="pic" onchange="readURL(this);"/></td>
<td><img id="preview"  width=150 height=150/></td>
</tr>
<input type="submit" value="회원가입"/><input type=button value="취소하기" onClick="backToList(this.form)"/><input type="reset" value="다시쓰기"/></td>
</table>
</form>
</body>
</html>