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
public class CourseRegistration {
    
    private Student student;
    private Course course;
    private String registrationSemester;
    private float grade;

    public CourseRegistration(Student student, Course course, String registrationSemester) {
        this.student = student;
        this.course = course;
        this.registrationSemester = registrationSemester;
    }

    public Course getCourse() {
        return course;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public float getGrade() {
        return grade;
    }
    
    
    
    
    
}
