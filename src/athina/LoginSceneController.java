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
        
        if (username.isEmpty() || password.isEmpty() ){
            errorLabel.setText("Συμπληρώστε όλα τα πεδία");
            return;
        }
        
        login(username, password, event);
    }
    
    private void login(String username, String password, ActionEvent event){
                
        for (Professor p : Data.professors){
            if(p != null) {
                if (p.getUsername().equals(username) && p.getPassword().equals(password)){
                    Athina.user = p;
                    goToMaster(event);
                    return;
                }
            }
        }
        
        for (Student s : Data.students){
            if(s != null) {
                if (s.getUsername().equals(username) && s.getPassword().equals(password)){
                    Athina.user = s;
                    goToMaster(event);
                    return;
                }
            }
        }
        
        for (Admin a : Data.admins){
            if(a != null) {
                if (a.getUsername().equals(username) && a.getPassword().equals(password)){
                    Athina.user = a;
                    goToMaster(event);
                    return;
                }
            }   
        }
        
        errorLabel.setText("Τα στοιχεία δεν είναι σωστά");
        
        
    }
    
    public void goToMaster(ActionEvent event) {
        try{
            Scene scene = new Scene (FXMLLoader.load(getClass().getResource("MasterScene.fxml")));
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.setResizable(false);
            window.setTitle("Athina");
            window.show();
        }
        catch(IOException e){
            e.printStackTrace();
        }
            
    }
}
