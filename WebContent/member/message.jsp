<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.work.model.dto.MessageEntity" %> 
<%@ include file="/inc/taglib_menu.jsp" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>응답결과</title>
<link type="text/css" rel="stylesheet" href="${initParam.CONTEXT_PATH}/css/common.css">

</head>
<body>
<!-- logo menu -->
<jsp:include page="/inc/logo_menu.jsp" />

<c:choose>
	<c:when test="${empty grade}">
		<jsp:include page="/inc/before_main_menu.jsp" />
	</c:when>
	
	<c:when test="${grade == 'A'}">
		<jsp:include page="/inc/after_admin_main_menu.jsp" />
	</c:when>
	
	<c:otherwise>
		<jsp:include page="/inc/after_main_menu.jsp" />
	</c:otherwise>
</c:choose>

<h3>${requestScope.messageEntity.message}</h3>
<h3><a href="${messageEntity.url}">${messageEntity.linkTitle}</a></h3>

<!-- footer menu -->
<jsp:include page="/inc/footer_menu.jsp" />

</body>
</html>












