/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package athina;

import athina.models.CourseRegistration;
import athina.models.Professor;
import athina.models.Student;
import athina.models.Course;
import athina.models.Admin;
import athina.models.User;
import athina.models.Announcement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author jojos
 */
public class Data {
    
    public static User[] users;
    public static Student[] students;
    public static Professor[] professors;
    public static Admin[] admins;
    public static Course[] courses;
    public static CourseRegistration[] registrations;
    public static Announcement[] announcements;
    public static int announcementsCounter;
    
    public Data() {
        students = new Student[50];
        professors = new Professor[20];
        admins = new Admin[10];
        courses = new Course[30];
        registrations = new CourseRegistration[50];
        announcements = new Announcement[50];
        announcementsCounter=1;
    }
    
    public void initializeData() {
        students[0] = new Student("ggeorgiou","123", "Giorgos", "Georgiou",3 ,new Date());
        students[1] = new Student("dpliakos","123", "Dimitris", "Pliakos",5 ,new Date());
        students[2] = new Student("nnikas", "123","Nikos", "Nikas",3 ,new Date());
        
        professors[0] = new Professor("kdiamant","123", "Kostas", "Diamantaras");
        professors[1] = new Professor("gtespas", "123","Giorgos", "Tespas");
        professors[2] = new Professor("gkeis", "123","Giannis", "Keis");
        
        
        admins[0] = new Admin("mkalou", "123","Maria", "Kalou");
        
        courses[0] = new Course("123-Θ", "Μαθηματικά 1", 6, 1, professors[2]);
        courses[1] = new Course("156-Θ", "Αρχιτεκτονική HY", 6, 3, professors[0]);
        courses[2] = new Course("083-Ε", "Προγραμματισμός 1 - Ε", 2, 1, professors[1]);
        courses[3] = new Course("083-Θ", "Προγραμματισμός 1", 4, 1, professors[1]);
        
        registrations[0] = new CourseRegistration(students[0], courses[0], "2018-19 XEIM",LocalDate.now());
        registrations[1] = new CourseRegistration(students[1], courses[1], "2018-19 XEIM",LocalDate.now());
        registrations[2] = new CourseRegistration(students[2], courses[2], "2018-19 XEIM",LocalDate.now());
        
        announcements[0] =  new Announcement(1, "Anakoinwsi 1", "Auto einai to swma ths anakoinvwshs", new Date(), admins[0]);
        announcements[1] =  new Announcement(1, "Anakoinwsi Mathimatikwn", "Auto einai to swma ths anakoinvwshs mathimatikwn",
                new Date(), professors[0], courses[0]);
                
    }
    
    public static void insertAdmin(Admin admin) {
        for (int i=0; i<admins.length; i++){
            if (admins[i] == null)
                admins[i] = admin;
        }
    }
    
    public static Course[] getTheoryOnlyCourses() {
        
        Course theoryCourses[]=new Course[30];
        int i =0;
        for(Course course : courses)
        {
            if (course == null)
                break;
            
            String id = course.getId();
            String workshopId = id.substring(0,id.length() - 1) + "Ε";
            if(id.endsWith("Θ"))
                if(!courseExists(workshopId))
                {
                    theoryCourses[i] = course;
                    i++;
                }
                    
        }
        return theoryCourses;
    }
    
    public static Course[] getTheoryCourses() {
        Course theoryCourses[]=new Course[30];
        int i =0;
        for(Course course : courses)
        {
            if (course == null)
                break;
            String id = course.getId();
            if(id.endsWith("Θ"))
                {
                    theoryCourses[i] = course;
                    i++;
                }
        }
        return theoryCourses;
    }
    
    private static boolean courseExists(String id)
    {
        for(Course course : courses)
        {
            if (course == null)
                break;
            
            if (course.getId().equals(id))
                return true;
        }
        return false;
    }
    
    public static void insertProfessor(Professor professor) {
        for (int i=0; i<professors.length; i++){
            if (professors[i] == null)
                professors[i] = professor;
        }
    }
    
    public static void insertStudent(Student student) {
        for (int i=0; i<students.length; i++) {
            if (students[i] == null)
                students[i] = student;
        }
    }
    
    public static void insertCourse(Course course) {
        for (int i=0; i<courses.length; i++) {
            if (courses[i] == null)
            {
                courses[i] = course;
                return;
            }
        }
    }
    
    public static void insertRegistration(Student student, Course course) {
        for(int i=0; i<registrations.length; i++){
            if (registrations[i] == null) {
                registrations[i] = new CourseRegistration(student, course, "2018-19 XEIM",LocalDate.now());
            }
        }
        
    }
}
