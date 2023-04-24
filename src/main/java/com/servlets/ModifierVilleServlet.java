package com.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.Ville;
import com.dao.APIRestDAOImpl;

/**
 * Servlet implementation class ModifierVilleServlet
 */
@WebServlet("/ModifierVilleServlet")
public class ModifierVilleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String codeCommune = request.getParameter("codeCommune");
		
		APIRestDAOImpl getVille = new APIRestDAOImpl();
		
		Ville ville = getVille.getVilleByCodeCommune(codeCommune);
		
        request.setAttribute("ville", ville);
        
        request.getRequestDispatcher("/WEB-INF/modifierVille.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    String codeCommune = request.getParameter("codeCommune");
	    
		APIRestDAOImpl villeAPI = new APIRestDAOImpl();
		Ville ville = villeAPI.getVilleByCodeCommune(codeCommune);
		
		villeAPI.updateVille(codeCommune, request.getParameter("nomCommune"), request.getParameter("codePostal"), request.getParameter("latitude"), request.getParameter("longitude"), ville);
	   	    
		doGet(request, response);
	}


}
