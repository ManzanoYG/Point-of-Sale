package view;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Categorie;
import model.GestionProduits;
import model.GestionTable;
import model.Produits;

public class TableauBP extends AnchorPane {
	private GestionProduits gestionP;
	private GestionTable gestionT;
	private Button btnSupp;
	private  TableView<Produits> tvPersonne;

	public TableauBP(GestionProduits gestionP,GestionTable gestionT) {
		this.gestionP=gestionP;
		this.gestionT = gestionT;
		AnchorPane.setRightAnchor(getBtnSupp(),10.);
		AnchorPane.setBottomAnchor(getBtnSupp(),10.);
//		AnchorPane.setRightAnchor(getTvPersonne(), 10.0);
//		AnchorPane.setTopAnchor(getTvPersonne(), 5.0);
		this.getChildren().addAll(getBtnSupp(),getTvPersonne());
	}

	public TableView<Produits> getTvPersonne() {
		if(tvPersonne == null) {
			tvPersonne = new TableView<>();
			tvPersonne.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			tvPersonne.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			tvPersonne.setItems(FXCollections.observableArrayList(gestionP.getPersonnes()));

			TableColumn<Produits, String> colNom = new TableColumn<>("Nom");
			colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));

			TableColumn<Produits, Double> colEtat = new TableColumn<>("Prix");
			colEtat.setCellValueFactory(new PropertyValueFactory<>("prix"));
			
			TableColumn<Produits, Categorie> colCateg = new TableColumn<>("Catégorie");
			colCateg.setCellValueFactory(new PropertyValueFactory<>("categ"));


			tvPersonne.setPrefWidth(800.);
			tvPersonne.setPrefHeight(650.);
			
			tvPersonne.getColumns().addAll(colNom,colEtat,colCateg);
		}

		return tvPersonne;
	}

	public Button getBtnSupp() {
		if(btnSupp == null) {
			btnSupp = new Button("Supprimer");
			btnSupp.setPrefWidth(150.);
			btnSupp.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					List<Produits> listSupp = getTvPersonne().getSelectionModel().getSelectedItems();
					gestionP.deleteList(listSupp);
					getTvPersonne().getItems().removeAll(listSupp);
				}
			});
		}
		return btnSupp;
	}
}