package serializers;

import java.io.*;
import java.util.ArrayList;
 
import models.Cineplexes;

public class CineplexSerializer {
    private static final String CSV_SEPARATOR = ",";

    public static void writeToCineplexesCSV(Cineplexes cineplexes)
    {
        try
        {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("database/cineplexesData.csv", true)));
            StringBuffer oneLine = new StringBuffer();
            oneLine.append(cineplexes.getCineplexCode());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(cineplexes.getName());
            oneLine.append(CSV_SEPARATOR);
            String cast = cineplexes.getCinemasCode().toString();
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
 
    public static ArrayList<Cineplexes> readFromCineplexesCSV()
    {
        try {
            ArrayList<Cineplexes> cineplexesList = new ArrayList<Cineplexes>();
            File file = new File("database/cineplexesData.csv");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String[] tempArr;
            while((line = br.readLine()) != null) {
               tempArr = line.split(CSV_SEPARATOR);
               String cineplexCode = tempArr[0];
               String name = tempArr[1];
               ArrayList<String> cinemasCode = new ArrayList<String>();
               String[] tempCast;
               tempCast = tempArr[2].split("; ");
               for (String s: tempCast) {           
                cinemasCode.add(s);
               }
               Cineplexes m = new Cineplexes(cineplexCode,name,cinemasCode);
               cineplexesList.add(m);
               
            }
            br.close();
            return cineplexesList;
        } 
        catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }  
    public static void overwriteCineplexesCSV(ArrayList<Cineplexes> aList) {
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("database/cineplexesData.csv",false)));
			for(Cineplexes cineplexes:aList) {
                StringBuffer oneLine = new StringBuffer();
                oneLine.append(cineplexes.getCineplexCode());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(cineplexes.getName());
                oneLine.append(CSV_SEPARATOR);
                String cast = cineplexes.getCinemasCode().toString();
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

    public static void updateCineplexesFromCSV(String cineplexCode, String name, ArrayList<String> cinemasCode) {
	    ArrayList<Cineplexes> aList = CineplexSerializer.readFromCineplexesCSV();
	    int flag =0;
	    for (Cineplexes a:aList) {
		    if (a.getCineplexCode().equals(cineplexCode) ) {
                a.setName(name);
                a.setCinemasCode(cinemasCode);
		    	flag=1;
		    	break;
	    	}
	    }
	    if (flag==1){
	        CineplexSerializer.overwriteCineplexesCSV(aList);
	        System.out.println("Cineplexes record, code = "+ cineplexCode+" successfully updated!");
        }
        else System.out.println("Cineplexes record, code = "+ cineplexCode+" update unsuccessful!");
    }

    public static void deleteCineplexesFromCSV(String cineplexCode) {
        ArrayList<Cineplexes> aList = CineplexSerializer.readFromCineplexesCSV();
        int index=0,flag=0;
        for (Cineplexes a:aList) {
            if (a.getCineplexCode().equals(cineplexCode)) {
                flag=1;
                break;
            }
            index++;
        }
        if (flag==1) {
            aList.remove(index);
            CineplexSerializer.overwriteCineplexesCSV(aList);
            System.out.println("Cineplexes record, code = "+ cineplexCode+" successfully deleted!");
        }
        else System.out.println("Cineplexes record, code = "+ cineplexCode+" deletion unsuccessful!");

	
    }
}
