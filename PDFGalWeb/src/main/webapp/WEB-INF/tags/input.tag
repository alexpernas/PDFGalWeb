<%@ include file="/WEB-INF/jsp/common/includes.jsp"%>

<%@ attribute name="styleClass" required="false"
	description="Class for input"%>
<%@ attribute name="code" required="true"
	description="Code for input label"%>
<%@ attribute name="labelClass" required="false"
	description="Class for label"%>
<%@ attribute name="path" required="true"
	description="Path to property for data binding"%>
<%@ attribute name="placeHolder" required="false"
	description="Placeholder for input"%>
<%@ attribute name="type" required="false" description="Input type"%>

<c:choose>
	<c:when test="${not empty labelClass}">
		<div class="${labelClass}">
			<spring:message code="${code}" />
		</div>
	</c:when>
	<c:otherwise>
		<spring:message code="${code}" />
	</c:otherwise>
</c:choose>

<form:input type="${type}" path="${path}" class="${styleClass}"
	placeholder="${placeHolder}" />
<br />
<form:errors path="${path}" class="error" />
