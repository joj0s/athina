/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package athina;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
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
    
    static CourseRegistration selectedRegistration;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    public void backButtonPressed(ActionEvent event) {
        try{
            EditGradeSceneController.selectedRegistration = selectedRegistration;
            Scene scene = new Scene (FXMLLoader.load(getClass().getResource("AdminGradingScene.fxml")));
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.setTitle("Athina - Εισαγωγή Βαθμού");
            window.show();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void submitButtonPressed() {
        
        if (gradeField.getText().isEmpty()){
            errorLabel.setText("Δεν έχετε βάλει βαθμό");
            return;
        }
        
        try{
            float value = Float.parseFloat(gradeField.getText());
            if (value>10 || value<0){
                errorLabel.setText("Λάθος αριθμός");
                return;
            }
            selectedRegistration.setGrade(value);
            errorLabel.setText("Επιτυχής εισαγωγή βαθμού");
        }
        catch(NumberFormatException e){
            errorLabel.setText("Δεν εχετε βάλει αριθμό");
        }
        
        
    }
    
}
