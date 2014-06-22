<%@ include file="/WEB-INF/jsp/common/includes.jsp"%>

<spring:url value="/merge" var="execute" />

<!-- Strings to use as values -->
<spring:message code="merge.button.add.value" var="labelAddButtonValue" />
<spring:message code="merge.button.value" var="labelButtonValue" />


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


<div>
	<h3>
		<spring:message code="merge.subtitle" />
	</h3>
</div>

<div>
	<spring:message code="merge.description"
		arguments="${labelButtonValue},${labelAddButtonValue}" />
</div>

<br />


<form:form method="POST" commandName="mergeForm"
	enctype="multipart/form-data">

	<input id="addFile" type="button" value="${labelAddButtonValue}" />

	<br />
	<br />

	<table id="fileTable">
		<tr>
			<td><input type="file" name="files[0]" /></td>
		</tr>
	</table>

	<br />
	<br />

	<br />
	<br />

	<spring:message code="merge.file.name.label" />
	<br />
	<form:input path="fileName" />

	<input type="submit" value="${labelButtonValue}">

</form:form>
