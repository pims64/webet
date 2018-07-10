<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<c:import url="header.jsp" />



<div class="container-fluid bg-3 text-center">
	<h2 class="h2-admin">
		<spring:message code="client.header" />
	</h2>
	<div class="row">
	<div class="col-sm-3"></div>
	<div class="col-sm-6">
		<form method="POST"
			action="${pageContext.request.contextPath}/clientcontrolleur/creer"
			modelAttribute="client"
			class="form-horizontal">

			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
				
			<form:hidden path="utilisateur.id" />

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
				<form:label path="client.utilisateur.email" cssClass="control-label col-sm-2">
					<spring:message code="client.utilisateur.email" />
					<span class="required">*</span>
				</form:label>
				<div class="col-sm-10">
					<form:input path="client.utilisateur.email" cssClass="form-control"/>
					&nbsp;
					<form:errors path="client.utilisateur.email" cssClass="errors" />
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
				<form:label path="client.age" cssClass="control-label col-sm-2">
					<spring:message code="client.age" />
					<span class="required">*</span>
				</form:label>
				<div class="col-sm-10">
					<form:input path="client.age" cssClass="form-control"/>
					&nbsp;
					<form:errors path="client.age" cssClass="errors" />
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

			<%-- 		<form:label path="client.utilisateur.role"><spring:message code="client.role" /></form:label>
		<form:select path="client.utilisateur.role">
			<form:option value="" label="" />
			<form:options items="${roles}" itemValue="name" itemLabel="name" />
		</form:select>&nbsp;<form:errors path="client.utilisateur.role" cssClass="errors" />
		<br> --%>
			<form:hidden path="client.utilisateur.role" value="ROLE_USER" />
			
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