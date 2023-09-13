<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	isELIgnored="false"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">
<title>마이페이지</title>
<script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>
<script>
    function backToList(obj){
	obj.action = "${contextPath}/main.do";
	obj.submit();
}
    function fn_enable(obj){
	document.getElementById("i_imageFileName").disabled = false;
    document.getElementById("i_imageFileName").style.display="table-row";
	document.getElementById("i_pwd").disabled = false;
    document.getElementById("i_name").disabled = false;
	document.getElementById("tr_btn_modify").style.display = "table-row";
	document.getElementById("tr_btn").style.display = "none";
	document.getElementById("i_imageFileName").disabled = false;
}
function fn_disabled(){
	document.getElementById("i_imageFileName").disabled = true;
    document.getElementById("i_imageFileName").style.display="none";
	document.getElementById("i_pwd").disabled = true;
    document.getElementById("i_name").disabled = true;
	document.getElementById("tr_btn_modify").style.display = "none";
	document.getElementById("tr_btn").style.display = "table-row";
}
function readURL(input){
	if(input.files && input.files[0]){
		var reader = new FileReader();
		reader.onload = function(e){
			$('#preview').attr('src', e.target.result);
		}
		reader.readAsDataURL(input.files[0]);
	}
}
function fn_modify_student(obj){
	obj.action = "${contextPath}/student/modStudent.do?id=${student.id}";
	obj.submit();
}
</script>
<style>
#preview{
    border-radius: 50%;
    width:150px;
    height:150px;
}
table{
    margin:0 auto;
}
#tr_btn_modify,#i_imageFileName{
display:none;
}
</style>
</head>
<body>
<form name="frmStudent" method="post" enctype="multipart/form-data">
    <table>
        <tr>
            <td colspan="2">
                <input type="hidden" name="originalFileName" value="${student.pic}"/>
                <img src="${contextPath}/download.do?pic=${student.pic}&id=${student.id}" id="preview"/><br>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="file" name="pic" id="i_imageFileName" disabled onchange="readURL(this);"/>
            </td>
        </tr>
        <tr>
            <td>학번</td>
            <td><input type="text" value="${student.id}" name="id" id="i_id" disabled/></td>
        </tr>
        <tr>
            <td>이름</td>
            <td><input type="text" value="${student.name}" name="name" id="i_name" disabled/></td>
        </tr>
        <tr>
            <td>비밀번호</td>
            <td><input type="text" value="${student.pwd}" name="pwd" id="i_pwd" disabled/></td>
        </tr>
        <tr id="tr_btn_modify">
            <td colspan="2" align="center">
            <input type="button" value="수정반영하기" onClick="fn_modify_student(frmStudent)">
            <input type="button" value="취소" onClick="fn_disabled(this.form)">
            </td>
            </tr>
            <tr id="tr_btn">
            <td colspan="2" align="center">
            <input type="button" value="수정하기" onClick="fn_enable(this.form)">
            <input type="button" value="메인페이지로 돌아가기" onClick="backToList(this.form)">
            </td>
            </tr>
    </table>
</form>
</body>
</html>