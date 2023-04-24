package com.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.Ville;
import com.dao.APIRestDAOImpl;

/**
 * Servlet implementation class AllVilleServlet
 */
@WebServlet("/AllVilleServlet")
public class AllVilleServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        int recordsPerPage = 50;
        
		APIRestDAOImpl getVille = new APIRestDAOImpl();
		List<Ville> villeList = getVille.getAllVilles();
		
        int noOfRecords = villeList.size();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        
        request.setAttribute("villes", villeList);
        request.setAttribute("noOfPages", noOfPages);
        request.getRequestDispatcher("/WEB-INF/allVille.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		String codeCommune = request.getParameter("codeCommune");
		
		
	    if (codeCommune != null && !codeCommune.isEmpty()) {
	    	
			APIRestDAOImpl apiVille = new APIRestDAOImpl();
			apiVille.deleteVille(codeCommune);
			
	    }else {
	        response.getWriter().write("Le code de commune n'a pas été fourni.");
	    }

		doGet(request, response);
	}

}
