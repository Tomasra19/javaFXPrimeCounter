package sample;

import java.util.ArrayList;
import java.util.List;

import static sample.StorageAdapter.writeCountingEnd;
import static sample.StorageAdapter.writeCountingStart;
import static sample.StorageAdapter.writeFactorsLine;

public class DataProcessingRunnable implements Runnable {

    private int firstNumber;
    private int lastNumber;
    private int multiNumber;

    volatile boolean isRunning;

    public DataProcessingRunnable(int firstNumber, int lastNumber, int multiNumber) {
        this.firstNumber = firstNumber;
        this.lastNumber = lastNumber;
        this.multiNumber = multiNumber;
    }

//    public static void Count (int first, int last, int multi) {
//        writeCountingStart(first, last, multi);
//        for (int i = first; i <= last; i+=multi) {
//            writeFactorsLine(i,findAllFactors(i));
//        }
//        writeCountingEnd();
//    }

    //Pagrindinis metodas
    @Override
    public void run() {
        writeCountingStart(firstNumber, lastNumber, multiNumber);
        for (int i = firstNumber; i <= lastNumber; i += multiNumber) {
            if (!isRunning) {
                writeFactorsLine(i, findAllFactors(i));
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                //kodas padidinti progressbarui
            } else {
                System.out.println("kazkas");
            }
        }
        writeCountingEnd();
    }

    //Returns list of all Prime factors of a number
    public static List<Integer> findAllFactors(int n) {
        List<Integer> factors = new ArrayList<Integer>();
        for (int i = 2; i <= n; i++) {
            while (n % i == 0) {
                factors.add(i);
                n /= i;
            }
        }
        return factors;
    }
}
