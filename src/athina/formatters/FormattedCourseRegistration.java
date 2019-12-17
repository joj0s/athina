/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package athina.formatters;

import java.time.LocalDate;
import java.util.Date;

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
    private LocalDate dateExamined;
    private LocalDate dateRegistered;

    public FormattedCourseRegistration(String username, String surname, String name, float grade,LocalDate dateExamined) {
        this.username = username;
        this.surname = surname;
        this.name = name;
        this.grade = grade;
        this.dateExamined = dateExamined;
    }

    public FormattedCourseRegistration(String courseName, int courseSemester, float grade,LocalDate dateExamined) {
        this.courseName = courseName;
        this.courseSemester = courseSemester;
        this.grade = grade;
        this.dateExamined = dateExamined;
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

    public String getName() {
        return name;
    }

    public float getGrade() {
        return grade;
    }

    public LocalDate getDateExamined() {
        return dateExamined;
    }

    public LocalDate getDateRegistered() {
        return dateRegistered;
    }


    

}
