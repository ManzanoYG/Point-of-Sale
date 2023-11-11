package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.GestionProduits;
import model.GestionTable;

public class CaisseBP extends BorderPane {
	private GestionProduits gestionP;
	private GestionTable gestionT;
	private GridPane gridIntérieur;
	private GridPane gridExterieur;
	private HBox hbGlob;

	public CaisseBP(GestionProduits gestionP, GestionTable gestionT) {
		this.gestionP = gestionP;
		this.gestionT = gestionT;
		this.setCenter(getHbGlob());
	}

	public GridPane getGridExterieur() {
		if(gridExterieur==null) {
			gridExterieur = new GridPane();
			gridExterieur.setStyle("-fx-background-image: url(\"img/exterieur.png\");-fx-background-size: 500px 700px;-fx-background-repeat: no-repeat;-fx-background-position: center;");
			int num = 15;
			for(int i =0; i<=2;i++) {
				for(int j = 0;j<=4;j++) {
					Label l = new Label("T"+num);
					l.setPadding(new Insets(-25.,0.,0.,0.));
					Button b = new Button();
					VBox vb = new VBox(b,l);
					vb.setAlignment(Pos.CENTER);
					vb.setPadding(new Insets(20.));
					gridExterieur.add(vb, i, j);	
					Image img = new Image("img/table.png");
					ImageView view = new ImageView(img);
					view.setFitHeight(70);
					view.setPreserveRatio(true);
					b.setGraphic(view);
					b.setStyle("-fx-background-color: white; -fx-opacity: 0.40; ");
//					for(int k=0;k<=gestionT.getPersonnes().size()-1;k++) {
//						if(num == gestionT.getPersonnes().get(k).getNumTable() && gestionT.getPersonnes().get(k).getEndroit().equals("exterieur") && gestionT.getPersonnes().get(k).getMontant()!=0.) {
//							b.setStyle("-fx-background-color: green; -fx-opacity: 0.40; ");
//						}
//					}
					b.setId(""+num);
					num--;
				}
			}
			gridExterieur.setAlignment(Pos.CENTER);
		}
		return gridExterieur;
	}

	public GridPane getGridIntérieur() {
		if(gridIntérieur == null) {
			gridIntérieur = new GridPane();
			gridIntérieur.setStyle("-fx-background-image: url(\"img/interieur.png\");-fx-background-size: 300px 700px;-fx-background-repeat: no-repeat;-fx-background-position: center;");
			int num = 7;
			for(int i =0; i<=2;i++) {
				for(int j = 0;j<=6;j++) {
					Image img = new Image("img/table.png");
					ImageView view = new ImageView(img);
					view.setFitHeight(70);
					view.setPreserveRatio(true);

					if(i==0) {
						Label l = new Label("Int"+num);
						l.setPadding(new Insets(-25.,0.,0.,0.));
						Button b = new Button();
						VBox vb = new VBox(b,l);
						vb.setAlignment(Pos.CENTER);
						vb.setPadding(new Insets(10.));
						gridIntérieur.add(vb, i, j);
						b.setGraphic(view);
						b.setStyle("-fx-background-color: white; -fx-opacity: 0.40; ");
//						for(int k=0;k<=gestionT.getPersonnes().size()-1;k++) {
//							if(num == gestionT.getPersonnes().get(k).getNumTable() && gestionT.getPersonnes().get(k).getEndroit().equals("interieur") && gestionT.getPersonnes().get(k).getMontant()!=0.) {
//								b.setStyle("-fx-background-color: green; -fx-opacity: 0.40; ");
//							}
//						}
						b.setId(""+num);
					}
					else {
						Rectangle r = new Rectangle();
						r.setWidth(70);
						r.setHeight(70);
						r.setFill(Color.TRANSPARENT);
						gridIntérieur.add(r, i, j);
					}
					num--;
				}
			}
			gridIntérieur.setAlignment(Pos.CENTER);
		}
		return gridIntérieur;
	}

	public HBox getHbGlob() {
		if(hbGlob == null) {
			hbGlob = new HBox(getGridIntérieur(),getGridExterieur());
			hbGlob.setAlignment(Pos.CENTER);
			hbGlob.setSpacing(100.);
		}
		return hbGlob;
	}

}