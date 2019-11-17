/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package athina;

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

    @Override
    public String toString() {
        return "Professor{" + "coursesTaught=" + coursesTaught + '}';
    }
    
    
}
