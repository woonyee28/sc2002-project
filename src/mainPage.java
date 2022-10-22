import java.util.*;
import java.io.*;
public class mainPage {
    
    //function for member log ins
    public static void membLogIn(String email, String password){
        System.out.println("Member email and password is: "+ email+" and "+password); //testing
        memberLogin user = new memberLogin(email, password);
        
        if (!user.checkExistence()){
            System.out.println("User account does not exist.");  
            //code here to return back to main function

        }
        else{
            if (!user.checkPassword()){
                System.out.println("Wrong password, key in again");
                //code here to type in password and check again

            }
        }

    }
    
    //function for admin log ins
    public static void staffLogIn(String email, String password){
        System.out.println("Member email and password is: "+ email+" and "+password); //testing
        adminLogin user = new adminLogin(email, password);
        
        if (!user.checkExistence()){
            System.out.println("User account does not exist.");  
            //code here to return back to main function
            
        }
        else{
            if (!user.checkPassword()){
                System.out.println("Wrong password, key in again");
                //code here to type in password and check again

            }
        }
    }
    
    //function to create new account 
    public static void createAcc(String email, String password){
        //code here
        memberLogin user = new memberLogin(email, password);
        if(!user.checkExistence()){
            //code to append email and other details into csv
            System.out.println("Account successfully created");
            //return to main page to login again
        }
        else{
            System.out.println("Account already exists!");
            //return to main page to log in
        }
    }

    
    //main function-----------------------------------------------------------------------------------------------------------------------
    public static void main(String[] args) {

        boolean check=true;
        System.out.println("Welcome to MOBLIMA Booking System!");
        while (check){
            check = false;
            Scanner input1 = new Scanner(System.in);
            System.out.println("Select the following:\n\t[1] Log In\t[2] Sign up");
            int signInChoice = input1.nextInt();

            switch(signInChoice){
                case 1:
                    //log in procedures
                    //select the domain first
                    Scanner input2 = new Scanner(System.in);
                    System.out.println("Are you a MOBLIMA member or admin?\n\t[1] MOMBLIMA member\t[2] MOBLIMA Admin");
                    int domain = input2.nextInt();

                    //input email
                    Scanner input3 = new Scanner(System.in);
                    System.out.println("Please key in your email address below: ");
                    String email = input3.nextLine();

                    //input password
                    Scanner input4 = new Scanner(System.in);
                    System.out.println("Please key in your password below: ");
                    String pw = input4.nextLine();

                    //user is a member
                    if (domain == 1){
                        membLogIn(email, pw);
                        //code here to display available functions for member (showMovies, bookMovies, reviews etc)
                        System.out.println("What would you like to do?");
                        System.out.println("\t[1] Show movies\t[2] Book Movies\t[3] View Bookings\t[4] More options");
                        
                    }

                    //user is an admin
                    else if (domain == 2){
                        staffLogIn(email, pw);
                        //code here to display available functions for staff
                        System.out.println("What would you like to do?");
                        System.out.println("\t[1] Change ticket prices\t[2] Change Movie Listings\t[3] Sales Report\t[4] More Options");
                    }
                    //error checking for wrong domain choice - will do later
                    
                    break;
                
                case 2:
                    //creating new account procedures (ONLY FOR MEMBERS --- to prevent giving random people admin priviledges)
                    Scanner input5 = new Scanner(System.in);
                    System.out.println("Please key in your email address below: ");
                    String newEmail = input5.nextLine();

                    Scanner input6 = new Scanner(System.in);
                    System.out.println("Please key in your password below: ");
                    String newPw = input6.nextLine();

                    createAcc(newEmail, newPw);
                    break;
                default:
                    System.out.println("Invalid choice!");
                    check = true;
            }
        }
        
    }
}