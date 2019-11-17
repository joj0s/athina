/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package athina;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
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
    
    private ObservableList<Course> selected = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        availableCoursesSemester.setCellValueFactory(new PropertyValueFactory<Course,Integer>("semester"));
        availableCoursesCredits.setCellValueFactory(new PropertyValueFactory<Course,Integer>("credits"));
        availableCoursesName.setCellValueFactory(new PropertyValueFactory<Course,String>("name"));
        selectedCoursesSemester.setCellValueFactory(new PropertyValueFactory<Course,Integer>("semester"));
        selectedCoursesCredits.setCellValueFactory(new PropertyValueFactory<Course,Integer>("credits"));
        selectedCoursesName.setCellValueFactory(new PropertyValueFactory<Course,String>("name"));
        
        addAllCoursesToTable();
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
    
    public void addAllCoursesToTable(){
        ObservableList<Course> courses = FXCollections.observableArrayList();
        courses = fillCourses();
        availableCoursesTable.setItems(courses);
    }
    
    private ObservableList<Course> fillCourses(){
        ObservableList<Course> courses = FXCollections.observableArrayList();
        int i = 0;
        while(Data.courses[i] != null){
            courses.add(Data.courses[i]);
            i ++; 
        }
        return courses;
    }
    
    public void addButtonPresses(ActionEvent event){
        Course select = availableCoursesTable.getSelectionModel().getSelectedItem();
        if (!selected.contains(select)){
            selected.add(select);
        }
        selectedCoursesTable.setItems(selected);
    }
    
    public void removeDoubleClick(ActionEvent event){
        Course select = selectedCoursesTable.getSelectionModel().getSelectedItem();
        selected.remove(select);
        selectedCoursesTable.setItems(selected);
    }
    
    public void registerCourses(ActionEvent event){
        Student student = (Student) Athina.user;
        ObservableList<Course> selected = FXCollections.observableArrayList();
        selected = selectedCoursesTable.getItems();
        for(int i=0;i<= Data.registrations.length-1;i++){
            if(selected.isEmpty()){
                break;
            }else{
                if(Data.registrations[i] == null){
                    Data.registrations[i] = new CourseRegistration(student,selected.get(0), "2018-19 XEIM");
                    selected.remove(0);      
                }
            }
          
        }
        int c = 0;
        for(int i =0;i<= Data.registrations.length-1;i++){
            if(Data.registrations[i] != null){
                c++;
            }
        }
        System.out.println(c);
        success.setText("Επιτυχημένη Δήλωση");
    }
}
