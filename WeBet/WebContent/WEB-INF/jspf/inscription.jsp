<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:import url="header.jsp"/>

	<h2>
		<spring:message code="client.header" />
	</h2>
	<form method="POST" action="${pageContext.request.contextPath}/clientcontrolleur/creer" modelAttribute="client">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

		<form:label path="client.nom"><spring:message code="client.nom" /><span class="required">*</span></form:label>
		<form:input path="client.nom" />&nbsp;<form:errors path="client.nom" cssClass="errors" />
		<br>
		<form:label path="client.prenom"><spring:message code="client.prenom" /><span class="required">*</span></form:label>
		<form:input path="client.prenom" />&nbsp;<form:errors path="client.prenom" cssClass="errors" />
		<br>
		<form:label path="client.utilisateur.email"><spring:message code="client.email" /><span class="required">*</span></form:label>
		<form:input path="client.utilisateur.email" />&nbsp;<form:errors path="client.utilisateur.email" cssClass="errors" />
		<br>
		<form:label path="client.utilisateur.motDePasse"><spring:message code="client.motDePasse" /><span class="required">*</span></form:label>
		<form:password path="client.utilisateur.motDePasse" />&nbsp;<form:errors path="client.utilisateur.motDePasse" cssClass="errors" />
		<br>
		<form:label path="client.utilisateur.role"><spring:message code="client.role" /></form:label>
		<form:select path="client.utilisateur.role">
			<form:option value="" label="" />
			<form:options items="${roles}" itemValue="name" itemLabel="name" />
		</form:select>&nbsp;<form:errors path="client.utilisateur.role" cssClass="errors" />
		<br>
		<form:label path="client.adresse.rue"><spring:message code="adresse.rue" /></form:label>
		<form:input path="client.adresse.rue" />
		<br>
		<form:label path="client.adresse.codePostal"><spring:message code="adresse.codePostal" /><span class="required">*</span></form:label>
		<form:input path="client.adresse.codePostal" />&nbsp;<form:errors path="client.adresse.codePostal" cssClass="errors" />
		<br>
		<form:label path="client.adresse.ville"><spring:message code="adresse.ville" /><span class="required">*</span></form:label>
		<form:input path="client.adresse.ville" />&nbsp;<form:errors path="client.adresse.ville" cssClass="errors" />
		<br>
		<input type="submit" value="<spring:message code="client.creer.submit" />" />
	</form>

<c:import url="footer.jsp"/>