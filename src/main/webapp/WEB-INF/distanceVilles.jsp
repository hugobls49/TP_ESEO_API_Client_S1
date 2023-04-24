<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des villes</title>
</head>
<body>

<style>
    label {
        display: block;
        margin-bottom: 10px;
    }
    select {
        padding: 5px;
        border-radius: 3px;
        border: 1px solid #ccc;
        width: 200px;
        margin-bottom: 20px;
    }
    input[type="submit"] {
        padding: 5px 10px;
        background-color: #4CAF50;
        color: #fff;
        border-radius: 3px;
        border: none;
        cursor: pointer;
    }
    p {
        font-size: 18px;
        margin-top: 20px;
    }
    a {
        display: block;
        margin-top: 20px;
    }
</style>

    <form action="DistanceVilleServlet" method="post">
	  <label for="villeDepart">Ville de départ :</label>
	  <select id="villeDepart" name="ville_depart">
	    <option value="">Choisissez une ville</option>
	    <c:forEach var="ville" items="${villes}">
	      <option value="${ville.nomCommune}">${ville.nomCommune}</option>
	    </c:forEach>
	  </select>
	  <br>
	  <label for="villeArrivée">Ville d'arrivée :</label>
	  <select id="villeArrivée" name="ville_arrivee">
	    <option value="">Choisissez une ville</option>
	    <c:forEach var="ville" items="${villes}">
	      <option value="${ville.nomCommune}">${ville.nomCommune}</option>
	    </c:forEach>
	  </select>
	  <br>
	  <input type="submit" value="Calculer">
	</form>
	
	<p>La distance entre ${villeDepart} et ${villeArrivee} est de ${distance} km.</p>

	<a href="http://localhost:8081/TP_ESEO_API_Client/allville">Toutes les villes</a>

</body>
</html>
