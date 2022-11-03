package serializers;

import java.util.ArrayList;

public interface InterfaceSerializer<T> {
    public void writeToCSV(T object);
    public ArrayList<T> readFromCSV();
    public void overwriteCSV(ArrayList<T> aList);
    public void updateFromCSV(T object);
    public void deleteFromCSV(T object);
}
