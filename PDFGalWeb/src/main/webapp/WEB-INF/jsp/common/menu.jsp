<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>

<div class="menu_item">
	<a href="${pageContext.servletContext.contextPath}/home">
		<spring:message code="menu.home" />
	</a>
</div>

<div class="menu_item">
	<a href="${pageContext.servletContext.contextPath}/merge">
		<spring:message code="menu.merge" />
	</a>
</div>

<div class="menu_item">
	<a href="${pageContext.servletContext.contextPath}/split">
		<spring:message code="menu.split" />
	</a>
</div>

<div class="menu_item">
	<a href="${pageContext.servletContext.contextPath}/protect">
		<spring:message code="menu.protect" />
	</a>
</div>

<div class="menu_item">
	<a href="${pageContext.servletContext.contextPath}/unprotect">
		<spring:message code="menu.unprotect" />
	</a>
</div>