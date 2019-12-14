/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package athina.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author apostoles
 */
public class AdminAddPrerequisitController implements Initializable {

    @FXML
    private ChoiceBox<?> mainCourseChoices;
    @FXML
    private ChoiceBox<?> preCourseChoices;
    @FXML
    private Label mainCourseLabel;
    @FXML
    private Label preCourseLabel;
    @FXML
    private Button returnButton;
    @FXML
    private Button submitButton;
    @FXML
    private Label statusLabel;
    @FXML
    private Label sceneTitleLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
