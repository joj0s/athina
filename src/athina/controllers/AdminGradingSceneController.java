/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package athina.controllers;

import athina.models.Course;
import athina.models.CourseRegistration;
import athina.Data;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jojos
 */
public class AdminGradingSceneController implements Initializable {

    @FXML
    private Label errorLabel;
    @FXML
    private ComboBox coursePicker;
    @FXML
    private TableView<FormattedCourseRegistration> gradesTable;
    @FXML
    private TableColumn<FormattedCourseRegistration, String> gradesTableUsername;
    @FXML
    private TableColumn<FormattedCourseRegistration, String> gradesTableSurname;
    @FXML
    private TableColumn<FormattedCourseRegistration, String> gradesTableName;
    @FXML
    private TableColumn<FormattedCourseRegistration, Float> gradesTableGrade;
    @FXML
    private TableColumn<FormattedCourseRegistration, LocalDate> gradesTableDate;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gradesTableUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        gradesTableSurname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        gradesTableName.setCellValueFactory(new PropertyValueFactory<>("name"));
        gradesTableGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));
        gradesTableDate.setCellValueFactory(new PropertyValueFactory<>("dateExamined"));
        addAllCoursesToComboBox();
    }    
    
    private void addAllCoursesToComboBox() {
        
        int i = 0;
        while(Data.courses[i] != null){
            coursePicker.getItems().add(Data.courses[i].getName());
            i++;
        }
            
    }
    
    public void courseSelected() {
        int courseIndex = coursePicker.getSelectionModel().getSelectedIndex();
        ArrayList<CourseRegistration> registrationList = Data.courses[courseIndex].getCurrentRegistrations();
        if (registrationList.isEmpty()){
            return;
        }
        populateRegistrationTable(registrationList);
    }
    
    
    private void populateRegistrationTable(ArrayList<CourseRegistration> registrations) {
        
        String username, surname, name;
        LocalDate dateExamined;
        float grade;
        ObservableList<FormattedCourseRegistration> list = FXCollections.observableArrayList();
        
        for (CourseRegistration r: registrations){
            username = r.getStudent().getUsername();
            surname = r.getStudent().getLastName();
            name = r.getStudent().getFirstName();
            grade = r.getGrade();
            dateExamined = r.getDateExamined();
            list.add(new FormattedCourseRegistration(username, surname, name, grade,dateExamined));
        }
        gradesTable.setItems(list);
        
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
    
    
    public void editButtonPressed(ActionEvent event) {
        
        int selectedRow = gradesTable.getSelectionModel().getSelectedIndex();
        
        System.out.println(selectedRow);
        
        if (selectedRow < 0){
            errorLabel.setText("Δεν έχετε κάνει επιλογή");
            return;
        }
        
        Course selectedCourse = Data.courses[coursePicker.getSelectionModel().getSelectedIndex()];
        
        
        Student selectedStudent = null;
        
        for(Student s: Data.students){
            if (s.getUsername().equals(gradesTable.getSelectionModel().getSelectedItem().getUsername())){
               selectedStudent = s;
                break; 
            }
                
        }
        
        
        CourseRegistration selectedRegistration = null;
        for(CourseRegistration r: Data.registrations) {
            if (r != null) {
                if (r.getStudent().equals(selectedStudent) && r.getCourse().equals(selectedCourse)) {
                    selectedRegistration = r;
                    break;
                }    
            }
        }
        
        try{
            EditGradeSceneController.selectedRegistration = selectedRegistration;
            Scene scene = new Scene (FXMLLoader.load(getClass().getResource("/athina/views/EditGradeScene.fxml")));
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.setTitle("Athina - Εισαγωγή Βαθμού");
            window.show();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        
    }
    
    
    
}
