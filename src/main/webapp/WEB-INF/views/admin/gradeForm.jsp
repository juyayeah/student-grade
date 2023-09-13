<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	isELIgnored="false"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html >
<html>
    <head>
        <meta charset="utf-8">
        <title>성적 등록창</title>
        <style>
            table{
                margin:0 auto;
            }
            #fm_btn{
                margin:0 auto;
            }
            .i_btn{
                margin:10px;
            }
        </style>
    </head>
    <body>
        <form action="${contextPath}/admin/addGrade.do" method="post">
            <table>
                <tr>
                    <td>학번</td>
                    <td><input type="text" name="id"></td>
                </tr>
                <tr>
                    <td>국어</td>
                    <td><input type="text" name="kor"></td>
                </tr>
                <tr>
                    <td>영어</td>
                    <td><input type="text" name="eng"></td>
                </tr>
                <tr>
                    <td>수학</td>
                    <td><input type="text" name="math"></td>
                </tr>
                <tr>
                    <td>역사</td>
                    <td><input type="text" name="history"></td>
                </tr>
            </table>
            <div id="fm_btn">
                <input type="submit" value="성적 등록하기" class="i_btn">
                <input type="reset" value="다시 적기" class="i_btn">
            </div>
        </form>
    </body>
</html>