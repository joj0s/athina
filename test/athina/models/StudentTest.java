/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package athina.models;

import athina.Athina;
import athina.Data;
import athina.controllers.CourseRegistrationSceneController;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author jojos
 */
public class StudentTest {
    
    private static Student instance ;
    private Data data;
    private Student student;

    public StudentTest() {
    }

    @Before
    public void setUp() throws Exception {
        data = new Data();
        Athina.user = new Student("nnikas", "123","Nikos", "Nikas",3 ,new Date());
        student = (Student) Athina.user;
        instance = student;

    }

    
    /**
     * Test of getRegistrations method, of class Student.
     */
    @Test
    public void testGetRegistrations() {
        System.out.println("getRegistrations");
                
        Course course1 = new Course("01-Θ");
        Course course2 = new Course("02-Θ");
        
        Student student2 = new Student("pgikas", "123", "Pavlos", "Gikas", 3 , new Date());

        data.registrations[0] = new CourseRegistration(student, course1, "2018-19 XEIM",LocalDate.now());
        data.registrations[1] = new CourseRegistration(student2, course2, "2018-19 XEIM",LocalDate.now());

        CourseRegistration expResult = data.registrations[0];
        int expSize = 1;
        assertEquals(expResult, instance.getRegistrations().get(0));
        assertEquals(expSize, instance.getRegistrations().size());
    }

    /**
     * Test of getPassedCourses method, of class Student.
     */
    @Test
    public void testGetPassedCourses() {
        System.out.println("getPassedCourses");
        Course course1 = new Course("01-Θ");
        Course course2 = new Course("02-Θ");
        
        data.registrations[0] = new CourseRegistration(student, course1, "2018-19 XEIM",LocalDate.now());
        data.registrations[1] = new CourseRegistration(student, course2, "2018-19 XEIM",LocalDate.now());
        
        data.registrations[0].setGrade(9);
        data.registrations[1].setGrade(3);
        
        int expSize = 1;
        Course expCourse = course1;
        
        assertEquals(expSize, instance.getPassedCourses().size());
        assertEquals(expCourse, instance.getPassedCourses().get(0));
    }

//    /**
//     * Test of getCurrentSemester method, of class Student.
//     */
//    @Test
//    public void testGetCurrentSemester() {
//        System.out.println("getCurrentSemester");
//        Student instance = null;
//        int expResult = 0;
//        int result = instance.getCurrentSemester();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setCurrentSemester method, of class Student.
//     */
//    @Test
//    public void testSetCurrentSemester() {
//        System.out.println("setCurrentSemester");
//        int currentSemester = 0;
//        Student instance = null;
//        instance.setCurrentSemester(currentSemester);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getDateEnrolled method, of class Student.
//     */
//    @Test
//    public void testGetDateEnrolled() {
//        System.out.println("getDateEnrolled");
//        Student instance = null;
//        Date expResult = null;
//        Date result = instance.getDateEnrolled();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setDateEnrolled method, of class Student.
//     */
//    @Test
//    public void testSetDateEnrolled() {
//        System.out.println("setDateEnrolled");
//        Date dateEnrolled = null;
//        Student instance = null;
//        instance.setDateEnrolled(dateEnrolled);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
