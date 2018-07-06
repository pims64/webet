<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<c:import url="header.jsp" />

<h2>
	<spring:message code="login.title" />
</h2>
<c:if test="${param.error}">
	<span class="error"><spring:message code="login.inconnu" /></span>
</c:if>
<c:if test="${param.logout}">
	<span class="error"><spring:message code="login.logout" /></span>
</c:if>
<form method="POST" action="<c:url value="/login" />">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	<label for="username"><spring:message code="login.username" /></label>
	<input type="text" name="username" value="" placeholder="Email">
	<br>
	<label for="password"><spring:message code="login.password" /></label>
	<input type="password" name="password" value="" placeholder="Mot de passe"> <br>
	<input type="submit" value="<spring:message code="login.submit" />" />
</form>


<c:import url="footer.jsp" />