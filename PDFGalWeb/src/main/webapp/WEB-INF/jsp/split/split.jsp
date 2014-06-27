<%@ include file="/WEB-INF/jsp/common/includes.jsp"%>

<spring:url value="/split" var="execute" />

<!-- Strings to use as values -->
<spring:message code="split.button.value" var="labelButtonValue" />
<spring:message code="split.pages.placeholder" var="labelPlaceHolder" />

<div class="padding_left">
	<h3>
		<spring:message code="split.subtitle" />
	</h3>
</div>

<div class="container">
	<spring:message code="split.description"
		arguments="${labelButtonValue}" />
</div>

<div class="container">

	<h5>
		<spring:message code="NUMBER_OF_PAGES" />
	</h5>

	<spring:message code="split.description.total"
		arguments="${labelButtonValue}" />

	<h5>
		<spring:message code="CONCRETE_PAGES_TO_SPLIT" />
	</h5>

	<spring:message code="split.description.concrete"
		arguments="${labelButtonValue}" />

	<br /> <br />

	<div class="padding_left_50">
		<spring:message code="split.description.concrete.good"
			arguments="${labelButtonValue}" />

		<br /> <br />

		<spring:message code="split.description.concrete.bad"
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
		<form:input path="pages" class="input" placeholder="${labelPlaceHolder}" />

		<br /> <br />

		<div class="button button_100">
			<a href="#" onClick="$(this).closest('form').submit()"> <spring:message
					code="split.button.value" />
			</a>
		</div>

	</div>

</form:form>
