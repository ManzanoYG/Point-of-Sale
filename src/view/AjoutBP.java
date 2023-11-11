package view;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import model.Categorie;
import model.GestionProduits;
import model.GestionTable;
import model.Produits;
import model.Table;
import util.Persistance;

public class AjoutBP extends BorderPane{
	private GestionProduits gestionP;
	private GestionTable gestionT;
	private TableView<Produits> tvTicket;
	private GridPane gridProd;
	private Button btnSave;
	private Button btnPrint;
	private Button btnEff;
	private VBox vbTV;
	private HBox hbSave;
	private HBox hbGridTV;
	private VBox vbGlob;
	private String categ;
	private HBox hbTotal;
	private Label lblTotal;
	private List<Produits> lstProd;
	private VBox vbTicket;
	private String endroit;
	private int numTable;
	private Label lblNumTable;

	public static final double BUTTON_WIDTH = 130.;
	public static double total = 0.;
	public static int nb=0;

	public AjoutBP(GestionProduits gestionP, GestionTable gestionT) {
		this.gestionP = gestionP;
		this.gestionT = gestionT;
		this.setCenter(getVbGlob());
		this.setBottom(getHbSave());
		this.setRight(getVbTV());
	}

	@SuppressWarnings("unchecked")
	public TableView<Produits> getTvTicket() {
		if(tvTicket == null) {
			tvTicket = new TableView<Produits>();
			tvTicket.setPrefHeight(Integer.MAX_VALUE);
			tvTicket.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			tvTicket.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			

			TableColumn<Produits, Integer> colQuant = new TableColumn<>("Qt");
			colQuant.setCellValueFactory(new PropertyValueFactory<>("quantite"));

			TableColumn<Produits, String> colNom = new TableColumn<>("Nom");
			colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));

			TableColumn<Produits, Double> colPrixTot = new TableColumn<>("Prix");
			colPrixTot.setCellValueFactory(new PropertyValueFactory<>("prixTot"));

			tvTicket.getColumns().addAll(colQuant,colNom,colPrixTot);
		}
		return tvTicket;
	}

	public GridPane getGridProd() {
		if(gridProd==null) {
			gridProd = new GridPane();
			gridProd.setPrefWidth(650.);
			gridProd.setPrefHeight(Integer.MAX_VALUE);
			gridProd.setPadding(new Insets(0.,0.,15.,0.));
			affichage(Categorie.MENU);
		}
		return gridProd;
	}

	public Button getBtnSave() {
		if(btnSave==null) {
			btnSave = new Button("Save");

			btnSave.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {	

				}
			});
		}
		return btnSave;
	}

	public Button getBtnPrint() {
		if(btnPrint==null) {
			btnPrint = new Button("Print");

		}
		return btnPrint;
	}

	public Button getBtnEff() {
		if(btnEff==null) {
			btnEff = new Button("Effacer");
			btnEff.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					RemiseAZero();	
				}
			});
		}
		return btnEff;
	}

	public VBox getVbTV() {
		if(vbTV==null) {
			vbTV = new VBox(getLblNumTable(),getTvTicket(),getHbTotal());
		}
		return vbTV;
	}

	public HBox getHbSave() {
		if(hbSave==null) {
			hbSave = new HBox();
			hbSave.setSpacing(5.);
			EnumSet.allOf(Categorie.class).forEach(l -> {
				hbSave.getChildren().add(new Button(l.getCateg()));
			});
			hbSave.getChildren().addAll(getBtnSave(),getBtnEff(),getBtnPrint());
			hbSave.setAlignment(Pos.BASELINE_CENTER);

			hbSave.getChildren().forEach(x ->{
				if(x instanceof Button) {
					((Button) x).setPrefHeight(BUTTON_WIDTH/2);
					((Button) x).setPrefWidth(BUTTON_WIDTH);
					if(((Button) x)!=getBtnSave() && ((Button) x)!=getBtnEff() && ((Button) x)!=getBtnPrint()) {
						((Button) x).setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
								// TODO Auto-generated method stub
								affichage(Categorie.valueOf(((Button) x).getText()
										.replace('é', 'e')
										.replace(" ", "_")
										.replace('è', 'e')
										.toUpperCase()
										));
							}
						});
					}
				}
			});
		}
		return hbSave;
	}

	public HBox getHbGridTV() {
		if(hbGridTV==null) {
			hbGridTV = new HBox(getGridProd());
		}
		return hbGridTV;
	}

	public VBox getVbGlob() {
		if(vbGlob==null) {
			vbGlob = new VBox(getHbGridTV());
		}
		return vbGlob;
	}

	public String getCateg() {
		return categ;
	}

	public void affichage(Categorie categ) {
		int nb=0;
		List<Produits> lstP = new ArrayList<Produits>();
		for(int i=0;i<=gestionP.getPersonnes().size()-1;i++) {
			if(gestionP.getPersonnes().get(i).getCateg()==categ) {
				lstP.add(gestionP.getPersonnes().get(i));
			}
		}
		getGridProd().getChildren().clear();
		for(int i=0;i<=4;i++) {
			for(int j=0;j<=8;j++) {
				Button b = new Button(lstP.get(nb).getNom()+"\n"+lstP.get(nb).getPrix()+"€\n");
				b.setPrefHeight(getButtonWidth()/1.86);
				b.setPrefWidth(getButtonWidth());
				b.setTextAlignment(TextAlignment.CENTER);
				b.setWrapText(true);
				b.setId(""+nb);
				getGridProd().add(b, i, j);

				b.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						// TODO Auto-generated method stub	
						int id = Integer.parseInt(b.getId());
						if(!getLstProd().contains(lstP.get(id))) {

							lstP.get(id).setQuantite(lstP.get(id).getQuantite()+1);
							lstP.get(id).setPrixTot(lstP.get(id).getQuantite()*lstP.get(id).getPrix());
							getLstProd().add(lstP.get(id));
							getTvTicket().setItems(FXCollections.observableArrayList(getLstProd()));
							getTvTicket().refresh();
						}
						else if(getLstProd().contains(lstP.get(id))) {

							getLstProd().get(getLstProd().indexOf(lstP.get(id))).setQuantite(getLstProd().get(getLstProd().indexOf(lstP.get(id))).getQuantite()+1);
							getLstProd().get(getLstProd().indexOf(lstP.get(id))).setPrixTot(roundError(getLstProd().get(getLstProd().indexOf(lstP.get(id))).getPrixTot() + getLstProd().get(getLstProd().indexOf(lstP.get(id))).getPrix()));
							getTvTicket().setItems(FXCollections.observableArrayList(getLstProd()));
							getTvTicket().refresh();
						}
						CalculerPrixTotTicket();
					}
				});
				if(nb==lstP.size()-1) {
					break;
				}
				nb++;
			}
			if(nb==lstP.size()-1) {
				break;
			}
		}
	}

	public static double getButtonWidth() {
		return BUTTON_WIDTH;
	}

	public List<Produits> getLstProd() {
		if(lstProd == null) {
			lstProd = new ArrayList<Produits>();
		}
		return lstProd;
	}

	public void CalculerPrixTotTicket() {
		total = 0.;
		getTvTicket().getItems().forEach(x ->{
			total = total + x.getPrixTot();
			roundError(total);
			getLblTotal().setText("Total : " + roundError(total) + "€");
		});
	}

	public HBox getHbTotal() {
		if(hbTotal == null) {
			hbTotal = new HBox(getLblTotal());
		}
		return hbTotal;
	}

	public Label getLblTotal() {
		if(lblTotal == null) {
			lblTotal = new Label("Total : 0,0 €");
		}
		return lblTotal;
	}

	public static double roundError(double value) {
		BigDecimal valueBigDecimal = new BigDecimal(Double.toString(value));
		return valueBigDecimal.setScale(1, RoundingMode.HALF_UP).doubleValue();
	}

	public static double roundError2(double value) {
		BigDecimal valueBigDecimal = new BigDecimal(Double.toString(value));
		return valueBigDecimal.setScale(2, RoundingMode.HALF_UP).doubleValue();
	}

	public VBox getVbTicket() {
		if(vbTicket==null) {
			vbTicket=new VBox();
		}
		return vbTicket;
	}

	public String getEndroit() {
		return endroit;
	}

	public int getNumTable() {
		return numTable;
	}


	public void setEndroit(String endroit) {
		this.endroit = endroit;
	}

	public void setNumTable(int numTable) {
		this.numTable = numTable;
	}

	public Label getLblNumTable() {
		if(lblNumTable==null) {
			lblNumTable=new Label();
			lblNumTable.setText("Table: "+getNumTable()+" - "+getEndroit());
		}
		return lblNumTable;
	}

	public void GenererTicket() {
		HBox hbEntete = new HBox();
		hbEntete.setPrefWidth(200.);
		Label entete = new Label();
		Image img = new Image("img/logo7.jpg");
		ImageView view = new ImageView(img);
		view.setFitWidth(200.);
		view.setPreserveRatio(true);

		entete.setTextAlignment(TextAlignment.CENTER);
		entete.setText("\nRaphael Manzano Gonzalez\n"
				+ "NIF : Z0472865-J\n"
				+ "Avd.Doctor Gregorio Maranon,43\n"
				+ "Torrevieja (Alicante)\n"
				+ "TLF: +34 692 311 422\n"
				+ "E-mail: elsarandres@hotmail.com\n"
				+ "=========================\n"
				+ "FACTURA SIMPLIFICADA\n"
				+ "-------------------------------------------\n");
		hbEntete.getChildren().add(entete);
		//Entete partie 2 OK
		HBox hbEnteteSuite = new HBox();
		Label lblEnteteSuite = new Label();
		DateFormat dateFormat = new SimpleDateFormat("d/m/YYYY   HH:mm");
		String date = dateFormat.format(new Date());
		lblEnteteSuite.setText("N Op.:     [Pendiente]             "+endroit.substring(0,3)+"/"+numTable+"\n"
				+ "-------------------------------------------\n"
				+ date +"                     Aurelia\n"
				+ "=========================\n"
				+ "Uds.   Producto                         Importe\n"
				+ "-------------------------------------------\n");
		hbEnteteSuite.getChildren().add(lblEnteteSuite);

		//Affichage Produit OK
		VBox vbProd = new VBox();
		for(int i=0;i<=getTvTicket().getItems().size()-1;i++) {
			HBox hbProd = new HBox();
			Label lblQt = new Label();
			lblQt.setPrefWidth(35.);
			Label lblNom = new Label();
			lblNom.setPrefWidth(135.);
			Label lblPrix = new Label();
			lblQt.setText(getTvTicket().getItems().get(i).getQuantite()+"");
			lblNom.setText(getTvTicket().getItems().get(i).getNom()+"");
			lblPrix.setText(getTvTicket().getItems().get(i).getPrixTot()+"");
			hbProd.getChildren().addAll(lblQt,lblNom,lblPrix);
			vbProd.getChildren().addAll(hbProd);
		}
		//TVA OK
		HBox hbTVA = new HBox();
		Label lblTVa = new Label();
		lblTVa.setStyle("-fx-font-color: black;");
		lblTVa.setText("-------------------------------------------\n"
				+"10,00%: Base:    "+(roundError2(total/1.1))+"  Cuota: "+(roundError2(total-(total/1.1)))+"\n"
				+"             Total:    "+(roundError2(total/1.1))+"  Total:   "+(roundError2(total-(total/1.1)))+"\n"
				+"-------------------------------------------\n");
		hbTVA.getChildren().add(lblTVa);
		//Prix Total OK
		HBox hbPrixTot = new HBox();
		Label lblPrixTot = new Label();
		lblPrixTot.setStyle("-fx-font-weight: bold;");
		lblPrixTot.setText("TOTAL (Impuestos Incl.)         "+roundError(total)+"\n"
				+ "=========================\n");

		hbPrixTot.getChildren().add(lblPrixTot);
		//Remerciements OK
		HBox hbRem = new HBox();
		hbRem.setAlignment(Pos.CENTER);
		Label lblRem = new Label();
		lblRem.setText("MUCHAS GRACIAS/THANKS YOU FOR YOUR VISIT\nIVA INCLUIDO/VAT INCLUDED\n");
		lblRem.setPadding(new Insets(0.,0.,0.,-10.));
		lblRem.setTextAlignment(TextAlignment.CENTER);
		lblRem.setStyle("-fx-font: 8 arial;");
		hbRem.getChildren().add(lblRem);

		getVbTicket().getChildren().addAll(view,hbEntete,hbEnteteSuite,vbProd,hbTVA,hbPrixTot,hbRem);

	}

	public void sauvegarder() {
		List<Table> lstTable = new ArrayList<Table>();
		for(int i=0;i<=gestionT.getPersonnes().size()-1;i++) {
			lstTable.add(gestionT.getPersonnes().get(i));
		}
		gestionT.deleteList(lstTable);
		for(int j=0;j<=lstTable.size()-1;j++) {
			if((lstTable.get(j).getNumTable()==numTable) && (lstTable.get(j).getEndroit().equals(endroit))) {
				lstTable.get(j).setMontant(total);
				lstTable.get(j).setLstProduits(getTvTicket().getItems());
				lstTable.get(j).getLstProduits().forEach(x ->{
					x.setQuantite(x.getQuantite());
				});
			}
			gestionT.ajouter(lstTable.get(j));
		}
		Persistance.ecrireJson("table.json", gestionT);
		getTvTicket().getItems().clear();
		getTvTicket().refresh();
	}

	public void RemiseAZero() {
		List<Table> lstTable = new ArrayList<Table>();
		for(int i=0;i<=gestionT.getPersonnes().size()-1;i++) {
			lstTable.add(gestionT.getPersonnes().get(i));

		}
		gestionT.deleteList(lstTable);	
		for(int j=0;j<=lstTable.size()-1;j++) {
			if(lstTable.get(j).getMontant()!=0. || lstTable.size()>0) {
				if(lstTable.get(j).getNumTable()==numTable && lstTable.get(j).getEndroit().equals(endroit)) {
					lstTable.get(j).setMontant(0.);
					lstTable.get(j).getLstProduits().removeAll(lstTable.get(j).getLstProduits());
					lstTable.get(j).getLstProduits().forEach(x ->{
						x.setQuantite(0);
					});
				}
			}
			gestionT.ajouter(lstTable.get(j));
		}
		total=0.0;
		Persistance.ecrireJson("table.json", gestionT);
		getTvTicket().getItems().clear();
		getTvTicket().refresh();
		getLblTotal().setText("Total : " + total + "€");
		getLstProd().clear();
	}

	public void ListProdFill() {
		for(int k=0;k<=gestionT.getPersonnes().size()-1;k++) {
			if(getNumTable() == gestionT.getPersonnes().get(k).getNumTable() && gestionT.getPersonnes().get(k).getEndroit().equals(getEndroit())) {
				getLstProd().addAll(gestionT.getPersonnes().get(k).getLstProduits());
				total=gestionT.getPersonnes().get(k).getMontant();
				getLblTotal().setText("Total : " + total + "€");
			}
		}
	}
}