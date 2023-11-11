package model;

public enum Categorie {
	MENU("Menu"),
	SELECTION("Selection"),
	BIERE("Bière"),
	SOFT("Soft"),
	CAFE("Cafe"),
	APERITIF("Aperitif"),
	DIGESTIF("Digestif"),
	VINS("Vins"),
	SUPPLEMENT("Supplement"),
	VARIE("Varié");
	
	private String categ;

	private Categorie(String catego) {
		this.categ = catego;
	}

	public String toString() {
		return categ;
	}

	public String getCateg() {
		return categ;
	}	
}