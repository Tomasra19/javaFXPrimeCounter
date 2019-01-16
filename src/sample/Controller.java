package sample;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;


public class Controller {
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
    public void initialize() {
        buttonStart.setDisable(true);
        buttonCancel.setDisable(true);
    }
    //numbers only
//    firstNumberInput.textProperty().addListener(new ChangeListener<String>() {
//        @Override
//        public void changed(ObservableValue<? extends String> observable, String oldValue,
//                String newValue) {
//            if (!newValue.matches("\\d*")) {
//                textField.setText(newValue.replaceAll("[^\\d]", ""));
//            }
//        }
//    });


    @FXML
    public void handleKeyReleased() {
        String input1 = firstNumberInput.getText();
        String input2 = lastNumberInput.getText();
        String input3 = multiNumberInput.getText();
//        int intinput1 = Integer.parseInt(firstNumberInput.getText());
//        int intinput2 = Integer.parseInt(lastNumberInput.getText());
//        int intinput3 = Integer.parseInt(multiNumberInput.getText());

        boolean isempty1 = input1.isEmpty() || input1.trim().isEmpty();
        boolean isempty2 = input2.isEmpty() || input2.trim().isEmpty();
        boolean isempty3 = input3.isEmpty() || input3.trim().isEmpty();
        boolean disableButtons = isempty1 || isempty2 || isempty3;
        buttonStart.setDisable(disableButtons);
        buttonCancel.setDisable(disableButtons);
    }


    public void count(ActionEvent event) {

        int firstNumber = Integer.parseInt(firstNumberInput.getText());
        int lastNumber = Integer.parseInt(lastNumberInput.getText());
        int multiNumber = Integer.parseInt(multiNumberInput.getText());

//        DataProcessingRunnable.Count(intinput1,intinput2,intinput3);
        Thread startCounting = new Thread(new DataProcessingRunnable(firstNumber,lastNumber,multiNumber));

        if (!startCounting.isAlive()) {
            startCounting.start();
        } else {
            startCounting.interrupt();
            startCounting.start();
        }

    }

    public void cancelCounting() {
        System.out.println("cancel");
    }
}
