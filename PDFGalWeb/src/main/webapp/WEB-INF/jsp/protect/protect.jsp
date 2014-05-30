<%@ include file="/WEB-INF/jsp/common/includes.jsp"%>

<spring:url value="/protect" var="execute" />

<!-- Strings to use as values -->
<spring:message code="protect.button.value" var="labelButtonValue" />

<div>
	<h3>
		<spring:message code="protect.subtitle" />
	</h3>
</div>

<div>
	<spring:message code="protect.description"
		arguments="${labelButtonValue}" />
</div>

<br />

<form:form method="POST" enctype="multipart/form-data"
	action="${uploadFile}">

	<spring:message code="protect.file.label" />
	<input type="file" name="file">

	<br />
	<br />

	<spring:message code="protect.password" />
	<input type="password" name="password">
	<br />
	<spring:message code="protect.repeat.password" />
	<input type="password" name="repeatedPassword">
	

	<br />
	<br />

	<input type="submit" value="${labelButtonValue}">

</form:form>

