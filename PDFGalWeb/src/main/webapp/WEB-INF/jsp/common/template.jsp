<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>

<html>
	<head>
		<!-- Icon -->
		<link rel="shortcut icon" href="${pageContext.servletContext.contextPath}/WEB-INF/imaes/flavicon.ico" type="image/x-icon" />
		<link rel="icon" href="${pageContext.servletContext.contextPath}/WEB-INF/imaes/favicon.ico" type="image/x-icon" />
		<!-- TODO Non funciona o do icono -->
		<!-- CSS styles -->
		<!-- TODO Non carga ben css, probar con c:url -->
		<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/WEB-INF/css/styles.css" type="text/css">
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
	</body>




</html>



<!-- <html> -->
<!--   <head> -->
<%--     <title><tiles:getAsString name="title"/></title> --%>
<!--   </head> -->
<!--   <body> -->
<!--         <table> -->
<!--       <tr> -->
<!--         <td colspan="2"> -->
<%--           <tiles:insertAttribute name="header" /> --%>
<!--         </td> -->
<!--       </tr> -->
<!--       <tr> -->
<!--         <td> -->
<%--           <tiles:insertAttribute name="menu" /> --%>
<!--         </td> -->
<!--         <td> -->
<%--           <tiles:insertAttribute name="body" /> --%>
<!--         </td> -->
<!--       </tr> -->
<!--       <tr> -->
<!--         <td colspan="2"> -->
<%--           <tiles:insertAttribute name="footer" /> --%>
<!--         </td> -->
<!--       </tr> -->
<!--     </table> -->
<!--   </body> -->
<!-- </html> -->