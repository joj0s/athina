/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package athina;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jojos
 */
public class RegisterUserSceneController implements Initializable {

    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField fnameField;
    @FXML
    private TextField lnameField;
    @FXML
    private ComboBox roleComboBox;
    @FXML
    private Label errorLabel;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        roleComboBox.getItems().addAll("admin","professor","student");
    }    
    
    public void registerButtonPressed() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String fname = fnameField.getText();
        String lname = lnameField.getText();
        boolean roleSelected = roleComboBox.getSelectionModel().isEmpty();
        
        if (username.isEmpty() || password.isEmpty() || fname.isEmpty() || lname.isEmpty() || roleSelected){
            errorLabel.setText("Συμπληρώστε όλα τα πεδία");
            return;
        }
        
        String role = roleComboBox.getSelectionModel().getSelectedItem().toString();
        switch (role) {
            case "admin": 
                Data.insertAdmin(new Admin(username, password, fname, lname));
                errorLabel.setText("Επιτυχής εγγραφή");
                break;
            case "student":
                Data.insertStudent(new Student(username, password, fname, lname, 1, new Date()));
                errorLabel.setText("Επιτυχής εγγραφή");
                break;
            case "professor":
                Data.insertProfessor(new Professor (username, password, fname, lname));
                errorLabel.setText("Επιτυχής εγγραφή");
                break;
        }
            
    }
    
    public void backButtonPressed(ActionEvent event) {
        try{
            Scene loginScene = new Scene (FXMLLoader.load(getClass().getResource("MasterScene.fxml")));
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(loginScene);
            window.setResizable(false);
            window.setTitle("Athina");
            window.show();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
}
