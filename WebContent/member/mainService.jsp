<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.myHome.model.dto.Member" %>
<%@ page import="com.myHome.model.dto.MessageEntity" %>
<%@ include file="/inc/taglib_menu.jsp" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 | MMS</title>
<link type="text/css" rel="stylesheet" href="${CONTEXT_PATH}/css/common.css">
</head>

<body>
<c:if test="${empty memberId or empty grade}">
	<jsp:useBean id="messageEntity" class="com.myHome.model.dto.MessageEntity" scope="request" />

	<c:set var="messageEntity" value="${messageEntity}" scope="request" />
	<jsp:forward page="/member/message.jsp" />
</c:if>
<!-- contents menu -->
<h3 class="title"><span class="seperator_title">|||</span>회원전용서비스 메인페이지<span class="seperator_title">|||</span></h3>

<!-- footer menu -->
<jsp:include page="/inc/footer.jsp" />

</body>
</html>