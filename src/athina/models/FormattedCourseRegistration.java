/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package athina.models;

/**
 *
 * @author jojos
 */
public class FormattedCourseRegistration {
    
    private String courseName;
    private int courseSemester;
    private String username;
    private String surname;
    private String name;
    private float grade;
    private String message="test";

    public FormattedCourseRegistration(String username, String surname, String name, float grade) {
        this.username = username;
        this.surname = surname;
        this.name = name;
        this.grade = grade;
        this.message = grade>=5?"Επιτυχία":"Αποτυχία";

    }

    public FormattedCourseRegistration(String courseName, int courseSemester, float grade) {
        this.courseName = courseName;
        this.courseSemester = courseSemester;
        this.grade = grade;
        this.message = grade>=5?"Επιτυχία":"Αποτυχία";
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCourseSemester() {
        return courseSemester;
    }
   

    public String getUsername() {
        return username;
    }

    public String getSurname() {
        return surname;
    }

    public String getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }

    public float getGrade() {
        return grade;
    }
    
    
    
}
