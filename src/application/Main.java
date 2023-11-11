package application;

import javafx.application.Application;
import javafx.stage.Stage;
import model.GestionProduits;
import model.GestionTable;
import view.MainWindowBP;
import javafx.scene.Scene;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		GestionProduits gestionP = new GestionProduits();
		GestionTable gestionT = new GestionTable();
		gestionP.importProd();
		gestionT.importTable();
		try {
			MainWindowBP root = new MainWindowBP(gestionP,gestionT);
			Scene scene = new Scene(root,974,724);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setFullScreen(true);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}