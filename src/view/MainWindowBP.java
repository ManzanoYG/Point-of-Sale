package view;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.PrinterJob;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import model.GestionProduits;
import model.GestionTable;
public class MainWindowBP extends BorderPane{
	private Button btnTableau;
	private Button btnCaisse;
	private Button btnArret;
	private Button btnDeco;
	private Button btnParam;
	private Button btnGraph;
	private GestionProduits gestionP;
	private GestionTable gestionT;
	private FenetreSP fenetre;
	private AjoutBP a;
	private VBox vbLeft;


	public static final double BUTTON_WIDTH = 80.;

	public MainWindowBP(GestionProduits gestionP, GestionTable gestionT) 
	{
		this.gestionP = gestionP;
		this.gestionT = gestionT;
		this.setLeft(getVbLeft()); 
		this.setCenter(getFenetre());
	}

	public Button getBtnTableau() {
		if(btnTableau==null) 
		{
			btnTableau = new Button();
			btnTableau.setPrefWidth(BUTTON_WIDTH);
			btnTableau.setVisible(false);

			Image img = new Image("img/donnees.png");
			ImageView view = new ImageView(img);
			view.setFitHeight(35);
			view.setPreserveRatio(true);
			btnTableau.setGraphic(view);

			btnTableau.setOnAction(new EventHandler<ActionEvent>() 
			{
				@Override
				public void handle(ActionEvent event) 
				{
					getFenetre().hide();
					// TODO Auto-generated method stub
					getFenetre().getPnlTableau().setVisible(true);
					//					getFenetre().getPnlTableau().getTvPersonne().getItems().removeAll(gestion.getPersonnes());
					//					getFenetre().getPnlTableau().getTvPersonne().setItems(FXCollections.observableArrayList(gestion.getPersonnes()));
				}
			});
		}
		return btnTableau;
	}

	public Button getBtnArret() {
		if(btnArret==null) 
		{
			btnArret = new Button();
			btnArret.setPrefWidth(BUTTON_WIDTH);

			Image img = new Image("img/arret.png");
			ImageView view = new ImageView(img);
			view.setFitHeight(35);
			view.setPreserveRatio(true);
			btnArret.setGraphic(view);

			btnArret.setOnAction(new EventHandler<ActionEvent>() 
			{
				@Override
				public void handle(ActionEvent event) 
				{
					Platform.exit();
				}
			});
		}
		return btnArret;
	}

	public Button getBtnCaisse() {
		if(btnCaisse==null) 
		{
			btnCaisse = new Button();
			btnCaisse.setPrefWidth(BUTTON_WIDTH);
			btnCaisse.setVisible(false);

			Image img = new Image("img/caisse.png");
			ImageView view = new ImageView(img);
			view.setFitHeight(35);
			view.setPreserveRatio(true);
			btnCaisse.setGraphic(view);

			btnCaisse.setOnAction(new EventHandler<ActionEvent>() 
			{
				@Override
				public void handle(ActionEvent event) 
				{
					getFenetre().hide();
					getFenetre().getPnlCaisse().setVisible(true);

				}
			});
			getFenetre().getPnlCaisse().getGridExterieur().getChildren().forEach(x ->
			{
				if(x instanceof VBox) {
					((VBox) x).getChildren().forEach(z ->
					{
						if(z instanceof Button) {
							((Button) z).setOnAction(new EventHandler<ActionEvent>() {

								@Override
								public void handle(ActionEvent event) {
									// TODO Auto-generated method stub
									getFenetre().hide();
									getFenetre().getPnlAjout().setVisible(true);
									getFenetre().getPnlAjout().setEndroit("exterieur");
									getFenetre().getPnlAjout().setNumTable(Integer.parseInt(z.getId()));
									getFenetre().getPnlAjout().getLblNumTable().setText("Table: "+Integer.parseInt(z.getId())+" - exterieur");
									getBtnCaisse().setDisable(true);
									getBtnDeco().setDisable(true);
									getBtnGraph().setDisable(true);
									getBtnParam().setDisable(true);
									getBtnTableau().setDisable(true);
									getFenetre().getPnlAjout().ListProdFill();
									getFenetre().getPnlAjout().getTvTicket().setItems(FXCollections.observableArrayList(getFenetre().getPnlAjout().getLstProd()));
									getFenetre().getPnlAjout().getTvTicket().refresh();
								}
							});
						}
					});
				}
			});
			getFenetre().getPnlCaisse().getGridIntérieur().getChildren().forEach(x ->
			{
				if(x instanceof VBox) {
					((VBox) x).getChildren().forEach(z ->
					{
						if(z instanceof Button) {
							((Button) z).setOnAction(new EventHandler<ActionEvent>() {

								@Override
								public void handle(ActionEvent event) {
									// TODO Auto-generated method stub
									getFenetre().hide();
									getFenetre().getPnlAjout().setVisible(true);
									getFenetre().getPnlAjout().setEndroit("interieur");
									getFenetre().getPnlAjout().setNumTable(Integer.parseInt(z.getId()));
									getFenetre().getPnlAjout().getLblNumTable().setText("Table: "+Integer.parseInt(z.getId())+" - interieur");
									getBtnCaisse().setDisable(true);
									getBtnDeco().setDisable(true);
									getBtnGraph().setDisable(true);
									getBtnParam().setDisable(true);
									getBtnTableau().setDisable(true);
									getFenetre().getPnlAjout().ListProdFill();
									getFenetre().getPnlAjout().getTvTicket().setItems(FXCollections.observableArrayList(getFenetre().getPnlAjout().getLstProd()));
									getFenetre().getPnlAjout().getTvTicket().refresh();
								}
							});
						}
					});
				}
			});
			getFenetre().getPnlAjout().getBtnPrint().setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					getFenetre().getPnlAjout().GenererTicket();
					PrinterJob job = PrinterJob.createPrinterJob();

					PageLayout pageLayout = job.getPrinter().createPageLayout(Paper.A0, PageOrientation.PORTRAIT, 0, 0, 0, 0);
					// Imprime la zone texte
					boolean printed = job.printPage(pageLayout,getFenetre().getPnlAjout().getVbTicket());
					if (printed) {
						job.endJob();
						getFenetre().getPnlAjout().getTvTicket().getItems().clear();
						getFenetre().getPnlAjout().getTvTicket().refresh();
						getFenetre().getPnlAjout().total=0.;
						getFenetre().getPnlAjout().getLblTotal().setText("Total : 0,0 €");
						getFenetre().getPnlAjout().getLstProd().clear();
						getFenetre().getPnlAjout().getVbTicket().getChildren().clear();
						getBtnCaisse().setDisable(false);
						getBtnDeco().setDisable(false);
						getBtnGraph().setDisable(false);
						getBtnParam().setDisable(false);
						getBtnTableau().setDisable(false);
						getFenetre().getPnlAjout().setVisible(false);
						getFenetre().getPnlCaisse().setVisible(true);
						getFenetre().getPnlAjout().RemiseAZero();
					}
				}
			});
			getFenetre().getPnlAjout().getBtnSave().setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
						getFenetre().getPnlAjout().sauvegarder();
						getFenetre().getPnlAjout().total=0.;
						getFenetre().getPnlAjout().getLblTotal().setText("Total : 0,0 €");
						getFenetre().getPnlAjout().getLstProd().clear();
						getBtnCaisse().setDisable(false);
						getBtnDeco().setDisable(false);
						getBtnGraph().setDisable(false);
						getBtnParam().setDisable(false);
						getBtnTableau().setDisable(false);
						getFenetre().getPnlAjout().setVisible(false);
						getFenetre().getPnlCaisse().setVisible(true);
						
						//-----------------------
						getFenetre().getPnlCaisse().getGridIntérieur().getChildren().forEach(x ->{
							if(x instanceof VBox) {
								((VBox) x).getChildren().forEach(z ->{
									if(z instanceof Button) {
										for(int k=0;k<=gestionT.getPersonnes().size()-1;k++) {
										if(Integer.parseInt(((Button) z).getId()) == gestionT.getPersonnes().get(k).getNumTable() && gestionT.getPersonnes().get(k).getEndroit().equals("interieur") && gestionT.getPersonnes().get(k).getMontant()!=0.) {
											((Button) z).setStyle("-fx-background-color: green; -fx-opacity: 0.60; ");
										}
										else if(Integer.parseInt(((Button) z).getId()) == gestionT.getPersonnes().get(k).getNumTable() && gestionT.getPersonnes().get(k).getEndroit().equals("interieur") && gestionT.getPersonnes().get(k).getMontant()==0.){
											((Button) z).setStyle("-fx-background-color: white; -fx-opacity: 0.40; ");
										}
									}
									}
								});
							}
						});
						getFenetre().getPnlCaisse().getGridExterieur().getChildren().forEach(x ->{
							if(x instanceof VBox) {
								((VBox) x).getChildren().forEach(z ->{
									if(z instanceof Button) {
										for(int k=0;k<=gestionT.getPersonnes().size()-1;k++) {
										if(Integer.parseInt(((Button) z).getId()) == gestionT.getPersonnes().get(k).getNumTable() && gestionT.getPersonnes().get(k).getEndroit().equals("exterieur") && gestionT.getPersonnes().get(k).getMontant()!=0.) {
											((Button) z).setStyle("-fx-background-color: green; -fx-opacity: 0.60; ");
										}
										else if(Integer.parseInt(((Button) z).getId()) == gestionT.getPersonnes().get(k).getNumTable() && gestionT.getPersonnes().get(k).getEndroit().equals("exterieur") && gestionT.getPersonnes().get(k).getMontant()==0.){
											((Button) z).setStyle("-fx-background-color: white; -fx-opacity: 0.40; ");
										}
									}
									}
								});
							}
						});
				}
			});
		}
		return btnCaisse;
	}

	public FenetreSP getFenetre() {
		if(fenetre==null) 
		{
			fenetre = new FenetreSP(gestionP, gestionT);
			fenetre.getPnlWelcome().getBtnValider().setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					if(fenetre.getPnlWelcome().getStCode().equals("000")) {
						getBtnTableau().setVisible(true);
						getBtnCaisse().setVisible(true);
						getBtnArret().setVisible(false);
						getBtnDeco().setVisible(true);
						getBtnArret().setManaged(false);
						getBtnParam().setVisible(true);
						getBtnGraph().setVisible(true);

						fenetre.hide();
						fenetre.getPnlTableau().setVisible(true);
					}
					if(fenetre.getPnlWelcome().getStCode().equals("123")) {
						System.out.println("ok");
					}
					fenetre.getPnlWelcome().getTxtfCode().setText("");
					fenetre.getPnlWelcome().setStCode("");
				}
			});
		}
		return fenetre;
	}

	public static double getButtonWidth() {
		return BUTTON_WIDTH;
	}

	public Button getBtnDeco() {
		if(btnDeco == null) {
			btnDeco = new Button();
			btnDeco.setPrefWidth(BUTTON_WIDTH);
			btnDeco.setVisible(false);

			Image img = new Image("img/verrouiller.png");
			ImageView view = new ImageView(img);
			view.setFitHeight(35);
			view.setPreserveRatio(true);
			btnDeco.setGraphic(view);

			btnDeco.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					getFenetre().hide();
					getFenetre().getPnlWelcome().setVisible(true);
					getBtnCaisse().setVisible(false);
					getBtnArret().setVisible(true);
					getBtnDeco().setVisible(false);
					getBtnParam().setVisible(false);
					getBtnTableau().setVisible(false);
					getBtnGraph().setVisible(false);
				}
			});
		}
		return btnDeco;
	}

	public VBox getVbLeft() {
		if(vbLeft == null) {
			vbLeft=new VBox(getBtnArret(),getBtnTableau(),getBtnCaisse(),getBtnGraph(),getBtnParam(),getBtnDeco());
			vbLeft.setStyle("-fx-border-style: solid;-fx-border-width: 0.5;-fx-border-color: gray;");
			vbLeft.setSpacing(5.);
			vbLeft.setPadding(new Insets(10.));
			vbLeft.setPrefWidth(100.);
		}
		return vbLeft;
	}

	public Button getBtnParam() {
		if(btnParam == null) {
			btnParam = new Button();
			btnParam.setPrefWidth(BUTTON_WIDTH);
			btnParam.setVisible(false);

			Image img = new Image("img/parametre.png");
			ImageView view = new ImageView(img);
			view.setFitHeight(35);
			view.setPreserveRatio(true);
			btnParam.setGraphic(view);
		}
		return btnParam;
	}

	public Button getBtnGraph() {
		if(btnGraph == null) {
			btnGraph = new Button();
			btnGraph.setPrefWidth(BUTTON_WIDTH);
			btnGraph.setVisible(false);

			Image img = new Image("img/graphique.png");
			ImageView view = new ImageView(img);
			view.setFitHeight(35);
			view.setPreserveRatio(true);
			btnGraph.setGraphic(view);
		}
		return btnGraph;
	}

	public AjoutBP getA() {
		if(a==null) {
			a = new AjoutBP(gestionP, gestionT);
		}
		return a;
	}
}