package com.mycompany.app;

import java.util.ArrayList;

public class Route {
	String name;
	String description;
	ArrayList<Review> reviews;
	int rating;
	
	public Route(String name, String description) {
		this.name = name;
		this.description = description;
		this.reviews = new ArrayList<Review>();
	}
	
	public void AddReview(String user_name, String route_name, String review_text, String rating) {
		Review new_review = new Review(user_name, route_name, review_text, rating);
		reviews.add(new_review);
	}
	
	public float computeRating() {
		float count = 0;
		for (int i = 0; i < this.reviews.size(); i++) {
			if (!reviews.get(i).rating.equals("null"))
				count += Integer.parseInt(reviews.get(i).rating);
		}
	
		return count / this.reviews.size();
	}
}
