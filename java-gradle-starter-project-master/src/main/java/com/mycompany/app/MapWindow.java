package com.mycompany.app;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.Viewpoint;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.symbology.SimpleLineSymbol;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;
import com.esri.arcgisruntime.symbology.TextSymbol;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MapWindow {
	
	private static  int hexRed = 0xFFFF0000;
	private static  int hexBlue = 0xFF00FF00;
	private static  int hexGreen = 0xFF0000FF;
	
	static ArrayList<Points> points = new ArrayList<Points>();
	
	private static  GraphicsOverlay graphicsOverlay;
	private static  MapView mapView;

	
	
	private static void setupGraphicsOverlay() {
		if (mapView != null) {
			graphicsOverlay = new GraphicsOverlay();
			mapView.getGraphicsOverlays().add(graphicsOverlay);
		}
	}
	
	private static void addPointGraphic(String nume, double x, double y, int color) {
		if (graphicsOverlay != null) {
			TextSymbol textSymbol = new TextSymbol(10, nume, 0xFF000000, TextSymbol.HorizontalAlignment.CENTER, TextSymbol.VerticalAlignment.BOTTOM);
			
		    SimpleMarkerSymbol pointSymbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CIRCLE, hexRed, 10.0f);
		    pointSymbol.setOutline(new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, color, 2.0f));
		    Point point = new Point(x, y, SpatialReferences.getWgs84());
		    Graphic pointGraphic = new Graphic(point, pointSymbol);
		    Graphic textGraphic = new Graphic(point, textSymbol);
		    graphicsOverlay.getGraphics().addAll(Arrays.asList(pointGraphic, textGraphic));

		    mapView.setViewpoint(new Viewpoint(46.77, 23.58, 55002));
		  }
	}
	
	private static void readPointsFromFile(String string) {
		try {
    		File fid = new File(string + ".txt");
    		fid.createNewFile(); 
    		Scanner reader = new Scanner(fid);
			while (reader.hasNextLine()) {
				String name = reader.nextLine();
				System.out.print(name);
				double x = reader.nextDouble();
				System.out.print(x);
				double y = reader.nextDouble();
				System.out.print(y);
				addPoint(name, x, y);
				if (reader.hasNextLine())
					reader.nextLine();
			}
			reader.close();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
	}
	
	public static void addPoint(String name, double x, double y) {
    	Points point =  new Points(name, x, y);
    	points.add(point);
    }
	
	
	public static void showPoints(int harta) {
		int color;
		setupGraphicsOverlay();
				
		points = new ArrayList<Points>();
		
		//in functie de ce layer am citesc dintr-un fisier anume, citeste coordonatele si numele unui punct (poate pentru un label sau ceva de genu)
		if (harta == 1)
			readPointsFromFile("parcuri");
		else if (harta == 2)
			readPointsFromFile("baruri");
		else if (harta == 3)
			readPointsFromFile("muzee");
		
		for (int i = 0; i < points.size(); i++)
		{
			color = hexBlue;
			addPointGraphic(points.get(i).name, points.get(i).y, points.get(i).x, color);
		}
	}

	public static void showMap(Stage stage, DataFile data, int harta) {
	    	
	    // create a JavaFX scene with a stack pane as the root node and add it to the scene
		StackPane stackPane = new StackPane();
        Scene scene = new Scene(stackPane);
        stage.setScene(scene);
        
    	// create a MapView to display the map and add it to the stack pane
        mapView = new MapView();
        stackPane.getChildren().add(mapView);

        // display the map by setting the map on the map view
        setupMap();
        showPoints(harta);
        
        //MADE BY STEFAN
        //display points of interest, color coded of course :3
        
        Label label = new Label("Meniu"); 
        
        // creating buttons 
        Button button1 = new Button("Vezi lista trasee"); 
        Button button2 = new Button("Log out"); 

        // creating toolbar 
        ToolBar toolbar = new ToolBar(label, button1, button2);
        stackPane.getChildren().add(toolbar);
        StackPane.setAlignment(toolbar, Pos.TOP_CENTER);
        
        button1.setOnAction(new EventHandler<ActionEvent>() {
          	
            @Override
            public void handle(ActionEvent e) {
            	RoutesListWindow.showRouteList(stage, data);
            	if (mapView != null)
            		mapView.dispose();
            }
        });
	        
        button2.setOnAction(new EventHandler<ActionEvent>() {
       	 
            @Override
            public void handle(ActionEvent e) {
            data.current_user = null;
            	LoginWindow.showLogin(stage, data);
            	if (mapView != null)
            		mapView.dispose();
            }
        });
	}
	  
	private static void setupMap() {
        if (mapView != null) {
            Basemap.Type basemapType = Basemap.Type.NAVIGATION_VECTOR;
            double latitude = 46.77;
            double longitude = 23.62;
            int levelOfDetail = 12;
            ArcGISMap map = new ArcGISMap(basemapType, latitude, longitude, levelOfDetail);
            mapView.setMap(map);
        }
    }
}
