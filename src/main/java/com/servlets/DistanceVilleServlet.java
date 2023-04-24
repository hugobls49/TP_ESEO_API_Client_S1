package com.servlets;

import com.beans.Ville;
import com.dao.APIRestDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class DistanceVilleServlet
 */
@WebServlet("/DistanceVilleServlet")
public class DistanceVilleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		APIRestDAOImpl getVille = new APIRestDAOImpl();
		
		List<Ville> villeList = getVille.getAllVilles();				

        // Ajout de la liste de villes à l'objet request
        request.setAttribute("villes", villeList);
        		
    	this.getServletContext().getRequestDispatcher("/WEB-INF/distanceVilles.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    String villeDepart = request.getParameter("ville_depart");
	    String villeArrivee = request.getParameter("ville_arrivee");
	    
	    APIRestDAOImpl getVille = new APIRestDAOImpl();
		List<Ville> villeList = getVille.getAllVilles();

	    // Trouver les villes de départ et d'arrivée dans la liste de toutes les villes
	    Ville villeDepartObj = null;
	    Ville villeArriveeObj = null;
	    for (Ville ville : villeList) {
	        if (ville.getNomCommune().equals(villeDepart)) {
	            villeDepartObj = ville;
	        }
	        if (ville.getNomCommune().equals(villeArrivee)) {
	            villeArriveeObj = ville;
	        }
	    }

	    // Calculer la distance entre les deux villes en utilisant la formule Haversine
	    double lat1 = villeDepartObj.getLatitude();
	    double lon1 = villeDepartObj.getLongitude();
	    double lat2 = villeArriveeObj.getLatitude();
	    double lon2 = villeArriveeObj.getLongitude();
	    double earthRadius = 6371; // rayon de la Terre en km
	    double dLat = Math.toRadians(lat2 - lat1);
	    double dLon = Math.toRadians(lon2 - lon1);
	    double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(lat1))
	            * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double distance = earthRadius * c;

	    // Ajouter la distance comme attribut de requête pour l'afficher dans la jsp
	    request.setAttribute("distance", distance);
	    request.setAttribute("villeDepart", villeDepartObj.getNomCommune());
	    request.setAttribute("villeArrivee", villeArriveeObj.getNomCommune());
        request.setAttribute("villes", villeList);

		
        request.getRequestDispatcher("/WEB-INF/distanceVilles.jsp").forward(request, response);
	}

}
