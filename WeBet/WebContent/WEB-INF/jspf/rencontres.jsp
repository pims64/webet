<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<hr />
<h2 class="h2-admin">
	<spring:message code="admin.rencontre.titre" />
</h2>

<div class="row">
<div class="col-sm-3"></div>
	<div class="col-sm-6">
	<form method="POST" action="${pageContext.request.contextPath}/rencontrecontrolleur/goToDetail"
					modelAttribute="sport" class="form-horizontal">
		<div class="form-group">
			<form:label path="sport.nom" cssClass="control-label col-sm-2">
				<spring:message code="sport.nom" />
			</form:label>
			<div class="col-sm-10">
				<form:select path="sport.id" cssClass="form-control">
					<form:options items="${sports}" itemValue="id" itemLabel="nom" />
				</form:select>
			</div>
		</div>
		
		<div class="col-sm-offset-1 col-sm-10">
			<input type="submit" value="<spring:message code="admin.rencontre.creer" />" />
		</div>
		
	</form>
	</div>
	<div class="col-sm-3"></div>
</div>