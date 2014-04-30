<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags"  prefix="spring" %>
<html>
	<head>
		<c:set var="titleLabel">
			<tiles:getAsString name="title"/>
		</c:set>
		<title>
			<spring:message code="${titleLabel}" />
		</title>
	</head>




</html>



<!-- <html>
  <head>
    <title><tiles:getAsString name="title"/></title>
  </head>
  <body>
        <table>
      <tr>
        <td colspan="2">
          <tiles:insertAttribute name="header" />
        </td>
      </tr>
      <tr>
        <td>
          <tiles:insertAttribute name="menu" />
        </td>
        <td>
          <tiles:insertAttribute name="body" />
        </td>
      </tr>
      <tr>
        <td colspan="2">
          <tiles:insertAttribute name="footer" />
        </td>
      </tr>
    </table>
  </body>
</html> -->
