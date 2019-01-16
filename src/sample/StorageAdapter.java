package sample;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class StorageAdapter {

    public static String getCurrentLocalDateTimeStamp() {
        return LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss.SSS"));
    }

    private static BufferedWriter bufferedWriter;





    public static void writeFactorsLine(int number, List<Integer> factorsList) {
        String listString = factorsList.stream().map(Object::toString)
                .collect(Collectors.joining("*"));
        String complete = getCurrentLocalDateTimeStamp() + " " + Integer.toString(number)+"="+listString;
        System.out.println(complete);
        try  {
            bufferedWriter.write(complete);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public static void writeCountingStart (int first, int last, int multi) {
        String complete = getCurrentLocalDateTimeStamp() + " Skaičiavimo pradžia. Naudojami skaičiai: " +
                Integer.toString(first) + ", " +
                Integer.toString(last) + ", " +
                Integer.toString(multi) + ".";
        System.out.println(complete);
        try {

            bufferedWriter = new BufferedWriter(new FileWriter("rezultatai.txt",true));

            bufferedWriter.write(complete);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeCountingEnd () {
        String complete = getCurrentLocalDateTimeStamp() + " Skaičiavimo pabaiga";
        System.out.println(complete);
            try {
            bufferedWriter.write(complete);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
