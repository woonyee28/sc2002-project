//Author Zheng Kai
//last modified - 30/10/22

package tests;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
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
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmm");  

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
        int seat =0;
        System.out.println(seatingPlan);

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
    //     if (noOfSeats == 1)
    // {
    //     System.out.println("Which seat would you like?");
    //     seat = amendSeats(seatingPlan);
    //     seatingPlan.remove(Integer.valueOf(seat));
    //     //TO IMPLEMENT NO: OF SeATS
    // }
    // else
    // {
    //     //If seats is more than 1, allow user to book multiple seats
    //     for(int z =1; z<=noOfSeats; z++)
    //     {
    //         // display which seat user is selecting
    //         System.out.println("Please select the seat you would like for seat "+ z  );
    //         // seat = bookSeats(seatingPlan);
    //         seatingPlan.add(seat);
    //         LocalDateTime now = LocalDateTime.now();
    //         SessionID.add(dtf.format(now));
    //     }
    // }
    int getIndex;

        while(true)
        {
            if (noOfSeats == 1)
            {
                amendSeat(seatingPlan, SessionID);
                //if not in db, means user selected a seat that is already empty
            }
            else
            {
                for (int i=1; i<=noOfSeats; i++)
                {
                    System.out.println("Selecting seat" + i + "..");
                    amendSeat(seatingPlan, SessionID);
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

        
        

    

    

private static int amendSeat(ArrayList<Integer> seatingPlan, ArrayList<String> SessionID)
{
    int seat;
    int getIndex;
    Scanner sc = new Scanner(System.in);
    System.out.println("Please select which seat would you like to cancel");
    seat = sc.nextInt();
    for(Integer i : seatingPlan)
    {
        // seat is inside the DB
        if (i==seat)
        {
            //need to add integer.value of to remove that integer value else will remove by the index
            getIndex = seatingPlan.indexOf(seat);
            seatingPlan.remove(getIndex);
            SessionID.remove(getIndex);
            // seatingPlan.remove(Integer.valueOf(seat));
            System.out.println("Successfully remove seat");
            return 1;
        }


}
    return -1;
}
//function not correct yet.
// private static int amendSeats(ArrayList<Integer> seatingPlan)
// {

//     Boolean loop_seat = true;
//     Scanner sc = new Scanner(System.in);
//     int seat = 0;
//     while(loop_seat)
//         {
//                int check_seat =0;
//                if(sc.hasNextInt())
//                {
//                 seat = sc.nextInt();
//             if(seat>70 || seat <1)
//             {
//                 System.out.println("Please choose seat that are available");
//                 continue;
//             }
//             for(Integer i : seatingPlan)
//             {
//                 if(seat == i)
//                 {
//                     check_seat++;
//                     System.out.println("Seat Taken. Please select another seat");
//                     break;
//                 }
                            
//             }
//             if(check_seat == 0)
//             {
//                 loop_seat = false;
//             }
//                }
            

//             else
//             {
//                 sc.nextLine();
//                 System.out.println("Enter a valid Integer value");
//                 // System.out.println("Please enter the valid integer");

//             }
           
//         }
//         return seat;

// }
    
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


