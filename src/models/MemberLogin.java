package models;

import serializers.MovieGoerSerializer;
import java.util.*;;
public class MemberLogin implements logIn{
    private ArrayList<MovieGoer> mList;

    public MemberLogin(){
        this.mList = MovieGoerSerializer.readFromMovieGoerCSV();
    }

    @Override
    public boolean checkPassword(String email, String hashedPassword){
        boolean correct = false;
        if (checkExistenceEmail(email)){
                for (MovieGoer m: mList){
                    if (m.getEmail().equals(email) && m.getPasswordHashed().equals(hashedPassword)){
                        correct = true;
                    }
                }
        }


        return correct;
    }

    @Override
    public boolean checkExistenceEmail(String email){
        boolean exists = false;
        for (MovieGoer m: this.mList){
            if (m.getEmail().equals(email)){
                exists = true;
                break;
            }
        }

        return exists;
    }

    public String checkName(String email){
        String name = email;
        for (MovieGoer m: this.mList){
            if (m.getEmail().equals(email)){
                name = m.getName();
                break;
            }
        }
        return name;
    }

    public static int run(String email, String password){
        MemberLogin login = new MemberLogin();
        if(!login.checkExistenceEmail(email)){
			System.out.println("Email does not exist!");
			return 0;
		}
		else {
			
			if(login.checkPassword(email,String.valueOf(password.hashCode()))){
				String name = login.checkName(email);
                System.out.println("Logging in...");
				System.out.println("Welcome "+ name);
				return 1;
			}
			else {
				System.out.println("Wrong password!");
				return 0;
			}
			
		}

    }
}
