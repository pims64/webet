<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<c:import url="header.jsp" />

<div class="container-fluid bg-3 text-center">
<div class="row">
<table class="table">
	<tr>
		<td><h2>${rencontre.equipeDomicile.nom}</h2></td>
		<td><h2><spring:message code="pari.nul"/></h2></td>
		<td><h2>${rencontre.equipeVisiteur.nom}</h2></td>
	</tr>
	<tr>
		<td><h3><spring:message code="pari.cote"/><br>${rencontre.coteDomicile}</h3></td>
		<td><h3><spring:message code="pari.cote"/><br>${rencontre.coteNul}</h3></td>
		<td><h3><spring:message code="pari.cote"/><br>${rencontre.coteVisiteur}</h3></td>
	</tr>
</table>
  </div></div>
  
  
 <div class="container-fluid bg-3 text-center">
<div class="row">
<div class="col-sm-4"></div>
<div class="col-sm-4">
	  	<form method="POST" action="${pageContext.request.contextPath}/paricontrolleur/creer" modelAttribute="pari">
	  		 	 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	  		 	 <form:hidden path="pari.id" />
	  		 	 
	  			<input type="hidden" name="rencontreId" value="${rencontreId}" />
	  						
	  		 	 <div class="form-group">
	  			 	 <form:label path="pari.sommePariee">
	  			 	 	<spring:message code="pari.sommePariee" />
	  			 	 	<span class="required">*</span></form:label>
	  			 	 <form:input type="number" min="1" max="${client.montantMaxPari}" path="pari.sommePariee" class="form-control"/>&nbsp;
	  				 <form:errors path="pari.sommePariee" cssClass="errors" />
	  		 	 </div>
	  	  
	  	  <div class="form-group">
	  			    <form:label path="pari.choixPari">
	  					<spring:message code="pari.pronostic" /><span class="required">*</span>
	  				</form:label>
	  			    <form:select path="pari.choixPari" cssClass="form-control">
	  					<form:options items="${choixPari}" itemValue="name" itemLabel="name" />
	  				</form:select>
	  	  </div>
	  	
	  			<input type="submit" value="<spring:message code="pari.creer.submit" />" class="btn btn-default"/>
	  		</form>
	  </div>
	  
<div class="col-sm-4"></div>
</div>
</div>

<c:import url="footer.jsp" />