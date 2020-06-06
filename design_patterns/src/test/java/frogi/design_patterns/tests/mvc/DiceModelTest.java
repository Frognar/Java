/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frogi.design_patterns.tests.mvc;
import frogi.design_patterns.mvc.DiceModel;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for MVC Model class.
 * @author Frogi
 */
public class DiceModelTest {
    
    DiceModel instance;
    
    /**
     * DiceModelTest class constructor.
     * Create DiceModel class instance.
     */
    public DiceModelTest() {
        this.instance = new DiceModel();
    }

    /**
     * Test of rollK4 method, of class DiceModel.
     */
    @Test
    public void testRollK4() {
        for(int i = 0; i < 10000; i++){
            // Uses model's rollK4() method.
            instance.rollK4();
            // Gets result of that method.
            int result = instance.getLastRoll();
            // Checks if the result has expectet value.
            assertTrue(0 < result && result <= 4);
        }
    }

    /**
     * Test of rollK6 method, of class DiceModel.
     */
    @Test
    public void testRollK6() {
        for(int i = 0; i < 10000; i++){
            // Uses model's rollK6() method.
            instance.rollK6();
            // Gets result of that method.
            int result = instance.getLastRoll();
            // Checks if the result has expectet value.
            assertTrue(0 < result && result <= 6);
        }
    }

    /**
     * Test of rollK8 method, of class DiceModel.
     */
    @Test
    public void testRollK8() {
        for(int i = 0; i < 10000; i++){
            // Uses model's rollK8() method.
            instance.rollK8();
            // Gets result of that method.
            int result = instance.getLastRoll();
            // Checks if the result has expectet value.
            assertTrue(0 < result && result <= 8);
        }
    }

    /**
     * Test of rollK10 method, of class DiceModel.
     */
    @Test
    public void testRollK10() {
        for(int i = 0; i < 10000; i++){
            // Uses model's rollK10() method.
            instance.rollK10();
            // Gets result of that method.
            int result = instance.getLastRoll();
            // Checks if the result has expectet value.
            assertTrue(0 < result && result <= 10);
        }
    }

    /**
     * Test of rollK12 method, of class DiceModel.
     */
    @Test
    public void testRollK12() {
        for(int i = 0; i < 10000; i++){
            // Uses model's rollK12() method.
            instance.rollK12();
            // Gets result of that method.
            int result = instance.getLastRoll();
            // Checks if the result has expectet value.
            assertTrue(0 < result && result <= 12);
        }
    }

    /**
     * Test of rollK20 method, of class DiceModel.
     */
    @Test
    public void testRollK20() {
        for(int i = 0; i < 10000; i++){
            // Uses model's rollK20() method.
            instance.rollK20();
            // Gets result of that method.
            int result = instance.getLastRoll();
            // Checks if the result has expectet value.
            assertTrue(0 < result && result <= 20);
        }
    }

    /**
     * Test of rollK100 method, of class DiceModel.
     */
    @Test
    public void testRollK100() {
        for(int i = 0; i < 10000; i++){
            // Uses model's rollK100() method.
            instance.rollK100();
            // Gets result of that method.
            int result = instance.getLastRoll();
            // Checks if the result has expectet value.
            assertTrue(0 < result || result <= 100);
        }
    }
}
