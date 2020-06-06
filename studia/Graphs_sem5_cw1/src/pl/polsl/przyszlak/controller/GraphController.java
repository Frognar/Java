package pl.polsl.przyszlak.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import pl.polsl.przyszlak.model.GraphModel;
import pl.polsl.przyszlak.view.GraphView;

/**
 * GraphController
 * Controller in MVC structure.
 * Allow to exchange date between Model and View.
 * @author Sebastian Przyszlak
 * @version 1.1
 */
public class GraphController {

    private GraphView theView;      // view
    private GraphModel theModel;    // model
    
    /**
     * Class constructor with inicjalization.
     * Adds Listener for button from view.
     * @param theView   view
     * @param theModel  model
     */
    public GraphController(GraphView theView, GraphModel theModel){
        this.theView = theView;
        this.theModel = theModel;
        
        this.theView.addVertexListener(new VertexListener());
        this.theView.addEdgeListener(new EdgeListener());
        this.theView.resetListener(new ResetListener());
    }
    
    /**
     * @return the theView
     */
    public GraphView getTheView() {
        return theView;
    }

    /**
     * @param theView the theView to set
     */
    public void setTheView(GraphView theView) {
        this.theView = theView;
    }

    /**
     * @return the theModel
     */
    public GraphModel getTheModel() {
        return theModel;
    }

    /**
     * @param theModel the theModel to set
     */
    public void setTheModel(GraphModel theModel) {
        this.theModel = theModel;
    }
    
    /**
     * Listener for AddVertexButton.
     * If button was pressed, adds given vertex if label was given by user.
     * Throw exception if text field is empty.
     */
    class VertexListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            String vertexLabel;
            
            try{
                vertexLabel = getTheView().getVertexFieldText();
                
                if(vertexLabel.isEmpty()){
                    throw new EmptyStringException("Incorrect label");      // empty string.
                }
                
                getTheModel().addVertex(vertexLabel);                            // add new vertex.
                getTheView().setVertexData(getTheModel().getGraph().getVertexData()); // update vertex table.
                
                
                if(!theModel.algorithmUpdate()){                                            // calculate FW shortest path algorthm, return false if detect negative cycle (distance[A][A] < 0).
                    throw new NegativeCycleException("Negative cycle detctd!");             // negative cycle in graph.
                }
                
                // Prepering data to print in 2d array.
                // First column is filled with vertices labels.
                String[] columns = new String[getTheModel().getGraph().getListOfLabels().length + 1];
                for(int i = 1; i < getTheModel().getGraph().getListOfLabels().length + 1; i++){
                    columns[i] = getTheModel().getGraph().getListOfLabels()[i - 1];
                }
                columns[0] = "\\";
                
                getTheView().setFWMatrixData(getTheModel().getGraphAlgorithm().getDistanceData(), getTheModel().getGraphAlgorithm().getPathData(), columns);
            }
            catch(EmptyStringException ex){
                //Logger.getLogger(GraphController.class.getName()).log(Level.SEVERE, null, ex);
                getTheView().displayErrorMessage(ex.getErrorMessage());
            }
            catch(NegativeCycleException ex) {
                //Logger.getLogger(GraphController.class.getName()).log(Level.SEVERE, null, ex);
                getTheView().displayErrorMessage(ex.getErrorMessage());
            }
        }
    }
    
    /**
     * Listener for AddEdgeButton.
     * If button was pressed, adds edge and needed vertex.
     * Throw exception if any text field is empty
     * and|or weight is not number value.
     */
    class EdgeListener implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            String startVertexLabel;
            String endVertexLabel;
            double weight;
            
            try{
                startVertexLabel = getTheView().getStartVertexFieldText();
                
                if(startVertexLabel.isEmpty()){
                    throw new EmptyStringException("Incorrect label for Start vertex!");    // empty string.
                }
                endVertexLabel = getTheView().getEndVertexFieldText();
                if(endVertexLabel.isEmpty()){
                    throw new EmptyStringException("Incorrect label for End vertex!");      // empty string.
                }
                weight = getTheView().getWeightFieldText();
                
                getTheModel().getGraph().addEdge(startVertexLabel, weight, endVertexLabel);
                getTheView().setVertexData(getTheModel().getGraph().getVertexData());
                getTheView().setEdgeData(getTheModel().getGraph().getEdgesData());
                
                if(!theModel.algorithmUpdate()){                                            // calculate FW shortest path algorthm, return false if detect negative cycle (distance[A][A] < 0).
                    throw new NegativeCycleException("Negative cycle detctd!");             // negative cycle in graph.
                }
                
                // Prepering data to print in 2d array.
                // First column is filled with vertices labels.
                String[] columns = new String[getTheModel().getGraph().getListOfLabels().length + 1];
                for(int i = 1; i < getTheModel().getGraph().getListOfLabels().length + 1; i++){
                    columns[i] = getTheModel().getGraph().getListOfLabels()[i - 1];
                }
                columns[0] = "\\";
                
                getTheView().setFWMatrixData(getTheModel().getGraphAlgorithm().getDistanceData(), getTheModel().getGraphAlgorithm().getPathData(), columns);
            }
            catch(EmptyStringException ex){
                //Logger.getLogger(GraphController.class.getName()).log(Level.SEVERE, null, ex);
                getTheView().displayErrorMessage(ex.getErrorMessage());
            }
            catch(IllegalArgumentException ex){
                //Logger.getLogger(GraphController.class.getName()).log(Level.SEVERE, null, ex);
                getTheView().displayErrorMessage("Incorrect weight!");
            }
            catch(NegativeCycleException ex) {
                //Logger.getLogger(GraphController.class.getName()).log(Level.SEVERE, null, ex);
                getTheView().displayErrorMessage(ex.getErrorMessage());
            }
        }
    }
    
    /**
     * Listener for ResetButton.
     * If button was pressed, clear all data.
     */
    class ResetListener implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            
            try{
                getTheModel().reset();
                getTheView().setVertexData(getTheModel().getGraph().getVertexData());
                getTheView().setEdgeData(getTheModel().getGraph().getEdgesData());                
                getTheView().setFWMatrixData(getTheModel().getGraphAlgorithm().getDistanceData(), getTheModel().getGraphAlgorithm().getPathData(), getTheModel().getGraph().getListOfLabels());
                getTheView().setEndVertexFieldText("");
                getTheView().setStartVertexFieldText("");
                getTheView().setVertexFieldText("");
                getTheView().setWeightFieldText("");
            }
            catch(Exception ex){
                getTheView().displayErrorMessage("Reset not completed!");
            }
        }
    }
}
