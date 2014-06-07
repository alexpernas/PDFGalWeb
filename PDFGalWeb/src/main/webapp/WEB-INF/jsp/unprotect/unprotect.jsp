<%@ include file="/WEB-INF/jsp/common/includes.jsp"%>

<spring:url value="/unprotect" var="execute" />

<!-- Strings to use as values -->
<spring:message code="unprotect.button.value" var="labelButtonValue" />

<div>
	<h3>
		<spring:message code="unprotect.subtitle" />
	</h3>
</div>

<div>
	<spring:message code="unprotect.description"
		arguments="${labelButtonValue}" />
</div>

<br />

<form:form method="POST" enctype="multipart/form-data"
	action="${uploadFile}">

	<spring:message code="unprotect.file.label" />
	<input type="file" name="file">

	<br />
	<br />

	<spring:message code="unprotect.password" />
	<input type="password" name="password">
	

	<br />
	<br />

	<input type="submit" value="${labelButtonValue}">

</form:form>
