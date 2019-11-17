/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package athina;

import java.io.IOException;
import java.net.URL;
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

    private Course[] availableCourses;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        if (Athina.user instanceof Professor) {
            searchProfessorCourses();
            ObservableList<String> list = FXCollections.observableArrayList();
            for (Course course : availableCourses) {
                list.add(course.getName());
            }
            courseComboBox.setItems(list);
            courseComboBox.setValue(list.get(0));
        } else {
            pane.getChildren().remove(courseComboBox);
            pane.getChildren().remove(courseLabel);

        }
    }

    @FXML
    private void post(ActionEvent event) {
        String title = titleTextField.getText();
        String body = bodyTextArea.getText();

        Data.announcementsCounter++;
        String aboutCourse = courseComboBox.getValue();

        if (title.isEmpty() || body.isEmpty()) {
            statusLabel.setText("Συμπληρώστε όλα τα κενά πεδία.");
        } else {
            if (Athina.user instanceof Professor) {
                Data.announcements[Data.announcementsCounter] = new Announcement(1, title, body, new Date(), Athina.user, findCourse(aboutCourse));
            } else {
                Data.announcements[Data.announcementsCounter] = new Announcement(1, title, body, new Date(), Athina.user);
            }
            statusLabel.setText("ΕΠΙΤΥΧΙΑ.");
        }


    }

    private void searchProfessorCourses() {
        String username = Athina.user.getUsername();

        for (Professor professor : Data.professors) {
            try {
                if (professor.getUsername().equals(username)) {
                    availableCourses = professor.getCoursesTaught();
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
    public void goBack(ActionEvent event) {
        try {
            Scene announcementScene = new Scene(FXMLLoader.load(getClass().getResource("AnnouncementsScene.fxml")));
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
