package managers;

public interface logIn {
    public boolean checkPassword(String email,String hashedPassword);
    public boolean checkExistenceEmail(String email);

    
}
