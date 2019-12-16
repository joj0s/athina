/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package athina.controllers;

import athina.Athina;
import athina.models.CourseRegistration;
import athina.models.FormattedCourseRegistration;
import athina.models.Student;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jojos
 */

public class CourseGradesSceneController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<FormattedCourseRegistration> gradesTable;
    @FXML
    private TableColumn<FormattedCourseRegistration, String> gradesTableCourse;
    @FXML
    private TableColumn<FormattedCourseRegistration, Integer> gradesTableSemester;
    @FXML
    private TableColumn<FormattedCourseRegistration, Float> gradesTableGrade;
    @FXML
    private TableColumn<FormattedCourseRegistration, LocalDate> gradesTableDateExamined;
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       gradesTableCourse.setCellValueFactory(new PropertyValueFactory<>("courseName"));
       gradesTableSemester.setCellValueFactory(new PropertyValueFactory<>("courseSemester"));
       gradesTableGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));
       gradesTableDateExamined.setCellValueFactory(new PropertyValueFactory<>("dateExamined"));
       
       Student thisStudent = (Student)Athina.user;
       ArrayList<CourseRegistration> registrations = thisStudent.getRegistrations();
       gradesTable.setItems(formatRegistrations(registrations));
       gradesTable.getSortOrder().add(gradesTableSemester);
    }    
    
    private ObservableList<FormattedCourseRegistration> formatRegistrations (ArrayList<CourseRegistration> registrations) {
        
        ObservableList<FormattedCourseRegistration> list = FXCollections.observableArrayList();
        String name = "";
        int semester = 0;
        float grade = 0;
        LocalDate dateExamined;
        
        for(CourseRegistration r: registrations){
            name = r.getCourse().getName();
            semester = r.getCourse().getSemester();
            grade = r.getGrade();
            dateExamined = r.getDateExamined();
            list.add(new FormattedCourseRegistration(name, semester, grade, dateExamined));
        }
        
        return list;
    }
    
    
    public void backButtonPressed(ActionEvent event) {
        try{
            Scene scene = new Scene (FXMLLoader.load(getClass().getResource("/athina/views/MasterScene.fxml")));
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
