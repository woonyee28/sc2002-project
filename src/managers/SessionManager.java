package managers;

import models.*;
import serializers.*;
import java.util.*;

public class SessionManager {
    static SessionSerializer ss = new SessionSerializer();

    private int adminID;

    public SessionManager(int adminID){
        this.adminID = adminID;
    }

    public void printAllSessions(){
        for (Sessions m: ss.readFromCSV()) {           
            System.out.println(m.toString()); 
        }
    }

    public void createNewSession(){
        MovieManager mm = new MovieManager(this.adminID);
        mm.printAllMovies();
        Scanner ii = new Scanner(System.in);
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Movie Id: ");
        int movieID = ii.nextInt();
        System.out.println("Enter Session Date YYYYMMDD: ");
        String sessionDate = sc.nextLine();
        System.out.println("Enter Session Time HHMM: ");
        String sessionTime = sc.nextLine();
        ArrayList<Integer> seatingPlan = new ArrayList<Integer>();
        seatingPlan.add(-1);
        Sessions sess = new Sessions(movieID, sessionDate, sessionTime, seatingPlan);
        ss.writeToCSV(sess);
        ;
        ;
    }
    
    public void modifySession(){
        Scanner ii = new Scanner(System.in);
        Scanner sc = new Scanner(System.in);
        int movieID = -1;
        String sessionDate="";
        String sessionTime = "";
        ArrayList<Integer> seatingPlan = new ArrayList<Integer>();
        System.out.println("Enter the Session's Movie Id: ");
        movieID = ii.nextInt();
        System.out.println("Enter Session Date YYYYMMDD: ");
        sessionDate = sc.nextLine();
        System.out.println("Enter Session Time HHMM: ");
        sessionTime = sc.nextLine();
        String changeOptions[] = {
            "Movie ID",
            "Session Date",
            "Session Time",
            "Exit ChangeOptions"
        };
        for (Sessions s: ss.readFromCSV()){
            if (s.getMovieID()==movieID && s.getSessionDate().equals(sessionDate) && s.getSessionTime().equals(sessionTime)){
                seatingPlan = s.getSeatingPlan();
            }
        }
        int newMovieID=movieID;
        String newSessionDate=sessionDate;
        String newSessionTime=sessionTime;
        int choice = -1;
        do{
            System.out.println("What would you like to change? ");
            
            int i;
            for (i = 1; i <= changeOptions.length; i++) {
                System.out.printf("(%d) %s \n", i, changeOptions[i-1]);
            }
            choice = ii.nextInt();
			
            
            switch (choice) {
                case 1:
                    System.out.println("Enter New movieID: ");
                    newMovieID = ii.nextInt();
                    break;
                case 2:
                    System.out.println("Enter New SessionDate: ");
                    newSessionDate = sc.nextLine();
                    break;                
				case 3:
                    System.out.println("Enter New SessionTime: ");
                    newSessionTime = sc.nextLine();
					break;
                case 4:
                    System.out.println("Exit ChangeOptions...");
                    break;
				default:
					System.out.println("Please input a valid option.");
					break;
			}
        }while (choice != 4);
        Sessions aa = new Sessions();
        aa.setSessionTime(sessionTime);
        aa.setSessionDate(sessionDate);
        aa.setMovieID(movieID);
        ss.deleteFromCSV(aa);
        Sessions sa = new Sessions(newMovieID,newSessionDate,newSessionTime,seatingPlan);
        ss.writeToCSV(sa);
        ;
        ;
    }
}
