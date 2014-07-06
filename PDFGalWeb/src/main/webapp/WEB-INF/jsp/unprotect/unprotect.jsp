<%@ include file="/WEB-INF/jsp/common/includes.jsp"%>
<%@ include file="/WEB-INF/jsp/common/download.jsp" %>

<spring:url value="/unprotect" var="execute" />

<!-- Strings to use as values -->
<spring:message code="unprotect.button.value" var="labelButtonValue" />
<spring:message code="unprotect.password.placeholder"
	var="labelPlaceHolder" />

<div class="padding_left">
	<h3>
		<spring:message code="unprotect.subtitle" />
	</h3>
</div>

<div class="container">
	<spring:message code="unprotect.description"
		arguments="${labelButtonValue}" />
</div>

<form:form method="POST" commandName="unProtectForm"
	enctype="multipart/form-data">

	<div class="container">

		<j:input type="file" path="file" code="unprotect.file.label" />

		<br /> <br />

		<j:input type="password" path="password" code="unprotect.password"
			placeHolder="${labelPlaceHolder}" styleClass="input_password" />


		<br /> <br />

		<j:submitButton code="unprotect.button.value" />

	</div>

</form:form>
