/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package athina;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.ScrollBar;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author apostoles
 */
public class AnnouncementsController implements Initializable {

    @FXML
    private Label filterLabel;
    @FXML
    private ComboBox<String> filterCombobox;
    @FXML
    private Button makeAnnouncementButton;
    @FXML
    private Button showAnnouncement;
    @FXML
    private TableView announcementTable;
    @FXML
    private TableColumn<FormattedAnnouncement, String> titleColumn;
    @FXML
    private TableColumn<FormattedAnnouncement, String> courseColumn;
    @FXML
    private TableColumn<FormattedAnnouncement, String> dateColumn;
    @FXML
    private ScrollBar announcementsScrollbar;
    @FXML
    private AnchorPane pane;

    public static FormattedAnnouncement selectedAnnouncement;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        if (Athina.user instanceof Student) {
            pane.getChildren().remove(makeAnnouncementButton);
        }
        if (Athina.user == null) {
            pane.getChildren().remove(filterCombobox);
            pane.getChildren().remove(makeAnnouncementButton);
            pane.getChildren().remove(filterLabel);
            showPublicOnly();
        } else {
            showDefaultAnnouncements();
        }

        titleColumn.setCellValueFactory(new PropertyValueFactory<FormattedAnnouncement, String>("title"));
        courseColumn.setCellValueFactory(new PropertyValueFactory<FormattedAnnouncement, String>("course"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<FormattedAnnouncement, String>("date"));
        //add your data to the table here.
        fillFilter(Data.courses);
    }

    private void showDefaultAnnouncements() {

        Announcement[] announcements = Data.announcements;
        ObservableList<FormattedAnnouncement> list = FXCollections.observableArrayList();

        for (Announcement announcement : announcements) {
            try {
                String aboutCourse;
                try {
                    aboutCourse = announcement.getAboutCourse().getName();
                } catch (Exception ex) {
                    aboutCourse = "ΓΕΝΙΚΗ ΑΝΑΚΟΙΝΩΣΗ";
                }
                String title = announcement.getTitle();
                String date = announcement.getDatePublished().toString();
                String body = announcement.getBody();

                list.add(new FormattedAnnouncement(aboutCourse, title, body, date));
            } catch (Exception ex) {
                break;

            }
        }
        announcementTable.setItems(list);
    }

    private void fillFilter(Course[] courses) {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("Όλα");
        for (Course course : courses) {
            try {
                list.add(course.getName());
            } catch (Exception ex) {
                break;
            }
        }

        filterCombobox.setValue("Όλα");
        filterCombobox.setItems(list);
    }

    public void filterTable() {
        String comboboxValue = filterCombobox.getValue();
        Announcement[] announcements = Data.announcements;
        ObservableList<FormattedAnnouncement> list = FXCollections.observableArrayList();

        if (comboboxValue == "Όλα") {
            showDefaultAnnouncements();
            return;
        } else {
            for (Announcement announcement : announcements) {
                try {
                    String aboutCourse;
                    try {
                        aboutCourse = announcement.getAboutCourse().getName();
                    } catch (Exception ex) {
                        aboutCourse = "ΓΕΝΙΚΗ ΑΝΑΚΟΙΝΩΣΗ";
                    }
                    String title = announcement.getTitle();
                    String date = announcement.getDatePublished().toString();
                    String body = announcement.getBody();

                    if (aboutCourse == filterCombobox.getValue()) {
                        list.add(new FormattedAnnouncement(aboutCourse, title, body, date));
                    }
                } catch (Exception ex) {
                    break;
                }
            }
        }
        announcementTable.setItems(list);
    }

    public void showPublicOnly() {
        ObservableList<FormattedAnnouncement> list = FXCollections.observableArrayList();
        Announcement[] announcements = Data.announcements;
        for (Announcement announcement : announcements) {
            try {
                String aboutCourse;
                if (announcement.getAboutCourse() == null) {
                    aboutCourse = "ΓΕΝΙΚΗ ΑΝΑΚΟΙΝΩΣΗ";
                    String title = announcement.getTitle();
                    String date = announcement.getDatePublished().toString();
                    String body = announcement.getBody();
                    list.add(new FormattedAnnouncement(aboutCourse, title, body, date));
                }
            } catch (Exception ex) {
                break;
            }
        }
        announcementTable.setItems(list);

    }

    public void setCurrentAnnouncement() {
        selectedAnnouncement = (FormattedAnnouncement) announcementTable.getSelectionModel().getSelectedItem();
    }

    public void showAnnouncement(ActionEvent event) {
        try {
            setCurrentAnnouncement();
            Scene announcementScene = new Scene(FXMLLoader.load(getClass().getResource("FullAnnouncementScene.fxml")));
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(announcementScene);
            window.setResizable(false);
            window.setTitle(selectedAnnouncement.getTitle());
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void postAnnouncement(ActionEvent event) {
        try {
            setCurrentAnnouncement();
            Scene announcementScene = new Scene(FXMLLoader.load(getClass().getResource("PostAnnouncementScene.fxml")));
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(announcementScene);
            window.setResizable(false);
            window.setTitle("Post Anouncement");
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goBack(ActionEvent event) {
        try {
            Scene goBackScene;
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                goBackScene = new Scene(FXMLLoader.load(getClass().getResource("MasterScene.fxml")));
                window.setTitle("Athina - Login");

            window.setScene(goBackScene);
            window.setResizable(false);
            window.setTitle("Announcements");
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
