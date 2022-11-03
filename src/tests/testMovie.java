package tests;

import java.util.ArrayList;

import models.Movie;
import serializers.MovieSerializer;
 
class testMovie {
    public static void main(String[] args) {
        MovieSerializer ms = new MovieSerializer();
        ArrayList<String> str = new ArrayList<String>();
        str.add("Yee");
        str.add("Ng");
        ArrayList<Integer> id = new ArrayList<Integer>();
        id.add(1);
        id.add(2);
        // Movie movie = new Movie(1,"TestingMovieCSV","Type","This is a synopsis",4,"Coming Soon","Woon",str,id);
        // MovieSerializer.writeToMovieCSV(movie);

        for (Movie m: MovieSerializer.readFromMovieCSV()) {           
            m.toString();
            System.out.println(m.getTitle()); 
        }
        MovieSerializer.deleteMovieFromCSV("HeyYo");
        MovieSerializer.updateMovieFromCSV(1,"TestingMovieCSV","Type","This is a synopsis - changed",4.0,"Coming Soon","Woon",str,id);

    }
}