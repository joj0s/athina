/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package athina;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author jojos
 */
public class LoginSceneController implements Initializable {
    
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML 
    private Label errorLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void loginButtonPressed(ActionEvent event) {
        
        String username = usernameField.getText();
        String password = passwordField.getText();
        
        if (username == null || password == null ){
            errorLabel.setText("Συμπληρώστε όλα τα πεδία");
            return;
        }
        
        login(username, password, event);
    }
    
    private void login(String username, String password, ActionEvent event){
        
    }
}
