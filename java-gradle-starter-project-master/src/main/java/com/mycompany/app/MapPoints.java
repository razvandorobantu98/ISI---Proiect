package com.mycompany.app;

import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.PointCollection;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.symbology.SimpleLineSymbol;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;

public class MapPoints {
	
	private static  int hexRed = 0xFFFF0000;
	private static  int hexBlue = 0xFF00FF00;
	private static  int hexGreen = 0xFF0000FF;
	
	private static double[][] parcuri = {{45.8181, 24.1747}};

	private static  GraphicsOverlay graphicsOverlay;
	private static  MapView mapView;
		
	private static void setupGraphicsOverlay() {
		if (mapView != null) {
			graphicsOverlay = new GraphicsOverlay();
			mapView.getGraphicsOverlays().add(graphicsOverlay);
		}
	}
	
	private static void addPointGraphic(double x, double y, int color) {
		if (graphicsOverlay != null) {
		    SimpleMarkerSymbol pointSymbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CIRCLE, hexRed, 10.0f);
		    pointSymbol.setOutline(new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, color, 2.0f));
		    Point point = new Point(x, y, SpatialReferences.getWgs84());
		    Graphic pointGraphic = new Graphic(point, pointSymbol);
		    graphicsOverlay.getGraphics().add(pointGraphic);
		  }
	}
	
	public static void showPoints() {
		
		setupGraphicsOverlay();
		for (int i = 0; i < 1; i++)
			addPointGraphic(parcuri[0][i], parcuri[1][i], hexRed);
	}

}
