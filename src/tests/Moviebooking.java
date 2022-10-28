package tests;
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
                    //insert function to show movie listing
                    break;
    
                case 2: 
                    System.out.println("Welcome to booking ticket page: ");
                    bookings();
                    break;
                case 3:
                    System.out.println("Please select a cineplex:  (Currently dont work)");
                    showSeatPlan();
                    break;
                default:
                    System.out.println("None selected.. Terminating back to main page");
                    //function to add to main page
                    break;
            }
            System.out.println("Going Back to main page...");

   
        
       
       

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

private static void showSeatPlan()
{
    String cineplex_choice;
    String cinema_code =null;
    Scanner sc = new Scanner(System.in);
    while(cinema_code==null)
    {
    for (Cineplexes m : Cineplex)
    {
        System.out.print(m.getCineplexCode() + ": " + m.getName() + "  ");
    
    }
    System.out.println();

    System.out.print("Selection(AA,BB,CC):");
    cineplex_choice = sc.next();
    
    
    cinema_code = getCineCode_V1(cineplex_choice);
    }
    printSeatingPlan(getOccupiedSeats(cinema_code));


}
private static void bookings()
{   
    String cineplex_choice;
    String cinema_code =null;
    int movie_choice;
    int cinema_class;
    ArrayList<Integer> seatingPlan = new ArrayList<>();
    ArrayList<String> SessionID = new ArrayList<>();
    Boolean loop_seat = true;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmm");  
    int noOfSeats;
    

    Scanner sc = new Scanner(System.in);
    while(cinema_code==null)
    {
        System.out.println("Please select which Cineplex you would like to book:\n");
        for (Cineplexes m : Cineplex)
        {
            System.out.print(m.getCineplexCode() + ": " + m.getName() + "  ");
        
        }
        System.out.println();
    
        System.out.print("Selection(AA,BB,CC):");
        cineplex_choice = sc.next();
        
        cinema_code = getCineCode_V1(cineplex_choice);
        // cinema_code = getCineCode(cineplex_choice);
        //cinema_code :aaa,bbb
    }
    
    //At this point, user selected which Cineplex & Cinema


    // System.out.println(cineplex_choice);

    //  cinema_choice = showCinema(cineplex_choice);
    // if (cinema_choice == null)
    // {
    //     System.out.println("Please Select the Cinema again");
    //     cineplex_choice = sc.next();
        
    //     cinema_choice = showCinema(cineplex_choice);
    // }
    // System.out.println(cinema_code);
    //show moving listings:
    System.out.println("Which movie would you like to book?");
    movie_choice = sc.nextInt();
    cinema_class = getCinemaClass(cinema_code);
    if (cinema_class == -1)
    {
        System.out.println("Some Error Occured, data might not be in database");
    }
    // System.out.println(cinema_class);
    
    // theatre.createRows(cinema_class, 50, 5);
    //SeatingPlan is a ArrayList that contains occupiedSeats
    seatingPlan = getOccupiedSeats(cinema_code);
    //SessionID is a ArrayList that contains sessionsID(Date+HHMM, (YYYYMMDDHHmm))
    SessionID = getSessionID(cinema_code);
    //Assuming moviegoers is created;
    // MovieGoer bob = new MovieGoer(1, "bob", "test@gmail.com", 1, "asda", 1, "TID_LSIT");

    System.out.println("Here is the seating plan for Cinema " + cinema_code.toUpperCase()+":");
    System.out.println("------------SCREEN------------");
    printSeatingPlan(seatingPlan);
    System.out.println("How many seats would you like?");
    noOfSeats = sc.nextInt();
    System.out.println("Which seat would you like?");
    int seat = 0;
    //TO IMPLEMENT NO: OF SeATS
    while(loop_seat)
    {
           int check_seat =0;
        seat = sc.nextInt();
        if(seat>70 || seat <1)
        {
            System.out.println("Please choose seat that are available");
            continue;
        }
        for(Integer i : seatingPlan)
        {
            if(seat == i)
            {
                check_seat++;
                System.out.println("Seat Taken. Please select another seat");
                break;
            }
                        
        }
        if(check_seat == 0)
        {
            loop_seat = false;
        }
       
    }

    System.out.println("Ticket price is: ");


    seatingPlan.add(seat);

    // FORMAT OF SESSION ID: DATE+TIME (yyyyMMddHHmm) Year->Month>Day->Hours->Minutes
    LocalDateTime now = LocalDateTime.now();
    //adds current time to SessionID
    SessionID.add(dtf.format(now));
    //Update data into CSV
    CinemaSerializer.updateCinemasFromCSV(cinema_code.toUpperCase(),toStringClass(cinema_class), seatingPlan,SessionID);
    System.out.println("Successfully booked the seat you requested");


    //at this point can either call the function again or go back main page.

    }

private static String toStringClass(int cinema_class)
{
    if(cinema_class == 1)
    {
        return "Regular";
    }
    else if (cinema_class==2)
    {
        return "Gold";
    }
    else
    {
        return "Platinum";
    }
}
    
private static void printSeatingPlan(ArrayList<Integer> seatingPlan)
{
    int count =0;
    final  int TOTALSEAT = 70;
    // for (int i=1; i<=6; i++)
    // {
    //     for (int j=1; j<=10; j++)
    //     {
    //         System.out.print(j);
    //     }
    //     System.out.println("");
    // }
    Collections.sort(seatingPlan);
    // for(Integer z :seatingPlan)
    // {
    //     System.out.println(z);
    //     System.out.println( z.getClass());
    // }

    
    for (int i =1; i<=TOTALSEAT; i++)
    {
        //check for every occupied seat while looping through the seat.
        //if occupied, print "X" 
        for (Integer c : seatingPlan)
        {

            if (i == c)
            {
                System.out.print("X  ");
                i++;
                count++;
                if (count==10)
                    {
                    System.out.println();
                    count=0;
                    break;
                    }
                continue;

                
            }
        }
        //Printing layout to be easily visualized in console
        if(i<10)
        {
            System.out.print("0"+i+ " ");
            count++;
        }
        else
        {
            System.out.print(i + " ");
            count++;
            if (count==10)
            {
             System.out.println();
             count=0;
            }
            
        }
       
       
    }

}

    //get getOccupiedSeats will return ArrayList of occupied seats with the cinema_choice input (aaa,bbb..)
private static ArrayList<Integer> getOccupiedSeats(String cinema_code)
{
    for(Cinemas c : Cinema)
    {
        if(c.getCinemaCode().toLowerCase().equals(cinema_code))
        {
            return c.getSeatingPlan();
        }
    }
    return null;
}

private static ArrayList<String> getSessionID(String cinema_code)
{
    for(Cinemas c : Cinema)
    {
        if(c.getCinemaCode().toLowerCase().equals(cinema_code))
        {
            return c.getSessionsID();
        }
    }
    return null;

}

// Parse in which cinmea user selected and return which class
//Regular - 1, Gold - 2, Plat 3
private static int getCinemaClass(String cinema_code)
{
    for(Cinemas c : Cinema)
    {
        // System.out.println("A" + c.getCinemaCode());
        if(c.getCinemaCode().toLowerCase().equals(cinema_code))
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


//This V1 version able to get directly from CSV, CINEPLEX cinema code.
//It is not hard coded

private static String getCineCode_V1(String cineplex_choice)
{
    String cinema_choice ="";
    Scanner sc = new Scanner(System.in);
    int error  =0;
    //Prints entire CinemaCode in the Cineplex
    System.out.println(Cineplex.size());
    for(int i =0; i<Cineplex.size(); i++)
    {
        if(Cineplex.get(i).getCineplexCode().equals(cineplex_choice.toUpperCase()))
        {
            System.out.println(Cineplex.get(i).getCinemasCode());
            System.out.println("Please select which Cinema you would like to book: ");
            cinema_choice = sc.next();
            if( Cineplex.get(i).getCinemasCode().contains(cinema_choice.toUpperCase()))
            {
                return cinema_choice.toLowerCase();
            }
            else
            {
                System.out.println("Please Enter Correctly");
                return null;
            }
        }
    }
    //if nothing is triggered, likely error, not found in DB or user enter incorrectly

    System.out.println("Some error occured");
    return null;

}


//Deprecated function, hard coded the section of row
// gets the Cineplex choice and returns the cinema choice in small case format
//returns aaa,aab,aac/ bba,bbb,bbc, cca,ccb,ccc

private static String getCineCode(String cineplex_Choice)
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
