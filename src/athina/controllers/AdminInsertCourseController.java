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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import athina.Data;
import athina.models.Course;
import athina.models.CourseRegistration;
import athina.models.FormattedAnnouncement;
import athina.models.FormattedCourseRegistration;
import athina.models.Professor;
import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author apostoles
 */
public class AdminInsertCourseController implements Initializable {

    @FXML
    private TextField titleField;
    @FXML
    private Label titleLabel;
    @FXML
    private Label idLabel;
    @FXML
    private TextField idField;
    @FXML
    private CheckBox workshopCheck;
    @FXML
    private Button submitButton;
    @FXML
    private Button returnButton;
    @FXML
    private Text statusLabel;
    @FXML
    private TextField semesterInput;
    @FXML
    private Label idLabel1;
    @FXML
    private Label workshopProfLabel;
    @FXML
    private ChoiceBox<Professor> profChoice;    
    @FXML
    private ChoiceBox<Professor> workshopProfChoice;
    @FXML
    private TextField creditsInput;
    @FXML
    private Label idLabel11;
    @FXML
    private AnchorPane pane;

    private String title;
    private String id;
    private boolean hasWorkshop;
    private int semester;
    private int credits;
    private Professor professor;
    private Professor workshopProfessor;
    private ObservableList<Professor> Professors = FXCollections.observableArrayList();



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        statusLabel.setText("");
        pane.getChildren().remove(workshopProfLabel);
        pane.getChildren().remove(workshopProfChoice);
        
        
        
        for(Professor professor : Data.professors){
            if(professor == null)
                break;
            
            Professors.add(professor);
        }
            profChoice.setItems(Professors);
            workshopProfChoice.setItems(Professors);
    }

    public void showWorkshopInput() {
        if (workshopCheck.isSelected()) {
            pane.getChildren().add(workshopProfLabel);
            pane.getChildren().add(workshopProfChoice);
        }
        if (!workshopCheck.isSelected()) {
            pane.getChildren().remove(workshopProfLabel);
            pane.getChildren().remove(workshopProfChoice);
        }        
    }

    public void submitButtonPressed() {
        if (verifySubmit()) {
            String theoryId = id+"-Θ";
            String theoryTitle = title + " (Θ) ";
            // public Course(String theoryId, String name, int credits, int semester, Professor professor)
            Data.insertCourse(new Course(theoryId,theoryTitle,credits,semester,professor));
            String workshopId = id+"-Ε";
            String workshopTitle = title + " (Ε) ";
            if(hasWorkshop)
                Data.insertCourse(new Course(workshopId,workshopTitle,credits,semester,workshopProfessor));
        }        
    }

    private boolean verifySubmit() {
        try {
            title = titleField.getText();
            id = idField.getText();
            //id should be numbers
            hasWorkshop = workshopCheck.isSelected();
            semester = Integer.parseInt(semesterInput.getText());
            credits = Integer.parseInt(creditsInput.getText());
            professor = profChoice.getValue();
            if(hasWorkshop)
                workshopProfessor = workshopProfChoice.getValue();
        } catch (Exception e) {
            statusLabel.setText("Λάθος στοιχεία.");
            return false;
        }
        if (title.isEmpty() || id.isEmpty() || semester == 0 || credits == 0 || professor==null) {
            statusLabel.setText("Λάθος στοιχεία.");
            return false;
        }
        
        if(hasWorkshop && workshopProfessor==null)
        {
            statusLabel.setText("Λάθος στοιχεία.");
            return false;
        }
        
        statusLabel.setText("Επιτυχής Προσθήκη μαθήματος με id "+id);
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
