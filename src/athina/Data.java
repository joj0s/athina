/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package athina;

import java.util.Date;

/**
 *
 * @author jojos
 */
public class Data {
    
    public Student[] students;
    public Professor[] professors;
    public Admin[] admins;
    public Course[] courses;
    public CourseRegistration[] registrations;
    public Announcement[] announcements;
    
    public Data() {
        students = new Student[50];
        professors = new Professor[20];
        admins = new Admin[10];
        courses = new Course[30];
        registrations = new CourseRegistration[50];
        announcements = new Announcement[50];
    }
    
    public void initializeData() {
        students[0] = new Student("ggeorgiou", "Giorgos", "Georgiou",3 ,new Date());
        students[1] = new Student("dpliakos", "Dimitris", "Pliakos",5 ,new Date());
        students[2] = new Student("nnikas", "Nikos", "Nikas",3 ,new Date());
        
        professors[0] = new Professor("kdiamant", "Kostas", "Diamantaras");
        professors[1] = new Professor("gtespas", "Giorgos", "Tespas");
        professors[0] = new Professor("gkeis", "Giannis", "Keis");
        
        admins[0] = new Admin("mkalou", "Maria", "Kalou");
        
        courses[0] = new Course(1, "Mathimatika 1", 6, 1, professors[2]);
        courses[1] = new Course(2, "Arxitektonikh HY", 6, 3, professors[0]);
        courses[2] = new Course(3, "Programmatismos", 4, 1, professors[1]);
        
        registrations[0] = new CourseRegistration(students[0], courses[0], "2018-19 XEIM");
        registrations[1] = new CourseRegistration(students[1], courses[1], "2018-19 XEIM");
        registrations[2] = new CourseRegistration(students[2], courses[2], "2018-19 XEIM");
        
        announcements[0] =  new Announcement(1, "Anakoinwsi 1", "Auto einai to swma ths anakoinvwshs", new Date(), admins[0]);
        announcements[0] =  new Announcement(1, "Anakoinwsi Mathimatikwn", "Auto einai to swma ths anakoinvwshs mathimatikwn",
                new Date(), professors[0], courses[0]);
        
    }
}
