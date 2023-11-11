package model;

import java.util.Objects;

public class Produits {
	private String nom;
	private double prix;
	private Categorie categ;
	private int quantite;
	private double prixTot;
	
	public Produits(String nom, double prix, Categorie categ) {
		this.nom = nom;
		this.prix = prix;
		this.categ = categ;
		quantite = 0;
		prixTot = 0.;
		
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public Categorie getCateg() {
		return categ;
	}

	public void setCateg(Categorie categ) {
		this.categ = categ;
	}
	
	public Produits clone() {
		return new Produits(getNom(), getPrix(),getCateg());
	}

	@Override
	public int hashCode() {
		return Objects.hash(nom);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produits other = (Produits) obj;
		return Objects.equals(nom, other.nom);
	}

	@Override
	public String toString() {
		return "Produits [nom=" + nom + ", prix=" + prix + ", categ=" + categ + "]";
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public double getPrixTot() {
		return prixTot;
	}

	public void setPrixTot(double prixTot) {
		this.prixTot = prixTot;
	}	
}