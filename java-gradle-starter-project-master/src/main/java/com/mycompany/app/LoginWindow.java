package com.mycompany.app;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class LoginWindow {
	public static void showLogin(Stage stage, DataFile data)
    {
    	GridPane grid = new GridPane();
    	grid.setAlignment(Pos.CENTER);
    	grid.setHgap(10);
    	grid.setVgap(10);
    	grid.setPadding(new Insets(25, 25, 25, 25));

    	Scene scene = new Scene(grid, 300, 275);
    	stage.setScene(scene);
    	
    	Text scenetitle = new Text("Log in / Register");
    	scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
    	scenetitle.setTextAlignment(TextAlignment.LEFT);
    	GridPane.setConstraints(scenetitle, 1, 0, 2, 1, HPos.CENTER, VPos.CENTER);
    	grid.getChildren().add(scenetitle);

    	Label userLabel = new Label("User Name:");
    	GridPane.setConstraints(userLabel, 1, 1, 1, 1, HPos.LEFT, VPos.CENTER);
    	grid.getChildren().add(userLabel);

    	TextField userField = new TextField();
    	GridPane.setConstraints(userField, 2, 1, 1, 1, HPos.LEFT, VPos.CENTER);
    	grid.getChildren().add(userField);

    	Label passwordLabel = new Label("Password:");
    	GridPane.setConstraints(passwordLabel, 1, 2, 1, 1, HPos.LEFT, VPos.CENTER);
    	grid.getChildren().add(passwordLabel);

    	PasswordField passwordField = new PasswordField();
    	GridPane.setConstraints(passwordField, 2, 2, 1, 1, HPos.LEFT, VPos.CENTER);
    	grid.getChildren().add(passwordField);
    	
    	//Debugging
    	//grid.setGridLinesVisible(true);
    	
    	CheckBox checkBox = new CheckBox("Locuiesc in Cluj");
    	GridPane.setConstraints(checkBox, 1, 4, 3, 1, HPos.LEFT, VPos.CENTER);
    	grid.getChildren().add(checkBox);
        
    	
    	Button signButton = new Button("Log in");
    	signButton.setMinWidth(10);
    	GridPane.setConstraints(signButton, 1, 6, 1, 1, HPos.RIGHT, VPos.CENTER);
    	grid.getChildren().add(signButton);
    	
    	final Text actiontarget = new Text();
    	GridPane.setConstraints(actiontarget, 0, 9, 3, 1, HPos.CENTER, VPos.CENTER);
    	grid.getChildren().add(actiontarget);
    	
        
        signButton.setOnAction(new EventHandler<ActionEvent>() {
        	 
            @Override
            public void handle(ActionEvent e) {
                actiontarget.setFill(Color.FIREBRICK);
                
                if (data.checkUser(userField.getText(), passwordField.getText()) == false)
                	actiontarget.setText("Contul nu exista!");
                else
                {
                	actiontarget.setText("V-ati autentificat cu succes!");
                	MapWindow.showMap(stage, data, 0);
                }
                userField.clear();
                passwordField.clear();
                checkBox.setSelected(false);
            }
        });
        
        Button regButton = new Button("Register");
        regButton.setMinWidth(10);
    	GridPane.setConstraints(regButton, 2, 6, 1, 1, HPos.RIGHT, VPos.CENTER);
    	grid.getChildren().add(regButton);
    	
    	
    	regButton.setOnAction(new EventHandler<ActionEvent>() {
       	 
            @Override
            public void handle(ActionEvent e) {
                actiontarget.setFill(Color.FIREBRICK);
                
                if (checkInputIntegrity(userField.getText(), passwordField.getText()) == 1)
                	actiontarget.setText("Campurile user si parola trebuie sa fie completate!");
                else if (checkInputIntegrity(userField.getText(), passwordField.getText()) == 2)
                	actiontarget.setText("Sunt permise doar caracterele: 0-9, a-z, A-Z!");
                else
                {
                	if (data.checkIfExists(userField.getText()))
                		actiontarget.setText("Contul deja exista!");
                	else {
                		actiontarget.setText("Cont creeat cu succes!");
	                	data.addUser(userField.getText(), passwordField.getText(), checkBox.isSelected());
	                	data.writeUsers();
                	}
                }
                userField.clear();
                passwordField.clear();
                checkBox.setSelected(false);
            }
        });
    }
    
    public static int checkInputIntegrity(String user, String password) {
    	if (user.length() == 0 || password.length() == 0)
    		return 1;
    	
    	for (int i = 0; i < user.length(); i++) {
    		char c = user.charAt(i);
    		if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9'))
    			continue;
    		
    		return 2;
    	}
    	for (int i = 0; i < password.length(); i++) {
    		char c = password.charAt(i);
    		if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9'))
    			continue;
    		
    		return 2;
    	}
    	return 0;
    }
}
