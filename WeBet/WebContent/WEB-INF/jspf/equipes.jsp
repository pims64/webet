<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
	
	
<hr/>
	<h2 class="h2-admin">
		<spring:message code="admin.equipe.titre" />
	</h2>
	
<hr/>
<div class="row">
	<div class="col-sm-2">
			<span class="row-header"><spring:message code="admin.equipe.creer" /></span>
	</div>
	<div class="col-sm-8">
		<form method="POST"
			action="${pageContext.request.contextPath}/equipecontrolleur/creer"
			modelAttribute="equipe" class="form-inline">


			<!-- NOM DE L'EQUIPE -->
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<form:hidden path="equipe.id" />
			<div class="form-group">
				<form:label path="equipe.nom">
					<spring:message code="equipe.nom" />
					<span class="required">*</span>
				</form:label>
				<form:input path="equipe.nom" cssClass="form-control"
					placeholder="nom de l'équipe" />
				&nbsp;
			</div>
			
			
			<!-- SPORT PRATIQUE -->
			<div class="form-group">
				<form:label path="equipe.sport.id">
					<spring:message code="equipe.sport" /><span class="required">*</span>
				</form:label>
				<form:select path="equipe.sport.id" cssClass="form-control">
					<form:options items="${sports}" itemValue="id" itemLabel="nom" />
				</form:select>
				&nbsp;
				<form:errors path="equipe.sport.id" cssClass="errors" />
			</div>
			<input type="submit"
				value="<spring:message code="equipe.creer.submit" />"
				class="btn btn-default" /><br>
				
				<form:errors path='equipe.nom' cssClass="errors" />
		</form>
	</div>

	<div class="col-sm-2"></div>
</div>
<hr/>
<div class="row">

	<div class="col-sm-2">

			<span class="row-header"><spring:message code="admin.equipe.liste" /></span>
	</div>
	<div class="col-sm-8">

		<table class="table">
			<tr>
				<th><spring:message code="equipe.table.id" /></th>
				<th><spring:message code="equipe.table.nom" /></th>
				<th><spring:message code="equipe.table.sport" /></th>
				<th><spring:message code="equipe.table.supprimer" /></th>
			</tr>
			<tbody>
				<c:forEach items="${equipes}" var="equipe">
					<tr>
						<td>${equipe.id }</td>
						<td>${equipe.nom }</td>
						<td>${equipe.sport.nom }</td>
						<td><a
							href="<c:url value="/equipecontrolleur/supprimer/${equipe.id}" />">
								<spring:message code="equipe.supprimer" />
						</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<div class="col-sm-2"></div>
</div>