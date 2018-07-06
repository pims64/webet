<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<h2>
	<spring:message code="admin.equipe.titre" />
</h2>

<h3>
	<spring:message code="admin.equipe.creer" />
</h3>
<form method="POST"	action="${pageContext.request.contextPath}/equipecontrolleur/creer"	modelAttribute="equipe" class="form-inline">

	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	<form:hidden path="equipe.id"/>
	<div class="form-group">
	<form:label path="equipe.nom"><spring:message code="equipe.nom" /><span class="required">*</span></form:label>
	<form:input path="equipe.nom" cssClass="form-control" placeholder="nom de l'équipe"/>&nbsp;<form:errors path="equipe.nom" cssClass="errors" />
	</div>
	<div class="form-group">
	<form:label path="equipe.sport.nom"><spring:message code="equipe.sport" /></form:label>
		<form:select path="equipe.sport.id" cssClass="form-control">
			<form:option value="" label="" />
			<form:options items="${sports}" itemValue="id" itemLabel="nom" />
		</form:select>&nbsp;<form:errors path="equipe.sport.nom" cssClass="errors" />
		</div>
<input type="submit" value="<spring:message code="equipe.creer.submit" />" class="btn btn-success"/>
</form>

<h3>
	<spring:message code="admin.equipe.liste" />
</h3>
<table class="table">
	<thead>
		<th><spring:message code="equipe.table.id" /></th>
		<th><spring:message code="equipe.table.nom" /></th>
		<th><spring:message code="equipe.table.sport" /></th>
		<th><spring:message code="equipe.table.supprimer" /></th>
	</thead>
	<tbody>
		<c:forEach items="${equipes}" var="equipe">
			<tr>
				<td>${equipe.id }</td>
				<td>${equipe.nom }</td>
				<td>${equipe.sport.nom }</td>
				<td>
					<a href="<c:url value="/equipecontrolleur/supprimer/${equipe.id}" />">
						<spring:message code="equipe.supprimer" />
					</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>