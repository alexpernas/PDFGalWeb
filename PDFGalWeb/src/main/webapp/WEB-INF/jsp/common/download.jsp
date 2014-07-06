<%@ include file="/WEB-INF/jsp/common/includes.jsp"%>

<spring:url
	value="/download.html?uri=${downloadForm.uri}&name=${downloadForm.name}"
	var="download" />

<c:if test="${not empty downloadForm}">

	<script type="text/javascript">
		// <![CDATA[

		$(function() {
			window.location = '${download}';
		});

		// ]]>
	</script>

</c:if>
