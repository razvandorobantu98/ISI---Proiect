package com.mycompany.app;

public class Review {
	String user_name;
	String route_name;
	String review_text;
	String rating;
	
	public Review(String user_name, String route_name, String review_text, String rating) {
		this.user_name = user_name;
		this.route_name = route_name;
		this.review_text = review_text;
		this.rating = rating;
	}
}
