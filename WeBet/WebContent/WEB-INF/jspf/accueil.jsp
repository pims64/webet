<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:import url="header.jsp"/>

<%-- <div class="container-fluid bg-3 text-center">
<div class="row">
	<div class="col-sm-2"></div>
	<div class="col-sm-8"><img src="<c:url value="/static/images/pari.jpg"/>" alt="Offre WeBet!!" width="100%"/></div>
	<div class="col-sm-2"></div>
</div>
</div> --%>

<!-- Affichage des rencontres sujets aux paris-->

<div class="container-fluid bg-3 text-center">
  	<h2>
		<spring:message code="accueil.rencontres.a.venir" />
	</h2>
	</div>
	
	<c:forEach items="${rencontres}" var="rencontre" varStatus="loop">

		<c:if test="${(loop.count-1) % 3 == 0 }">
			<div class="container-fluid bg-3 text-center">
		  	<div class="row-accueil">
		</c:if>
		
    	<div class="col-sm-3 pari">
	    	<span class="pari-equipe">${rencontre.equipeDomicile.nom }</span>
	    	<br>VS<br>
	    	<span class="pari-equipe">${rencontre.equipeVisiteur.nom }</span>
	    	<br>
	    	
	    	<spring:message code="rencontre.indication.debut" /> 
	    	<span class="pari-date"><fmt:formatDate value="${rencontre.dateDebut}" pattern="dd MMM y (HH:mm)" /></span>
	    	<br>
	    	
	    	<spring:message code="rencontre.indication.fin" /> 
	    	<span class="pari-date"><fmt:formatDate value="${rencontre.dateFin}" pattern="dd MMM y (HH:mm)" /></span>
	    	<br>
	    	<br>
	    	<sec:authorize access="hasRole('ROLE_USER')">
				<a	href="<c:url value="/paricontrolleur/goToCreer/${rencontre.id }" />" class="btn btn-default">
					<spring:message code="rencontre.action.parier" />
				</a>
			</sec:authorize>
    	</div>
    	
    	<c:if test="${loop.count % 3 == 0 }">
    	
			</div></div>
		</c:if>
		
   </c:forEach>
</div>

<%-- <div class="container-fluid bg-3 text-center">
	<div class="row">
		<div class="col-sm-2"></div>
		
		<div class="col-sm-8">	
			<table class="table">
			<tr>
				<th><spring:message code="rencontre.table.id" /></th>
				<th><spring:message code="rencontre.equipeDomicile" /></th>
				<th><spring:message code="rencontre.equipeVisiteur" /></th>
				<th><spring:message code="rencontre.debut" /></th>
				<th><spring:message code="rencontre.fin" /></th>
				<sec:authorize access="hasRole('ROLE_USER')"><th><spring:message code="rencontre.indication.parier" /></th></sec:authorize>
			</tr>
			<tbody>
			<c:forEach items="${rencontres}" var="rencontre">
				<tr>
					<td>${rencontre.id }</td>
					<td>${rencontre.equipeDomicile.nom }</td>
					<td>${rencontre.equipeVisiteur.nom }</td>
					<td><fmt:formatDate value="${rencontre.dateDebut}" pattern="dd/MM/yyyy HH:mm" /></td>
					<td><fmt:formatDate value="${rencontre.dateFin}" pattern="dd/MM/yyyy HH:mm" /></td>
					<td>${rencontre.dateDebut }</td>
					<td>${rencontre.dateFin }</td>
					<sec:authorize access="hasRole('ROLE_USER')"><td>
						<a	href="<c:url value="/paricontrolleur/goToCreer/${rencontre.id }" />">
							<spring:message code="rencontre.action.parier" />
					</a></td></sec:authorize>
				</tr>
			</c:forEach>
			</tbody>
			</table>
		</div>
		<div class="col-sm-2"></div>
	</div>
</div> --%>
<c:import url="footer.jsp"/>