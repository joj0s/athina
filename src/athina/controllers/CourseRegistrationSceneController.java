/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package athina.controllers;

import athina.Athina;
import athina.models.Course;
import athina.models.CourseRegistration;
import athina.Data;
import athina.models.Student;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author jojos
 */
public class CourseRegistrationSceneController implements Initializable {
    
    @FXML
    private TableView<Course> availableCoursesTable;
    @FXML
    private TableColumn<Course,Integer> availableCoursesSemester;
    @FXML
    private TableColumn<Course,Integer> availableCoursesCredits;
    @FXML
    private TableColumn<Course,String> availableCoursesName;
    @FXML
    private TableView<Course> selectedCoursesTable;
    @FXML
    private TableColumn<Course,String> selectedCoursesName;
    @FXML
    private TableColumn<Course,Integer> selectedCoursesSemester;
    @FXML
    private TableColumn<Course,Integer> selectedCoursesCredits;
    @FXML 
    private Label success;
    
    private int totalCredits = 0;
    private Student student;
    
    private ObservableList<Course> selectedCourses = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        student = (Student) Athina.user;
        availableCoursesSemester.setCellValueFactory(new PropertyValueFactory<Course,Integer>("semester"));
        availableCoursesCredits.setCellValueFactory(new PropertyValueFactory<Course,Integer>("credits"));
        availableCoursesName.setCellValueFactory(new PropertyValueFactory<Course,String>("name"));
        selectedCoursesSemester.setCellValueFactory(new PropertyValueFactory<Course,Integer>("semester"));
        selectedCoursesCredits.setCellValueFactory(new PropertyValueFactory<Course,Integer>("credits"));
        selectedCoursesName.setCellValueFactory(new PropertyValueFactory<Course,String>("name"));        
        addAllCoursesToTable();
    }    
    
    public void setStudent(Student student) {
        this.student = student;
    }
    public void backButtonPressed(ActionEvent event) {
        try{
            Scene loginScene = new Scene (FXMLLoader.load(getClass().getResource("/athina/views/MasterScene.fxml")));
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
    
    public void addAllCoursesToTable(){
        ObservableList<Course> courses = FXCollections.observableArrayList();
        courses = getAvailableCourses();
        availableCoursesTable.setItems(courses);
    }
    
    public ObservableList<Course> getAvailableCourses(){
        ObservableList<Course> courses = FXCollections.observableArrayList();
        Student student = (Student)Athina.user;
        ArrayList<Course> passedCourses = student.getPassedCourses();
        int i = 0;
        while(Data.courses[i] != null){
            if (!passedCourses.contains(Data.courses[i]))
                if(Data.courses[i].getPreRequisit()!=null)
                {
                   if(!passedCourses.contains(Data.courses[i].getPreRequisit()))
                       break;
                }
                courses.add(Data.courses[i]);
            i++; 
        }
        return courses;
    }
    
    public void addButtonPresses(ActionEvent event){
        ObservableList<Course> selected = FXCollections.observableArrayList();
        selected = availableCoursesTable.getSelectionModel().getSelectedItems();
        
        
        if ( selected.size() > 1) {
            success.setText("Μπορείτε να επιλέγετε ένα μάθημα τη φορά");
            return;
        }
                
        Course currentCourse = selected.get(0); 
                
        if (totalCredits + currentCourse.getCredits() > 12) {
            success.setText("Ξεπεράστηκε το όριο ΔΜ");
            return;
        } 
        
        
        if (courseIsLab(currentCourse)){
            if (!courseTheoryIsRegistered(currentCourse)) {
                success.setText("Η αντίστοιχη θεωρία δεν έχει δηλωθεί");
                return;
            }
        }
        
        if (selectedCourses.contains(currentCourse)){
            success.setText("Αυτό το μάθημα έχει ήδη επιλεχθεί");
            return;
        }
        
        selectedCourses.add(currentCourse); 
        totalCredits += currentCourse.getCredits();
        
        selectedCoursesTable.setItems(selectedCourses);
    }
    
    public void removeButtonPressed(ActionEvent event){
        Course removedCourse = selectedCoursesTable.getSelectionModel().getSelectedItem();
        selectedCourses.remove(removedCourse);
        totalCredits -= removedCourse.getCredits();
        selectedCoursesTable.setItems(selectedCourses);
    }
    
    public void registerCourses(ActionEvent event){
       if (selectedCourses.isEmpty()) {
           success.setText("Η δήλωση είναι κενή");
           return;
       }
       
       for(int i=0; i<athina.Data.registrations.length; i++){
           if (selectedCourses.isEmpty())
               break;
           if ( athina.Data.registrations[i] == null ){
               athina.Data.registrations[i] = new CourseRegistration(student,selectedCourses.get(0), "2018-19 XEIM",LocalDate.now());
               selectedCourses.remove(0);
            }
                
        }
       
        success.setText("Επιτυχημένη Δήλωση");
    }
    
    public boolean courseIsLab (Course course) {
        int courseTypeIndex = course.getId().indexOf('-') + 1;
        return course.getId().charAt(courseTypeIndex) == 'Ε' ;
    }
    
    public boolean courseTheoryIsRegistered (Course course) {
        String courseTheoryId = course.getId().substring(0, course.getId().indexOf('-')) + "-Θ";
        ArrayList<CourseRegistration> studentRegistrations = student.getRegistrations();
        
        if(studentRegistrations.isEmpty())
            return false;
        
        for(CourseRegistration reg: studentRegistrations) {
            if (reg.getCourse().getId().equals(courseTheoryId) )
                    return true;         
        }
        
        for(Course c: selectedCourses){
            if (c.getId().equals(courseTheoryId))
                    return true;
        }
        
        return false;
    }
    
 
    
    
}
