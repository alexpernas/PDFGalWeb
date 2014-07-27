<%@ include file="/WEB-INF/jsp/common/includes.jsp"%>
<%@ include file="/WEB-INF/jsp/common/download.jsp" %>

<spring:url value="/bookmark" var="execute" />


<div class="padding_left">
	<h3>
		<spring:message code="bookmark.subtitle" />
	</h3>
</div>


<form:form method="POST" commandName="bookmarkForm"
	enctype="multipart/form-data">

	<div class="container">

<!-- 		<div class="button button_140"> -->
<%-- 			<a id="addFile" href="#"> <spring:message --%>
<%-- 					code="merge.button.add.value" /> --%>
<!-- 			</a> -->
<!-- 		</div> -->

<!-- 		<br /> -->

<!-- 		<table id="fileTable"> -->
<!-- 			<tr> -->
<!-- 				<td><input type="file" name="files[0]" /></td> -->
<!-- 			</tr> -->
<!-- 		</table> -->
<%-- 		<form:errors path="files" class="error" /> --%>

		<br /> <br />

		<j:submitButton code="bookmark.button.value" />

	</div>

</form:form>