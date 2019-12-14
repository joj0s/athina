/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package athina.controllers;

import athina.models.Course;
import athina.Data;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author apostoles
 */
public class AdminAddPrerequisitController implements Initializable {

    @FXML
    private ChoiceBox<Course> mainCourseChoices;
    @FXML
    private ChoiceBox<Course> preCourseChoices;
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
    
    private ObservableList<Course> courses = FXCollections.observableArrayList();
    private Course mainCourse;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        statusLabel.setText("");
        for(Course course : Data.getTheoryOnlyCourses()){
            
            if(course == null)
                break;
            
            courses.add(course);
        }
        mainCourseChoices.setItems(courses);
    }
    
    public void filterPreRequisit(){
        ObservableList<Course> preRequisitCourses = FXCollections.observableArrayList();
        mainCourse = mainCourseChoices.getValue();
        for(Course course : Data.getTheoryOnlyCourses()){
            
            if(course == null)
                break;
                        
            if(!course.getId().equals(mainCourse.getId()) && course.getSemester() < mainCourse.getSemester())
                preRequisitCourses.add(course);
        }
        preCourseChoices.setItems(preRequisitCourses);
    }
    
    public void submit(){
        
        if(verify())
        {
            statusLabel.setText("Επιτυχία - σύνδεση προαπαιτούμενου.");
            mainCourse.setPreRequisit(preCourseChoices.getValue());
        }
        else
            statusLabel.setText("Αποτυχία - Ελέγξτε τα στοιχεία.");
    }
    
    public boolean verify(){
        if (mainCourseChoices.getValue() == null || preCourseChoices.getValue() == null)
            return false;
        
        return true;
    }
    
    public void goBack(ActionEvent event) {
        try {
            Scene goBackScene;
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            goBackScene = new Scene(FXMLLoader.load(getClass().getResource("/athina/views/AdminCourseManagementScene.fxml")));
            window.setTitle("Athina");

            window.setScene(goBackScene);
            window.setResizable(false);
            window.setTitle("Announcements");
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
