package model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import util.Persistance;

public class GestionProduits {

	private List<Produits> lstProduits;

	public GestionProduits() {
		lstProduits = new  ArrayList<>();		
	}

	public boolean ajouter(Produits p) {
		if(!lstProduits.contains(p)) {
			return lstProduits.add(p.clone());
		}
		return false;
	}

	public List<Produits> getPersonnes() {
		List<Produits> result = new ArrayList<>();
		for(Produits p : lstProduits) {
			result.add(p.clone());
		}
		return result;
	}

	public boolean deleteList(List<Produits> p) {
		return lstProduits.removeAll(p);
	}

	public boolean delete(Produits p) {
		return lstProduits.remove(p);
	}

	public boolean create(Produits produit)
	{
		if(!lstProduits.contains(produit))
		{
			return lstProduits.add(produit.clone());
		}
		return false;
	}

	public void importProd() {
		File fileOpen= new File("produits.json");
		//fileChooserOpen.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON","*.json"));
		if(fileOpen!=null)
		{
			GestionProduits tmp = Persistance.lireJson(fileOpen, null);
			for(Produits p: tmp.getPersonnes())
			{
				lstProduits.add(p);
			}
		}
	}
}
