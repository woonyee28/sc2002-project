package tests;

import models.*;

import java.util.Scanner;

import java.util.ArrayList;
import serializers.MovieSerializer;


public class Display_movie {

    static MovieSerializer ms = new MovieSerializer();

    private static ArrayList<String> nowShowing = new ArrayList<>();
    private static ArrayList<String> preview = new ArrayList<>();
    private static ArrayList<String> endofShowing = new ArrayList<>();
    

    public static void main(String[] args) {
        
        int guest = 1;
        int member = 2;
        int admin = 3;

        int choice;
        
        Scanner sc = new Scanner(System.in);




        // System.out.println("View movies");
        showMovieListing();
        




    }

    private static void showMovieListing()
    {
        for (Movie m: ms.readFromCSV()) {           
            // m.toString();
            // System.out.println(m.getMovieID()+1 +": " +  m.getTitle()); 
            if (m.getShowingStatus().equals("Preview"))
            {
                preview.add(m.getTitle());
            }
            else if (m.getShowingStatus().equals("End Of Showing"))
            {
                endofShowing.add(m.getTitle());
            }
            else
            {
                nowShowing.add(m.getTitle());
            }
            

        }
        // System.out.println(preview);
        //     System.out.println(endofShowing);
        //     System.out.println(nowShowing);
            System.out.println("Previews:");
            for(int i=0; i<preview.size(); i++)
            {
                System.out.println(preview.get(i));
            }
            System.out.println();
            System.out.println("Now Showing:");
            for(int i=0; i<nowShowing.size(); i++)
            {
                System.out.println(nowShowing.get(i));
            }
            System.out.println();
            System.out.println("End of showing:");
            for(int i=0; i<endofShowing.size(); i++)
            {
                System.out.println(endofShowing.get(i));
            }
    }

    
}
