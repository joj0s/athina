/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package athina.controllers;

import athina.models.FormattedAnnouncement;
import static athina.controllers.AnnouncementsController.selectedAnnouncement;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author apostoles
 */

 

public class FullAnnouncementSceneController implements Initializable {

     @FXML
    private Text announcementBody;
    @FXML
    private Label announcementDateLabel;
    @FXML
    private Label announcementTitleLabel;
    @FXML
    private Button goBackButton;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setAnnouncement(AnnouncementsController.selectedAnnouncement);
    }
    
    private void setAnnouncement(FormattedAnnouncement announcement){
        announcementDateLabel.setText(announcement.getDate());
        announcementBody.setText(announcement.getBody());
        announcementTitleLabel.setText(announcement.getTitle());
    }
    
    public void goBack(ActionEvent event){
        try{
            Scene announcementScene = new Scene (FXMLLoader.load(getClass().getResource("/athina/views/AnnouncementsScene.fxml")));
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(announcementScene);
            window.setResizable(false);
            window.setTitle("Announcements");
            window.show();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
