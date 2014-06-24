<%@ include file="/WEB-INF/jsp/common/includes.jsp"%>

<spring:url value="/unprotect" var="execute" />

<!-- Strings to use as values -->
<spring:message code="unprotect.button.value" var="labelButtonValue" />

<div class="padding_left">
	<h3>
		<spring:message code="unprotect.subtitle" />
	</h3>
</div>

<div class="container">
	<spring:message code="unprotect.description"
		arguments="${labelButtonValue}" />
</div>

<br />

<form:form method="POST" commandName="unProtectForm"
	enctype="multipart/form-data">

	<div class="container">

		<spring:message code="unprotect.file.label" />
		<form:input type="file" path="file" />

		<br /> <br />

		<spring:message code="unprotect.password" />
		<form:input type="password" path="password" />


		<br /> <br />

		<div class="button button_100">
			<a href="#" onClick="$(this).closest('form').submit()"> <spring:message
					code="unprotect.button.value" />
			</a>
		</div>

	</div>

</form:form>
