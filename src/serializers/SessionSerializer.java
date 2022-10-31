package serializers;

import java.io.*;
import java.util.ArrayList;

import models.Sessions;

public class SessionSerializer {
    private static final String CSV_SEPARATOR = ",";

    public static void writeToSessionCSV(Sessions session)
    {
        try
        {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("database/sessionsData.csv", true)));
            StringBuffer oneLine = new StringBuffer();
            oneLine.append(session.getMovieID());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(session.getSessionDate());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(session.getSessionTime());
            oneLine.append(CSV_SEPARATOR);
            String cast = session.getSeatingPlan().toString();
            cast = cast.replace(',', ';').substring(1, cast.length() - 1);
            oneLine.append(cast);

            bw.write(oneLine.toString());
            bw.newLine();
            bw.flush();
            bw.close();
        }
        catch (UnsupportedEncodingException e) {System.out.printf("'%s' %n", "Unsupported Encoding");}
        catch (FileNotFoundException e){System.out.printf("'%s' %n", "File Not Found"); }
        catch (IOException e){e.printStackTrace();}
    }
  
    public static ArrayList<Sessions> readFromSessionCSV()
    {
        try {
            ArrayList<Sessions> sessionList = new ArrayList<Sessions>();
            File file = new File("database/sessionsData.csv");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String[] tempArr;
            while((line = br.readLine()) != null) {
               tempArr = line.split(CSV_SEPARATOR);
               int movieID = Integer.valueOf(tempArr[0]);
               String sessionDate = tempArr[1];
               String sessionTime = tempArr[2];
               ArrayList<Integer> seatingPlan = new ArrayList<Integer>();
               String[] tempSessionID;
               tempSessionID = tempArr[3].split("; ");
               for (String s: tempSessionID) {           
                seatingPlan.add(Integer.valueOf(s));
               }
               Sessions m = new Sessions(movieID, sessionDate, sessionTime, seatingPlan);
               sessionList.add(m);
               
            }
            br.close();
            return sessionList;
        } 
        catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void overwriteSessionsCSV(ArrayList<Sessions> aList) {
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("database/sessionsData.csv",false)));
			for(Sessions session:aList) {
                StringBuffer oneLine = new StringBuffer();
                oneLine.append(session.getMovieID());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(session.getSessionDate());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(session.getSessionTime());
                oneLine.append(CSV_SEPARATOR);
                String cast = session.getSeatingPlan().toString();
                cast = cast.replace(',', ';').substring(1, cast.length() - 1);
                oneLine.append(cast);
        
    
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

    public static void updateSessionsFromCSV(int movieID, String sessionDate, String sessionTime, ArrayList<Integer> seatingPlan) {
	    ArrayList<Sessions> aList = SessionSerializer.readFromSessionCSV();
	    int flag =0;
	    for (Sessions a:aList) {
		    if (a.getMovieID()==movieID && a.getSessionDate().equals(sessionDate) && a.getSessionTime().equals(sessionTime)) {
                a.setSeatingPlan(seatingPlan);
		    	flag=1;
		    	break;
	    	}
	    }
	    if (flag==1){
	        SessionSerializer.overwriteSessionsCSV(aList);
	        System.out.println("Sessions record, date = "+sessionDate+" time = " + sessionTime+" successfully updated!");
        }
        else System.out.println("Sessions record, date = "+sessionDate+" time = " + sessionTime+" update unsuccessful!");
    }

    public static void deleteSessionsFromCSV(int movieID, String sessionDate, String sessionTime) {
        ArrayList<Sessions> aList = SessionSerializer.readFromSessionCSV();
        int index=0,flag=0;
        for (Sessions a:aList) {
            if (a.getMovieID()==movieID && a.getSessionDate().equals(sessionDate) && a.getSessionTime().equals(sessionTime)){
                flag=1;
                break;
            }
            index++;
        }
        if (flag==1) {
            aList.remove(index);
            SessionSerializer.overwriteSessionsCSV(aList);
            System.out.println("Sessions record, date = "+sessionDate+" time = " + sessionTime+" successfully deleted!");
        }
        else System.out.println("Sessions record, date = "+sessionDate+" time = " + sessionTime+" deletion unsuccessful!");

	
    }
}
