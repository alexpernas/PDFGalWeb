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

<form:form method="POST" commandName="protectForm"
	enctype="multipart/form-data">

	<spring:message code="protect.file.label" />
	<form:input type="file" path="file" />

	<br />
	<br />

	<spring:message code="protect.password" />
	<form:input type="password" path="password" />
	<br />
	<spring:message code="protect.repeat.password" />
	<form:input type="password" path="repeatedPassword" />


	<br />
	<br />

	<input type="submit" value="${labelButtonValue}">

</form:form>

