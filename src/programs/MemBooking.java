package programs;

import java.util.*;
import models.*;
import serializers.CinemaSerializer;
import serializers.CineplexSerializer;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;



public class MemBooking {
    private int movieID;

    static CinemaSerializer cs = new CinemaSerializer();
    static CineplexSerializer cps = new CineplexSerializer();
    private static ArrayList<Cineplexes> Cineplex = cps.readFromCSV();
    private static ArrayList<Cinemas> Cinema = cs.readFromCSV();

    public MemBooking(int movieID){
        this.movieID = movieID;
    }

    public void run(int movieID){
       String cinema_code = null;
       int cinema_class = -1;
       ArrayList<Integer> seatingPlan = new ArrayList<>();
       ArrayList<String> SessionID = new ArrayList<>();
       Boolean loop_seat = true;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmm");  
        int noOfSeats;

       Scanner sc = new Scanner(System.in);
       while (cinema_code==null){
        System.out.println("Please choose a cineplex: ");
        for (Cineplexes m: Cineplex){
            System.out.println(m.getCineplexCode() + ": " + m.getName() + "  ");
        }
        System.out.print("Selection(AA,BB,CC):");
        String cineplex_choice = sc.nextLine();

        cinema_code = getCineCode_V1(cineplex_choice);
       }
       
       cinema_class = getCinemaClass(cinema_code);  //cinema class is gold/platinum etc.
       //if cinema-class = -1, return error


       /*
        CODE HERE TO DISPLAY SESSIONS AVAILABLE FOR CHOSEN MOVIE< CINEPLEX, CINEMA

        sessions needs to be updated
         - include which cineplex and which cinema
         - based on user choice, return all available days and timings of the movie
         - user will select a prefered session (save sessionID)

       */

      seatingPlan = getOccupiedSeats(cinema_code);

      System.out.println("Here is the seating plan for Cinema " + cinema_code.toUpperCase()+":");
        System.out.println("------------SCREEN------------");
        printSeatingPlan(seatingPlan);
        System.out.println("How many seats would you like?");
        
        int seat = 0;
        while(true)
        {
            noOfSeats = sc.nextInt();
            System.out.println(seatingPlan.size());
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
            seatingPlan.add(seat);
            LocalDateTime now = LocalDateTime.now();
            SessionID.add(dtf.format(now));
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
                seatingPlan.add(seat);
                LocalDateTime now = LocalDateTime.now();
                SessionID.add(dtf.format(now));
            }
        }

        double totalCost = ticketPrice(noOfSeats);
        System.out.printf("The total cost is: %.2f", totalCost);

        // FORMAT OF SESSION ID: DATE+TIME (yyyyMMddHHmm) Year->Month>Day->Hours->Minutes
    
        //adds current time to SessionID

        //Update data into CSV
        Cinemas up = new Cinemas();
        up.setCinemaClass(toStringClass(cinema_class));
        up.setCinemaCode(cinema_code.toUpperCase());
        up.setSeatingPlan(seatingPlan);
        up.setSessionsID(SessionID);
        cs.updateFromCSV(up);
        System.out.println("Successfully booked the seat you requested");


        //at this point can either call the function again or go back main page.

    }
    
    //-----------------------------------------------------------------------------------//
    //-----------------------------------------------------------------------------------//

    private static String getCineCode_V1(String cineplex_choice){
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

    //-----------------------------------------------------------------------------------//

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

    //-----------------------------------------------------------------------------------//

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

    //-----------------------------------------------------------------------------------//

    private static int bookSeats( ArrayList<Integer> seatingPlan)
    {
        Boolean loop_seat = true;
        Scanner sc = new Scanner(System.in);
        int seat = 0;
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
            return seat;
    }

    //-----------------------------------------------------------------------------------//

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

    //-----------------------------------------------------------------------------------//
    
    public static double ticketPrice(int numofSeats){
        double price =0.0;
        Scanner sc1 = new Scanner(System.in);
        /*
         CODE HERE TO RETURN TOTAL TICKET PRICE
         - check if weekday or not
         -if weekday
            -after 6pm: standard price
            -before 6pm: standard, student, senior
         -else
            - special price

        */

        return price;
    }

    //-----------------------------------------------------------------------------------//
    
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

}
