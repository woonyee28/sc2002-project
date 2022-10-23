package tests;

import java.util.ArrayList;

import models.Movie;
import serializers.MovieSerializer;

class testMovie {
    public static void main(String[] args) {
        ArrayList<String> str = new ArrayList<String>();
        str.add("Yee");
        str.add("Ng");
        ArrayList<Integer> id = new ArrayList<Integer>();
        id.add(1);
        id.add(2);
        Movie movie = new Movie(1,"TestingMovieCSV","Type","This is a synopsis",4,"Coming Soon","Woon",str,id);
        MovieSerializer.writeToMovieCSV(movie);
    }
}