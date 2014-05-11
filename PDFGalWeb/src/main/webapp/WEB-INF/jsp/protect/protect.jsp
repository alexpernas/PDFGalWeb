<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>

<spring:url value="/protect" var="execute" />

Protect


<form:form method="POST" enctype="multipart/form-data"
		action="${uploadFile}">
		File to upload: <input type="file" name="file"><br />Contrasinal: <input
			type="text" name="password"><br /> <br /> <input type="submit"
			value="execute"> Press here to upload the file!
</form:form>

