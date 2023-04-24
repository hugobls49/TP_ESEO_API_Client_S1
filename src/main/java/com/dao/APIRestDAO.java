package com.dao;

import java.util.List;

import com.beans.Ville;

public interface APIRestDAO {

	public List<Ville> getAllVilles();
	
	public Ville getVilleByCodeCommune(String codeCommune);
	
	public void deleteVille(String codeCommune);
	
	public void updateVille(String codeCommune, String nomCommunale, String codePostal, String latitude, String longitude, Ville ville);
	
}
