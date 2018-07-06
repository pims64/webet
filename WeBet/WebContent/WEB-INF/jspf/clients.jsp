<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<h2>
	<spring:message code="admin.client.titre" />
</h2>

<h3>
	<spring:message code="admin.client.liste" />
</h3>
<table class="table">
	<thead>
		<th><spring:message code="client.table.id" /></th>
		<th><spring:message code="client.table.prenom" /></th>
		<th><spring:message code="client.table.nom" /></th>
		<th><spring:message code="client.table.email" /></th>
		<th><spring:message code="client.table.supprimer" /></th>
	</thead>
	<tbody>
		<c:forEach items="${clients}" var="client">
			<tr>
				<td>${client.id }</td>
				<td>${client.prenom }</td>
				<td>${client.nom }</td>
				<td>${client.utilisateur.email }</td>
				<td>
					<a href="<c:url value="/clientcontrolleur/supprimer/${client.id}" />">
						<spring:message code="client.supprimer" />
					</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>