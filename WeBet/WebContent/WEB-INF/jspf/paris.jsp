<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<c:import url="header.jsp" />

<div class="container">
  <h2>Equipe Domicile:  ${rencontre.equipeDomicile.nom}    Cote:  ${rencontre.coteDomicile}</h2>
  <h2>Equipe Visiteur:  ${rencontre.equipeVisiteur.nom}    Cote:  ${rencontre.coteVisiteur}</h2>
  <h3>Cote Match nul: ${rencontre.coteNul}</h3>
  <p>Informations nécessaires pour pouvoir parier:</p>
 
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
<%-- 		<form:errors path="pari.choixPari" cssClass="errors" /> --%>
    </div>
    <%-- 
    <form:hidden path="pari.client.id"/>
    <form:hidden path="pari.rencontre.id"/> --%>
  
	<input type="submit" value="<spring:message code="pari.creer.submit" />" />
</form>
  
</div>

<c:import url="footer.jsp" />