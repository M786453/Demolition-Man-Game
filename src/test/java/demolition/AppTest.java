/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package demolition;

import static java.lang.Thread.sleep;
import static java.time.Duration.ofMinutes;
import static java.time.Duration.ofSeconds;
import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DynamicTest;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 *
 * @author Ahtesham Sarwar
 */
public class AppTest {
    
    public AppTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }

    
    
    @Test
    @Disabled("This is disabled test")
    public void demoTestMethod(){
        
//        assertTrue(false,"This is demo method");
//        Throwable exception = assertThrows(IllegalArgumentException.class,()-> Integer.parseInt("Hello World"));
//        
//        assertEquals("Parameter should be an Integer",exception.getMessage());
        System.out.println(System.getProperty("os.name"));
//        Assumptions.assumeFalse(System.getProperty("os.name").contains("Windows"), "This is windows os");
          
    }
    
    
    @Test
    public void timeoutTesting(){
        
        
        assertTimeout(ofSeconds(1),()-> sleep(100));
        
    }
    
    
    @Test
    public void exceptionTesting(){
        
        
        Throwable exception = assertThrows(IllegalArgumentException.class,()-> Integer.parseInt("hello"));
        
        assertEquals("Parameter must be an Integer",exception.getMessage());
        
    }
    
    
    
    @RepeatedTest(5)
    public void repeatedTestMethod(){
        
        System.out.println("Hello World!");
        
    }
    
    
    /**
     * Test of main method, of class App.
     */
    @Test
    public void testMain() {
        
//        System.out.println("Main Test");
        
//        assertEquals(4,9);



        
    }
    
    
     public static int[][] data() {
        return new int[][] { { 1 , 2, 2 }, { 5, 3, 15 }, { 121, 4, 484 } };
    }

    @ParameterizedTest
    @MethodSource(value =  "data")
    void testWithStringParameter(int[] data) {
        MyClass tester = new MyClass();
        int m1 = data[0];
        int m2 = data[1];
        int expected = data[2];
        assertEquals(expected, tester.multiply(m1, m2));
    }


    /**
     * Test of settings method, of class App.
     */
    @Test
    public void testSettings() {
    }

    /**
     * Test of setup method, of class App.
     */
    @Test
    public void testSetup() {
    }

    /**
     * Test of draw method, of class App.
     */
    @Test
    public void testDraw() {
    }

    /**
     * Test of keyReleased method, of class App.
     */
    @Test
    public void testKeyReleased() {
    }
    
    
     class MyClass {
        public int multiply(int i, int j) {
            return i * j;
        }
    }
    
}



