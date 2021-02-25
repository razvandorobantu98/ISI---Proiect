package com.mycompany.app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class AddReviewWindow {
	
	public static void showAddReview(Stage stage, DataFile data, Route current_route) {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
    	grid.setHgap(10);
    	grid.setVgap(10);
    	grid.setPadding(new Insets(25, 25, 25, 25));
    	
        BorderPane root = new BorderPane();
    	Scene scene = new Scene(root, 300, 275);
    	stage.setScene(scene);
    	
    	//Debugging
    	//grid.setGridLinesVisible(true);
    	
    	Label label = new Label("Meniu"); 
    	Button button1 = new Button("Vezi lista trasee");
        Button button2 = new Button("Log out");

        // creating toolbar 
        ToolBar toolbar = new ToolBar(label, button1, button2);
        root.setTop(toolbar);
		root.setCenter(grid);
		
		button1.setOnAction(new EventHandler<ActionEvent>() {
           	
            @Override
            public void handle(ActionEvent e) {
            	RoutesListWindow.showRouteList(stage, data);
            }
        });
	    
        button2.setOnAction(new EventHandler<ActionEvent>() {
       	 
            @Override
            public void handle(ActionEvent e) {
            	data.current_user = null;
            	LoginWindow.showLogin(stage, data);
            }
        });
    	
    	Text scenetitle = new Text("Adauga recenzie");
    	scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
    	scenetitle.setTextAlignment(TextAlignment.LEFT);
    	GridPane.setConstraints(scenetitle, 0, 0, 3, 1, HPos.CENTER, VPos.CENTER);
    	grid.getChildren().add(scenetitle);
    	
    	Label review_label = new Label("Recenzie:");
    	GridPane.setConstraints(review_label, 0, 1, 1, 1, HPos.LEFT, VPos.CENTER);
    	grid.getChildren().add(review_label);
    	
    	Label combo_label = new Label("Nota:");
    	GridPane.setConstraints(combo_label, 0, 2, 1, 1, HPos.LEFT, VPos.CENTER);
    	grid.getChildren().add(combo_label);
    	
    	TextField review_field = new TextField();
    	GridPane.setConstraints(review_field, 1, 1, 3, 1, HPos.CENTER, VPos.CENTER);
    	grid.getChildren().add(review_field);
    	
    	ObservableList<String> options = 
    			FXCollections.observableArrayList(
    					"10",
    		            "9",
    		            "8",
    		            "7",
    		            "6",
    		            "5",
    		            "4",
    		            "3",
    		            "2",
    		            "1"
    		        );
    	final ComboBox<String> comboBox = new ComboBox<>(options);
    	GridPane.setConstraints(comboBox, 0, 2, 3, 1, HPos.CENTER, VPos.CENTER);
    	grid.getChildren().add(comboBox);
    	
    	Button sendButton = new Button("Posteaza");
    	sendButton.setMinWidth(10);
    	GridPane.setConstraints(sendButton, 0, 3, 3, 1, HPos.CENTER, VPos.CENTER);
    	grid.getChildren().add(sendButton);
    	
    	final Text actiontarget = new Text();
    	GridPane.setConstraints(actiontarget, 0, 4, 3, 1, HPos.CENTER, VPos.CENTER);
    	grid.getChildren().add(actiontarget);
    	
    	sendButton.setOnAction(new EventHandler<ActionEvent>() {
       	 
            @Override
            public void handle(ActionEvent e) {
            	if (review_field.getText().equals("") || comboBox.getValue() == null) {
            		actiontarget.setText("Completati toate campurile!");
            	}
            	else {
            		System.out.println();
            		current_route.AddReview(data.current_user.username, current_route.name, review_field.getText(), (String)comboBox.getValue());
            		data.writeReviews();
            		ReviewWindow.showReviewsList(stage, data, current_route);
            	}
            }
        });
    	
	}
}