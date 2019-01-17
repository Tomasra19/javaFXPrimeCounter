package sample;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class StorageAdapter {

    public final  String FILE_NAME = "rezultatai.txt";

    private static BufferedWriter bufferedWriter;
    //constructor creates new file
    public StorageAdapter() {
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(FILE_NAME, true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //gets  current date stamp with ms
    public String getCurrentLocalDateTimeStamp() {
        return LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss.SSS"));
    }
    //writes line of numbers factors and timestamp
    public void writeFactorsLine(int number, List<Integer> factorsList) {
        String listString = factorsList.stream().map(Object::toString)
                .collect(Collectors.joining("*"));
        String complete = getCurrentLocalDateTimeStamp() + " " + Integer.toString(number) + "=" + listString;
        System.out.println(complete);
        try {
            bufferedWriter.write(complete);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
    //writes counting started line
    public void writeCountingStart(int first, int last, int multi) {
        String complete = getCurrentLocalDateTimeStamp() + " Skaičiavimo pradžia. Naudojami skaičiai: " +
                Integer.toString(first) + ", " +
                Integer.toString(last) + ", " +
                Integer.toString(multi) + ".";
        System.out.println(complete);
        try {
            bufferedWriter.write(complete);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //writes counting ended line
    public void writeCountingEnd() {
        String complete = getCurrentLocalDateTimeStamp() + " Skaičiavimo pabaiga";
        System.out.println(complete);
        try {
            bufferedWriter.write(complete);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeBufferedWriter() {
        try {
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
