package com.mycompany.app;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class ReviewWindow {
	public static void showReviewsList(Stage stage, DataFile data, Route current_route) {
		GridPane grid = new GridPane();
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
    	Button button2 = new Button("Adauga recenzie");
        Button button3 = new Button("Log out");

        // creating toolbar 
        ToolBar toolbar = new ToolBar(label, button1, button2, button3);
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
            	AddReviewWindow.showAddReview(stage, data, current_route);
            }
        });
	    
        button3.setOnAction(new EventHandler<ActionEvent>() {
       	 
            @Override
            public void handle(ActionEvent e) {
            	data.current_user = null;
            	LoginWindow.showLogin(stage, data);
            }
        });
    	
    	Text scenetitle = new Text("Recenzii " + current_route.name + ":");
    	scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
    	scenetitle.setTextAlignment(TextAlignment.LEFT);
    	GridPane.setConstraints(scenetitle, 0, 0, 3, 1, HPos.CENTER, VPos.CENTER);
    	grid.getChildren().add(scenetitle);
    	
    	int idx = 1;
    	for (int i = 0; i < current_route.reviews.size(); i++) {
    		Label user_label = new Label("Utilizator: " + current_route.reviews.get(i).user_name);
    		user_label.setFont(Font.font("Tahoma", FontWeight.NORMAL, 18));
        	GridPane.setConstraints(user_label, 0, idx, 2, 1, HPos.LEFT, VPos.CENTER);
        	grid.getChildren().add(user_label);
        	
        	String type = "Turist";
        	for (User user : data.users) {
        		if (user.username.equals(current_route.name) && user.type == true) {
        			type = "Locuitor";
        		}
        	}
        	
        	Label type_label = new Label(type);
        	type_label.setFont(Font.font("Tahoma", FontWeight.NORMAL, 18));
        	GridPane.setConstraints(type_label, 2, idx, 1, 1, HPos.RIGHT, VPos.CENTER);
        	grid.getChildren().add(type_label);
        	
        	Label rating_label = new Label(current_route.reviews.get(i).rating);
        	rating_label.setFont(Font.font("Tahoma", FontWeight.NORMAL, 18));
        	GridPane.setConstraints(rating_label, 3, idx, 1, 1, HPos.RIGHT, VPos.CENTER);
        	grid.getChildren().add(rating_label);
        	
        	Label text_label = new Label(current_route.reviews.get(i).review_text);
        	text_label.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
        	GridPane.setConstraints(text_label, 0, idx + 1, 1, 1, HPos.LEFT, VPos.CENTER);
        	grid.getChildren().add(text_label);
        	idx += 4;
    	}
	}
}
