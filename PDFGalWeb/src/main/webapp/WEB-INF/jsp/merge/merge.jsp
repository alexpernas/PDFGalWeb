<%@ include file="/WEB-INF/jsp/common/includes.jsp"%>
<%@ include file="/WEB-INF/jsp/common/download.jsp" %>

<spring:url value="/merge" var="execute" />

<!-- Strings to use as values -->
<spring:message code="merge.button.add.value" var="labelAddButtonValue" />
<spring:message code="merge.button.value" var="labelButtonValue" />
<spring:message code="merge.file.name.placeholder"
	var="labelPlaceHolder" />


<script type="text/javascript">
	// <![CDATA[

	$(function() {
		//Add more file components if add button is clicked
		$('#addFile')
				.click(
						function() {
							var fileIndex = $('#fileTable tr').children().length;
							$('#fileTable')
									.append(
											'<tr><td>'
													+ '   <input type="file" name="files['+ fileIndex +']" />'
													+ '</td></tr>');
						});
	});

	// ]]>
</script>


<div class="padding_left">
	<h3>
		<spring:message code="merge.subtitle" />
	</h3>
</div>

<div class="container">
	<spring:message code="merge.description"
		arguments="${labelButtonValue},${labelAddButtonValue}" />
</div>


<form:form method="POST" commandName="mergeForm"
	enctype="multipart/form-data">

	<div class="container">

		<div class="button button_140">
			<a id="addFile" href="#"> <spring:message
					code="merge.button.add.value" />
			</a>
		</div>

		<br />

		<table id="fileTable">
			<tr>
				<td><input type="file" name="files[0]" /></td>
			</tr>
		</table>
		<form:errors path="files" class="error" />

		<br /> <br />

		<j:input path="fileName" code="merge.file.name.label"
			placeHolder="${labelPlaceHolder}" styleClass="input" />

		<br /> <br />

		<j:submitButton code="merge.button.value" />

	</div>

</form:form>
