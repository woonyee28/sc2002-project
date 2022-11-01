package models;
import java.util.*;
import serializers.MovieGoerSerializer;

public class MemberCreate implements logIn {
    private ArrayList<MovieGoer> mList;

    public MemberCreate(){
        this.mList= MovieGoerSerializer.readFromMovieGoerCSV();
    }

    @Override
    public boolean checkPassword(String email, String hashedPassword) {
        boolean correct = false;
		if (checkExistenceEmail(email))
			for (MovieGoer m :mList) {
				if (m.getEmail().equals(email) && m.getPasswordHashed().equals(hashedPassword)) {
						correct = true;
				}
			}
		
		return correct;
    }

    @Override
    public boolean checkExistenceEmail(String email) {
        boolean exists =false;
		for (MovieGoer m:this.mList ) {
			if (m.getEmail().equals(email)) {
				exists = true;
				break;
			}
		}
        return exists;
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

    public int run(){
        MemberCreate create = new MemberCreate();
        int movieGoerID = 0;
        String name=null;
        String email=null;
        int age = -1;
        String password = null;
        String passwordHashed = null;
        int mobile = -1;
		
        System.out.println("--------- Create an account ---------");
        Scanner input1 = new Scanner(System.in);
        System.out.println("Please enter your email ID:");
        email = input1.nextLine();

        if (!create.checkExistenceEmail(email)){
            movieGoerID = checkExistenceID() + 1;
            System.out.println("Please enter your name:");
            name = input1.nextLine();
            System.out.println("Please enter your age:");
            age = input1.nextInt();
            System.out.println("Please enter your mobile number:");
            mobile = input1.nextInt();

            Scanner input2 = new Scanner(System.in); 
            System.out.println("Please enter your password:");
            password = input2.nextLine();
            ;
            
            passwordHashed = String.valueOf(password.hashCode());

            MovieGoer newMem = new MovieGoer(movieGoerID, name, email, age, passwordHashed, mobile, null);
            MovieGoerSerializer.writeToMovieGoerCSV(newMem);
            System.out.println("Account successfully created. Please log in again");
            return 1;
        }
        else{
            System.out.println("Account already exists!");
        }
        ;
    
        return 0;
		
    }
    
}