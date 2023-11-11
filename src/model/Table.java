package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Table {
	private int numTable;
	private double montant = 0.0;
	private String endroit; 
	private List<Produits> lstProduits;
	
	public Table(int numTable, double montant, String endroit) {
		this.numTable = numTable;
		this.montant = montant;
		this.endroit = endroit;
		lstProduits = new ArrayList<Produits>();
	}

	public int getNumTable() {
		return numTable;
	}

	public void setNumTable(int numTable) {
		this.numTable = numTable;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public List<Produits> getLstProduits() {
		return lstProduits;
	}

	public void setLstProduits(List<Produits> lstProduits) {
		this.lstProduits = lstProduits;
	}


	public Table clone() {
		Table tClone = new Table(getNumTable(), getMontant(),getEndroit());
		lstProduits.stream().forEach(f -> tClone.lstProduits.add(f));
		
		return tClone;
	}



	@Override
	public String toString() {
		return "Table [numTable=" + numTable + ", montant=" + montant + ", lstProduits="
				+ lstProduits + ", endroit=" + endroit + "]";
	}

	public String getEndroit() {
		return endroit;
	}

	public void setEndroit(String endroit) {
		this.endroit = endroit;
	}

	@Override
	public int hashCode() {
		return Objects.hash(endroit, numTable);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Table other = (Table) obj;
		return Objects.equals(endroit, other.endroit) && numTable == other.numTable;
	}
}