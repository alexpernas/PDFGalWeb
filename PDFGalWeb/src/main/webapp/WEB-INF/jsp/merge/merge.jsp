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


<div class="padding_left">
	<h3>
		<spring:message code="merge.subtitle" />
	</h3>
</div>

<div class="container">
	<spring:message code="merge.description"
		arguments="${labelButtonValue},${labelAddButtonValue}" />
</div>

<br />


<form:form method="POST" commandName="mergeForm"
	enctype="multipart/form-data">

	<div class="container">
	
	<div class="button button_140">
			<a id="addFile" href="#">
			<spring:message code="merge.button.add.value" />
			</a>
		</div>

		<br />
		<br />

		<table id="fileTable">
			<tr>
				<td><input type="file" name="files[0]" /></td>
			</tr>
		</table>

		<br /> <br />

		<spring:message code="merge.file.name.label" />
		<br /> <br />
		<form:input path="fileName" />

		<br /> <br />

		<div class="button button_100">
			<a href="#" onClick="$(this).closest('form').submit()"> <spring:message
					code="merge.button.value" />
			</a>
		</div>

	</div>

</form:form>
