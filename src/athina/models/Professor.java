/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package athina.models;

import athina.Data;
import java.util.ArrayList;

/**
 *
 * @author jojos
 */
public class Professor extends User{
    
    private Course[] coursesTaught;
    
    public Professor(String username,String password, String firstName, String lastName) {
        super(username, password, firstName, lastName);
    }

    public ArrayList<Course> getCoursesTaughtList() {
       int i=0;
       ArrayList<Course> courses = new ArrayList<>();
       
       while(Data.courses[i] != null){
            if (Data.courses[i].getProfessor().equals(this))
                courses.add(Data.courses[i]);
            i++;
        }
       
       return courses;
    }

    public void setCoursesTaught(Course[] coursesTaught) {
        this.coursesTaught = coursesTaught;
    }
    
    public void addCoursesTaught(Course course){
        for(int i=0;i<coursesTaught.length;i++)
            if( coursesTaught[i].equals(null) ){
             coursesTaught[i] = course;
             break;
            }   
    }
    
    public boolean hasCourse(Course course){
        for(Course c:coursesTaught)
        {
            if (c.getName().equals(course.getName()))
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return super.getUsername();
    }
    
    
}
