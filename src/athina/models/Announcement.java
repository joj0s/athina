/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package athina.models;

import java.util.Date;

/**
 *
 * @author jojos
 */
public class Announcement {

    private int id;
    private String title;
    private String body;
    private Date datePublished;
    private User author;
    private Course aboutCourse;
    private boolean isPublic;

    public Announcement(int id, String title, String body, Date datePublished, User author) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.datePublished = datePublished;
        this.author = author;
        isPublic = true;
    }

    public Announcement(int id, String title, String body, Date datePublished, User author, Course aboutCourse) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.datePublished = datePublished;
        this.author = author;
        this.aboutCourse = aboutCourse;
        isPublic = false;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public Date getDatePublished() {
        return datePublished;
    }

    public User getAuthor() {
        return author;
    }

    public Course getAboutCourse() {
        return aboutCourse;
    }

    public boolean isIsPublic() {
        return isPublic;
    }

}
