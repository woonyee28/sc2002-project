//Author Zheng Kai
//last modified - 30/10/22

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
import models.Movie;
import serializers.MovieSerializer;
import serializers.SessionSerializer;
import models.Sessions;

import javax.lang.model.element.Element;


// this class defines the movie booking of movies selected
public class Moviebooking {

    private String movieTitle;
    private String theatre;
    private String cineplex; // which of the 3 cineplex
    private static int movieid_selected;
    private static String movie_date;
    private static String movie_time;
    private static int movie_time_choice = 0;
    private static ArrayList<String> ss_datetime = new ArrayList<>();


    private int amountofTicket;
    static CinemaSerializer cs = new CinemaSerializer();
    static CineplexSerializer cps = new CineplexSerializer();
    static SessionSerializer ss = new SessionSerializer();
    static MovieSerializer ms = new MovieSerializer();
    private static ArrayList<Cineplexes> Cineplex = cps.readFromCSV();
    private static ArrayList<Cinemas> Cinema = cs.readFromCSV();

    private static ArrayList<String> nowShowing = new ArrayList<>();
    private static ArrayList<String> preview = new ArrayList<>();
    private static ArrayList<String> endofShowing = new ArrayList<>();
    

	public static void main(String[] args) {
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
            //Now Showing
            else
            {
                nowShowing.add(m.getTitle());
            }
            

        }

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

// parse in cinema_code to get the sessionID, to print out relevant movies according to the cinema chose
//Function that requires user to input Movie & Session Timing and will return the seatingPlan
private static ArrayList<Integer> showMovieListing(String cinema_code)
{
    //declare movieID & seatPlan
    ArrayList<Integer> movieID = new ArrayList<>();
    ArrayList<Integer> seatPlan = new ArrayList<>();
    // for(Cinemas cine : cs.readFromCSV())
    // {
    //     System.out.println(cine.getSessionsID());
    //     for(Sessions m : ss.readFromCSV());
    //     {
    //         System.out.println(m);
    //     }
    // }
    
    Scanner sc = new Scanner(System.in);
    int movieid_choice;
    int which_cine =-1;
    
    // ArrayList<String> ss_time = new ArrayList<>();

    // for(Sessions s: ss.readFromCSV())
    // {
    //     ss_date.add(s.getSessionDate());
    //     ss_time.add(s.getSessionTime());
    // }
    // Collections.sort(ss_date);
    // Collections.sort(ss_time);
    // System.out.println(ss_date);
    // System.out.println(ss_time);
    
    //add all the movies in the cinema into movieID
    // System.out.println(Cineplex.size());
    for(int i =0; i<Cinema.size();  i++)
    {
        //get the cinema that its parsed in
        //to get the index of which cinema to later check the array of sessionID
        if(Cinema.get(i).getCinemaCode().equals(cinema_code.toUpperCase()))
        {
            for(Sessions m : ss.readFromCSV())
            {
                if(Cinema.get(i).getSessionsID().contains(m.getSessionDate()+m.getSessionTime()))
                {
                    which_cine = i;
                    // System.out.println(m.getSessionDate()+m.getSessionTime());
                    movieID.add(m.getMovieID());
                }
            }
        }
    }
    if(which_cine == -1)
    {
        System.out.println("No session availble from the movie you selected");
        return null;
    }
    preview.clear();
    nowShowing.clear();
    endofShowing.clear();

    // show the movies showing for that cinema selected
    //print out in console
    for (Movie m: ms.readFromCSV())
    {
        if(movieID.contains(m.getMovieID()))
        {
            if (m.getShowingStatus().equals("Preview"))
            {
                preview.add(m.getMovieID()+1 + ": "+ m.getTitle());
            }
            else if (m.getShowingStatus().equals("Now Showing"))
            {
                nowShowing.add(m.getMovieID()+1 + ": "+ m.getTitle());
            }
     
            // System.out.println(m.getMovieID()+1 + ": "+ m.getTitle());
        }

    }
    
 

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
    System.out.println("Please select the movie");
    
    movieid_selected = sc.nextInt() - 1;
    // movieid_choice = sc.nextInt() - 1 ;
    System.out.println("Please select the movie time");
    int count =0;
    ss_datetime.clear();
    //Store all the movie session timing to ss_datetime ArrayList
    for(Sessions m : ss.readFromCSV())
    {
        //movieIDs are unique, compare the movieID to the sessionsData
        if(movieid_selected == m.getMovieID())
        {
            count++;
            ss_datetime.add(m.getSessionDate()+m.getSessionTime());
        }
    }
    //Sort the timing array
    Collections.sort(ss_datetime);


    // print the session timings (Sorted order)
    for(int i=0; i<ss_datetime.size(); i++)
    {
        System.out.println(i+1 +": Date: " + ss_datetime.get(i).substring(0,8)+ " Time: "+ ss_datetime.get(i).substring(8));
    }
    count =0;

    //user selected which session timing
    movie_time_choice = sc.nextInt();

    //get the movie session timing seating plan
    for(Sessions m:ss.readFromCSV())
    {
        if(m.getSessionDate().equals(ss_datetime.get(movie_time_choice-1).substring(0,8)))
        {
            if(m.getSessionTime().equals(ss_datetime.get(movie_time_choice-1).substring(8)))
            {
                //Stores the unique sessionsID that the user selected
                movie_date = m.getSessionDate();
                movie_time = m.getSessionTime();
                seatPlan = m.getSeatingPlan();
            }
            
        }
        // count++;
    }
    // System.out.println(seatPlan);
    // get the seating plan according to user input of selection session in order to print later
    // for (Sessions m :  ss.readFromCSV())
    // {
    //     if (movieid_selected == m.getMovieID())
    //     {
    //         count++;
    //         if (movie_time_choice == count)
    //         {
    //             // get the specific seating plan for that specific sessionID
    //             movie_date = m.getSessionDate();
    //             movie_time = m.getSessionTime();
    //             System.out.println(m.getSeatingPlan());
    //             seatPlan = m.getSeatingPlan();
    //         }
    //     }
    // }
    //return the seatplan of user selected session
    return seatPlan;
    

    

    // for(Sessions m : ss.readFromCSV())
    // {
    //     // System.out.println(m.getSessionDate() + m.getSessionTime());
    //     for(Cinemas cine: cs.readFromCSV())
    //     {
    //         if(cine.getSessionsID().contains(m.getSessionDate()+m.getSessionTime()))
    //         {
    //             System.out.println(m.getSessionDate()+m.getSessionTime());
    //         }
    //     }
    // }
    
}

private static void showSeatPlan()
{
    String cineplex_choice;
    String cinema_code =null;
    Scanner sc = new Scanner(System.in);
    ArrayList<Integer> seatingPlan = new ArrayList<>();
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
    System.out.println("Which movie would you like to view?");
    seatingPlan =  showMovieListing(cinema_code);
    // System.out.println(getOccupiedSeats(cinema_code));
    // printSeatingPlan(getOccupiedSeats(cinema_code));
    printSeatingPlan(seatingPlan);


}
private static void bookings()
{   
    String cineplex_choice;
    String cinema_code =null;
    int movie_choice;
    int cinema_class;
    ArrayList<Integer> seatingPlan = new ArrayList<>();
    ArrayList<String> SessionID = new ArrayList<>();
    ArrayList<Integer> selectedSeat = new ArrayList<>();

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
        // System.out.println("This is cinema code: "+ cinema_code);
        // cinema_code = getCineCode(cineplex_choice);
        //cinema_code :aaa,bbb
    }
    SessionID = getSessionID(cinema_code);
    
    System.out.println("Which movie would you like to book?");
    seatingPlan =  showMovieListing(cinema_code);
    System.out.println(seatingPlan);

    // movie_choice = sc.nextInt() -1 ;// user select movie based on movieID
    // System.out.println("Select session time");

    cinema_class = getCinemaClass(cinema_code);
    if (cinema_class == -1)
    {
        System.out.println("Some Error Occured, data might not be in database");
    }
    // System.out.println(cinema_class);
    
    // theatre.createRows(cinema_class, 50, 5);
    //SeatingPlan is a ArrayList that contains occupiedSeats
    // seatingPlan = getOccupiedSeats(cinema_code);
    //SessionID is a ArrayList that contains sessionsID(Date+HHMM, (YYYYMMDDHHmm))
    
    //Assuming moviegoers is created;
    // MovieGoer bob = new MovieGoer(1, "bob", "test@gmail.com", 1, "asda", 1, "TID_LSIT");

    System.out.println("Here is the seating plan for Cinema " + cinema_code.toUpperCase()+":");
    System.out.println("------------SCREEN------------");
    printSeatingPlan(seatingPlan);
    System.out.println("How many seats would you like?");
    
    int seat = 0;
    while(true)
    {
        noOfSeats = sc.nextInt();
        // System.out.println(seatingPlan.size());
        if (noOfSeats<1)
        {
            System.out.println("Please select at least 1 seat..");
            // seat = 0;
            continue;
        }
        else if(noOfSeats >= 70-seatingPlan.size())
        {
            System.out.println("No of seat exceeded remaining seats left, Please select again");
            // seat = 0;
            continue;
        }
        break;

    }
   
    if (noOfSeats == 1)
    {
        System.out.println("Which seat would you like?");
        seat = bookSeats(seatingPlan);
         //Here to add Tarun's requested seat. selectedSeat is an array consists of user selected seats
        selectedSeat.add(seat);

        seatingPlan.add(seat);
        // LocalDateTime now = LocalDateTime.now();
        // SessionID.add(dtf.format(now));
        //TO IMPLEMENT NO: OF SeATS
    }
    else
    {
        //If seats is more than 1, allow user to book multiple seats
        for(int z =1; z<=noOfSeats; z++)
        {
            // display which seat user is selecting
            System.out.println("Please select the seat you would like for seat "+ z  );
            seat = bookSeats(seatingPlan);
            //Here to add Tarun's requested seat. selectedSeat is an array consists of user selected seats
            selectedSeat.add(seat);

            seatingPlan.add(seat);
            // LocalDateTime now = LocalDateTime.now();
            // SessionID.add(dtf.format(now));
        }
    }
   
    System.out.println("Ticket price is: xxx");


  

    // FORMAT OF SESSION ID: DATE+TIME (yyyyMMddHHmm) Year->Month>Day->Hours->Minutes
    
    //adds current time to SessionID

    //update data into Sessions CSV
    Sessions s = new Sessions();
    s.setMovieID(movieid_selected);
    s.setSessionDate(ss_datetime.get(movie_time_choice-1).substring(0,8));
    s.setSessionTime(ss_datetime.get(movie_time_choice-1).substring(8));
    s.setSeatingPlan(seatingPlan);
    ss.updateFromCSV(s);
    System.out.println("Successfully booked the seat you requested");
    
    
    // //Update data into CSV
    // Cinemas up = new Cinemas();
    // up.setCinemaClass(toStringClass(cinema_class));
    // up.setCinemaCode(cinema_code.toUpperCase());
    // up.setSeatingPlan(seatingPlan);
    // up.setSessionsID(SessionID);
    // cs.updateFromCSV(up);
    // System.out.println("Successfully booked the seat you requested");


    //at this point can either call the function again or go back main page.

    }

private static int bookSeats( ArrayList<Integer> seatingPlan)
{
    Boolean loop_seat = true;
    Scanner sc = new Scanner(System.in);
    int seat = 0;
    while(loop_seat)
        {
               int check_seat =0;
               if(sc.hasNextInt())
               {
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
            

            else
            {
                sc.nextLine();
                System.out.println("Enter a valid Integer value");
                // System.out.println("Please enter the valid integer");

            }
           
        }
        return seat;
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

// private static ArrayList<Integer> getOccupiedSeats_sess(Strin)

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
// this function returns aaa,bbb,bbc...
private static String getCineCode_V1(String cineplex_choice)
{
    String cinema_choice ="";
    Scanner sc = new Scanner(System.in);
    int error  =0;
    //Prints entire CinemaCode in the Cineplex
    // System.out.println(Cineplex.size());
    for(int i =0; i<Cineplex.size(); i++)
    {
        if(Cineplex.get(i).getCineplexCode().equals(cineplex_choice.toUpperCase()))
        {
            System.out.println(Cineplex.get(i).getCinemasCode());
            System.out.println("Please select which Cinema you would like to view: ");
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

