package models;
import java.util.*;
import serializers.StaffSerializer;

public class StaffCreate implements logIn {
    private ArrayList<Staff> sList;

    public StaffCreate(){
        this.sList= StaffSerializer.readFromStaffCSV();
    }

    @Override
    public boolean checkPassword(String email, String hashedPassword) {
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
    public boolean checkExistenceEmail(String email) {
        boolean exists =false;
		for (Staff s:this.sList ) {
			if (s.getEmail().equals(email)) {
				exists = true;
				break;
			}
		}
        return exists;
    }

    public boolean checkExistenceID(int id){
        boolean exists =false;
		for (Staff s:this.sList ) {
			if (s.getStaffID()==id) {
				exists = true;
				break;
			}
		}
        return exists;

    }

    public int run(){
        StaffCreate create = new StaffCreate();
		String email=null,name=null,password=null,hashedPassword =null;
		int staffID =0;
		Scanner sc = new Scanner(System.in).useDelimiter("\n");
		System.out.println("Creating new Staff...");
		System.out.println("Please enter staffID:");
		if(sc.hasNextInt()) {
			staffID = sc.nextInt();
		}
		if(!create.checkExistenceID(staffID)) {
			System.out.println("Please enter email:");
			if(sc.hasNext()) {
				email=sc.next();
			}
			if(!create.checkExistenceEmail(email)) {
				System.out.println("Please enter password:");
				if(sc.hasNext()) {
				password=sc.next();
				}
				System.out.println("Please enter name:");
				if(sc.hasNext()) {
					name=sc.next();
				}
			
				hashedPassword = String.valueOf(password.hashCode());
			
			Staff newAdmin = new Staff(staffID,name,email,hashedPassword); 
			StaffSerializer.writeToStaffCSV(newAdmin);
			System.out.println("New Staff user succesfully added");
            return 1;
		}
		else {
			System.out.print("Email already exists!");
            return 0;
		}
	}
		else System.out.println("StaffID alread exists!");
        return 0;
    }
    
}
