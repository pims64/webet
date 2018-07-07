<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
	
	
<hr/>
	<h2 class="h2-admin">
		<spring:message code="admin.rencontre.titre" />
	</h2>
<div class="row">
	<div class="col-sm-2">
			<span class="row-header"><spring:message code="admin.rencontre.creer" /></span>
	</div>
	<div class="col-sm-8">
		<form method="POST"
			action="${pageContext.request.contextPath}/equipecontrolleur/creer"
			modelAttribute="rencontre" class="form-inline">

			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<form:hidden path="rencontre.id" />
			
			<div class="form-group">
				<form:label path="rencontre.equipeDomicile.nom">
					<spring:message code="rencontre.equipeDomicile" /><span class="required">*</span>
				</form:label>
			<form:select path="rencontre.equipeDomicile.id" cssClass="form-control">
					<form:options items="${equipes}" itemValue="id" itemLabel="nom" />
				</form:select>
			</div>
			
			<div class="form-group">
				<form:label path="rencontre.equipeVisiteur.nom">
					<spring:message code="rencontre.equipeVisiteur" /><span class="required">*</span>
				</form:label>
			<form:select path="rencontre.equipeVisiteur.id" cssClass="form-control">
					<form:options items="${equipes}" itemValue="id" itemLabel="nom" />
			</form:select>
			</div>
			
			
			
			<%-- <div class="form-group">
				<form:label path="rencontre.nom">
					<spring:message code="equipe.nom" />
					<span class="required">*</span>
				</form:label>
				<form:input path="equipe.nom" cssClass="form-control"
					placeholder="nom de l'équipe" />
				&nbsp;
				<form:errors path="equipe.nom" cssClass="errors" />
			</div>
			<div class="form-group">
				<form:label path="equipe.sport.nom">
					<spring:message code="equipe.sport" /><span class="required">*</span>
				</form:label>
				<form:select path="equipe.sport.id" cssClass="form-control">
					<form:option value="" label="" />
					<form:options items="${sports}" itemValue="id" itemLabel="nom" />
				</form:select>
				&nbsp;
				<form:errors path="equipe.sport.nom" cssClass="errors" />
			</div> --%>
			<input type="submit"
				value="<spring:message code="equipe.creer.submit" />"
				class="btn btn-default" />
		</form>
	</div>

	<div class="col-sm-2"></div>
</div>
<hr/>
<hr/>
<hr/>