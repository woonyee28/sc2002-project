package models;

public class Price {
    private String cat;
    private double price;

    public Price(){}
    
    public Price(String cat, double price){
        this.cat=cat;
        this.price=price;
    }

    public String getCat() {
        return cat;
    }
    
    public double getPrice() {
        return price;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    private static final String CSV_SEPARATOR =",";
        
    public String toString() {
        StringBuffer oneLine = new StringBuffer();
        oneLine.append(this.getCat());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(String.format("%.2f",this.getPrice()));
        return oneLine.toString();     
    }
    
}
