<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="UTF-8">
<title>Modifier la ville</title>
</head>
<body>
	<h1>Modifier la ville ${ville.codeCommune} - ${ville.nomCommune}</h1>
	<form action="modifierville?codeCommune=${ville.codeCommune}" method="post">
		<input type="hidden" name="codeCommune" value="${ville.codeCommune}" />

		<label for="nomCommune">Nom de la commune :</label> <input type="text"
			name="nomCommune" value="${ville.nomCommune}" /> <br />
		<br /> <label for="codePostal">Code postal :</label> <input
			type="text" name="codePostal" value="${ville.codePostal}" /> <br />
		<br /> <label for="latitude">Latitude :</label> <input type="text"
			name="latitude" value="${ville.latitude}" /> <br />
		<br /> <label for="longitude">Longitude :</label> <input type="text"
			name="longitude" value="${ville.longitude}" /> <br />
		<br /> <input type="submit" />

	</form>
	
	<a href="http://localhost:8081/TP_ESEO_API_Client/allville">Retour</a>
	
</body>
</html>