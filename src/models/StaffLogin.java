package models;
import serializers.StaffSerializer;
import java.util.*;



public class StaffLogin implements logIn{
    private ArrayList<Staff> sList;

	public StaffLogin(){
		this.sList=StaffSerializer.readFromStaffCSV();
	}



	@Override
	public  boolean checkPassword(String email, String hashedPassword) {	
		boolean correct = false;
		if (checkExistenceEmail(email))
			for (Staff s :sList) {
				if (s.getEmail().equals(email) && s.getPasswordHashed().equals(hashedPassword)) {
						correct = true;
				}
			}
		
		return correct;
	}


	@Override
	public  boolean checkExistenceEmail(String email) {
		boolean exists =false;
		for (Staff s:this.sList ) {
			if (s.getEmail().equals(email)) {
				exists = true;
				break;
			}
		}
		return exists;
	}

	public static int run(String email, String password){
		StaffLogin login = new StaffLogin();
		if(!login.checkExistenceEmail(email)){
			System.out.println("Email does not exist!");
			return 0;
		}
		else {
			
			if(login.checkPassword(email,String.valueOf(password.hashCode()))){
				System.out.println("succesfully logged in");
				System.out.println("Welcome "+ email);
				return 1;
			}
			else {
				System.out.println("Wrong password!");
				return 0;
			}
			
		}

    }
}