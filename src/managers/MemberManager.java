package managers;

import serializers.*;

import java.util.ArrayList;
import java.util.Scanner;

import models.*;

public class MemberManager implements logIn {
    private static ArrayList<MovieGoer> mList;
    

    public MemberManager(){
        this.mList = MovieGoerSerializer.readFromMovieGoerCSV();
    }

    @Override
    public boolean checkPassword(String email, String hashedPassword){
        boolean correct = false;
        if (checkExistenceEmail(email)){
                for (MovieGoer m: mList){
                    if (m.getEmail().equals(email) && m.getPasswordHashed().equals(hashedPassword)){
                        correct = true;
                    }
                }
        }


        return correct;
    }

    @Override
    public boolean checkExistenceEmail(String email){
        boolean exists = false;
        for (MovieGoer m: this.mList){
            if (m.getEmail().equals(email)){
                exists = true;
                break;
            }
        }

        return exists;
    }

    public String checkName(String email){
        String name = email;
        for (MovieGoer m: this.mList){
            if (m.getEmail().equals(email)){
                name = m.getName();
                break;
            }
        }
        return name;
    }

    public static int logIn(){
        MemberManager login = new MemberManager();
        Scanner input = new Scanner(System.in);
        System.out.println("Please key in your email:");
        String email = input.nextLine();
        System.out.println("Please key in your password:");
        String password = input.nextLine();
        if(!login.checkExistenceEmail(email)){
			System.out.println("Email does not exist!");
			return -1;
		}
		else {
			
			if(login.checkPassword(email,String.valueOf(password.hashCode()))){
				String name = login.checkName(email);
                System.out.println("Logging in...");
				System.out.println("Welcome "+ name);
				return MemberManager.checkMovieGoerID(email);
			}
			else {
				System.out.println("Wrong password!");
				return -1;
			}
		}
    }

    public int checkExistenceID(){
        int largest = 0;
		for (MovieGoer m:this.mList) {
			if (largest < m.getMovieGoersID()) {
				largest = m.getMovieGoersID();
			}
		}
        return largest;

    }

    public static int createMember(){
        MemberManager create = new MemberManager();
        int movieGoerID = 0;
        String name=null;
        String email=null;
        int age = -1;
        String password = null;
        String passwordHashed = null;
        int mobile = -1;
		
        System.out.println("--------- Create an account ---------");
        Scanner input1 = new Scanner(System.in);
        System.out.println("Please enter your email:");
        email = input1.nextLine();

        if (!create.checkExistenceEmail(email)){
            movieGoerID = create.checkExistenceID() + 1;
            System.out.println("Please enter your name:");
            name = input1.nextLine();
            System.out.println("Please enter your age:");
            age = input1.nextInt();
            System.out.println("Please enter your mobile number:");
            mobile = input1.nextInt();

            Scanner input2 = new Scanner(System.in); 
            System.out.println("Please enter your password:");
            password = input2.nextLine();
            
            passwordHashed = String.valueOf(password.hashCode());

            MovieGoer newMem = new MovieGoer(movieGoerID, name, email, age, passwordHashed, mobile, null);
            MovieGoerSerializer.writeToMovieGoerCSV(newMem);
            System.out.println("Account successfully created. Please log in again");
            return 1;
        }
        else{
            System.out.println("Account already exists!");
        }
        return 0;
		
    }
    public static void signUp(){
        // Create Account then go back to memberLogIn
        int check1=0;
        check1 = createMember();
        if (check1==1){
            logIn();
        }

    }

    public static int checkMovieGoerID(String email){
        for (MovieGoer m: mList){
            if (m.getEmail().equals(email)){
                return m.getMovieGoersID();
            }
        }
        return -1;
    }

    
    
}
