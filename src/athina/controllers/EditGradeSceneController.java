/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package athina.controllers;

import athina.models.CourseRegistration;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jojos
 */
public class EditGradeSceneController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField gradeField;
    @FXML
    private Label errorLabel;
    @FXML
    private DatePicker datepicker;

    static CourseRegistration selectedRegistration;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void backButtonPressed(ActionEvent event) {
        try {
            EditGradeSceneController.selectedRegistration = selectedRegistration;
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/athina/views/AdminGradingScene.fxml")));
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.setTitle("Athina - Εισαγωγή Βαθμού");
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void submitButtonPressed() {

        if (gradeField.getText().isEmpty()) {
            errorLabel.setText("Δεν έχετε βάλει βαθμό");
            return;
        }

        if (datepicker.getValue() == null) {
            errorLabel.setText("Δεν έχετε βάλει ημερμοηνία.");
            return;
        }

        try {
            float value = Float.parseFloat(gradeField.getText());
            if (value > 10 || value < 0) {
                errorLabel.setText("Λάθος αριθμός");
                return;
            }
            LocalDate dateExamined = datepicker.getValue();
            selectedRegistration.setGrade(value);
            selectedRegistration.setDateExamined(dateExamined);
            errorLabel.setText("Επιτυχής εισαγωγή βαθμού");
        } catch (NumberFormatException e) {
            errorLabel.setText("Δεν εχετε βάλει αριθμό");
        }

    }

}
