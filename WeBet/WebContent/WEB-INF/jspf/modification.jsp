<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<c:import url="header.jsp" />



<div class="container-fluid bg-3 text-center">

	<div class="row">
		<div class="col-sm-3">
			<span class="row-header"><spring:message code="pari.listeParis" /></span>
		</div>
		<div class="col-sm-6">
		
			<table class="table">
				<tr>
					<th><spring:message code="pari.table.equipeDomicile" /></th>
					<th><spring:message code="pari.table.equipeVisiteur" /></th>
					<th><spring:message code="pari.table.mise" /></th>
					<th><spring:message code="pari.table.choixPari" /></th>
					<th><spring:message code="pari.table.gain" /></th>
				</tr>
				<tbody>
					<c:forEach items="${paris}" var="pari">
					<tr>
						<td>${pari.rencontre.equipeDomicile.nom }</td>
						<td>${pari.rencontre.equipeVisiteur.nom }</td>
						<td>${pari.sommePariee }</td>
						<td>${pari.choixPari }</td>
						<td>${pari.gain }</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		
		</div>
		<div class="col-sm-3"></div>
	</div>
	
	<hr><br>

	<div class="row">
	<div class="col-sm-3">
		<span class="row-header"><spring:message code="client.modification.compte" /></span>
	</div>
	<div class="col-sm-6">
		<form method="POST"
			action="${pageContext.request.contextPath}/clientcontrolleur/modifier"
			modelAttribute="client"
			class="form-horizontal">

			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<form:hidden path="client.id"/>
			<form:hidden path="client.age"/>
			<form:hidden path="client.utilisateur.id"/>
			<form:hidden path="client.utilisateur.email"/>
			<form:hidden path="client.utilisateur.role"/>
			

			<div class="form-group">
				<form:label path="client.nom" cssClass="control-label col-sm-2">
					<spring:message code="client.nom" />
					<span class="required">*</span>
				</form:label>
				<div class="col-sm-10">
					<form:input path="client.nom" cssClass="form-control"/>
					&nbsp;
					<form:errors path="client.nom" cssClass="errors" />
				</div>
			</div>
			
			<div class="form-group">
				<form:label path="client.prenom" cssClass="control-label col-sm-2">
					<spring:message code="client.prenom" />
					<span class="required">*</span>
				</form:label>
				<div class="col-sm-10">
					<form:input path="client.prenom" cssClass="form-control"/>
					&nbsp;
					<form:errors path="client.prenom" cssClass="errors" />
				</div>
			</div>
			
			
			<div class="form-group">
				<form:label path="client.utilisateur.motDePasse" cssClass="control-label col-sm-2">
					<spring:message code="client.utilisateur.motDePasse" />
					<span class="required">*</span>
				</form:label>
				<div class="col-sm-10">
					<form:password path="client.utilisateur.motDePasse" cssClass="form-control"/>
					&nbsp;
					<form:errors path="client.utilisateur.motDePasse" cssClass="errors" />
				</div>
			</div>


			<div class="form-group">
				<form:label path="client.montantMaxPari" cssClass="control-label col-sm-2">
					<spring:message code="client.montantMaxPari" />
					<span class="required">*</span>
				</form:label>
				<div class="col-sm-10">
					<form:input path="client.montantMaxPari" placeholder="default 500" cssClass="form-control"/>
					&nbsp;
					<form:errors path="client.montantMaxPari" cssClass="errors" />
				</div>
			</div>
			
			<div class="form-group">
				<form:label path="client.adresse.rue" cssClass="control-label col-sm-2">
					<spring:message code="adresse.rue" />
				</form:label>
				<div class="col-sm-10">
					<form:input path="client.adresse.rue" cssClass="form-control"/>
					&nbsp;
					<form:errors path="client.adresse.rue" cssClass="errors" />
				</div>
			</div>

			<div class="form-group">
				<form:label path="client.adresse.complement" cssClass="control-label col-sm-2">
					<spring:message code="adresse.complement" />
				</form:label>
				<div class="col-sm-10">
					<form:input path="client.adresse.complement" cssClass="form-control"/>
					&nbsp;
					<form:errors path="client.adresse.complement" cssClass="errors" />
				</div>
			</div>

			<div class="form-group">
				<form:label path="client.adresse.codePostal" cssClass="control-label col-sm-2">
					<spring:message code="adresse.codePostal" />
					<span class="required">*</span>
				</form:label>
				<div class="col-sm-10">
					<form:input path="client.adresse.codePostal" cssClass="form-control"/>
					&nbsp;
					<form:errors path="client.adresse.codePostal" cssClass="errors" />
				</div>
			</div>

			<div class="form-group">
				<form:label path="client.adresse.ville" cssClass="control-label col-sm-2">
					<spring:message code="adresse.ville" />
					<span class="required">*</span>
				</form:label>
				<div class="col-sm-10">
					<form:input path="client.adresse.ville" cssClass="form-control"/>
					&nbsp;
					<form:errors path="client.adresse.ville" cssClass="errors" />
				</div>
			</div>

			<div class="form-group">
				<form:label path="client.adresse.pays" cssClass="control-label col-sm-2">
					<spring:message code="adresse.pays" />
				</form:label>
				<div class="col-sm-10">
					<form:input path="client.adresse.pays" cssClass="form-control"/>
					&nbsp;
					<form:errors path="client.adresse.pays" cssClass="errors" />
				</div>
			</div>

			<div class="col-sm-offset-2 col-sm-10">
				<input type="submit" value="<spring:message code="client.creer.submit" />" />
			</div>
		</form>
		</div>
		<div class="col-sm-3"></div>
	</div>
</div>



<c:import url="footer.jsp" />