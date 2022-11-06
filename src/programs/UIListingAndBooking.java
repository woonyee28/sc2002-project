package programs;

import java.text.ParseException;
import java.util.Scanner;

import managers.MovieBookingManager;

public class UIListingAndBooking {
    private int adminOrmember; // 1 == admin, 0 == member, -1 == Guest
    private int id; // -1 == Member

    public UIListingAndBooking(int id, int adminOrmember){
        this.id = id;
        this.adminOrmember = adminOrmember;
    }

    public void run() throws ParseException{
        int selection_choice;
        String book_choice;
        
       
        Scanner sc = new Scanner(System.in);

        MovieBookingManager mbm = new MovieBookingManager(id, adminOrmember);
        do{
            System.out.println("Welcome to booking page! ");
            System.out.println("1: Show movie listings ");
            System.out.println("2: Book Tickets ");
            System.out.println("3: Print Seating Plan ");
            System.out.println("4: Exit to Previous Level ");
            selection_choice =  sc.nextInt();
            switch(selection_choice)
            { 
                case 1: 
                    System.out.println("Now Showing:");
                    mbm.showMovieListing();
                    System.out.println("Would you like to book a movie?(Y/N)");
                    book_choice = sc.next().toLowerCase();
                    if(book_choice.equals("y") || book_choice.equals("yes"))
                    {
                        mbm.bookings(id);
                        break;
                    }
                    System.out.println("test(Y/N)");

                    break;
                case 2: 
                    System.out.println("Welcome to booking ticket page: ");
                    mbm.bookings(id);
                    break;
                case 3:
                    System.out.println("Please select a cineplex: ");
                    mbm.showSeatPlan();
                    break;
                case 4:
                    System.out.println("Exiting to the previous level...");
                    break;
                default:
                    System.out.println("Please input a valid option.");
                    //function to add to main page
                    break;
            }
           
        }while(selection_choice!=4);
    }
}
