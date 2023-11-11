package model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import util.Persistance;

public class GestionTable {
	
private List<Table> lstTable;
	
	public GestionTable() {
		lstTable = new  ArrayList<>();
	}
	
	public boolean ajouter(Table t) {
		if(!lstTable.contains(t)) {
			return lstTable.add(t.clone());
		}
		return false;
	}
	
	public List<Table> getPersonnes() {
		List<Table> result = new ArrayList<>();
		for(Table p : lstTable) {
			result.add(p.clone());
		}
		return result;
	}
	
	public boolean deleteList(List<Table> t) {
		return lstTable.removeAll(t);
	}
	
	public boolean delete(Table t) {
		return lstTable.remove(t);
	}
	
	public boolean create(Table table)
	{
        if(!lstTable.contains(table))
        {
            return lstTable.add(table.clone());
        }
		return false;
	}
	
	public boolean update(Table l1, Table l2)
	{		
		lstTable.set(lstTable.indexOf(l1), l2.clone());
		return true;
	}
	
	public void importTable() {
		File fileOpen= new File("table.json");
		//fileChooserOpen.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON","*.json"));
		if(fileOpen!=null)
		{
			GestionTable tmp = Persistance.lireJsonTable(fileOpen, null);
			for(Table t: tmp.getPersonnes())
			{
				lstTable.add(t);
			}
		}
	}
}