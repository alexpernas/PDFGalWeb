<%@ include file="/WEB-INF/jsp/common/includes.jsp"%>

<spring:url value="/split" var="execute" />

<!-- Strings to use as values -->
<spring:message code="split.button.value" var="labelButtonValue" />

<div class="padding_left">
	<h3>
		<spring:message code="split.subtitle" />
	</h3>
</div>

<div class="container">
	<spring:message code="split.description"
		arguments="${labelButtonValue}" />
</div>

<br />

<div class="container">
	<div style="margin-left: 60px;">
		<spring:message code="split.description.mode1"
			arguments="${labelButtonValue}" />
	</div>

	<br />

	<div style="margin-left: 200px;">
		<spring:message code="split.description.mode1.good"
			arguments="${labelButtonValue}" />
	</div>

	<br />

	<div style="margin-left: 200px;">
		<spring:message code="split.description.mode1.bad"
			arguments="${labelButtonValue}" />
	</div>

	<br />

	<div style="margin-left: 60px;">
		<spring:message code="split.description.mode2"
			arguments="${labelButtonValue}" />
	</div>
</div>

<br />


<form:form method="POST" commandName="splitForm"
	enctype="multipart/form-data">

	<div class="container">

		<spring:message code="split.file.label" />
		<form:input type="file" path="file" />

		<br /> <br />

		<spring:message code="split.select.label" />
		<form:select path="splitMode">
			<c:forEach var="item" items="${splitModes}">
				<form:option value="${item}">
					<spring:message code="${item}" />
				</form:option>
			</c:forEach>
		</form:select>

		<br /> <br />

		<spring:message code="split.pages.label" />
		<form:input path="pages" />

		<br /> <br />

		<div class="button button_100">
			<a href="#" onClick="$(this).closest('form').submit()"> <spring:message
					code="split.button.value" />
			</a>
		</div>

	</div>

</form:form>
