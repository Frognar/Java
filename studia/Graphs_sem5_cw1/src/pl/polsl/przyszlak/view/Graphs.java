
package pl.polsl.przyszlak.view;

import pl.polsl.przyszlak.model.GraphModel;
import pl.polsl.przyszlak.controller.GraphController;

/**
 * Main Class in project.
 * Store Model, View and Controller.
 * @author Sebastian Przyszlak
 * @version 1.0
 */
public class Graphs {

    /**
     * Main fucnction.
     * Create view, model and controller.
     * Made view visable.
     * @param args the command line arguments
     * No need any params
     */
    public static void main(String[] args) {
        
        GraphView graphView = new GraphView();
        GraphModel graphModel = new GraphModel();
        GraphController graphController = new GraphController(graphView, graphModel);
        
        graphView.setVisible(true);
    }
    
}
