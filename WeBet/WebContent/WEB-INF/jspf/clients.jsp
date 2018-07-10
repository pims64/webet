<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<hr/>
	<h2 class="h2-admin">
		<spring:message code="admin.client.titre" />
	</h2>
	
<hr/>
	<div class="row">
		
		<div class="col-sm-2">
			<span class="row-header"><spring:message code="admin.client.liste" /></span>
		</div>
		
		<div class="col-sm-8">
			<table class="table">
				<tr>
					<th><spring:message code="client.table.id" /></th>
					<th><spring:message code="client.table.prenom" /></th>
					<th><spring:message code="client.table.nom" /></th>
					<th><spring:message code="client.table.email" /></th>
					<th><spring:message code="client.table.role" /></th>
					<th><spring:message code="client.table.supprimer" /></th>
				</tr>
					<c:forEach items="${clients}" var="client">
						<tr>
							<td>${client.id }</td>
							<td>${client.prenom }</td>
							<td>${client.nom }</td>
							<td>${client.utilisateur.email }</td>
							<td>${client.utilisateur.role }</td>
							<td>
								<a href="<c:url value="/clientcontrolleur/supprimer/${client.id}" />">
									<spring:message code="client.supprimer" />
								</a>
							</td>
						</tr>
					</c:forEach>
			</table>
		</div>
		<div class="col-sm-2"></div>
	</div>