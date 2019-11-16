/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package athina;

/**
 *
 * @author jojos
 */
public class Professor extends User{
    
    private Course[] coursesTaught;
    
    public Professor(String username,String password, String firstName, String lastName) {
        super(username, password, firstName, lastName);
    }

    public Course[] getCoursesTaught() {
        return coursesTaught;
    }

    public void setCoursesTaught(Course[] coursesTaught) {
        this.coursesTaught = coursesTaught;
    }
    
    
}
