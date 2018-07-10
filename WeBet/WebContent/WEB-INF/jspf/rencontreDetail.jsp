<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
	
	<c:import url="header.jsp" />
	
	
<div class="container-fluid bg-3 text-center">

	<a href="${pageContext.request.contextPath}/admincontrolleur/goToAdmin"><spring:message code="menu.retour.admin" /></a><br>

	<hr/>

	<div class="row">
		<div class="col-sm-2">
			<span class="row-header"><c:out value="${sport.nom}"/></span>
		</div>
		<div class="col-sm-8">
			<form method="POST"
				action="${pageContext.request.contextPath}/rencontrecontrolleur/creer"
				modelAttribute="rencontre" class="form-inline">
	
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<input type="hidden" name="equipeDomicile.sport.id" value="${sportId}" />
				<input type="hidden" name="isResultNeeded" value="${isResultNeeded}" />
				<form:hidden path="rencontre.id" />
				
				<table class="table">
					<tr>
						<td>
							<div class="form-group">
								<form:label path="rencontre.equipeDomicile.id">
									<spring:message code="rencontre.equipeDomicile" /><span class="required">*</span>
								</form:label>
								<form:select path="rencontre.equipeDomicile.id" cssClass="form-control">
								        <form:options items="${equipes}" itemValue="id" itemLabel="nom" />
								</form:select>
							</div>
						</td>
						<td>
							<div class="form-group">
								<form:label path="rencontre.equipeVisiteur.id">
								<spring:message code="rencontre.equipeVisiteur" /><span class="required">*</span>
								</form:label>
								<form:select path="rencontre.equipeVisiteur.id" cssClass="form-control">
								<form:options items="${equipes}" itemValue="id" itemLabel="nom" />
								</form:select>
								<form:errors path="error.rencontre.equipeVisiteur.identique" cssClass="errors" />
							</div>
						</td>
						<td></td>
					</tr>
					<tr>
					
					<!-- Déclaration du date pattern en tant que var pour utilisation dans les placeholders -->
					<spring:message code="rencontre.date.pattern" var="datePattern"/>
						<td>
							<div class="form-group">
								<form:label path="rencontre.dateDebut">
									<spring:message code="rencontre.dateDebut" />
									<span class="required">*</span>
								</form:label>
					
								<form:input path="rencontre.dateDebut" cssClass="form-control" 
									placeholder="${datePattern}" />
								&nbsp;
								<form:errors path="rencontre.dateDebut" cssClass="errors" />
							</div>
						</td>
						<td>
							<div class="form-group">
								<form:label path="rencontre.dateFin">
									<spring:message code="rencontre.dateFin" />
									<span class="required">*</span>
								</form:label>
								<form:input path="rencontre.dateFin" cssClass="form-control"
									placeholder="${datePattern}" />
								&nbsp;
								<form:errors path="rencontre.dateFin" cssClass="errors" />
							</div>
						</td>
						<td></td>	
					</tr>
					<tr>
						<spring:message code="rencontre.coteVisiteur" var="coteVisiteur"/>
						<spring:message code="rencontre.coteDomicile" var="coteDomicile"/>
						<td>
							<div class="form-group">
								<form:label path="rencontre.coteDomicile">
									<spring:message code="rencontre.coteDomicile" />
									<span class="required">*</span>
								</form:label>
								<form:input path="rencontre.coteDomicile" cssClass="form-control"
									placeholder="${coteDomicile}" />
								&nbsp;
								<form:errors path="rencontre.coteDomicile" cssClass="errors" />
							</div>
						</td>
						<td>
							<div class="form-group">
								<form:label path="rencontre.coteVisiteur">
									<spring:message code="rencontre.coteVisiteur" />
									<span class="required">*</span>
								</form:label>
								<form:input path="rencontre.coteVisiteur" cssClass="form-control"
									placeholder="${coteVisiteur}" />
								&nbsp;
								<form:errors path="rencontre.coteVisiteur" cssClass="errors" />
							</div>
						</td>
						<td>
							<div class="form-group">
								<form:label path="rencontre.coteNul">
									<spring:message code="rencontre.coteNul" />
									<span class="required">*</span>
								</form:label>
								<form:input path="rencontre.coteNul" cssClass="form-control"
									placeholder="${coteVisiteur}" />
								&nbsp;
								<form:errors path="rencontre.coteNul" cssClass="errors" />
							</div>
						</td>
					</tr>
					
					<!-- Affichage des champs du résultat uniquement si la date est passée -->
					<c:if test="${isResultNeeded}">
						<spring:message code="rencontre.scoreDomicile" var="scoreDomicile"/>
						<spring:message code="rencontre.scoreVisiteur" var="scoreVisiteur"/>
						<tr>
							<td>
								<div class="form-group">
									<form:label path="rencontre.scoreDomicile">
										<spring:message code="rencontre.scoreDomicile" />
										<span class="required">*</span>
									</form:label>
									<form:input path="rencontre.scoreDomicile" cssClass="form-control"
										placeholder="${scoreDomicile}" />
									&nbsp;
									<form:errors path="rencontre.coteNul" cssClass="errors" />
								</div>
							</td>
							<td>
								<div class="form-group">
									<form:label path="rencontre.scoreVisiteur">
										<spring:message code="rencontre.scoreVisiteur" />
										<span class="required">*</span>
									</form:label>
									<form:input path="rencontre.scoreVisiteur" cssClass="form-control"
										placeholder="${scoreVisiteur}" />
									&nbsp;
									<form:errors path="rencontre.coteNul" cssClass="errors" />
								</div>
							</td>
						</tr>
					</c:if>
				</table>
				
				<input type="submit" value="<spring:message code="rencontre.creer.submit" />"
					class="btn btn-default" />
			</form>
		</div>
		
	<div class="col-sm-2"></div>
	
	</div>
	
<hr/>
	<div class="row">
		
		<div class="col-sm-2">
			<span class="row-header"><spring:message code="admin.rencontre.liste" /></span>
		</div>
		
		<div class="col-sm-8">
			<table class="table">
				<tr>
					<th><spring:message code="rencontre.table.id" /></th>
					<th><spring:message code="rencontre.table.equipeDomicile" /></th>
					<th><spring:message code="rencontre.table.equipeVisiteur" /></th>
					<th><spring:message code="rencontre.table.dateDebut" /></th>
					<th><spring:message code="rencontre.table.dateFin" /></th>
					<th><spring:message code="rencontre.table.coteDomicile" /></th>
					<th><spring:message code="rencontre.table.coteVisiteur" /></th>
					<th><spring:message code="rencontre.table.coteNul" /></th>
					<th><spring:message code="rencontre.table.scoreDomicile" /></th>
					<th><spring:message code="rencontre.table.scoreVisiteur" /></th>
					<th><spring:message code="rencontre.table.modifier" /></th>
					<th><spring:message code="rencontre.table.supprimer" /></th>
				</tr>
				<tbody>
					<c:forEach items="${rencontres}" var="rencontre">
						<tr>
							<td>${rencontre.id }</td>
							<td>${rencontre.equipeDomicile.nom }</td>
							<td>${rencontre.equipeVisiteur.nom }</td>
							<td>${rencontre.dateDebut }</td>
							<td>${rencontre.dateFin }</td>
							<td>${rencontre.coteDomicile }</td>
							<td>${rencontre.coteVisiteur }</td>
							<td>${rencontre.coteNul }</td>
							<td>${rencontre.scoreDomicile }</td>
							<td>${rencontre.scoreVisiteur }</td>
							<td>
								<a href="<c:url value="/rencontrecontrolleur/modifier/${sportId}/${rencontre.id}" />">
									<spring:message code="rencontre.modifier" />
								</a>
							</td>
							<td>
								<a href="<c:url value="/rencontrecontrolleur/supprimer/${sportId}/${rencontre.id}" />">
									<spring:message code="rencontre.supprimer" />
								</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="col-sm-2"></div>
	</div>

	<hr/>
	
	<%-- <div class="row">
		
		<div class="col-sm-2">
			<span class="row-header"><spring:message code="admin.rencontre.resultat" /></span>
		</div>
		
		<div class="col-sm-8">
		
			<form method="POST"	action="${pageContext.request.contextPath}/rencontrecontrolleur/resultat"
				modelAttribute="rencontre" class="form-inline">
				
				<form:hidden path="rencontre.id"/>
				
				<spring:message code="rencontre.scoreDomicile" var="scoreDomicile"/>
				<spring:message code="rencontre.scoreVisiteur" var="scoreVisiteur"/>
			
				<div class="form-group">
					<form:label path="rencontre.scoreDomicile">
						<spring:message code="${rencontre.equipeVisiteur.nom }" />
						<span class="required">*</span>
					</form:label>
					<form:input path="rencontre.scoreDomicile" cssClass="form-control"
						placeholder="${scoreDomicile }" />
					&nbsp;
					<form:errors path="recontre.scoreDomicile" cssClass="errors" />
				</div>
				
				<div class="form-group">
					<form:label path="rencontre.scoreDomicile">
						<spring:message code="${rencontre.equipeVisiteur.nom }" />
						<span class="required">*</span>
					</form:label>
					<form:input path="rencontre.scoreDomicile" cssClass="form-control"
						placeholder="${scoreVisiteur }" />
					&nbsp;
					<form:errors path="recontre.scoreDomicile" cssClass="errors" />
				</div>
				
				<input type="submit" value="<spring:message code="rencontre.resultat.submit" />"
					class="btn btn-default" />
			
			</form>
		
		</div>
		
		<div class="col-sm-2"></div>
	</div> --%>

</div>

	

	<c:import url="footer.jsp" />
