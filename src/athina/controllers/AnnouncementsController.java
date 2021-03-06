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
import athina.formatters.FormattedAnnouncement;
import athina.models.Student;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
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
    private Label radioLabel;
    @FXML
    private Label choiceLabel;
    @FXML
    private ComboBox<String> filterCombobox;
    @FXML
    private Button makeAnnouncementButton;
    @FXML
    private Button showAnnouncement;
    @FXML
    private RadioButton semesterRadio;
    @FXML
    private RadioButton allRadio;
    @FXML
    private RadioButton courseRadio;
    @FXML
    private TableView announcementTable;
    @FXML
    private TableColumn<FormattedAnnouncement, String> titleColumn;
    @FXML
    private TableColumn<FormattedAnnouncement, String> courseColumn;
    @FXML
    private TableColumn<FormattedAnnouncement, String> dateColumn;
    @FXML
    private TableColumn<FormattedAnnouncement, String> semesterColumn;
    @FXML
    private AnchorPane pane;

    public static FormattedAnnouncement selectedAnnouncement;
    private ToggleGroup toggleGroup = new ToggleGroup();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        semesterRadio.setToggleGroup(toggleGroup);
        courseRadio.setToggleGroup(toggleGroup);
        allRadio.setToggleGroup(toggleGroup);
        toggleGroup.selectToggle(allRadio);

        if (Athina.user == null) {
            showPublicOnly();
        } else {
            showDefaultAnnouncements();
        }

        filterUIelements();

        titleColumn.setCellValueFactory(new PropertyValueFactory<FormattedAnnouncement, String>("title"));
        courseColumn.setCellValueFactory(new PropertyValueFactory<FormattedAnnouncement, String>("course"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<FormattedAnnouncement, String>("date"));
        semesterColumn.setCellValueFactory(new PropertyValueFactory<FormattedAnnouncement, String>("semester"));

        //add your data to the table here.
        fillFilter();
    }

    private void showDefaultAnnouncements() {

        Announcement[] announcements = Data.announcements;
        ObservableList<FormattedAnnouncement> list = FXCollections.observableArrayList();

        for (Announcement announcement : announcements) {
            try {
                String[] formattedAnnouncementData = getFormattedAnnouncementData(announcement);
                FormattedAnnouncement formattedAnnouncement = new FormattedAnnouncement(formattedAnnouncementData[0],
                        formattedAnnouncementData[1],
                        formattedAnnouncementData[2],
                        formattedAnnouncementData[3],
                        formattedAnnouncementData[4]);
                list.add(formattedAnnouncement);
            } catch (Exception ex) {
                break;

            }
        }
        announcementTable.setItems(list);
    }

    private String[] getFormattedAnnouncementData(Announcement announcement) {
        String[] data = new String[5];
        try {
            String aboutCourse, semester;
            try {
                aboutCourse = announcement.getAboutCourse().getName();
                semester = Integer.toString(announcement.getAboutCourse().getSemester());

            } catch (Exception ex) {
                aboutCourse = "ΓΕΝΙΚΗ ΑΝΑΚΟΙΝΩΣΗ";
                semester = "ΟΛΑ";

            }
            String title = announcement.getTitle();
            String date = announcement.getDatePublished().toString();
            String body = announcement.getBody();

            data[0] = aboutCourse;
            data[1] = semester;
            data[2] = title;
            data[3] = body;
            data[4] = date;
            return data;
        } catch (Exception ex) {
            return data;
        }
    }

    public void fillFilter() {

        Course[] courses = Data.courses;
        ObservableList<String> list = FXCollections.observableArrayList();
        RadioButton selectedButton = (RadioButton) toggleGroup.getSelectedToggle();
        switch (selectedButton.getText()) {
            case "Χωρίς":
                if (Athina.user != null) {
                    showDefaultAnnouncements();
                }
                updateUIfilters(false);
                return;
            case "Μάθημα":
                list.add("Όλα");
                for (Course course : courses) {
                    try {
                        list.add(course.getName());
                    } catch (Exception ex) {
                        break;
                    }
                }
                updateUIfilters(true);
                filterCombobox.setValue("Όλα");
                filterCombobox.setItems(list);
                break;
            case "Εξάμηνο":
                list.add("Όλα");
                for (Course course : courses) {
                    try {
                        if (!list.contains(Integer.toString(course.getSemester()))) {
                            list.add(Integer.toString(course.getSemester()));
                        }
                    } catch (Exception ex) {
                        break;
                    }
                }
                updateUIfilters(true);
                filterCombobox.setValue("Όλα");
                filterCombobox.setItems(list);
                break;
        }
        filterTable();
    }

    private void filterUIelements() {
        if (Athina.user instanceof Student) {
            pane.getChildren().remove(makeAnnouncementButton);
        }
        if (Athina.user == null) {
            pane.getChildren().remove(filterCombobox);
            pane.getChildren().remove(makeAnnouncementButton);
            pane.getChildren().remove(filterLabel);
            pane.getChildren().remove(courseRadio);
            pane.getChildren().remove(allRadio);
            pane.getChildren().remove(semesterRadio);
            pane.getChildren().remove(choiceLabel);
            pane.getChildren().remove(radioLabel);
            showPublicOnly();
        }
    }

    private void updateUIfilters(boolean show) {
        if (show) {
            if (!pane.getChildren().contains(filterCombobox)) {
                pane.getChildren().add(filterCombobox);
                pane.getChildren().add(choiceLabel);
            }
        } else if (pane.getChildren().contains(filterCombobox)) {
            pane.getChildren().remove(filterCombobox);
            pane.getChildren().remove(choiceLabel);
        }

    }

    public void filterTable() {
        if (Athina.user == null) {
            showPublicOnly();
            return;
        }
        String comboboxValue = filterCombobox.getValue();
        Announcement[] announcements = Data.announcements;
        ObservableList<FormattedAnnouncement> list = FXCollections.observableArrayList();

        if (comboboxValue == "Όλα") {
            showDefaultAnnouncements();
            return;
        } else {
            RadioButton selectedButton = (RadioButton) toggleGroup.getSelectedToggle();

            if (selectedButton.getText().equals("Μάθημα")) {
                for (Announcement announcement : announcements) {
                    try {
                        String[] formattedAnnouncementData = getFormattedAnnouncementData(announcement);
                        FormattedAnnouncement formattedAnnouncement = new FormattedAnnouncement(formattedAnnouncementData[0],
                                formattedAnnouncementData[1],
                                formattedAnnouncementData[2],
                                formattedAnnouncementData[3],
                                formattedAnnouncementData[4]);

                        if (formattedAnnouncementData[0].equals(filterCombobox.getValue())) {
                            list.add(formattedAnnouncement);
                        }
                    } catch (Exception ex) {
                        break;
                    }
                }
            } else {
                for (Announcement announcement : announcements) {
                    try {
                        String[] formattedAnnouncementData = getFormattedAnnouncementData(announcement);
                        FormattedAnnouncement formattedAnnouncement = new FormattedAnnouncement(formattedAnnouncementData[0],
                                formattedAnnouncementData[1],
                                formattedAnnouncementData[2],
                                formattedAnnouncementData[3],
                                formattedAnnouncementData[4]);

                        if (formattedAnnouncementData[1].equals(filterCombobox.getValue())) {
                            list.add(formattedAnnouncement);
                        }
                    } catch (Exception ex) {
                        break;
                    }
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
                String aboutCourse, semester;
                if (announcement.getAboutCourse() == null) {
                    aboutCourse = "ΓΕΝΙΚΗ ΑΝΑΚΟΙΝΩΣΗ";
                    semester = "ΟΛΑ";
                    String title = announcement.getTitle();
                    String date = announcement.getDatePublished().toString();
                    String body = announcement.getBody();
                    list.add(new FormattedAnnouncement(aboutCourse, semester, title, body, date));
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
            Scene announcementScene = new Scene(FXMLLoader.load(getClass().getResource("/athina/views/FullAnnouncementScene.fxml")));
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
            Scene announcementScene = new Scene(FXMLLoader.load(getClass().getResource("/athina/views/PostAnnouncementScene.fxml")));
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
            goBackScene = new Scene(FXMLLoader.load(getClass().getResource("/athina/views/MasterScene.fxml")));
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
