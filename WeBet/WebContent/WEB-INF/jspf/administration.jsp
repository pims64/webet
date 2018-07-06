<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<c:import url="header.jsp" />


<a href="<c:url value="/logout" />"><spring:message
		code="menu.logout" /></a>
<h1>ADMINISTRATION</h1>


<div>
	<c:import url="equipes.jsp" />
</div>

<div>
	<c:import url="clients.jsp" />
</div>

<c:import url="footer.jsp" />