package programs;

import java.util.Scanner;

import managers.MovieManager;
import managers.SessionManager;

public class UINewListingSession {
    private final String menuOptions[] = {
        "Print All Movies",
        "Print All Sessions",
        "Create New Movie",
        "Create New Session",
        "Modify Movie Detail",
        "Modify Session Detail",
        "Exit New Listing Session"
    };

    public void run(){
        int choice = -1;

        Scanner sc = new Scanner(System.in);

        do{
            System.out.println("====================UI Manage Movie / Session======================");
            int i;
            for (i = 1; i <= menuOptions.length; i++) {
                System.out.printf("(%d) %s \n", i, menuOptions[i-1]);
            }
            choice = Integer.valueOf(sc.next());
			
            switch (choice) {
                case 1:
                    System.out.println("printAllMovies():");
                    MovieManager.printAllMovies();
                    break;
                case 2:
                    System.out.println("printAllSessions:");
                    SessionManager.printAllSessions();
                    break;                
				case 3:
                    System.out.println("createNewMovie():");
                    MovieManager.createNewMovie();
					break;
				case 4:
                    System.out.println("createNewSession():");
					SessionManager.createNewSession();
					break;
                case 5:
                    System.out.println("modifyMovie()");
                    MovieManager.modifyMovie();
                    break;
				case 6:
                    System.out.println("modifySession()");
                    SessionManager.modifySession();
                    break;
                case 7:
                    System.out.println("Program exiting...");
                    break;
				default:
					System.out.println("Please input a valid option.");
					break;
			}
        }while (choice != 7);
        ;
    }
}
