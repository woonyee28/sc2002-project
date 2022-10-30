package serializers;

import java.io.*;
import java.util.ArrayList;
import models.Ticket;

public class TicketSerializer {
    private static final String CSV_SEPARATOR = ",";

    

    public static void writeToTicketCSV(Ticket t)
    {
        try
        {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("database/ticketData.csv", true)));
            StringBuffer oneLine = new StringBuffer();
            oneLine.append(t.getMovieType());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(t.getCineplexCode());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(t.getAgeCat());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(t.getDayType());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(t.getAfterSix());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(t.getPrice());
            bw.write(oneLine.toString());
            bw.newLine();
            bw.flush();
            bw.close();
        }
        catch (UnsupportedEncodingException e) {System.out.printf("'%s' %n", "Unsupported Encoding");}
        catch (FileNotFoundException e){System.out.printf("'%s' %n", "File Not Found"); }
        catch (IOException e){e.printStackTrace();}
    }

    public static ArrayList<Ticket> readFromTicketCSV()
    {
        try {
            ArrayList<Ticket> adminList = new ArrayList<Ticket>();
            File file = new File("database/ticketData.csv");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String[] tempArr;
            while((line = br.readLine()) != null) {
               tempArr = line.split(CSV_SEPARATOR);
               String movieType = tempArr[0];
               String cineplexCode = tempArr[1];
               String ageCat = tempArr[2];
               String dayType = tempArr[3];
               Boolean afterSix = Boolean.parseBoolean(tempArr[4]);
               int price =Integer.parseInt(tempArr[5]);
               Ticket newT = new Ticket(movieType,cineplexCode,ageCat,dayType,afterSix,price);
               adminList.add(newT);
               
            }
            br.close();
            return adminList;
        } 
        catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static void overwriteTicketCSV(ArrayList<Ticket> tList) {
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("database/ticketData.csv",false)));
			for(Ticket t:tList) {
                StringBuffer oneLine = new StringBuffer();
                oneLine.append(t.getMovieType());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(t.getCineplexCode());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(t.getAgeCat());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(t.getDayType());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(t.getAfterSix());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(t.getPrice());
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

    public static void updateTicketfromCSV(String movieType, String cineplexCode, String ageCat,String dayType,Boolean afterSix,int price) {
	    ArrayList<Ticket> tList = TicketSerializer.readFromTicketCSV();
	    int flag =0;
	    for (Ticket t:tList) {
		    if (t.getMovieType().equals(movieType) 
            && t.getCineplexCode().equals(cineplexCode) 
            && t.getAgeCat().equals(ageCat) 
            && t.getDayType().equals(dayType)
            && t.getAfterSix()==afterSix) {
		    	t.setPrice(price);
		    	flag=1;
		    	break;
	    	}
	    }
	    if (flag==1){
	        TicketSerializer.overwriteTicketCSV(tList);
	        System.out.println("Ticket: "+ movieType + " "+ cineplexCode+" " + ageCat+ " "+ dayType + " "+ afterSix+ " price successfully updated!");
	    } else System.out.println("Ticket: "+ movieType + " "+ cineplexCode+" " + ageCat+ " "+ dayType + " "+ afterSix+ " price update unsuccesful!");
    }

public static void deleteTicketFromCSV(String movieType, String cineplexCode, String ageCat,String dayType,Boolean afterSix,int price) {
	ArrayList<Ticket> tList = TicketSerializer.readFromTicketCSV();
	int index=0,flag=0;
	for (Ticket t:tList) {
		if (t.getMovieType().equals(movieType) 
            && t.getCineplexCode().equals(cineplexCode) 
            && t.getAgeCat().equals(ageCat) 
            && t.getDayType().equals(dayType)
            && t.getAfterSix()==afterSix
            && t.getPrice()==price)
            {
			flag=1;
			break;
		}
		index++;
	}
	if (flag==1) {
		tList.remove(index);
		TicketSerializer.overwriteTicketCSV(tList);
		System.out.println("Ticket: "+ movieType + " "+ cineplexCode+" " + ageCat+ " "+ dayType + " "+ afterSix+" "+price + " successfully deleted!");
	    } else System.out.println("Ticket: "+ movieType + " "+ cineplexCode+" " + ageCat+ " "+ dayType + " "+ afterSix+ " "+price+ " deletion unsuccesful!");
    
    }
    
}
