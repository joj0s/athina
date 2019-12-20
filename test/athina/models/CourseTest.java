/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package athina.models;

import athina.Data;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jojos
 */
public class CourseTest {
    
    private static Course instance ;
    private Data data;

    public CourseTest() {
    }

    
    @Before
    public void setUp() {
        instance = new Course("123-Θ", "Μαθηματικά 1", 6, 1, new Professor("gkeis", "123","Giannis", "Keis"));
        Data data = new Data();
    }

    /**
     * Test of getCurrentRegistrations method, of class Course.
     */
    @Test
    public void testGetCurrentRegistrations() {
        System.out.println("getCurrentRegistrations");
        
        Student s1 = new Student("pgikas", "123", "Pavlos", "Gikas", 3 , new Date());
        Student s2 = new Student("pgikas", "123", "Pavlos", "Gikas", 3 , new Date());
        
        Course course2 = new Course("113-Θ", "Μαθηματικά 2", 6, 1, new Professor("gkeis", "123","Giannis", "Keis"));
        
        data.registrations[0] = new CourseRegistration(s1, instance, "2018-19 XEIM",LocalDate.now());
        data.registrations[1] = new CourseRegistration(s2, instance, "2018-19 XEIM",LocalDate.now());
        data.registrations[2] = new CourseRegistration(s2, course2, "2018-19 XEIM",LocalDate.now());
        
        int expSize = 2;
        CourseRegistration expReg1 = data.registrations[0];
        CourseRegistration expReg2 = data.registrations[1];
        
        assertEquals(expSize, instance.getCurrentRegistrations().size());
        assertEquals(expReg1, instance.getCurrentRegistrations().get(0));
        assertEquals(expReg2, instance.getCurrentRegistrations().get(1));

    }

    
}
