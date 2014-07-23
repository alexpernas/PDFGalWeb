<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>

<div class="menu_item">
	<a href="${pageContext.servletContext.contextPath}/home.html">
		<spring:message code="menu.home" />
	</a>
</div>

<div class="menu_item">
	<a href="${pageContext.servletContext.contextPath}/merge.html">
		<spring:message code="menu.merge" />
	</a>
</div>

<div class="menu_item">
	<a href="${pageContext.servletContext.contextPath}/split.html">
		<spring:message code="menu.split" />
	</a>
</div>

<div class="menu_item">
	<a href="${pageContext.servletContext.contextPath}/protect.html">
		<spring:message code="menu.protect" />
	</a>
</div>

<div class="menu_item">
	<a href="${pageContext.servletContext.contextPath}/unprotect.html">
		<spring:message code="menu.unprotect" />
	</a>
</div>

<div class="menu_item">
	<a href="${pageContext.servletContext.contextPath}/watermark.html">
		<spring:message code="menu.watermark" />
	</a>
</div>
