package models;

import models.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import models.Cineplexes;
import serializers.CinemaSerializer;
import serializers.CineplexSerializer;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;    
import models.Movie;
import serializers.MovieSerializer;
import serializers.SessionSerializer;
import models.Sessions;
import javax.lang.model.element.Element;

public class Booking {
    
    private String movieTitle;
    private String theatre;
    private String cineplex; // which of the 3 cineplex
    private static int movieid_selected;
    private static String movie_date;
    private static String movie_time;


    private int amountofTicket;
    static CinemaSerializer cs = new CinemaSerializer();
    static CineplexSerializer cps = new CineplexSerializer();
    static SessionSerializer ss = new SessionSerializer();
    static MovieSerializer ms = new MovieSerializer();
    private static ArrayList<Cineplexes> Cineplex = cps.readFromCSV();
    private static ArrayList<Cinemas> Cinema = cs.readFromCSV();

    public static void run(){
        double price=0;
        int selection_choice;
        String book_choice;
        
       
        Scanner sc = new Scanner(System.in);
		System.out.println("\n Welcome to booking page! ");
        System.out.println("1: Show movie listings:");
        System.out.println("2: Book Tickets");
        System.out.println("3: Print Seating Plan");
        selection_choice =  sc.nextInt();
     
            switch(selection_choice)
            {
                case 0:
                    System.out.println("Going back to main page:..");
                    //function to go back main page
                    break;
                case 1: 
                    System.out.println("Now Showing:");
                    
                    showMovieListing();
                    System.out.println("Would you like to book a movie?(Y/N)");
                    book_choice = sc.next().toLowerCase();
                    if(book_choice.equals("y") || book_choice.equals("yes"))
                    {
                        bookings();
                        break;
                    }
                    else
                    {
                        System.out.println("Going back to main page..");
                        //call for main page function
                        break;
                    }
                    

    
                case 2: 
                    System.out.println("Welcome to booking ticket page: ");
                    bookings();
                    break;
                case 3:
                    System.out.println("Please select a cineplex: ");
                    showSeatPlan();
                    break;
                default:
                    System.out.println("None selected.. Terminating back to main page");
                    //function to add to main page
                    break;
            }
            System.out.println("Going Back to main page...");

    }

    
    
}
