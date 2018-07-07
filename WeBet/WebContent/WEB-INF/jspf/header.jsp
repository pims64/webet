<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<link rel="stylesheet"
	href="<c:url value="/static/css/bootstrap.min.css" />">
<link href="<c:url value="/static/css/style.css" />" rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Galada|Titillium+Web"
	rel="stylesheet">
<script src="<c:url value="/static/js/jquery.min.js" />"></script>
<script src="<c:url value="/static/js/bootstrap.min.js" />"></script>
</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand active"
					href="<c:url value="/hellocontrolleur/goToSite"/>">WeBet</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<!-- 				<ul class="nav navbar-nav">
					<li><a href="#">Home</a></li>
					<li><a href="#">About</a></li>
					<li><a href="#">Gallery</a></li>
					<li><a href="#">Contact</a></li>
				</ul> -->
				<%-- <sec:authentication property="principal.client.id" /> --%>
				<ul class="nav navbar-nav navbar-right">
					<sec:authorize access="hasAnyRole('ROLE_USER')">
						<li><a href="${pageContext.request.contextPath }/clientcontrolleur/modifier/<sec:authentication property="principal.client.id" />"><span class="glyphicon glyphicon-user"></span>
								<spring:message code="menu.compte" /></a></li>
					</sec:authorize>
					<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_USER')">
						<li><a href="<c:url value="/logout"/>"><span
								class="glyphicon glyphicon-log-out"></span> <spring:message
									code="menu.logout" /></a></li>
					</sec:authorize>

					<sec:authorize access="hasRole('ANONYMOUS')">
						<li><a href="<c:url value="/clientcontrolleur/goToCreer"/>"><span
								class="glyphicon glyphicon-plus-sign"></span> <spring:message
									code="menu.creer.compte" /></a></li>
						<li><a href="" data-toggle="modal" data-target="#connexionModal"><span
								class="glyphicon glyphicon-log-in"></span> <spring:message
									code="menu.connexion" /></a>
						</li>
					</sec:authorize>



				</ul>
			</div>
		</div>
	</nav>

	<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
		<div class="jumbotron">
			<div class="container text-center">
				<h1><spring:message code="jumbotron.admin" /></h1>
			</div>
		</div>
	</sec:authorize>
	<sec:authorize access="hasAnyRole('ROLE_USER', 'ANONYMOUS')">
		<div class="jumbotron">
			<div class="container text-center">
				<h1><spring:message code="jumbotron.user" /></h1>
				<p>We Bet! You Get!</p>
			</div>
		</div>
	</sec:authorize>
	
	
	
<div id="connexionModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title"><spring:message code="modal.connexion.titre" /></h4>
      </div>
      <div class="modal-body">
        <form method="POST" action="<c:url value="/login" />">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	<div class="form-group">
		<label for="username"><spring:message code="login.username" /></label>
		<div class="input-group">
			<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
			<input type="text" name="username" value="" placeholder="Email" class="form-control">
		</div>
	</div>
	<div class="form-group">
		<label for="password"><spring:message code="login.password" /></label>
		<div class="input-group">
			<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
			<input type="password" name="password" value="" placeholder="Mot de passe" class="form-control">
		</div>
	</div>
	<input type="submit" value="<spring:message code="login.submit" />" class="btn btn-default"/>
      </form>
      </div>      
    </div>

  </div>
</div>
	
	
	
	