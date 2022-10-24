package models;

import java.util.ArrayList;

public class Cineplexes {
    String cineplexCode;
    String name;
    ArrayList<String> cinemasCode;

    public Cineplexes() {
    }

    public Cineplexes(String cineplexCode, String name, ArrayList<String> cinemasCode) {
        this.cineplexCode = cineplexCode;
        this.name = name;
        this.cinemasCode = cinemasCode;
    }

    public String getCineplexCode() {
        return this.cineplexCode;
    }

    public void setCineplexCode(String cineplexCode) {
        this.cineplexCode = cineplexCode;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getCinemasCode() {
        return this.cinemasCode;
    }

    public void setCinemasCode(ArrayList<String> cinemasCode) {
        this.cinemasCode = cinemasCode;
    }

    @Override
    public String toString() {
        return "{" +
            " cineplexCode='" + getCineplexCode() + "'" +
            ", name='" + getName() + "'" +
            ", cinemasCode='" + getCinemasCode() + "'" +
            "}";
    }

}
