<%@ include file="/WEB-INF/jsp/common/includes.jsp"%>
<%@ include file="/WEB-INF/jsp/common/download.jsp"%>

<spring:url value="/watermark" var="execute" />

<!-- Strings to use as values -->
<spring:message code="watermark.button.value" var="labelButtonValue" />
<spring:message code="watermark.pages.placeholder"
	var="labelPagesPlaceHolder" />
<spring:message code="watermark.text.placeholder"
	var="labelTextPlaceHolder" />

<div class="padding_left">
	<h3>
		<spring:message code="watermark.subtitle" />
	</h3>
</div>

<div class="container">
	<spring:message code="watermark.description.start" />
	<br />
	<br />
	<spring:message code="watermark.description.text" />
	<br />
	<br />
	<spring:message code="watermark.description.color" />
	<br />
	<br />
	<spring:message code="watermark.description.transparence" />
	<br />
	<br />
	<spring:message code="watermark.description.position" />
	<br />
	<br />
	<spring:message code="watermark.description.pages" />
	<br />
	<br />
	<spring:message code="watermark.description.end"
		arguments="${labelButtonValue}" />
</div>

<form:form method="POST" commandName="watermarkForm"
	enctype="multipart/form-data">

	<div class="container">

		<j:input type="file" path="file" code="watermark.file.label" />

		<br /> <br />

		<j:input path="text" code="watermark.text.label"
			placeHolder="${labelTextPlaceHolder}" styleClass="input" />

		<br /> <br />

		<spring:message code="watermark.color.label" />
		<form:select path="color">
			<c:forEach var="item" items="${colors}">
				<form:option value="${item}">
					<spring:message code="${item}" />
				</form:option>
			</c:forEach>
		</form:select>

		<br /> <br />

		<spring:message code="watermark.alpha.label" />
		<form:select path="alpha" items="${alphaList}" />

		<br /> <br />

		<spring:message code="watermark.watermarkposition.label" />
		<br />
		<form:select path="watermarkPosition">
			<c:forEach var="item" items="${watermarkPositions}">
				<form:option value="${item}">
					<spring:message code="${item}" />
				</form:option>
			</c:forEach>
		</form:select>

		<br /> <br />

		<j:input path="pages" code="watermark.pages.label"
			placeHolder="${labelPagesPlaceHolder}" styleClass="input" />

		<br /> <br />

		<j:submitButton code="watermark.button.value" />

	</div>

</form:form>
