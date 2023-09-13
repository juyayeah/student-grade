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
        <title>학생 리스트</title>
        <script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>
        <script>
            function fn_enable(obj, num, id){
                document.getElementById("i_imageFileName"+num).style.display="table-row";
                document.getElementById("i_name"+num).disabled = false;
                document.getElementById("i_pwd"+num).disabled = false;
                document.getElementById("mod"+num).setAttribute("value", "수정반영");
                document.getElementById("mod"+num).setAttribute("onclick", "fn_modify_student(frm_student,"+id+")");
            }
            function fn_able(obj, num){
                document.getElementById("i_imageFileName"+num).style.display="none";
                document.getElementById("i_name"+num).disabled = true;
                document.getElementById("i_pwd"+num).disabled = true;
                document.getElementById("mod"+num).setAttribute("value", "수정");
                document.getElementById("mod"+num).setAttribute("onclick", "fn_enable(this.form,"+num+")");
            }
            function fn_modify_student(obj, id){
                obj.action = "${contextPath}/admin/modStudent.do?id="+id;
                obj.submit();
            }

            function fn_remove(obj, id){
                obj.action = "${contextPath}/admin/deleteStudent.do?id="+id;
                obj.submit();
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
        </script>
        <style>
            table{
                margin:0 auto;
            }
            #preview{
                border-radius: 50%;
                width:150px;
                height:150px;
            }
            #img_td{
               width:280px;
               height:180px;
            }
        </style>
    </head>
    <body>
        <h3>그린고등학교 학생 리스트</h3>
        <form name="frm_student" method="post" enctype="multipart/form-data">
        <table>
            <tr>
                <td>사진</td>
                <td>학번</td>
                <td>이름</td>
                <td>비밀번호</td>
                <td>수정</td>
                <td>삭제</td>
                <td>취소</td>
            </tr>
            <c:forEach var="student" items="${studentList}" varStatus="num">
                <tr>
                    <td id="img_td">
                        <input type="hidden" name="originalFileName" value="${student.pic}"/>
                        <img src="${contextPath}/download.do?pic=${student.pic}&id=${student.id}"  id="preview"/><br>
                        <input type="file" name="pic" id="i_imageFileName${num.count}" style="display:none" onchange="readURL(this);"/>
                    </td>
                    <td><input type="text" value="${student.id}" name="id" id="i_id" size="6" disabled /></td>
                    <td><input type="text" value="${student.name}" name="name" id="i_name${num.count}"  size="6" disabled/></td>
                    <td><input type="text" value="${student.pwd}" name="pwd" id="i_pwd${num.count}"  size="6" disabled/></td>
                    <td><input type="button" value="수정" style="width:70px;" onClick="fn_enable(this.form, '${num.count}', '${student.id}')" id="mod${num.count}"></td>
                    <td><input type="button" value="삭제" style="width:60px;" onClick="fn_remove(this.form, '${student.id}')"></td>
                    <td><input type="button" value="취소" style="width:60px;" onClick="fn_able(this.form, '${num.count}')" id="back${num.count}"></td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="7"><button type="button" onClick="location.href='${contextPath}/admin/studentForm.do'">학생 등록</td>
            </tr>
        </table>
    </form>
    </body>
</html>