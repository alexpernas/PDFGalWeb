<%@ include file="/WEB-INF/jsp/common/includes.jsp"%>

<spring:url value="/protect" var="execute" />

<!-- Strings to use as values -->
<spring:message code="protect.button.value" var="labelButtonValue" />
<spring:message code="protect.password.placeholder"
	var="labelPasswordPlaceHolder" />
<spring:message code="protect.repeat.password.placeholder"
	var="labelRepeatPasswordPlaceHolder" />

<div class="padding_left">
	<h3>
		<spring:message code="protect.subtitle" />
	</h3>
</div>

<div class="container">
	<spring:message code="protect.description"
		arguments="${labelButtonValue}" />
</div>

<form:form method="POST" commandName="protectForm"
	enctype="multipart/form-data">

	<div class="container">

		<spring:message code="protect.file.label" />
		<form:input type="file" path="file" />

		<br /> <br />

		<div class="horizontal_20">
			<spring:message code="protect.password" />
		</div>

		<form:input type="password" path="password" class="input_password"
			placeholder="${labelPasswordPlaceHolder}" />

		<br /> <br />

		<div class="horizontal_20">
			<spring:message code="protect.repeat.password" />
		</div>

		<form:input type="password" path="repeatedPassword"
			class="input_password"
			placeholder="${labelRepeatPasswordPlaceHolder}" />


		<br /> <br />

		<div class="button button_100">
			<a href="#" onClick="$(this).closest('form').submit()"> <spring:message
					code="protect.button.value" />
			</a>
		</div>

	</div>

</form:form>

