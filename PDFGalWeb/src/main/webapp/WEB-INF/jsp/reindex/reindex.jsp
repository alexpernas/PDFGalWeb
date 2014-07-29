<%@ include file="/WEB-INF/jsp/common/includes.jsp"%>
<%@ include file="/WEB-INF/jsp/common/download.jsp"%>

<spring:url value="/reindex" var="execute" />

<!-- Strings to use as values -->
<spring:message code="reindex.button.add.value"
	var="labelAddButtonValue" />
<spring:message code="reindex.button.value" var="labelButtonValue" />
<spring:message code="reindex.page.label" var="labelPage" />
<spring:message code="reindex.style.label" var="labelStyle" />

<script type="text/javascript">
	// <![CDATA[

	$(function() {
		//Add more indexation components if add button is clicked
		$('#addIndexation')
				.click(
						function() {
							var reindexationIndex = $('#indexationTable tr')
									.children().length;
							$('#indexationTable')
									.append(
											'<tr><td>'
													+ '   <input type="text" name="pagesList['+ reindexationIndex +']" class="input_short" placeholder="${labelPage}" />'
													+ '</td> <td>'
													+ '   <select name="numberingStylesList['+ reindexationIndex +']"> '
													+ ' <c:forEach var="item" items="${numberingStyles}"> '
													+ ' <option value="${item}"> '
													+ ' <spring:message code="${item}" /> '
													+ ' </option> '
													+ ' </c:forEach> '
													+ ' </select>'
													+ '</td> </tr>');
						});
	});

	// ]]>
</script>

<div class="padding_left">
	<h3>
		<spring:message code="reindex.subtitle" />
	</h3>
</div>

<div class="container">
	<spring:message code="reindex.description"
		arguments="${labelButtonValue},${labelAddButtonValue}" />
</div>

<form:form method="POST" commandName="reindexForm"
	enctype="multipart/form-data">

	<div class="container">

		<j:input type="file" path="file" code="reindex.file.label" />

		<br />

		<div class="button button_140">
			<a id="addIndexation" href="#"> <spring:message
					text="${labelAddButtonValue}" />
			</a>
		</div>

		<br />

		<div class="horizontal_short">
			<spring:message text="${labelPage}" />
		</div>
		<div class="bold">
			<spring:message text="${labelStyle}" />
		</div>

		<table id="indexationTable">
			<tr>
				<td><input type="text" name="pagesList[0]" class="input_short"
					placeholder="${labelPage}" /></td>
				<td><select name="numberingStylesList[0]">
						<c:forEach var="item" items="${numberingStyles}">
							<option value="${item}">
								<spring:message code="${item}" />
							</option>
						</c:forEach>
				</select></td>
			</tr>
		</table>
		<form:errors path="numberingStylesList" class="error" />

		<br /> <br />

		<j:submitButton code="reindex.button.value" />

	</div>

</form:form>

