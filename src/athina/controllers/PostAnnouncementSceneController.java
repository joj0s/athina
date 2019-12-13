/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package athina.controllers;

import athina.models.Announcement;
import athina.Athina;
import athina.models.Course;
import athina.Data;
import athina.models.Professor;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import java.util.Date;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author apostoles
 */
public class PostAnnouncementSceneController implements Initializable {

    @FXML
    private TextArea bodyTextArea;
    @FXML
    private TextField titleTextField;
    @FXML
    private ComboBox<String> courseComboBox;
    @FXML
    private Label titleCourse;
    @FXML
    private Label announceMentDateLabel;
    @FXML
    private Label bodyLabel;
    @FXML
    private Button postButton;
    @FXML
    private Button goBackButton;
    @FXML
    private Label statusLabel;
    @FXML
    private Label courseLabel;
    @FXML
    private AnchorPane pane;

    private ArrayList<Course> availableCourses;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        if (Athina.user instanceof Professor) {
            populateDropdown();
        } else {
            pane.getChildren().remove(courseComboBox);
            pane.getChildren().remove(courseLabel);

        }
    }
    
    private void populateDropdown(){
         searchProfessorCourses();
            ObservableList<String> list = FXCollections.observableArrayList();
            for (Course course : availableCourses) {
                list.add(course.getName());
            }
            courseComboBox.setItems(list);
            courseComboBox.setValue(list.get(0));
    }

    @FXML
    private void post(ActionEvent event) {
        if (validateFields()) {
            String title = titleTextField.getText();
            String body = bodyTextArea.getText();
            String aboutCourse = courseComboBox.getValue();
            Data.announcementsCounter++;
            if (Athina.user instanceof Professor) {
                Data.announcements[Data.announcementsCounter] = new Announcement(1, title, body, new Date(), Athina.user, findCourse(aboutCourse));
            } else {
                Data.announcements[Data.announcementsCounter] = new Announcement(1, title, body, new Date(), Athina.user);
            }
        }
    }

    private boolean validateFields() {
        String title = titleTextField.getText();
        String body = bodyTextArea.getText();
        if (title.isEmpty() || body.isEmpty()) {
            statusLabel.setText("Συμπληρώστε όλα τα κενά πεδία.");
            return false;
        } else {
            statusLabel.setText("ΕΠΙΤΥΧΙΑ.");
            return true;
        }
    }

    private void searchProfessorCourses() {
        String username = Athina.user.getUsername();

        for (Professor professor : Data.professors) {
            try {
                if (professor.getUsername().equals(username)) {
                    availableCourses = professor.getCoursesTaughtList();
                    return;
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    private Course findCourse(String name) {
        for (Course course : Data.courses) {
            if (course.getName().equals(name)) {
                return course;
            }
        }
        return null;
    }

    @FXML
    private void goBack(ActionEvent event) {
        try {
            Scene announcementScene = new Scene(FXMLLoader.load(getClass().getResource("/athina/views/AnnouncementsScene.fxml")));
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(announcementScene);
            window.setResizable(false);
            window.setTitle("Announcements");
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
