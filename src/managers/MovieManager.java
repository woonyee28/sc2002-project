package managers;

import models.*;
import serializers.*;
import java.util.*;

public class MovieManager {
    private int adminID;
    static MovieSerializer ms = new MovieSerializer();

    public MovieManager(int adminID){
        this.adminID = adminID;
    }

    public void printAllMovies(){
        for (Movie m: ms.readFromCSV()){
            System.out.println(m.toString());
        }
    }

    public void createNewMovie(){
        Scanner sc = new Scanner(System.in);
        int movieID=-1;
        for (Movie m: ms.readFromCSV()){
            movieID = Math.max(m.getMovieID(),movieID);
        }
        System.out.println("Enter Title: ");
        String title = sc.nextLine();
        System.out.println("Enter Type: ");
        String type = sc.nextLine();
        System.out.println("Enter Synopsis (Without Comma): ");
        String synopsis = sc.nextLine();
        System.out.println("Enter Rating (2 Decimals): ");
        Double rating = sc.nextDouble();
        sc.nextLine();
        System.out.println("Enter Showing Status (No Longer Showing, Now Showing, Coming Soon): ");
        String showingStatus = sc.nextLine();
        System.out.println("Enter Director Name: ");
        String director = sc.nextLine();
        ArrayList<String> cast = new ArrayList<String>();
        System.out.println("Enter Number of Cast(s): ");
        int number = sc.nextInt();
        sc.nextLine();
        for (int k=1;k<=number;k++){
            System.out.println("Enter Name of Cast " + String.valueOf(k) + ": ");
            String temp = sc.nextLine();
            cast.add(temp);
        }
        ArrayList<Integer> reviewsID = new ArrayList<Integer>();
        reviewsID.add(-1);
        Movie m = new Movie(movieID+1, title, type, synopsis, rating, showingStatus, director, cast, reviewsID);
        ms.writeToCSV(m);
        System.out.println("Movie Recorded!");
        ;
    }

    public void modifyMovie(){
        Scanner sc = new Scanner(System.in);
        Scanner ii = new Scanner(System.in);
        System.out.println("Which Movie ID would you like to change? ");
        int id = ii.nextInt();
        int movieID=-1;
        String title="null"; 
        String type="null";
        String synopsis="null";
        Double rating=0.0;
        String showingStatus="null";
        String director="null";
        ArrayList<String> cast=new ArrayList<String>();
        ArrayList<Integer> reviewsID = new ArrayList<Integer>();
        for (Movie m: ms.readFromCSV()){
            if (m.getMovieID()==id){
                movieID = id;
                title = m.getTitle();
                type = m.getType();
                synopsis = m.getSynopsis();
                rating = m.getRating();
                showingStatus = m.getShowingStatus();
                director = m.getDirector();
                cast = m.getCasts();
                reviewsID = m.getReviewsID();
            }
        }
        String changeOptions[] = {
            "Title",
            "Type",
            "Synopsis",
            "Showing Status",
            "Director",
            "Cast",
            "Done and Exit ChangeOptions"
        };
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
                    System.out.println("Enter New Title: ");
                    title = sc.nextLine();
                    
                    break;
                case 2:
                    System.out.println("Enter New Type: ");
                    type = sc.nextLine();
                    
                    break;                
				case 3:
                    System.out.println("Enter New Synopsis: ");
                    synopsis = sc.nextLine();
                    
					break;
				case 4:
                    System.out.println("Enter New Showing Status (No Longer Showing, Now Showing, Coming Soon): ");
					showingStatus = sc.nextLine();
                
					break;
                case 5:
                    System.out.println("Enter New Director: ");
                
                    director = sc.nextLine();
                    
                    break;
				case 6:
                    ArrayList<String> casts = new ArrayList<String>();
                    System.out.println("Enter Number of Cast(s): ");
                    int number = ii.nextInt();
                    for (int k=1;k<=number;k++){
                        System.out.println("Enter Name of Cast " + String.valueOf(k) + ": ");
                        String temp = sc.nextLine();
                        casts.add(temp);
                    }
                    cast = casts;
                    break;
                case 7:
                    System.out.println("Exit ChangeOptions...");
                    break;
				default:
					System.out.println("Please input a valid option.");
					break;
			}
        }while (choice != 7);
        Movie up = new Movie();
        up.setMovieID(movieID);
        up.setTitle(title);
        up.setType(type);
        up.setShowingStatus(showingStatus);
        up.setSynopsis(synopsis);
        up.setRating(rating);
        up.setDirector(director);
        up.setCast(cast);
        up.setReviewID(reviewsID);
        ms.updateFromCSV(up);
        ;
        ;
    }
}
