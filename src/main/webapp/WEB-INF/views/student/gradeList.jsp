<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	isELIgnored="false"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html >
<html>
    <head>
        <meta charset="utf-8">
        <title>성적 확인창</title>
        <script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>
        <script>
        function fn_enable(obj){
	document.getElementById("i_kor").disabled = false;
	document.getElementById("i_eng").disabled = false;
    document.getElementById("i_math").disabled = false;
    document.getElementById("i_history").disabled = false;
    document.getElementById("i_sum").style.display = "none";
    document.getElementById("i_avg").style.display = "none";
    document.getElementById("i_rank").style.display = "none";
	document.getElementById("tr_btn_modify").style.display = "table-row";
	document.getElementById("tr_btn").style.display = "none";
}
function fn_disabled(){
    document.getElementById("i_kor").disabled = true;
	document.getElementById("i_eng").disabled = true;
    document.getElementById("i_math").disabled = true;
    document.getElementById("i_history").disabled = true;
    document.getElementById("i_sum").style.display = "table-row";
    document.getElementById("i_avg").style.display = "table-row";
    document.getElementById("i_rank").style.display = "table-row";
	document.getElementById("tr_btn_modify").style.display = "none";
	document.getElementById("tr_btn").style.display = "table-row";
}
function fn_modify_grade(obj){
	obj.action = "${contextPath}/student/modGrade.do";
	obj.submit();
}
        </script>
        <style>
            #tr_btn_modify{
            display:none;
            }
            table{
                margin:0 auto;
            }
        </style>
    </head>
    <body>
        <h3>성적을 확인하세요.</h3>
        <form name="gradeFrm" method="post">
            <table>
                    <tr id="i_rank">
                        <td>등수</td>
                        <td>
                            <input type="text" value="${grade.rank}"  disabled/>
                        </td>
                    </tr>
                    <tr>
                        <td>국어</td>
                        <td>
                            <input type="text" value="${grade.kor}" name="kor" id="i_kor" disabled/>
                            <input type="hidden" value="${grade.id}" name="id"/>
                        </td>
                    </tr>
                    <tr>
                        <td>영어</td>
                        <td>
                            <input type="text" value="${grade.eng}" name="eng" id="i_eng" disabled/>
                        </td>
                    </tr>
                    <tr>
                        <td>수학</td>
                        <td>
                            <input type="text" value="${grade.math}" name="math" id="i_math" disabled/>
                        </td>
                    </tr>
                    <tr>
                        <td>역사</td>
                        <td>
                            <input type="text" value="${grade.history}" name="history" id="i_history" disabled/>
                        </td>
                    </tr>
                    <tr  id="i_sum">
                        <td>총점</td>
                        <td>
                            <input type="text" value="${grade.sum}" disabled/>
                        </td>
                    </tr>
                    <tr id="i_avg">
                        <td>평균</td>
                        <td>
                            <input type="text" value="${grade.avg}"  disabled/>
                        </td>
                    </tr>
                    <tr id="tr_btn_modify">
                        <td colspan="2" align="center">
                            <input type="button" value="수정 반영하기" onClick="fn_modify_grade(frmArticle)">
                            <input type="button" value="취소" onClick="fn_disabled(this.form)">
                        </td>
                    </tr>
                    <tr id="tr_btn">
                        <td colspan="2" align="center">
                            <input type="button" value="성적 수정하기" onClick="fn_enable(this.form)">
                        </td>
                    </tr>
            </table>
        </form>
    </body>
</html>