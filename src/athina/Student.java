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
public class Student extends User{
    
    private int currentSemester;
    private Date dateEnrolled;

    public Student(String username,String password, String firstName,
        String lastName,int currentSemester,Date dateEnrolled) {
        super(username, password, firstName, lastName);
        this.currentSemester = currentSemester;
        this.dateEnrolled = dateEnrolled;
    }

    
    public Course[] getPassedCourses() {
        return null;
    }

    public int getCurrentSemester() {
        return currentSemester;
    }

    public void setCurrentSemester(int currentSemester) {
        this.currentSemester = currentSemester;
    }

    public Date getDateEnrolled() {
        return dateEnrolled;
    }

    public void setDateEnrolled(Date dateEnrolled) {
        this.dateEnrolled = dateEnrolled;
    }
    
    
}
