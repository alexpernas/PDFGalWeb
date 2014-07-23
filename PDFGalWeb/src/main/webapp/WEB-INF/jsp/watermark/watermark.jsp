<%@ include file="/WEB-INF/jsp/common/includes.jsp"%>
<%@ include file="/WEB-INF/jsp/common/download.jsp"%>

<spring:url value="/watermark" var="execute" />

<!-- Strings to use as values -->
<spring:message code="watermark.text.placeholder" var="labelTextPlaceHolder" />

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

		<j:submitButton code="watermark.button.value" />

	</div>

</form:form>
