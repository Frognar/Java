package frogi.design_patterns.mvc;

/**
 * Class Controller from the MVC design pattern demo.
 * @author Frogi
 */
public class DiceController {
    // MVC model
    private DiceModel model;
    // MVC view
    private DiceView view;
    
    /**
     * DiceController class constructor
     * Assigns model and view to controller
     * @param model - MVC model
     * @param view - MVC view
     */
    public DiceController(DiceModel model, DiceView view){
        this.model = model;
        this.view = view;
    }
    
    /**
     * Uses model's rollK4() method.
     * Simulation of throwing a four-sided dice.
     */
    public void rollK4(){
        this.model.rollK4();
    }
    
    /**
     * Uses model's rollK6() method.
     * Simulation of throwing a six-sided dice.
     */
    public void rollK6(){
        this.model.rollK6();
    }
    
    /**
     * Uses model's rollK8() method.
     * Simulation of throwing a eight-sided dice.
     */
    public void rollK8(){
        this.model.rollK8();
    }
    
    /**
     * Uses model's rollK10() method.
     * Simulation of throwing a ten-sided dice.
     */
    public void rollK10(){
        this.model.rollK10();
    }
    
    /**
     * Uses model's rollK12() method.
     * Simulation of throwing a twelve-sided dice.
     */
    public void rollK12(){
        this.model.rollK12();
    }
    
    /**
     * Uses model's rollK20() method.
     * Simulation of throwing a twenty-sided dice.
     */
    public void rollK20(){
        this.model.rollK20();
    }
    
    /**
     * Uses model's rollK100() method.
     * Simulation of throwing a hundred-sided dice.
     */
    public void rollK100(){
        this.model.rollK100();
    }
    
    
    /**
     * Uses model's getLastRoll() method.
     * @return lastRoll from model - last dice roll result
     */
    public int getLastRoll(){
        return this.model.getLastRoll();
    }
    
    /**
     * Uses view's printDiceRollResult() method.
     * Prints the dice roll result in the console.
     */
    public void updateView(){
        this.view.printDiceRollResult(this.model.getLastRoll());
    }
}
