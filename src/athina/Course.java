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

    public int getId() {
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

    public Professor getProfessor() {
        return professor;
    }
    
    
    
}