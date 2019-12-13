/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package athina.controllers;

import athina.models.Admin;
import athina.Athina;
import athina.models.Professor;
import athina.models.Student;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;

public class MasterSceneController implements Initializable {

    @FXML
    private Label usernameLabel;
    @FXML
    private Button loginButton;
    @FXML
    private Button registrationButton;
    @FXML
    private Button gradesButton;
    @FXML 
    private Button announcementsButton;
    @FXML
    private Button registerButton;
    @FXML
    private Button logOutButton;
    @FXML
    private AnchorPane pane;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (Athina.user instanceof Student) {
            pane.getChildren().remove(loginButton);
            pane.getChildren().remove(registerButton);
            usernameLabel.setText(Athina.user.getUsername());
        }
        else if (Athina.user instanceof Professor) {
            pane.getChildren().remove(registrationButton);
            pane.getChildren().remove(gradesButton);
            pane.getChildren().remove(registerButton);
            pane.getChildren().remove(loginButton);
            usernameLabel.setText(Athina.user.getUsername());
        }
        else if (Athina.user instanceof Admin) {
            pane.getChildren().remove(registrationButton);
            pane.getChildren().remove(loginButton);
            pane.getChildren().remove(registrationButton);
            usernameLabel.setText(Athina.user.getUsername());
        }
        else {
            pane.getChildren().remove(logOutButton);
            pane.getChildren().remove(registrationButton);
            pane.getChildren().remove(gradesButton);
            pane.getChildren().remove(registerButton);
        }
    }    
    
    
        public void loginButtonPressed(ActionEvent event) {
        try{
            Scene loginScene = new Scene (FXMLLoader.load(getClass().getResource("/athina/views/LoginScene.fxml")));
            Athina.user=null;
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(loginScene);
            window.setResizable(false);
            window.setTitle("Athina - Login");
            window.show();
        }
        catch(IOException e){
            e.printStackTrace();
        }    
    }
    
    
    public void logoutButtonPressed(ActionEvent event) {
        try{
            Athina.user=null;
            Scene loginScene = new Scene (FXMLLoader.load(getClass().getResource("/athina/views/LoginScene.fxml")));
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(loginScene);
            window.setResizable(false);
            window.setTitle("Athina - Login");
            window.show();
        }
        catch(IOException e){
            e.printStackTrace();
        }    
    }

    public void announcementsButtonPressed(ActionEvent event) {
        try{
            Scene announcementsScene = new Scene (FXMLLoader.load(getClass().getResource("/athina/views/AnnouncementsScene.fxml")));
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(announcementsScene);
            window.setResizable(false);
            window.setTitle("Announcements");
            window.show();
        }
        catch(IOException e){
            e.printStackTrace();
        }    
    }
    
    public void registerButtonPressed(ActionEvent event) {
        
        try{
            Scene registerScene = new Scene (FXMLLoader.load(getClass().getResource("/athina/views/RegisterUserScene.fxml")));
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(registerScene);
            window.setTitle("Athina - Εγγραφή χρήστη");
            window.show();
        }
        catch(IOException e){
            e.printStackTrace();
        }   
        
    }
    
    public void registerCourse(ActionEvent event){
        
        try{
            Scene courses = new Scene (FXMLLoader.load(getClass().getResource("/athina/views/CourseRegistrationScene.fxml")));
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(courses);
            window.setResizable(false);
            window.setTitle("Athina - Δήλωση Μαθημάτων");
            window.show();
        }
        catch(IOException e){
            e.printStackTrace();
            }  
        }
    
    public void gradesButtonPressed(ActionEvent event) {
        
        try{
            Scene scene = null;
            if (Athina.user instanceof Admin)
                scene = new Scene (FXMLLoader.load(getClass().getResource("/athina/views/AdminGradingScene.fxml")));
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