package com.dao;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

import com.beans.Ville;
import com.fasterxml.jackson.databind.ObjectMapper;

public class APIRestDAOImpl implements APIRestDAO{

	@Override
	public List<Ville> getAllVilles() {

		// Création d'un client Http
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // Création d'une requête HttpGet
        HttpGet httpGet = new HttpGet("http://localhost:8080/villes");

        // Exécution de la requête et récupération de la réponse
        CloseableHttpResponse httpResponse;
		try {
			
			httpResponse = httpClient.execute(httpGet);
	        // Transformation du résultat JSON en une liste d'objets de type Ville
	        ObjectMapper objectMapper = new ObjectMapper();
	        Ville[] villes = objectMapper.readValue(httpResponse.getEntity().getContent(), Ville[].class);
	        List<Ville> villeList = Arrays.asList(villes);

	        // Fermeture des ressources
	        httpResponse.close();
	        httpClient.close();
	        
	        return villeList;
	        
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;		
	}

	@Override
	public Ville getVilleByCodeCommune(String codeCommune) {
	    // Récupération de toutes les villes
	    List<Ville> villes = getAllVilles();
	    
	    // Recherche de la ville correspondant au code commune spécifié
	    for (Ville ville : villes) {
	        if (ville.getCodeCommune().equals(codeCommune)) {
	            return ville;
	        }
	    }
	    // Si la ville n'a pas été trouvée, renvoie null
	    return null;
	}

	@Override
	public void deleteVille(String codeCommune) {

        String apiUrl = "http://localhost:8080/delete/" + codeCommune;

        HttpURLConnection connection;
		try {
			connection = (HttpURLConnection) new URL(apiUrl).openConnection();
	        connection.setRequestMethod("DELETE");
	        @SuppressWarnings("unused")
			int responseCode = connection.getResponseCode();
	        
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void updateVille(String codeCommune, String nomCommune, String codePostal, String latitude,
			String longitude, Ville ville) {
	
		
//	    {
//        "codeCommune": "01002",
//        "libelleAcheminement": "RIVES DU LOIRE EN ANJOU",
//        "codePostal": "19150",
//        "nomCommune": "RIVES DU LOIRE EN ANJOU",
//        "latitude": 65.9756078125,
//        "ligne5": "",
//        "longitude": 10.34313562094
//    }
		
	    // construction de l'objet JSON pour la requête de mise à jour
	    JSONObject villeJSON = new JSONObject();
	    villeJSON.put("codeCommune", codeCommune);
	    villeJSON.put("libelleAcheminement", ville.getLibelleAcheminement());
	    villeJSON.put("codePostal", codePostal);
	    villeJSON.put("nomCommune", nomCommune);
	    villeJSON.put("latitude", latitude);
	    villeJSON.put("ligne5", "");
	    villeJSON.put("longitude", longitude);

	    // envoi de la requête PUT à l'API
	    String apiUrl = "http://localhost:8080/update/" + codeCommune;
	    HttpClient client = HttpClientBuilder.create().build();
	    HttpPut putRequest = new HttpPut(apiUrl);
	    StringEntity input;
		try {
			input = new StringEntity(villeJSON.toString());
		    input.setContentType("application/json");
		    putRequest.setEntity(input);
		    try {
				@SuppressWarnings("unused")
				HttpResponse apiResponse = client.execute(putRequest);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}




}
