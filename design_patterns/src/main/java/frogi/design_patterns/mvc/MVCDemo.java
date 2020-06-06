package frogi.design_patterns.mvc;

/**
 * Example of MVC pattern
 * @author Frogi
 */
public class MVCDemo {
    public static void main(String[] args){
        // Create Model View Controller
        DiceModel model = new DiceModel();
        DiceView view = new DiceView();
        DiceController controller = new DiceController(model, view);
        
        // Use model
        controller.rollK100();
        // Update view
        controller.updateView();
        controller.rollK20();
        controller.updateView();
    }
}
