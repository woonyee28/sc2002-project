interface logIn{
    boolean checkExistence();
    boolean checkPassword();
}

class memberLogin implements logIn{
    String email, password;

    memberLogin(String email, String password){
        this.email = email;
        this.password = password;
    }

    public boolean checkExistence(){
        boolean exist = false;
        //using email, check for existence in CSV file
        //if (email in CSV file) {exist = true;}
        return exist;
    }

    public boolean checkPassword(){
        boolean correct = false;
        int inputHashed = password.hashCode();
        //check if inputHashed matches the hashed password of corresponding email
        //if (inputHash is a match){correct = true}
        //if (inputHash is not a match){correct = false}
        return correct;
    }
}

class adminLogin implements logIn{
    String email, password;

    adminLogin(String email, String password){
        this.email = email;
        this.password = password;
    }

    public boolean checkExistence(){
        boolean exist = false;
        //using email, check for existence in CSV file
        //if (email in CSV file) {exist = true;}
        return exist;
    }

    public boolean checkPassword(){
        boolean correct = false;
        int inputHashed = password.hashCode();
        //check if inputHashed matches the hashed password of corresponding email
        //if (inputHash is a match){correct = true}
        //if (inputHash is not a match){correct = false}
        return correct;
    }
}