package serializers;

import java.io.*;
import java.util.ArrayList;
import models.Price;

public class PriceSerializer {
    private static final String CSV_SEPARATOR = ",";

    public static void writeToStaffCSV(Price p)
    {
        try
        {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("database/priceData.csv", true)));
            StringBuffer oneLine = new StringBuffer();
            oneLine.append(p.getCat());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(p.getPrice());
            bw.write(oneLine.toString());
            bw.newLine();
            bw.flush();
            ;
        }
        catch (UnsupportedEncodingException e) {System.out.printf("'%s' %n", "Unsupported Encoding");}
        catch (FileNotFoundException e){System.out.printf("'%s' %n", "File Not Found"); }
        catch (IOException e){e.printStackTrace();}
    }

    public static ArrayList<Price> readFromPriceCSV()
    {
        try {
            ArrayList<Price> pList = new ArrayList<Price>();
            File file = new File("database/priceData.csv");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String[] tempArr;
            while((line = br.readLine()) != null) {
               tempArr = line.split(CSV_SEPARATOR);
               String cat = tempArr[0];
               double price = Double.valueOf(tempArr[1]);
               Price p = new Price(cat,price);
               pList.add(p);
               
            }
            ;
            return pList;
        } 
        catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static void overwriteStaffCSV(ArrayList<Price> pList) {
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("database/priceData.csv",false)));
			for(Price p:pList) {
				StringBuffer oneLine = new StringBuffer();
                oneLine.append(p.getCat());
				oneLine.append(CSV_SEPARATOR);
                oneLine.append(p.getPrice());
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

    public static void updatePriceFromCSV(String cat, double newPrice) {
	    ArrayList<Price> pList = PriceSerializer.readFromPriceCSV();
	    int flag =0;
	    for (Price p:pList) {
		    if (p.getCat().equals(cat)) {
                p.setPrice(newPrice);
		    	flag=1;
		    	break;
	    	}
	    }
	    if (flag==1){
	        PriceSerializer.overwriteStaffCSV(pList);
	        System.out.printf("%s price succesfully updated to $%.2f!\n",cat,newPrice);
	    } else System.out.println(cat + " price update unsuccesful!");
}
    
}
