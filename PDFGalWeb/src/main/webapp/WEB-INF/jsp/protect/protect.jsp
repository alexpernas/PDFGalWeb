<%@ include file="/WEB-INF/jsp/common/includes.jsp"%>

<spring:url value="/protect" var="execute" />

<!-- Strings to use as values -->
<spring:message code="protect.button.value" var="labelButtonValue" />

<div class="padding_left">
	<h3>
		<spring:message code="protect.subtitle" />
	</h3>
</div>

<div class="container">
	<spring:message code="protect.description"
		arguments="${labelButtonValue}" />
</div>

<br />

<form:form method="POST" commandName="protectForm"
	enctype="multipart/form-data">

	<div class="container">

		<spring:message code="protect.file.label" />
		<form:input type="file" path="file" />

		<br /> <br />

		<spring:message code="protect.password" />
		<form:input type="password" path="password" />
		<br />
		<spring:message code="protect.repeat.password" />
		<form:input type="password" path="repeatedPassword" />


		<br /> <br />

		<div class="button button_100">
			<a href="#" onClick="$(this).closest('form').submit()"> <spring:message
					code="protect.button.value" />
			</a>
		</div>

	</div>

</form:form>

