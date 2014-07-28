<%@ include file="/WEB-INF/jsp/common/includes.jsp"%>
<%@ include file="/WEB-INF/jsp/common/download.jsp"%>

<spring:url value="/bookmark" var="execute" />

<!-- Strings to use as values -->
<spring:message code="bookmark.button.add.value"
	var="labelAddButtonValue" />
<spring:message code="bookmark.button.value" var="labelButtonValue" />
<spring:message code="bookmark.page.label" var="labelPage" />
<spring:message code="bookmark.text.label" var="labelText" />
<spring:message code="bookmark.title.placeholder"
	var="labelTitlePlaceHolder" />

<script type="text/javascript">
	// <![CDATA[

	$(function() {
		//Add more bookmark components if add button is clicked
		$('#addBookmark')
				.click(
						function() {
							var bookmarkIndex = $('#bookmarksTable tr')
									.children().length;
							$('#bookmarksTable')
									.append(
											'<tr><td>'
													+ '   <input type="text" name="pagesList['+ bookmarkIndex +']" class="input_short" placeholder="${labelPage}" />'
													+ '</td> <td>'
													+ '   <input type="text" name="textsList['+ bookmarkIndex +']"  class="input" placeholder="${labelText}" />'
													+ '</td> </tr>');
						});
	});

	// ]]>
</script>

<div class="padding_left">
	<h3>
		<spring:message code="bookmark.subtitle" />
	</h3>
</div>

<div class="container">
	<spring:message code="bookmark.description"
		arguments="${labelButtonValue},${labelAddButtonValue}" />
</div>


<form:form method="POST" commandName="bookmarkForm"
	enctype="multipart/form-data">

	<div class="container">

		<j:input type="file" path="file" code="bookmark.file.label" />

		<br />

		<j:input path="title" code="bookmark.title.label"
			placeHolder="${labelTitlePlaceHolder}" styleClass="input" />

		<br />

		<div class="button button_140">
			<a id="addBookmark" href="#"> <spring:message
					text="${labelAddButtonValue}" />
			</a>
		</div>

		<br />

		<div class="horizontal_short">
			<spring:message text="${labelPage}" />
		</div>
		<div class="bold">
			<spring:message text="${labelText}" />
		</div>

		<table id="bookmarksTable">
			<tr>
				<td><input type="text" name="pagesList[0]"
					class="input_short" placeholder="${labelPage}" /></td>
				<td><input type="text" name="textsList[0]" class="input"
					placeholder="${labelText}" /></td>
			</tr>
		</table>
		<form:errors path="textsList" class="error" />

		<br /> <br />

		<j:submitButton code="bookmark.button.value" />

	</div>

</form:form>