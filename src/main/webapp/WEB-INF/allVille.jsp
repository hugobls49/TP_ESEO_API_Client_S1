<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
  <meta charset="UTF-8">
  <title>Villes</title>
  <style>
    /* CSS pour la pagination */
    .pagination {
      display: flex;
      justify-content: center;
      align-items: center;
    }

    .pagination a {
      color: #333;
      border: 1px solid #ccc;
      padding: 5px 10px;
      margin: 0 5px;
      text-decoration: none;
    }

    .pagination a.active {
      background-color: #007bff;
      color: #fff;
      border-color: #007bff;
    }
  </style>
</head>

<body>

    <c:set var="currentPage" value="1" />

    <c:if test="${not empty param.page}">
        <c:set var="currentPage" value="${param.page}" />
    </c:if>

    <c:set var="pageSize" value="50" />

    <table border="1" cellpadding="5" cellspacing="5">
        <thead>
            <tr>
                <th>Code Commune</th>
                <th>Nom Commune</th>
                <th>Modifier</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${villes}" var="ville" begin="${(currentPage-1)*pageSize}" end="${currentPage*pageSize-1}">
                <tr>
                    <td>${ville.codeCommune}</td>
                    <td>${ville.nomCommune}</td>
                    <td><a href="modifierville?codeCommune=${ville.codeCommune}">Modifier</a></td>        
					<td>
	                    <form method="POST" action="allville" onsubmit="return confirm('Êtes-vous sûr de vouloir supprimer cette ville ?')">>
	                        <input type="hidden" name="codeCommune" value="${ville.codeCommune}">
	                        <button type="submit">Supprimer</button>
	                    </form>
                	</td>   
				</tr>
            </c:forEach>
        </tbody>
    </table>

    <div class="pagination">
    <c:if test="${currentPage gt 1}">
        <a href="?page=${currentPage - 1}">&laquo;</a>
    </c:if>

    <c:choose>
        <c:when test="${currentPage le 2}">
            <c:forEach begin="1" end="3" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <a href="?page=${i}" class="active">${i}</a>
                    </c:when>
                    <c:otherwise>
                        <a href="?page=${i}">${i}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${noOfPages gt 3}">...</c:if>
        </c:when>
        <c:when test="${currentPage ge noOfPages-1}">
            <c:if test="${noOfPages gt 3}">...</c:if>
            <c:forEach begin="${noOfPages-2}" end="${noOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <a href="?page=${i}" class="active">${i}</a>
                    </c:when>
                    <c:otherwise>
                        <a href="?page=${i}">${i}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <c:if test="${noOfPages gt 3}">...</c:if>
            <c:forEach begin="${currentPage-1}" end="${currentPage+1}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <a href="?page=${i}" class="active">${i}</a>
                    </c:when>
                    <c:otherwise>
                        <a href="?page=${i}">${i}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${noOfPages gt currentPage+1}">...</c:if>
        </c:otherwise>
    </c:choose>

    <c:if test="${currentPage lt noOfPages}">
        <a href="?page=${currentPage + 1}">&raquo;</a>
    </c:if>
</div>


	<a href="http://localhost:8081/TP_ESEO_API_Client/distancevilles">Calcul de la distance</a>

</body>
</html>
