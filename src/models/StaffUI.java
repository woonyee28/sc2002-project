package models;
import java.util.*;

import serializers.StaffSerializer;

public class StaffUI  implements logIn {
	private ArrayList<Staff> sList;

    public StaffUI(){
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

	public boolean checkExistenceName(String name){
		boolean exists = false;
		for (Staff s: this.sList){
			if (s.getName().equals(name)){
				exists =true;
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

	public static void createNewStaff(){
        StaffUI create = new StaffUI();
		String email=null,name=null,password=null,hashedPassword =null;
		int staffID =0;
		Scanner sc = new Scanner(System.in).useDelimiter("\n");
		try{
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
			
			Staff newStaff = new Staff(staffID,name,email,hashedPassword); 
			StaffSerializer.writeToStaffCSV(newStaff);
			System.out.println("New Staff user succesfully added");
		}
		else {
			System.out.print("Email already exists!");
		}
	
	}
		else {
			System.out.println("StaffID alread exists!");
		}
	}finally{
		sc.close();
	}
	}

		public static void deleteStaff(){
			StaffUI delete = new StaffUI();
			String name =null;
			int staffID =0;
			Scanner sc = new Scanner(System.in).useDelimiter("\n");
			try{
			System.out.println("Deleting Staff...");
			System.out.println("Please enter id of Staff to be deleted:");
			if (sc.hasNextInt()) {
				staffID = sc.nextInt();
			}
			if (delete.checkExistenceID(staffID)) {
				System.out.println("Please enter name of Staff to be deleted:");
				if (sc.hasNext()) {
					name = sc.next();
				}
				if (delete.checkExistenceName(name)) {
					StaffSerializer.deleteStaffFromCSV(staffID, name);
				}else System.out.println("Staff "+ name+ " not found!");
			}else System.out.println("Staff id " + staffID +" not found!" );
		}finally{
			sc.close();
		}
		}

		public static void printStaffList(){
			for(Staff s:StaffSerializer.readFromStaffCSV()){
				s.toString();
				System.out.println(s);
			}
		}

    }





     
    

