package serializers;

import java.io.*;
import java.util.ArrayList;
import models.Price;

public class PriceSerializer implements InterfaceSerializer<Price>{
    private static final String CSV_SEPARATOR = ",";

    @Override
    public void writeToCSV(Price p)
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
    @Override
    public ArrayList<Price> readFromCSV()
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
    @Override
    public void overwriteCSV(ArrayList<Price> pList) {
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
    @Override
    public void updateFromCSV(Price m) {
        PriceSerializer p = new PriceSerializer();
	    ArrayList<Price> pList = p.readFromCSV();
	    int flag =0;
	    for (Price pp:pList) {
		    if (pp.getCat().equals(m.getCat())) {
                pp.setPrice(m.getPrice());
		    	flag=1;
		    	break;
	    	}
	    }
	    if (flag==1){
	        p.overwriteCSV(pList);
	        System.out.printf("%s price succesfully updated to $%.2f!\n", m.getCat() , m.getPrice());
	    } else System.out.println(m.getCat() + " price update unsuccesful!");
    }

    @Override
    public void deleteFromCSV(Price o) {
        PriceSerializer p = new PriceSerializer();
	    ArrayList<Price> pList = p.readFromCSV();
	    int flag =0;
        int index = 0;
	    for (Price pp:pList) {
		    if (pp.getCat().equals(o.getCat())) {
                pp.setPrice(o.getPrice());
		    	flag=1;
		    	break;
	    	}
            index++;
	    }
        if (flag==1) {
            pList.remove(index);
            p.overwriteCSV(pList);
            System.out.println("Price record, category = " +o.getCat()+" successfully deleted!");
	    } else System.out.println("Price record, category = " + o.getCat() +" delete unsuccessful!");
        
    }
    
}