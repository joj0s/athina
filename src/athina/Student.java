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

    public Student(String username, String firstName,
            String lastName, String password, int currentSemester,Date dateEnrolled) {
        super(username, firstName, lastName, password);
        this.currentSemester = currentSemester;
        this.dateEnrolled = dateEnrolled;
    }

    
    public Course[] getPassedCourses() {
        return null;
    }
    
}
