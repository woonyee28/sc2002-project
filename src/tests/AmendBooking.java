package tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import models.Cinemas;
import models.Cineplexes;
import serializers.CinemaSerializer;
import serializers.CineplexSerializer;
import tests.Moviebooking;

public class AmendBooking {

    private static ArrayList<Cineplexes> Cineplex = CineplexSerializer.readFromCineplexesCSV();
    private static ArrayList<Cinemas> Cinema = CinemaSerializer.readFromCinemaCSV();
    

    public static void main(String[] args) {
        String cineplex;
        String cineplex_choice;
        String cinema_code =null;
        int cinema_class;

        ArrayList<String> SessionID = new ArrayList<>();
        ArrayList<Integer> seatingPlan = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Amend Booking Page:");
        // System.out.println("Please select which Cineplex would you like to amend");
        while(cinema_code==null)
        {
            System.out.println("Please select which Cineplex you would like to cancel booking:\n");
            for (Cineplexes m : Cineplex)
            {
                System.out.print(m.getCineplexCode() + ": " + m.getName() + "  ");
            
            }
            System.out.println();
        
            System.out.print("Selection(AA,BB,CC):");
            cineplex_choice = sc.next();
            
            cinema_code =  getCineCode_V1(cineplex_choice);
            // cinema_code = getCineCode(cineplex_choice);
            //cinema_code :aaa,bbb
        }
        seatingPlan = getOccupiedSeats(cinema_code);
        SessionID = getSessionID(cinema_code);
        System.out.println("Here is the seating plan for Cinema " + cinema_code.toUpperCase()+":");
        System.out.println("------------SCREEN------------");
        printSeatingPlan(seatingPlan);
        int noOfSeats;
        System.out.println("How many seat would you like to cancel");
        noOfSeats = sc.nextInt();
        int seat =0;
        System.out.println(seatingPlan);

        while(true)
        {
            if (noOfSeats == 1)
            {
                System.out.println("Please select which seat would you like to cancel");
                seat = sc.nextInt();
                for(Integer i : seatingPlan)
                {
                    // seat is inside the DB
                    if (i==seat)
                    {
                        seatingPlan.remove(Integer.valueOf(seat));
                        System.out.println("Successfully remove seat");
                        break;

                    }
                    //if not in db, means user selected a seat that is already empty
                    else
                    {

                    }

                }

            }
            break;
        }
        System.out.println(seatingPlan);
        cinema_class = getCinemaClass(cinema_code);

        CinemaSerializer.updateCinemasFromCSV(cinema_code.toUpperCase(),toStringClass(cinema_class), seatingPlan,SessionID);
        printSeatingPlan(seatingPlan);
        // System.out.println("Please select Which seat would you like to cancel?");

        // seat = sc.nextInt();


        
        

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

}


