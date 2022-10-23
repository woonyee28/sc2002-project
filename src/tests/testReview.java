package tests;

import models.Review;
import serializers.ReviewSerializer;

public class testReview {
    public static void main(String[] args) {
        Review review = new Review(0,0,4.9,"Nice movie!",0);
        ReviewSerializer.writeToReviewCSV(review);
        for (Review m: ReviewSerializer.readFromReviewCSV()) {           
            m.toString();
            System.out.println(m); 
        }
    }
}
