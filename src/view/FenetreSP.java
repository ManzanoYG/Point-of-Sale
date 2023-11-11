package view;

import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import model.GestionProduits;
import model.GestionTable;

public class FenetreSP extends StackPane {
	private TableauBP pnlTableau;
	private ConnexionFP pnlWelcome;
	private CaisseBP pnlCaisse;
	private AjoutBP pnlAjout;
	private GestionProduits gestionP;
	private GestionTable gestionT;
	
	public FenetreSP(GestionProduits gestionP,GestionTable gestionT) {
		this.gestionP=gestionP;
		this.gestionT = gestionT;
		this.getChildren().addAll(getPnlTableau(),getPnlWelcome(),getPnlCaisse(),getPnlAjout());
		
	}

	public TableauBP getPnlTableau() {
		if(pnlTableau==null) {
			pnlTableau = new TableauBP(gestionP, gestionT);
			pnlTableau.setVisible(false);
		}
		return pnlTableau;
	}

	public ConnexionFP getPnlWelcome() {
		if(pnlWelcome==null) {
			pnlWelcome = new ConnexionFP();
		}
		return pnlWelcome;
	}
	
	public CaisseBP getPnlCaisse() {
		if(pnlCaisse==null) {
			pnlCaisse = new CaisseBP(gestionP, gestionT);
			pnlCaisse.setVisible(false);
		}
		return pnlCaisse;
	}

	public AjoutBP getPnlAjout() {
		if(pnlAjout==null) {
			pnlAjout = new AjoutBP(gestionP, gestionT);
			pnlAjout.setVisible(false);
		}
		return pnlAjout;
	}
	
	public void hide()
	{
		for(Node n : this.getChildren())
		{
			n.setVisible(false);
		}
	}
}