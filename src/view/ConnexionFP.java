package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ConnexionFP extends FlowPane {
	private Button btn0;
	private Button btn1;
	private Button btn2;
	private Button btn3;
	private Button btn4;
	private Button btn5;
	private Button btn6;
	private Button btn7;
	private Button btn8;
	private Button btn9;

	private Button btnValider;
	private Button btnEffacer;

	private TextField txtfCode;

	private HBox hb789;
	private HBox hb456;
	private HBox hb123;
	private HBox hb0;
	private HBox hbVaEf;

	private VBox vbGlob;

	private String stCode = "";
	private static final double hautBtn = 650.;

	public ConnexionFP() {
		this.setAlignment(Pos.CENTER);
		this.getChildren().add(getVbGlob());
	}

	public Button getBtn0() {
		if(btn0 == null) {
			btn0 = new Button("0");
			btn0.setPrefWidth(Integer.MAX_VALUE);
			btn0.setPrefHeight(Integer.MAX_VALUE);
			btn0.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					getTxtfCode().setText(getTxtfCode().getText()+"•");
					setStCode(getStCode()+"0");
				}
			});
		}
		return btn0;
	}

	public Button getBtn1() {
		if(btn1 == null) {
			btn1 = new Button("1");
			btn1.setPrefWidth(Integer.MAX_VALUE);
			btn1.setPrefHeight(Integer.MAX_VALUE);
			btn1.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					getTxtfCode().setText(getTxtfCode().getText()+"•");
					setStCode(getStCode()+"1");
				}
			});
		}
		return btn1;
	}

	public Button getBtn2() {
		if(btn2 == null) {
			btn2 = new Button("2");
			btn2.setPrefWidth(Integer.MAX_VALUE);
			btn2.setPrefHeight(Integer.MAX_VALUE);
			btn2.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					getTxtfCode().setText(getTxtfCode().getText()+"•");
					setStCode(getStCode()+"2");
				}
			});
		}
		return btn2;
	}

	public Button getBtn3() {
		if(btn3 == null) {
			btn3 = new Button("3");
			btn3.setPrefWidth(Integer.MAX_VALUE);
			btn3.setPrefHeight(Integer.MAX_VALUE);
			btn3.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					getTxtfCode().setText(getTxtfCode().getText()+"•");
					setStCode(getStCode()+"3");
				}
			});
		}
		return btn3;
	}

	public Button getBtn4() {
		if(btn4 == null) {
			btn4 = new Button("4");
			btn4.setPrefWidth(Integer.MAX_VALUE);
			btn4.setPrefHeight(Integer.MAX_VALUE);
			btn4.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					getTxtfCode().setText(getTxtfCode().getText()+"•");
					setStCode(getStCode()+"4");
				}
			});
		}
		return btn4;
	}

	public Button getBtn5() {
		if(btn5 == null) {
			btn5 = new Button("5");
			btn5.setPrefWidth(Integer.MAX_VALUE);
			btn5.setPrefHeight(Integer.MAX_VALUE);
			btn5.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					getTxtfCode().setText(getTxtfCode().getText()+"•");
					setStCode(getStCode()+"5");
				}
			});
		}
		return btn5;
	}

	public Button getBtn6() {
		if(btn6 == null) {
			btn6 = new Button("6");
			btn6.setPrefWidth(Integer.MAX_VALUE);
			btn6.setPrefHeight(Integer.MAX_VALUE);
			btn6.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					getTxtfCode().setText(getTxtfCode().getText()+"•");
					setStCode(getStCode()+"6");
				}
			});
		}
		return btn6;
	}

	public Button getBtn7() {
		if(btn7 == null) {
			btn7 = new Button("7");
			btn7.setPrefWidth(Integer.MAX_VALUE);
			btn7.setPrefHeight(Integer.MAX_VALUE);
			btn7.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					getTxtfCode().setText(getTxtfCode().getText()+"•");
					setStCode(getStCode()+"7");
				}
			});
		}
		return btn7;
	}

	public Button getBtn8() {
		if(btn8 == null) {
			btn8 = new Button("8");
			btn8.setPrefWidth(Integer.MAX_VALUE);
			btn8.setPrefHeight(Integer.MAX_VALUE);
			btn8.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					getTxtfCode().setText(getTxtfCode().getText()+"•");
					setStCode(getStCode()+"8");
				}
			});
		}
		return btn8;
	}

	public Button getBtn9() {
		if(btn9 == null) {
			btn9 = new Button("9");
			btn9.setPrefWidth(Integer.MAX_VALUE);
			btn9.setPrefHeight(Integer.MAX_VALUE);
			btn9.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					getTxtfCode().setText(getTxtfCode().getText()+"•");
					setStCode(getStCode()+"9");
				}
			});
		}
		return btn9;
	}

	public Button getBtnValider() {
		if(btnValider == null) {
			btnValider = new Button();
			
			Image img = new Image("img/deverouiller.png");
			ImageView view = new ImageView(img);
			view.setFitHeight(90);
		     view.setPreserveRatio(true);
		     btnValider.setGraphic(view);
			btnValider.setPrefWidth(Integer.MAX_VALUE);
			btnValider.setPrefHeight(Integer.MAX_VALUE);
		}
		return btnValider;
	}

	public Button getBtnEffacer() {
		if(btnEffacer == null) {
			btnEffacer = new Button("Effacer");
			btnEffacer.setPrefWidth(Integer.MAX_VALUE);
			btnEffacer.setPrefHeight(Integer.MAX_VALUE);
			btnEffacer.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					getTxtfCode().setText("");
					setStCode("");
				}
			});
		}
		return btnEffacer;
	}

	public TextField getTxtfCode() {
		if(txtfCode == null) {
			txtfCode = new TextField();
			txtfCode.setFont(Font.font(25));
			txtfCode.setPromptText("Code de connexion");
			txtfCode.setAlignment(Pos.CENTER);
		}
		return txtfCode;
	}

	public HBox getHb789() {
		if(hb789 == null) {
			hb789 = new HBox(getBtn7(),getBtn8(),getBtn9());
			hb789.setSpacing(5.);
			hb789.setPadding(new Insets(5.));
		}
		return hb789;
	}

	public HBox getHb456() {
		if(hb456 == null) {
			hb456 = new HBox(getBtn4(),getBtn5(),getBtn6());
			hb456.setSpacing(5.);
			hb456.setPadding(new Insets(5.));
		}
		return hb456;
	}

	public HBox getHb123() {
		if(hb123 == null) {
			hb123 = new HBox(getBtn1(),getBtn2(),getBtn3());
			hb123.setSpacing(5.);
			hb123.setPadding(new Insets(5.));
		}
		return hb123;
	}

	public HBox getHb0() {
		if(hb0 == null) {
			hb0 = new HBox(getBtn0());
			hb0.setSpacing(5.);
			hb0.setPadding(new Insets(5.));
		}
		return hb0;
	}

	public HBox getHbVaEf() {
		if(hbVaEf == null) {
			hbVaEf = new HBox(getBtnValider(),getBtnEffacer());
			hbVaEf.setSpacing(5.);
			hbVaEf.setPadding(new Insets(5.));
		}
		return hbVaEf;
	}

	public VBox getVbGlob() {
		if(vbGlob == null) {
			vbGlob = new VBox(getTxtfCode(),getHb789(),getHb456(),getHb123(),getHb0(),getHbVaEf());
			vbGlob.setPrefWidth(400);
			vbGlob.setPrefHeight(getHautbtn());
		}
		return vbGlob;
	}

	public String getStCode() {
		return stCode;
	}

	public void setStCode(String stCode) {
		this.stCode = stCode;
	}

	public static double getHautbtn() {
		return hautBtn;
	}
}