package frogi.design_patterns.mvc;
import java.util.Random;

/**
 * Class Model from the MVC design pattern demo.
 * @author Frogi
 */
public class DiceModel {
    // pseudo-random number generator
    private Random rnd;
    // last dice roll result
    private int lastRoll;
    
    /**
     * DiceModel class constructor.
     * Initializes the pseudo-random number generator
     * and sets last dice roll to 0.
     */
    public DiceModel(){
        this.rnd = new Random();
        this.lastRoll = 0;
    }
    
    /**
     * Simulation of throwing a four-sided dice.
     * Saves result in lastRoll variable.
     */
    public void rollK4(){
        // rnd.nextInt(4) generates numbers from 0 to 3.
        this.lastRoll = rnd.nextInt(4) + 1;
    }
    
    
    /**
     * Simulation of throwing a six-sided dice.
     * Saves result in lastRoll variable.
     */
    public void rollK6(){
        // rnd.nextInt(6) generates numbers from 0 to 5.
        this.lastRoll = rnd.nextInt(6) + 1;
    }
    
    
    /**
     * Simulation of throwing a eight-sided dice.
     * Saves result in lastRoll variable.
     */
    public void rollK8(){
        // rnd.nextInt(8) generates numbers from 0 to 7.
        this.lastRoll = rnd.nextInt(8) + 1;
    }
    
    
    /**
     * Simulation of throwing a ten-sided dice.
     * Saves result in lastRoll variable.
     */
    public void rollK10(){
        // rnd.nextInt(10) generates numbers from 0 to 9.
        this.lastRoll = rnd.nextInt(10) + 1;
    }
    
    
    /**
     * Simulation of throwing a twelve-sided dice.
     * Saves result in lastRoll variable.
     */
    public void rollK12(){
        // rnd.nextInt(12) generates numbers from 0 to 11.
        this.lastRoll = rnd.nextInt(12) + 1;
    }
    
    
    /**
     * Simulation of throwing a twenty-sided dice.
     * Saves result in lastRoll variable.
     */
    public void rollK20(){
        // rnd.nextInt(20) generates numbers from 0 to 19.
        this.lastRoll = rnd.nextInt(20) + 1;
    }
    
    
    /**
     * Simulation of throwing a hundred-sided dice.
     * Saves result in lastRoll variable.
     */
    public void rollK100(){
        // rnd.nextInt(100) generates numbers from 0 to 99.
        this.lastRoll = rnd.nextInt(100) + 1;
    }
    
    /**
     * @return lastRoll - last dice roll result
     */
    public int getLastRoll(){
        return this.lastRoll;
    }
}
