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

		<j:input type="file" path="file" code="protect.file.label" />

		<br /> <br />

		<j:input type="password" path="password" code="protect.password"
			placeHolder="${labelPasswordPlaceHolder}" styleClass="input_password"
			labelClass="horizontal_20" />

		<br />

		<j:input type="password" path="repeatedPassword"
			code="protect.repeat.password"
			placeHolder="${labelRepeatPasswordPlaceHolder}"
			styleClass="input_password" labelClass="horizontal_20" />


		<br /> <br />

		<j:submitButton code="protect.button.value" />

	</div>

</form:form>

