<%@ include file="/WEB-INF/jsp/common/includes.jsp"%>

<spring:url value="/split" var="execute" />

<!-- Strings to use as values -->
<spring:message code="split.button.value" var="labelButtonValue" />

<div>
	<h3>
		<spring:message code="split.subtitle" />
	</h3>
</div>

<div>
	<spring:message code="split.description"
		arguments="${labelButtonValue}" />
</div>

	<br />

<div style="margin-left:60px;">
	<spring:message code="split.description.mode1"
		arguments="${labelButtonValue}" />
</div>

	<br />

<div style="margin-left:200px;">
	<spring:message code="split.description.mode1.good"
		arguments="${labelButtonValue}" />
</div>

	<br />

<div style="margin-left:200px;">
	<spring:message code="split.description.mode1.bad"
		arguments="${labelButtonValue}" />
</div>

	<br />

<div style="margin-left:60px;">
	<spring:message code="split.description.mode2"
		arguments="${labelButtonValue}" />
</div>

<br />
