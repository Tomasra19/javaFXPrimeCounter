package sample;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;


public class Controller {
    //initialize fxml fields
    @FXML
    private TextField firstNumberInput;
    @FXML
    private TextField lastNumberInput;
    @FXML
    private TextField multiNumberInput;

    @FXML
    private Button buttonStart;
    @FXML
    private Button buttonCancel;

    @FXML
    private ProgressBar progressBar;
    @FXML
    private Label labelForPB;


    private Thread t = null;
    private DataProcessingTask task = null;


    //disable buttons before user input
    @FXML
    public void initialize() {
        buttonStart.setDisable(true);
        buttonCancel.setDisable(true);
    }
    @FXML
    public void handleKeyReleased() {
        String input1 = firstNumberInput.getText();
        String input2 = lastNumberInput.getText();
        String input3 = multiNumberInput.getText();
        boolean isempty1 = input1.isEmpty() || input1.trim().isEmpty();
        boolean isempty2 = input2.isEmpty() || input2.trim().isEmpty();
        boolean isempty3 = input3.isEmpty() || input3.trim().isEmpty();
        boolean isNumber = checkIfnumber(input1, input2, input3);
        boolean disableButtons = isempty1 || isempty2 || isempty3 || !isNumber;
        buttonStart.setDisable(disableButtons);
        buttonCancel.setDisable(disableButtons);
    }
    //On button start click starts counting using another thread
    public void count(ActionEvent event) throws InterruptedException {
        labelForPB.setVisible(true);
        progressBar.setVisible(true);

        int firstNumber = Integer.parseInt(firstNumberInput.getText());
        int lastNumber = Integer.parseInt(lastNumberInput.getText());
        int multiNumber = Integer.parseInt(multiNumberInput.getText());
        if (t != null) {
            task.terminate();
            task.setStartAgain();
            t.join();
            task = new DataProcessingTask(firstNumber, lastNumber, multiNumber);
            progressBar.progressProperty().bind(task.progressProperty());
            labelForPB.textProperty().bind(task.messageProperty());
            t = new Thread(task);
            t.start();
        } else {
            task = new DataProcessingTask(firstNumber, lastNumber, multiNumber);
            progressBar.progressProperty().bind(task.progressProperty());
            labelForPB.textProperty().bind(task.messageProperty());
            t = new Thread(task);
            t.start();
        }
    }
    //stop counting for button stop
    public void stop(ActionEvent event) {
        if (t != null) {
            task.terminate();
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        progressBar.setVisible(false);
        labelForPB.setVisible(false);
    }

    //checks if input is integer
    public boolean checkIfnumber(String a, String b, String c) {
        try {
            Integer.parseInt(a);
            Integer.parseInt(b);
            Integer.parseInt(c);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
