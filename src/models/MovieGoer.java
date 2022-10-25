package models;

public class MovieGoer {
    private int movieGoersID;
    private String name;
    private String email;
    private int age;
    private String passwordHashed;
    private int mobileNumber; 
    private String TID_List;

    public MovieGoer(int movieGoersID, String name, String email, int age, String passwordHashed, int mobileNumber, String TID_List){
        this.movieGoersID = movieGoersID;
        this.name = name;
        this.email = email;
        this.age = age;
        this.passwordHashed = passwordHashed;
        this.mobileNumber = mobileNumber;
        this.TID_List = TID_List;
    }

    // get methods
    public int getMovieGoersID(){
        return this.movieGoersID;
    }

    public String getName(){
        return this.name;
    }

    public String getEmail(){
        return this.email;
    }

    public int getAge(){
        return this.age;
    }

    public String getPasswordHashed(){
        return this.passwordHashed;
    }

    public int getMobileNumber(){
        return this.mobileNumber;
    }

    public String getTID_List(){
        return this.TID_List;
    }

    // set methods
    public void setMovieGoersID(int movieGoersID){
        this.movieGoersID = movieGoersID;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setAge(int age){
        this.age = age;
    }

    public void setpasswordHashed(String passwordHashed){
        this.passwordHashed = passwordHashed;
    }

    public void setMobileNumber(int mobileNumber){
        this.mobileNumber = mobileNumber;
    }

    public void setTID_List(String TID_List){
        this.TID_List = TID_List;
    }

    // Generate String representation of the Object
    private static final String CSV_SEPARATOR = ",";
    public String toString()
    {
        StringBuffer oneLine = new StringBuffer();
        oneLine.append(this.getMovieGoersID());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(this.getName());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(this.getEmail());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(this.getAge());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(this.getPasswordHashed());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(this.getMobileNumber());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(this.getTID_List());

        return oneLine.toString();
    }
}
