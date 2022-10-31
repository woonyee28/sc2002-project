package managers;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

import models.Movie;
import models.Review;
import models.Transaction;
import serializers.MovieSerializer;
import serializers.ReviewSerializer;
import serializers.TransactionSerializer;

public class SalesManager {
    public static void sortByRating(){
        Map<Double,String> d = new TreeMap<Double,String>(Collections.reverseOrder());
        for (Movie m: MovieSerializer.readFromMovieCSV()) {           
            d.put(m.getRating(),m.getTitle());
        }
        Set<Entry<Double, String>> set = d.entrySet();
        Iterator<Entry<Double, String>> i = set.iterator();
        int c=0;
        while (i.hasNext()) {
            Map.Entry me = (Map.Entry)i.next();
            System.out.print(me.getValue() + ": ");
            System.out.println(me.getKey());
            c+=1;
            if (c==5){
                break;
            }
        }
    }

    public static void sortBySales(){
        final Map<Double, Integer> d = new TreeMap<Double,Integer>(Collections.reverseOrder());
        for (Transaction m: TransactionSerializer.readFromTransactionCSV()) {   
            int id = m.getMovieID();  
            Set<Double> prices = d.keySet();      
            if (d.containsValue(id)){
                Double ans=0.0;
                for( Double p : prices ){
                    if( d.get(p) == id){
                        ans = p;
                    }
                }
                Double price = ans+m.getPrice();
                d.remove(ans);
                d.put(price,id);
            }else{
                d.put(m.getPrice(),id);
            }
        }
        Set set = d.entrySet();
        Iterator i = set.iterator();
        int c=0;
        while (i.hasNext()) {
            Map.Entry me = (Map.Entry)i.next();
            for (Movie m: MovieSerializer.readFromMovieCSV()) {           
                if (me.getValue().equals(m.getMovieID())){
                    System.out.print(m.getTitle() + ": ");
                    break;
                }
            }
            System.out.println(me.getKey());
            c+=1;
            if (c==5){
                break;
            }
        }
    }

    public static void showReview(){
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
        int check = 0;
        for (Review r: ReviewSerializer.readFromReviewCSV()){
            if (movieid == r.getMovieID()){
                System.out.println(r.getReviews());
                check=1;
            }
        }
        if (check==0){
            System.out.println("The movie has no review yet.");
        }
        sc.close();
    }
}
