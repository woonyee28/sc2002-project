package models;

public class Staff {
    private int staffID;
    private String name;
    private String email;
    private String passwordHashed;
    
    public Staff(int staffID, String name, String email, String passwordHashed){
        this.staffID = staffID;
        this.name = name;
        this.email = email;
        this.passwordHashed = passwordHashed;
    }

    // get methods
    public int getStaffID(){
        return this.staffID;
    }

    public String getName(){
        return this.name;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPasswordHashed(){
        return this.passwordHashed;
    }

    // set methods
    public void setStaffID(int staffID){
        this.staffID = staffID;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPasswordHashed(String passwordHashed){
        this.passwordHashed = passwordHashed;
    }

    // Generate String representation of the Object
    private static final String CSV_SEPARATOR = ",";
    public String toString()
    {
        StringBuffer oneLine = new StringBuffer();
        oneLine.append(this.getStaffID());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(this.getName());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(this.getEmail());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(this.getPasswordHashed());

        return oneLine.toString();
    }

   
}
