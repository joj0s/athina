/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package athina.controllers;

import athina.Athina;
import athina.models.Admin;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author apostoles
 */
public class AdminCourseManagementController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void addCourses(ActionEvent event) {
        
        try{
            Scene registerScene = new Scene (FXMLLoader.load(getClass().getResource("/athina/views/AdminInsertCourseScene.fxml")));
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(registerScene);
            window.setTitle("Athina - Εγγραφή χρήστη");
            window.show();
        }
        catch(IOException e){
            e.printStackTrace();
        }   
        
    }
    
    public void addPrerequisit(ActionEvent event) {
        
        try{
            Scene registerScene = new Scene (FXMLLoader.load(getClass().getResource("/athina/views/AdminAddPrerequisitScene.fxml")));
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(registerScene);
            window.setTitle("Athina - Εγγραφή χρήστη");
            window.show();
        }
        catch(IOException e){
            e.printStackTrace();
        }   
        
    }
    
    public void goBack(ActionEvent event) {
        
        try{
            Scene scene = null;
            if (Athina.user instanceof Admin)
                scene = new Scene (FXMLLoader.load(getClass().getResource("/athina/views/MasterScene.fxml")));
            else
                scene = new Scene (FXMLLoader.load(getClass().getResource("/athina/views/CourseGradesScene.fxml")));
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.setTitle("Athina - Βαθμολογίες");
            window.show();
        }
        catch(IOException e){
            e.printStackTrace();
        }   
    }
    
}
