package serializers;

import java.io.*;
import java.util.ArrayList;

import models.Staff;

public class StaffSerializer {
    private static final String CSV_SEPARATOR = ",";

    public static void writeToStaffCSV(Staff staff)
    {
        try
        {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("database/staffsData.csv", true)));
            StringBuffer oneLine = new StringBuffer();
            oneLine.append(staff.getStaffID());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(staff.getName());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(staff.getEmail());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(staff.getPasswordHashed());

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
            ArrayList<Staff> staffList = new ArrayList<Staff>();
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
               String passwordHashed = tempArr[3];
               Staff m = new Staff(staffID,name,email,passwordHashed);
               staffList.add(m);
            }
            br.close();
            return staffList;
        } 
        catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
