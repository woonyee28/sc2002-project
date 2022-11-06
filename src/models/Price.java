package models;

public class Price {
    private String movieType;
    private String cat;
    private double price;
   

    public Price(){}
    
    public Price(String movieType,String cat, double price){
        this.cat=cat;
        this.price=price;
        this.movieType=movieType;
    }

    public String getMovieType(){
        return this.movieType;
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

    public void setMovieType(String movieType){
        this.movieType = movieType;
    }

    private static final String CSV_SEPARATOR =",";
        
    public String toString() {
        StringBuffer oneLine = new StringBuffer();
        oneLine.append(this.getMovieType());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(this.getCat());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(String.format("%.2f",this.getPrice()));
        return oneLine.toString();     
    }
    
}
