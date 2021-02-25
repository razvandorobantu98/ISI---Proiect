package com.mycompany.app;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class RoutesListWindow {
    public static void showRouteList(Stage stage, DataFile data) {
    	
    	GridPane grid = new GridPane();
    	grid.setAlignment(Pos.TOP_LEFT);
    	grid.setHgap(10);
    	grid.setVgap(10);
    	grid.setPadding(new Insets(25, 25, 25, 25));

    	BorderPane root = new BorderPane();
     	Scene scene = new Scene(root, 300, 275);
     	stage.setScene(scene);
     	
     	Label label = new Label("Meniu"); 
        Button button1 = new Button("Log out"); 

        // creating toolbar 
        ToolBar toolbar = new ToolBar(label, button1);
        root.setTop(toolbar);
 		root.setCenter(grid);
 	        
         button1.setOnAction(new EventHandler<ActionEvent>() {
        	 
             @Override
             public void handle(ActionEvent e) {
             	data.current_user = null;
             	LoginWindow.showLogin(stage, data);
             }
         });
    	
    	//Debugging
    	//grid.setGridLinesVisible(true);
    	
    	Text scenetitle = new Text("Lista rute turistice");
    	scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
    	scenetitle.setTextAlignment(TextAlignment.LEFT);
    	GridPane.setConstraints(scenetitle, 0, 0, 3, 1, HPos.LEFT, VPos.CENTER);
    	grid.getChildren().add(scenetitle);
		
        int idx = 0;
        for (int i = 0; i < data.routes.size(); i++) {
        	Label route_name = new Label("Traseu " + (i + 1) + ": " + (data.routes).get(i).name);
        	route_name.setFont(Font.font("Tahoma", FontWeight.NORMAL, 18));
        	GridPane.setConstraints(route_name, 0, idx + 1, 3, 1, HPos.LEFT, VPos.CENTER);
        	grid.getChildren().add(route_name);
        	
        	Label route_desc = new Label((data.routes).get(i).description);
        	route_desc.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
        	GridPane.setConstraints(route_desc, 0, idx + 2, 3, 1, HPos.LEFT, VPos.CENTER);
        	grid.getChildren().add(route_desc);
        	
        	Button button_review = new Button("Vezi recenzii");
        	GridPane.setConstraints(button_review, 0, idx + 3, 1, 1, HPos.RIGHT, VPos.CENTER);
        	grid.getChildren().add(button_review);
        	
        	final Integer static_i = i;
        	button_review.setOnAction(new EventHandler<ActionEvent>() {
           	
                @Override
                public void handle(ActionEvent e) {
                    ReviewWindow.showReviewsList(stage, data, (data.routes).get(static_i));
                }
            });
        	
        	Button button_rating = new Button("Vezi rating");
        	GridPane.setConstraints(button_rating, 1, idx + 3, 1, 1, HPos.RIGHT, VPos.CENTER);
        	grid.getChildren().add(button_rating);
        	
        	Label rating_label = new Label("");
        	route_desc.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
        	GridPane.setConstraints(rating_label, 3, idx + 3, 1, 1, HPos.LEFT, VPos.CENTER);
        	grid.getChildren().add(rating_label);
        	
        	button_rating.setOnAction(new EventHandler<ActionEvent>() {
           	 
                @Override
                public void handle(ActionEvent e) {
                	float rating_val = (data.routes).get(static_i).computeRating();
                	rating_label.setText("Rating: " + String.valueOf(rating_val));
                }
            });
        	
        	Button button_map = new Button("Vezi harta");
        	GridPane.setConstraints(button_map, 2, idx + 3, 1, 1, HPos.RIGHT, VPos.CENTER);
        	grid.getChildren().add(button_map);
        	
        	button_map.setOnAction(new EventHandler<ActionEvent>() {
           	 
                @Override
                public void handle(ActionEvent e) {
                    MapWindow.showMap(stage, data, static_i + 1);
                }
            });
        	
        	idx += 5;
        }

    }
}
