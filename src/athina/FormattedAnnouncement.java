package athina;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author apostoles
 */
public class FormattedAnnouncement {
    private String course;
    private String title;
    private String date;
    private String body;

    public FormattedAnnouncement(String course, String title,String body, String date) {
        this.course = course;
        this.title = title;
        this.date = date;
        this.body = body;
    }

    public String getCourse() {
        return course;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }
    
    public String getBody() {
        return body;
    }
    
    
}
