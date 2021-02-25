package com.mycompany.app;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataFile {

	User current_user;
	ArrayList<User> users;
	ArrayList<Route> routes;
	
	public DataFile() {
		current_user = null;
		users = new ArrayList<User>();
		routes = new ArrayList<Route>();
	}
	
    public void readUsers() {
    	 try {
    		 File fid = new File("users.txt");
    		 fid.createNewFile(); 
    		 Scanner reader = new Scanner(fid);
    		 while (reader.hasNextLine()) {
    			 String data = reader.nextLine();
    			 String[] info = data.split(" ");
    			 String username = info[0];
    			 String password = info[1];
    			 Boolean type = false;
    			 if (info[2].equals("yes"))
    				 type = true;
    			 User user = new User(username, password, type);
    			 users.add(user);
    			 
    		 }
    		 reader.close();
    	 } catch (IOException e) {
    		 e.printStackTrace();
    	 }
    	 
    	 //writeUsers();
    }
    
    public void writeUsers() {
    	try {
    		FileWriter writer = new FileWriter("users.txt");
    		for(User user : users) {
        		String check = "no";
        		if (user.type == true)
        			check = "yes";
        		writer.write(user.username + " " + user.password + " " + check + "\n");
        	}
    		writer.close();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    public void addUser(String username, String password, Boolean type) {
    	User user =  new User(username, password, type);
    	users.add(user);
    }
    
    public Boolean checkUser(String username, String password) {
    	for(User user : users) {
    		if (user.username.equals(username) && user.password.equals(password)) {
    			current_user = user;
    			return true;
    		}
    	}
    	return false;
    }
    
    public Boolean checkIfExists(String username) {
    	for(User user : users) {
    		if (user.username == username)
    			return true;
    	}
    	return false;
    }
    
    public void readRoutes() {
    	try {
    		File fid = new File("routes.txt");
    		fid.createNewFile(); 
    		Scanner reader = new Scanner(fid);
			while (reader.hasNextLine()) {
				String name = reader.nextLine();
				String description = reader.nextLine();
				if (reader.hasNextLine())
					reader.nextLine();
				Route route = new Route(name, description);
				routes.add(route);
			}
			reader.close();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    public void readReviews( ) {
    	try {
    		File fid = new File("reviews.txt");
    		fid.createNewFile(); 
    		Scanner reader = new Scanner(fid);
			while (reader.hasNextLine()) {
				String user = reader.nextLine();
				String name = reader.nextLine();
				String text = reader.nextLine();
				String rating = reader.nextLine();
				if (reader.hasNextLine())
					reader.nextLine();
				for (Route route : routes) {
					if (route.name.equals(name)) {
						route.AddReview(user, name, text, rating);
						break;
					}
				}
			}
			reader.close();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    public void writeReviews() {
    	try {
    		FileWriter writer = new FileWriter("reviews.txt");
    		for (Route route : routes) {
    			for (Review review : route.reviews) {
    				writer.write(review.user_name + "\n");
    				writer.write(review.route_name + "\n");
    				writer.write(review.review_text + "\n");
    				writer.write(review.rating + "\n\n");
    			}
    		}
    		writer.close();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
}
