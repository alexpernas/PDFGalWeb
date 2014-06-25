<%@ include file="/WEB-INF/jsp/common/includes.jsp"%>

<spring:url value="/home" var="execute" />

<div class="container">

	<div class="horizontal_50">
		<img src="<c:url value="/resources/images/pdf_image.png" />" />
	</div>

	<div class="horizontal_50">
		<spring:message code="index.description" />
	</div>

</div>
