<%@ include file="/WEB-INF/jsp/common/includes.jsp"%>

<%@ attribute name="code" required="true"
	description="Code for button label"%>

<div class="button button_100">
	<a href="#" onClick="$(this).closest('form').submit()"> <spring:message
			code="${code}" />
	</a>
</div>
