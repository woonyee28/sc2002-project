package managers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

import managers.*;
import models.*;
import serializers.*;

public class BookingHistManager {
    public static void showBookingHistory(int movieGoerID){
        String tid = "";
        for (MovieGoer mg: MovieGoerSerializer.readFromMovieGoerCSV()) {
            if (mg.getMovieGoersID()==movieGoerID){
                tid = mg.getTID_List();
            }
        }
        String[] tempArr;
        tempArr = tid.split("; ");
        for (String s: tempArr){
            for (Transaction t: TransactionSerializer.readFromTransactionCSV()){
                if (t.getTID().equals(s)){
                    System.out.println("\n"); 
                    System.out.println("Your Booking TID: "); 
                    System.out.println(t.getTID());   
                    System.out.println("Movie Name: ");   
                    int movieid = t.getMovieID();
                    for (Movie m: MovieSerializer.readFromMovieCSV()) {           
                        if (m.getMovieID() == movieid){
                            System.out.println(m.getTitle());  
                        }
                    }
                    System.out.println("Booking Date: ");
                    System.out.println(t.getBookingDate());   
                    System.out.println("Booking Time: ");
                    System.out.println(t.getBookingTime());   
                    System.out.println("Cinema Code: ");
                    System.out.println(t.getCinemaCode());   
                    System.out.println("Seat Number: ");
                    System.out.println(t.getSeatingNum());   
                    System.out.println("Ticket Price: ");
                    System.out.println(t.getPrice()); 
                    
                }
            }
        }
    }


    public static void inputReviewAndRating(int movieGoerID){
        int maxx = 0;
        for (Review r: ReviewSerializer.readFromReviewCSV()) {
            maxx = Math.max(r.getReviewsID(),maxx);
        }
        System.out.println("Input the movie name: ");
        Scanner sc = new Scanner(System.in);
        int movieid = -1;
        String movieName = sc.nextLine();
        for (Movie m: MovieSerializer.readFromMovieCSV()) {           
            if (movieName.equals(m.getTitle())){
                movieid = m.getMovieID();
                break;
            }
        }
        if (movieid==-1){
            System.out.println("Movie doesn't exist!");   
            return;
        }
        System.out.println("Input the review without comma: ");
        String review = sc.nextLine();
        System.out.println("Input the rating in 1 decimal: ");
        Double rating = sc.nextDouble();
        Review rrr= new Review(maxx+1, movieGoerID, rating, review, movieid);
        ReviewSerializer.writeToReviewCSV(rrr);
        int count=0;
        Double score=0.0;
        for (Review r: ReviewSerializer.readFromReviewCSV()){
            if (r.getMovieID()==movieid){
                count+=1;
                score+=r.getRating();
            }
        }
        score/=count;
        for (Movie m: MovieSerializer.readFromMovieCSV()) {    
            if (movieid == m.getMovieID()){
                ArrayList<Integer> reviewsID;
                reviewsID = m.getReviewsID();
                reviewsID.add(maxx+1);
                MovieSerializer.updateMovieFromCSV(movieid,m.getTitle(),m.getType(),m.getSynopsis(),score,m.getShowingStatus(),m.getDirector(),m.getCasts(),reviewsID);
                break;
            }
        }
        System.out.println("Update successful!");        
    }
    
}
