
import java.util.Scanner;

import javax.lang.model.element.Element;
// this class defines the movie booking of movies selected
public class Moviebooking {

    private String movieTitle;
    private String theatre;
    private String cineplex; // which of the 3 cineplex


    private int amountofTicket;




	public static void main(String[] args) {
		double price=0;
		
		System.out.print("\n Enter the number of your choice: ");
        Scanner sc = new Scanner(System.in);
        
        Theatre test = new Theatre(1);
        test.createRows(1, 11, 13);
        test.printSeatPlan();
        Theatre nex = new Theatre (2);
        nex.createRows(1, 5, 3);
        MovieName movie1 = new MovieName("TEST_Movie", "22,22,22", test);
        MovieGoer bob = new MovieGoer(2);
        ticket ticket_1 = new ticket(bob, movie1);
        if(ticket_1.reserveSeat(11-1, 2-1))
        {
            System.out.println("Successfully book");
            price = ticket_1.calculatePrice();
            System.out.println("Your ticket price is: "+ price);
            System.out.println(test.getNumRow());
            
        }
        else
        {
            System.out.println("seat taken");
        }
        test.printSeatPlan();
        


}
}
