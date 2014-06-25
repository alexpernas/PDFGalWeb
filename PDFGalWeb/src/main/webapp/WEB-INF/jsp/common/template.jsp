<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>

<html>
	<head>
		<!-- Icon -->
		<link href="<c:url value="/resources/favicon.ico" />" rel="shortcut icon">
		<link href="<c:url value="/resources/favicon.ico" />" rel="icon">
		
		<!-- CSS styles -->
		<link href="<c:url value="/resources/css/styles.css" />" rel="stylesheet">
		
		
		<!-- ********** -->
		<!-- Javascript -->
		<!-- ********** -->
		
		<!-- JQUERY 1.11.1 -->
		<script src="<c:url value="/resources/js/jquery/jquery-1.11.1.min.js" />"></script>
		
		
		<!-- Charset -->
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		
		
		<c:set var="titleLabel">
			<tiles:getAsString name="title"/>
		</c:set>
		<title>
			<spring:message code="${titleLabel}" />
		</title>
	</head>
	
	<body>
		<div id="banner">
            <%@ include file="/WEB-INF/jsp/common/header.jsp" %>
        </div>
        
        <div id="menu">
            <%@ include file="/WEB-INF/jsp/common/menu.jsp" %>
        </div>
        
        <div id="content">
        	<tiles:insertAttribute name="content" />
        </div>
        
        <div id="footer">
            <%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
        </div>
	</body>




</html>
