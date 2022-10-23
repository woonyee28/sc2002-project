package models;

public class Holiday {
    private String name;
    private String date;

    public Holiday(String name, String date){
        this.name = name;
        this.date = date;
    }

    // get methods
    public String getName(){
        return this.name;
    }

    public String getDate(){
        return this.date;
    }

    // set methods
    public void setName(String name){
        this.name = name;
    }

    public void setDate(String date){
        this.date = date;
    }

    // Generate String representation of the Object
    private static final String CSV_SEPARATOR = ",";
    public String toString()
    {
        StringBuffer oneLine = new StringBuffer();
        oneLine.append(this.getName());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(this.getDate());

        return oneLine.toString();
    }
}
