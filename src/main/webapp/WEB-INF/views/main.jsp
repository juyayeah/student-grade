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
<meta charset="UTF-8">
<title>메인페이지</title>
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script> 

<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css" />
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick-theme.css" />
<script>
    $(document).ready(function(){
        $('.single-item').slick({
            autoplay:true,
            prevArrow:$('.prevArrow'),
            nextArrow:$('.nextArrow')
        });
        
    });
    
</script>
<style>
.prevArrow{
    position: absolute;
    left:340px;
    top:300px;
}
</style>
</head>
<body>
<c:choose>
    <c:when test="${isLogOn == true && student.id == 'admin' }">
        <h1>그린고등학교입니다.</h1>
        <h1>관리자 모드로 로그인하셨습니다.</h1>
    </c:when>
    <c:when test="${isLogOn == true && student != null}">
        <h1>그린고등학교입니다.</h1>
        <h1>메뉴를 클릭해서 이용해 주세요.</h1>
    </c:when>
    <c:otherwise>
        <h1>그린고등학교입니다</h1>
        <h1>로그인, 회원가입 후 이용해 주세요.</h1>
    </c:otherwise>
</c:choose>
</body>
</html>