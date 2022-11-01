package serializers;

import java.io.*;
import java.util.ArrayList;

import models.MovieGoer;

public class MovieGoerSerializer {
    private static final String CSV_SEPARATOR = ",";

    public static void writeToMovieGoerCSV(MovieGoer movieGoer)
    {
        try
        {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("database/movieGoersData.csv", true)));
            StringBuffer oneLine = new StringBuffer();
            oneLine.append(movieGoer.getMovieGoersID());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(movieGoer.getName());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(movieGoer.getEmail());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(movieGoer.getAge());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(movieGoer.getPasswordHashed());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(movieGoer.getMobileNumber());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(movieGoer.getTID_List());

            bw.write(oneLine.toString());
            bw.newLine();
            bw.flush();
            ;
        }
        catch (UnsupportedEncodingException e) {System.out.printf("'%s' %n", "Unsupported Encoding");}
        catch (FileNotFoundException e){System.out.printf("'%s' %n", "File Not Found"); }
        catch (IOException e){e.printStackTrace();}
    }

    public static ArrayList<MovieGoer> readFromMovieGoerCSV()
    {
        try {
            ArrayList<MovieGoer> movieGoerList = new ArrayList<MovieGoer>();
            File file = new File("database/movieGoersData.csv");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String[] tempArr;
            while((line = br.readLine()) != null) {
               tempArr = line.split(CSV_SEPARATOR);
               int movieGoerID = Integer.valueOf(tempArr[0].replaceAll("\\uFEFF", ""));
               String name = tempArr[1];
               String email = tempArr[2];
               int age = Integer.valueOf(tempArr[3]);
               String passwordHashed = tempArr[4];
               int mobileNumber = Integer.valueOf(tempArr[5]);
               String TID_List = tempArr[6];
               
               MovieGoer m = new MovieGoer(movieGoerID,name,email,age,passwordHashed,mobileNumber,TID_List);
               movieGoerList.add(m);
               
            }
            ;
            return movieGoerList;
        } 
        catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void overwriteMovieGoerCSV(ArrayList<MovieGoer> aList) {
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("database/movieGoersData.csv",false)));
			for(MovieGoer movieGoer:aList) {
                StringBuffer oneLine = new StringBuffer();
                oneLine.append(movieGoer.getMovieGoersID());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(movieGoer.getName());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(movieGoer.getEmail());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(movieGoer.getAge());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(movieGoer.getPasswordHashed());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(movieGoer.getMobileNumber());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(movieGoer.getTID_List());
    
                bw.write(oneLine.toString());
                bw.newLine();
			}
			bw.flush();
			;
		}
		catch (UnsupportedEncodingException e) {System.out.printf("'%s' %n", "Unsupported Encoding");}
        catch (FileNotFoundException e){System.out.printf("'%s' %n", "File Not Found"); }
        catch (IOException e){e.printStackTrace();}
	
    }

    public static void updateMovieGoerFromCSV(int movieGoersID, String name, String email, int age, String passwordHashed, int mobileNumber, String TID_List) {
	    ArrayList<MovieGoer> aList = MovieGoerSerializer.readFromMovieGoerCSV();
	    int flag =0;
	    for (MovieGoer a:aList) {
		    if (a.getName().equals(name) && a.getEmail().equals(email)) {
		    	a.setMovieGoersID(movieGoersID);
                a.setAge(age);
                a.setpasswordHashed(passwordHashed);
                a.setMobileNumber(mobileNumber);
                a.setTID_List(TID_List);
		    	flag=1;
		    	break;
	    	}
	    }
	    if (flag==1){
	        MovieGoerSerializer.overwriteMovieGoerCSV(aList);
	        System.out.println("MovieGoer record, name = " + name +" successfully updated!");
	    } else System.out.println("MovieGoer record, name " + name+" update unsuccessful!");
    }

    public static void deleteMovieGoerFromCSV(String name, String email) {
        ArrayList<MovieGoer> aList = MovieGoerSerializer.readFromMovieGoerCSV();
        int index=0,flag=0;
        for (MovieGoer a:aList) {
            if (a.getName().equals(name) && a.getEmail().equals(email)) {
                flag=1;
                break;
            }
            index++;
        }
        if (flag==1) {
            aList.remove(index);
            MovieGoerSerializer.overwriteMovieGoerCSV(aList);
            System.out.println("MovieGoer record, name = " + name +" successfully deleted!");
	    } else System.out.println("MovieGoer record, name " + name+" delete unsuccessful!");
    }
}
 