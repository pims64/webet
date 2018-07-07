<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<c:import url="header.jsp" />

<div class="container-fluid bg-3 text-center">
	<h2 class="h2-admin">
		<spring:message code="login.title" />
	</h2>
	<hr />
	<div class="row">
	<div class="col-sm-2"></div>
		<c:if test="${param.error}">
			<span class="error"><spring:message code="login.inconnu" /></span>
		</c:if>
		<c:if test="${param.logout}">
			<span class="error"><spring:message code="login.logout" /></span>
		</c:if>
		<form method="POST" action="<c:url value="/login" />"
			class="form-horizontal col-sm-8">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<div class="form-group">
				<label for="username" class="control-label col-sm-2"><spring:message
						code="login.username" /></label>
				<div class="col-sm-10">
				
					<div class="input-group">
						<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
						<input type="text" name="username" value=""
							placeholder="<spring:message code="login.username" />" class="form-control">
					</div>
				</div>
			</div>
			<div class="form-group">
				<label for="password" class="control-label col-sm-2"><spring:message
							code="login.password" /></label>
				<div class="col-sm-10">
					<div class="input-group">
		    			<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
						<input type="password" name="password" value=""
								placeholder="<spring:message code="login.password" />" class="form-control">
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<input type="submit" value="<spring:message code="login.submit" />"
						class="btn btn-default form-control" />
				</div>
			</div>
	
		</form>
		
	<div class="col-sm-2"></div>
	</div>
</div>

<c:import url="footer.jsp" />