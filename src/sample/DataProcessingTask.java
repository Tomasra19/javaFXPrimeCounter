package sample;

import javafx.concurrent.Task;

import java.util.ArrayList;
import java.util.List;


public class DataProcessingTask extends Task {

    private StorageAdapter storageAdapter;

    private int firstNumber;
    private int lastNumber;
    private int multiNumber;
    //boolean to terminate task or run
    private volatile boolean running = true;
    //boolean to determine if task was started again or should complete
    private volatile boolean startAgain = false;
    //ints to increase progressbar correctly.
    private int maxProgress;
    private int progressIterator;

    public DataProcessingTask(int firstNumber, int lastNumber, int multiNumber) {
        this.firstNumber = firstNumber;
        this.lastNumber = lastNumber;
        this.multiNumber = multiNumber;
        storageAdapter = new StorageAdapter();
        for (int i = firstNumber; i <= lastNumber; i += multiNumber) {
            maxProgress++;
        }

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
    //main counting method, counts and writes lines to file rezultatai.txt
    @Override
    protected Object call() throws Exception {
        progressIterator = 0;
        storageAdapter.writeCountingStart(firstNumber, lastNumber, multiNumber);
        for (int i = firstNumber; i <= lastNumber; i += multiNumber) {
            if (running) {
                progressIterator++;
                updateMessage("Skaidomas skaičius: " + i);
                storageAdapter.writeFactorsLine(i, findAllFactors(i));
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                updateProgress(progressIterator, maxProgress);
            }
        }
        if (!startAgain) {
            storageAdapter.writeCountingEnd();
            storageAdapter.closeBufferedWriter();
            updateMessage("Skaidymas baigtas. Rezultatai faile " + storageAdapter.FILE_NAME);
        }
        return null;
    }
    //to stop task
    public void terminate() {
        running = false;
    }
    //if used doesn't write end line "Skaiciavimas Baigtas"
    public void setStartAgain() {
        startAgain = true;
    }

}
