/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package athina.controllers;

import athina.Athina;
import athina.Data;
import athina.models.Course;
import athina.models.CourseRegistration;
import athina.models.Professor;
import athina.models.Student;
import java.time.LocalDate;
import java.util.Date;
import javafx.event.ActionEvent;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apostoles
 */
public class CourseRegistrationSceneControllerTest {

    private static CourseRegistrationSceneController instance ;
    private Data data;

    public CourseRegistrationSceneControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        Data data = new Data();
        instance = new CourseRegistrationSceneController();
        Athina.user = new Student("nnikas", "123","Nikos", "Nikas",3 ,new Date());
    }

    @After
    public void tearDown() {
    }

    /*
    TEST ΤΩΝ ΚΡΙΤΗΡΙΩΝ ΑΠΟΔΟΧΗΣ ΓΙΑ ΤΗΝ ΕΓΓΡΑΦΗ ΜΑΘΗΜΑΤΩΝ
    1. ΤΕΣΤ ΓΙΑ ΤΗ ΣΩΣΤΗ ΕΜΦΑΝΙΣΗ ΤΩΝ ΔΙΑΘΕΣΙΜΩΝ ΜΑΘΗΜΑΤΩΝ
    2. ΤΕΣΤ ΓΙΑ ΤΟΝ ΣΩΣΤΟ ΔΙΑΧΩΡΙΣΜΟ ΕΡΓΑΣΤΗΡΙΩΝ - ΘΕΩΡΙΩΝ
    3. ΤΕΣΤ ΓΙΑ ΤΗ ΣΩΣΤΗ ΑΝΑΓΝΩΡΙΣΗ ΗΔΗ ΕΓΓΕΓΡΑΜΕΝΩΝ ΜΑΘΗΜΑΤΩΝ
    */
    
    
    @Test
    public void testGetAvailableCourses() {
        System.out.println("Test getAvailableCourses");

        instance.setStudent((Student) Athina.user);
        Professor p = new Professor("kdiamant","123", "Kostas", "Diamantaras");
        Course course1 = new Course("123-Θ", "Μαθηματικά 1", 6, 1, p);        
        Course course2 = new Course("156-Θ", "Αρχιτεκτονική HY", 6, 3, p);
        course2.setPreRequisit(course1);

        data.courses[0] = course1;
        data.courses[1] = course2;
                
        int expectedSize = 1;
        Course expectedCourse = course1;
                
        assertEquals(expectedSize, instance.getAvailableCourses().size());
        assertEquals(expectedCourse, instance.getAvailableCourses().get(0));
     }
    
    @Test
    public void testCourseIsLab() {
        System.out.println("Test courseIsLab method");
        Course course = new Course("02-Ε");
        assertTrue(instance.courseIsLab(course));
    }
    
    @Test
    public void testCourseTheoryIsRegistered () {
        System.out.println("Test courseTheoryIsRegistered method");
        Course course = new Course("01-Θ");
        instance.setStudent((Student) Athina.user);
        data.registrations[0] = new CourseRegistration((Student) Athina.user, course, "2018-19 XEIM",LocalDate.now());
        assertTrue(instance.courseTheoryIsRegistered(course));
    }

}