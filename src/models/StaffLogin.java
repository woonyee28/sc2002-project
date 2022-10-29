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

	public String checkName(String email){
        String name = email;
        for (Staff s: this.sList){
            if (s.getEmail().equals(email)){
                name = s.getName();
                break;
            }
        }
        return name;
    }

	public static int run(String email, String password){
		StaffLogin login = new StaffLogin();
		if(!login.checkExistenceEmail(email)){
			System.out.println("Account does not exist! Approach staff for assistance.");
			return 0;
		}
		else {
			
			if(login.checkPassword(email,String.valueOf(password.hashCode()))){
				String name = login.checkName(email);
				System.out.println("Logging in...");
				System.out.println("Welcome "+ name);
				return 1;
			}
			else {
				System.out.println("Wrong password!");
				return 0;
			}
			
		}

    }
}