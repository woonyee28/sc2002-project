package serializers;

import java.io.*;
import java.util.ArrayList;

import models.Staff;

public class StaffSerializer {
    private static final String CSV_SEPARATOR = ",";

    

    public static void writeToStaffCSV(Staff admin)
    {
        try
        {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("database/StaffsData.csv", true)));
            StringBuffer oneLine = new StringBuffer();
            oneLine.append(admin.getStaffID());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(admin.getName());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(admin.getEmail());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(admin.getPasswordHashed());
            bw.write(oneLine.toString());
            bw.newLine();
            bw.flush();
            bw.close();
        }
        catch (UnsupportedEncodingException e) {System.out.printf("'%s' %n", "Unsupported Encoding");}
        catch (FileNotFoundException e){System.out.printf("'%s' %n", "File Not Found"); }
        catch (IOException e){e.printStackTrace();}
    }

    public static ArrayList<Staff> readFromStaffCSV()
    {
        try {
            ArrayList<Staff> adminList = new ArrayList<Staff>();
            File file = new File("database/staffsData.csv");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String[] tempArr;
            while((line = br.readLine()) != null) {
               tempArr = line.split(CSV_SEPARATOR);
               int staffID = Integer.valueOf(tempArr[0]);
               String name = tempArr[1];
               String email = tempArr[2];
               String hashed = tempArr[3];
               Staff admin = new Staff(staffID,name,email,hashed);
               adminList.add(admin);
               
            }
            br.close();
            return adminList;
        } 
        catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static void overwriteStaffCSV(ArrayList<Staff> aList) {
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("database/staffsData.csv",false)));
			for(Staff a:aList) {
				StringBuffer oneLine = new StringBuffer();
				oneLine.append(a.getStaffID());
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(a.getName());
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(a.getEmail());
				oneLine.append(CSV_SEPARATOR);
	            oneLine.append(a.getPasswordHashed());
	            bw.write(oneLine.toString());
	            bw.newLine();
			}
			bw.flush();
			bw.close();
		}
		catch (UnsupportedEncodingException e) {System.out.printf("'%s' %n", "Unsupported Encoding");}
        catch (FileNotFoundException e){System.out.printf("'%s' %n", "File Not Found"); }
        catch (IOException e){e.printStackTrace();}
	
    }

    public static void updateStaffFromCSV(int staffID, String name, String email,String hash) {
	    ArrayList<Staff> aList = StaffSerializer.readFromStaffCSV();
	    int flag =0;
	    for (Staff a:aList) {
		    if (a.getName().equals(name) && a.getStaffID()==staffID ) {
		    	a.setEmail(email);
		    	a.setPasswordHashed(hash);
		    	flag=1;
		    	break;
	    	}
	    }
	    if (flag==1){
	        StaffSerializer.overwriteStaffCSV(aList);
	        System.out.println("Admin account: name = " +name+" id = "+ staffID+" successfully updated!");
	    } else System.out.println("Admin account: name = " +name+" id = "+ staffID+" update unsuccessful!");
}

public static void deleteStaffFromCSV(int staffID, String name) {
	ArrayList<Staff> aList = StaffSerializer.readFromStaffCSV();
	int index=0,flag=0;
	for (Staff a:aList) {
		if (a.getName().equals(name) && a.getStaffID()==staffID) {
			flag=1;
			break;
		}
		index++;
	}
	if (flag==1) {
		aList.remove(index);
		StaffSerializer.overwriteStaffCSV(aList);
		System.out.println("Admin account: name = " +name+" id = "+ staffID+" successfully deleted!");
	}
	else System.out.println("Staff record, name = " +name+" id = "+ staffID+" deletion unsuccessful!");

	
    }
}
