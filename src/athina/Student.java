/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package athina;

import java.util.ArrayList;
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

    
    
    public ArrayList<CourseRegistration> getRegistrations() {
        int i = 0;
        ArrayList<CourseRegistration> currentRegistrations = new ArrayList<>();
        while (Data.registrations[i] != null){
            if(Data.registrations[i].getStudent().equals(this))
                currentRegistrations.add(Data.registrations[i]);
            i++;
        }
        
        return currentRegistrations;
    }
    
    public ArrayList<Course> getPassedCourses() {
        int i = 0;
        ArrayList<Course> passedCourses = new ArrayList<>();
        ArrayList<CourseRegistration> currentRegistrations = this.getRegistrations();
        
        for(CourseRegistration c: currentRegistrations) {
            if (c.getGrade() >= 5)
                passedCourses.add(c.getCourse());
        }
        
        return passedCourses;        
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
