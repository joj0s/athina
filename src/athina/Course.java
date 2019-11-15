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
public class Course {
    
    private int id;
    private String name;
    private int credits;
    private int semester;
    private Professor professor;

    public Course(int id, String name, int credits, int semester, Professor professor) {
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.semester = semester;
        this.professor = professor;
    }
    
    public CourseRegistration[] getCurrentRegistrations() {
        return null;
    }
    
}
