package serializers;

import java.io.*;
import java.util.ArrayList;

import models.Holiday;

public class HolidaySerializer {
    private static final String CSV_SEPARATOR = ",";

    public static void writeToHolidayCSV(Holiday holiday)
    {
        try
        {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("database/holidayData.csv", true)));
            StringBuffer oneLine = new StringBuffer();
            oneLine.append(holiday.getName());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(holiday.getDate());

            bw.write(oneLine.toString());
            bw.newLine();
            bw.flush();
            bw.close();
        }
        catch (UnsupportedEncodingException e) {System.out.printf("'%s' %n", "Unsupported Encoding");}
        catch (FileNotFoundException e){System.out.printf("'%s' %n", "File Not Found"); }
        catch (IOException e){e.printStackTrace();}
    }

    public static ArrayList<Holiday> readFromHolidayCSV()
    {
        try {
            ArrayList<Holiday> holidayList = new ArrayList<Holiday>();
            File file = new File("database/holidayData.csv");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String[] tempArr;
            while((line = br.readLine()) != null) {
               tempArr = line.split(CSV_SEPARATOR);
               String name = tempArr[0];
               String date= tempArr[1];
               Holiday m = new Holiday(name,date);
               holidayList.add(m);
            }
            br.close();
            return holidayList;
        } 
        catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void overwriteHolidayCSV(ArrayList<Holiday> hList) {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("database/holidayData.csv",false)));
            for(Holiday h:hList) {
                StringBuffer oneLine = new StringBuffer();
                oneLine.append(h.getName());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(h.getDate());
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

public static void updateHolidayFromCSV(String name, String newDate) {
    ArrayList<Holiday> hList = HolidaySerializer.readFromHolidayCSV();
    int flag =0;
    for (Holiday h:hList) {
        if (h.getName().equals(name)) {
            h.setDate(newDate);
            flag=1;
        }
    }
    if (flag==1){
    HolidaySerializer.overwriteHolidayCSV(hList);
    System.out.println(name + " Holiday succesfully updated!");
    } else System.out.println("update of "+ name + " holiday unsuccessful!");
}

public static void deleteHolidayFromCSV(String name) {
    ArrayList<Holiday> hList = HolidaySerializer.readFromHolidayCSV();
    int index=0,flag=0;
    for (Holiday h:hList) {
        if (h.getName().equals(name)) {
            flag=1;
            break;
        }
        index++;
    }
    if (flag==1) {
        hList.remove(index);
        HolidaySerializer.overwriteHolidayCSV(hList);
        System.out.println(name + " Holiday succesfully deleted");
    }
    else System.out.println("Deletion of" +name+" holiday unsuccesful!");

    
}
}
