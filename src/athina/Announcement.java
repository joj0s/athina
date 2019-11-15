/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package athina;

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

    public Announcement(int id, String title, String body, Date datePublished, User author) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.datePublished = datePublished;
        this.author = author;
    }
    
    
}
