<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<c:import url="header.jsp"/>


<a href="<c:url value="/logout" />"><spring:message code="menu.logout" /></a>
<h1>ADMINISTRATION</h1>

<h2><spring:message code="admin.equipe.titre" /></h2>



<h3><spring:message code="admin.equipe.liste" /></h3>
<c:forEach items="equipes" var="equipe">

</c:forEach>

<c:import url="footer.jsp"/>