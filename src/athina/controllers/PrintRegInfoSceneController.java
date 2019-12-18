/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package athina.controllers;

import athina.formatters.FormattedRegInfo;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
public class PrintRegInfoSceneController implements Initializable {

    
    @FXML
    private TableView regInfoTable;
    @FXML
    private TableColumn<FormattedRegInfo, String> regTypeColumn;
    @FXML
    private TableColumn<FormattedRegInfo, String> regUsernameColumn;
    @FXML
    private TableColumn<FormattedRegInfo, String> regCourseColumn;
    @FXML
    private TableColumn<FormattedRegInfo, LocalDate> regDateColumn; 
    @FXML
    private TableColumn<FormattedRegInfo, LocalDate> regExamDateColumn; 
    @FXML
    private TableColumn<FormattedRegInfo, Float> regGradeColumn; 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        regTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        regUsernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        regCourseColumn.setCellValueFactory(new PropertyValueFactory<>("course"));
        regDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        regExamDateColumn.setCellValueFactory(new PropertyValueFactory<>("examDate"));
        regGradeColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));
        
        printAllRegInfo();

    }   
    
    private void printAllRegInfo() {
        int i = 0;
        ObservableList<FormattedRegInfo> list = FXCollections.observableArrayList();

        while (athina.Data.registrations[i] != null)
        {
            String username = athina.Data.registrations[i].getStudent().getUsername();
            String courseId = athina.Data.registrations[i].getCourse().getId();
            LocalDate date = athina.Data.registrations[i].getDateRegistered();
            if (athina.Data.registrations[i].getDateExamined() != null) 
            {
                LocalDate examDate = athina.Data.registrations[i].getDateExamined();
                float grade = athina.Data.registrations[i].getGrade();
                list.add(new FormattedRegInfo("Βαθμολογία",username, courseId, date,examDate, grade));
            }
            else
            {
                list.add(new FormattedRegInfo("Δήλωση",username, courseId, date));
            }
            i++;            
        }
        
        regInfoTable.setItems(list);
    }
    
    public void backButtonPressed(ActionEvent event) {
        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/athina/views/MasterScene.fxml")));
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.setTitle("Athina");
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }    
}
