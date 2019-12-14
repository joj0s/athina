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
public class Course {
    
    private String id;
    private String name;
    private int credits;
    private int semester;
    private Professor professor;
    private Course preRequisit;
    
    public Course(String id, String name, int credits, int semester, Professor professor) {
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.semester = semester;
        this.professor = professor;
    }
    
    public Course(String id, String name, int credits, int semester, Professor professor,Course preRequisit) {
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.semester = semester;
        this.professor = professor;
        this.preRequisit = preRequisit;
    }
    
    
    public ArrayList<CourseRegistration> getCurrentRegistrations() {
        int i = 0;
        ArrayList<CourseRegistration> registrations = new ArrayList<>();
        
        while(Data.registrations[i] != null){
            if (Data.registrations[i].getCourse() == this){
                registrations.add(Data.registrations[i]);
            }
                
            i++;
        }
        
        return registrations;
    }
    
    

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
//
//    public String getName() {
//        return name;
//    }

    public int getCredits() {
        return credits;
    }

    public int getSemester() {
        return semester;
    }
    
    public void setPreRequisit(Course course) {
        this.preRequisit = course;
    }
    
    public Course getPreRequisit() {
        return preRequisit;
    }

    public Professor getProfessor() {
        return professor;
    }
    
    @Override
    public String toString() {
        return name;
    }
    
    
}