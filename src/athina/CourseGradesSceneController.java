/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package athina;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       gradesTableCourse.setCellValueFactory(new PropertyValueFactory<>("courseName"));
       gradesTableSemester.setCellValueFactory(new PropertyValueFactory<>("courseSemester"));
       gradesTableGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));
       
       Student thisStudent = (Student)Athina.user;
       ArrayList<CourseRegistration> registrations = thisStudent.getRegistrations();
       gradesTable.setItems(formatRegistrations(registrations));
    }    
    
    private ObservableList<FormattedCourseRegistration> formatRegistrations (ArrayList<CourseRegistration> registrations) {
        
        ObservableList<FormattedCourseRegistration> list = FXCollections.observableArrayList();
        String name = "";
        int semester = 0;
        float grade = 0;
        
        for(CourseRegistration r: registrations){
            name = r.getCourse().getName();
            semester = r.getCourse().getSemester();
            grade = r.getGrade();
            list.add(new FormattedCourseRegistration(name, semester, grade));
        }
        
        return list;
    }
    
    
    public void backButtonPressed() {
        
    }
}
