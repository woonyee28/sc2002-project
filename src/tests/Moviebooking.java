package tests;
import models.*;
import models.ticket;

import java.util.ArrayList;
import java.util.Scanner;
import models.Cineplexes;
import serializers.CinemaSerializer;
import serializers.CineplexSerializer;

import javax.lang.model.element.Element;
// this class defines the movie booking of movies selected
public class Moviebooking {

    private String movieTitle;
    private String theatre;
    private String cineplex; // which of the 3 cineplex


    private int amountofTicket;
    private static ArrayList<Cineplexes> Cineplex = CineplexSerializer.readFromCineplexesCSV();
    private static ArrayList<Cinemas> Cinema = CinemaSerializer.readFromCinemaCSV();

	public static void main(String[] args) {
		double price=0;
        int selection_choice;
       
        Scanner sc = new Scanner(System.in);
		System.out.println("\n Welcome to booking page! ");
        System.out.println("1: Show movie listings:");
        System.out.println("2: Book Tickets");
        selection_choice =  sc.nextInt();
     
            switch(selection_choice)
            {
                case 0:
                    System.out.println("Going back to main page:..");
                    //function to go back main page
                    break;
                case 1: 
                    System.out.println("Now Showing:");
                    
                    //insert function to show movie listing
                    break;
    
                case 2: 
                    System.out.println("Welcome to booking ticket page: ");
                    bookings();
                    break;
                default:
                    System.out.println("None selected.. Terminating back to main page");
                    //function to add to main page
                    break;
            }

   
        
       
       

        // Theatre test = new Theatre(1);
        // test.createRows(1, 11, 13);
        // test.printSeatPlan();
        // Theatre nex = new Theatre (2);
        // nex.createRows(1, 5, 3);
        // MovieName movie1 = new MovieName("TEST_Movie", "22,22,22", test);
        // MovieGoer_zk bob = new MovieGoer_zk(1);
        // // ticket ticket_1 = new ticket(bob, movie1);
        // if(ticket_1.reserveSeat(11-1, 2-1))
        // {
        //     System.out.println("Successfully book");
        //     price = ticket_1.calculatePrice();
        //     System.out.println("Your ticket price is: "+ price);
            // System.out.println(test.getNumRow());
            
        // }
        // else
        // {
        //     System.out.println("seat taken");
        // }
        // test.printSeatPlan();
        


}
private static void bookings()
{   
    String cineplex_choice;
    String cinema_choice;
    int movie_choice;
    int cinema_class;

    

    Scanner sc = new Scanner(System.in);
    System.out.println("Please select which Cineplex you would like to book:");
    for (Cineplexes m : Cineplex)
    {
        System.out.print(m.getCineplexCode() + ": " + m.getName() + "  ");
    }
    System.out.println();

    System.out.print("Selection(AA,BB,CC):");
    cineplex_choice = sc.next();
    //At this point, user selected which Cineplex

    // System.out.println(cineplex_choice);
     cinema_choice = showCinema(cineplex_choice);
    if (cinema_choice == null)
    {
        System.out.println("Please Select the Cinema again");
        cineplex_choice = sc.next();
        
        cinema_choice = showCinema(cineplex_choice);
    }
    System.out.println(cinema_choice);
    //show moving listings:
    System.out.println("Which movie would you like to book?");
    movie_choice = sc.nextInt();
    Theatre theatre = new Theatre(1);
    cinema_class = getCinemaClass(cinema_choice);
    if (cinema_class == -1)
    {
        System.out.println("Some Error Occured, data might not be in database");
    }
    System.out.println(cinema_class);
    
    theatre.createRows(cinema_class, 50, 5);
    



    }


// Parse in which cinmea user selected and return which class
//Regular - 1, Gold - 2, Plat 3
private static int getCinemaClass(String cinema)
{
    for(Cinemas c : Cinema)
    {
        // System.out.println("A" + c.getCinemaCode());
        if(c.getCinemaCode().toLowerCase().equals(cinema))
        {
            if(c.getCinemaClass().equals("Gold"))
            {
                return 2;
            }
            else if (c.getCinemaClass().equals("Platinum"))
            {
                return 3;
            }
            else if (c.getCinemaClass().equals("Regular"))
            {
                return 1;
            }
            // System.out.println(c.getCinemaClass());
            // return c.getCinemaClass();
        }

    }
    //if not found in csv
    return -1;

}


// gets the Cineplex choice and returns the cinema choice in small case format
//returns aaa,aab,aac/ bba,bbb,bbc, cca,ccb,ccc
private static String showCinema(String cineplex_Choice)
{
    String cinema_choice ="";
    Scanner sc = new Scanner(System.in);
    if(cineplex_Choice.toLowerCase().equals("aa")) // selected first 3
    {
        System.out.println("Please select which Cinemas you would like to book:");
        for(int i =0; i<3; i++)
        {
            System.out.print(Cinema.get(i).getCinemaCode() + " ");
        }
        
        // cinema_choice = sc.next();
        System.out.println("Selection: ");
        return sc.next().toLowerCase();
    }
    else if (cineplex_Choice.toLowerCase().equals("bb"))
    {
        System.out.println("Please select which Cinemas you would like to book:");
        for(int i=3; i<6; i++)
        {
            System.out.print(Cinema.get(i).getCinemaCode() + " ");
        }
        System.out.println("Selection: ");
        return sc.next().toLowerCase();
    }
    else if (cineplex_Choice.toLowerCase().equals("cc"))
    {
        System.out.println("Please select which Cinemas you would like to book:");
        for(int i=6; i<9; i++)
        {
            System.out.print(Cinema.get(i).getCinemaCode() + " ");
        }
        System.out.println("Selection: ");
        return sc.next().toLowerCase();
    }
    else{
        System.out.println("Invalid selection made..");
        return null;
    }
    
}
}

