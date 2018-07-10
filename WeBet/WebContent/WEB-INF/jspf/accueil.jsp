<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<c:import url="header.jsp"/>

<div class="row">
	<div class="col-sm-2"></div>
	<div class="col-sm-8"><img src="<c:url value="/static/images/pari.jpg"/>" alt="Offre WeBet!!" width="100%"/></div>
	<div class="col-sm-2"></div>
</div>

<div class="container">

<!-- Affichage des rencontres sujets aux paris-->
<h4>
	<spring:message code="accueil.rencontres.a.venir" />
</h4>

<div class="col-sm-8">
	<table class="table">
		<tr>
			<th><spring:message code="rencontre.table.id" /></th>
			<th><spring:message code="rencontre.equipeDomicile" /></th>
			<th><spring:message code="rencontre.equipeVisiteur" /></th>
			<sec:authorize access="hasRole('ROLE_USER')"><th><spring:message code="rencontre.indication.parier" /></th></sec:authorize>
		</tr>
		<tbody>
			<c:forEach items="${rencontres}" var="rencontre">
				<tr>
					<td>${rencontre.id }</td>
					<td>${rencontre.equipeDomicile.nom }</td>
					<td>${rencontre.equipeVisiteur.nom }</td>
					<sec:authorize access="hasRole('ROLE_USER')"><td>
						<a	href="<c:url value="/paricontrolleur/goToCreer/${rencontre.id }" />">
							<spring:message code="rencontre.action.parier" />
					</a></td></sec:authorize>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
</div>

<c:import url="footer.jsp"/>