package com.mycompany.app;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    public static DataFile data;
    
    public static void main(String[] args) {
    	
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
    	
        DataFile data = new DataFile();
    	data.readUsers();
    	data.readRoutes();
    	data.readReviews();
    	
        stage.setTitle("My Map App");
        stage.setWidth(800);
        stage.setHeight(700);
        stage.show();
        
        LoginWindow.showLogin(stage, data);
    }
     
    /**
     * Stops and releases all resources used in application.
     */
    @Override
    public void stop() {
    }
}
