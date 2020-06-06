package pl.polsl.przyszlak.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import pl.polsl.przyszlak.model.GraphModel;
import pl.polsl.przyszlak.view.GraphView;

/**
 * GraphController
 * Controller in MVC structure.
 * Allow to exchange date between Model and View.
 * @author Sebastian Przyszlak
 * @version 1.2
 */
public class GraphController {

    /** View - to show user the data */
    private GraphView theView;
    
    /** Model - to calculate and process data */
    private GraphModel theModel;
    
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
     * Listener for AddVertexButton.
     * If button was pressed, adds given vertex if label was given by user.
     * Throw exception if text field is empty.
     */
    class VertexListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            String vertexLabel;
            
            try {
                vertexLabel = getTheView().getVertexFieldText();
                
                if(vertexLabel.isEmpty()) {
                    throw new EmptyStringException("Incorrect label");                  // empty string.
                }
                
                getTheModel().getGraph().addVertex(vertexLabel);                        // add new vertex.
                getTheView().setVertexData(getTheModel().getGraph().getVertexData());   // update vertex table.
                getTheModel().getGraphAlgorithm().doAlgorithm(getTheModel().getGraph().getAdjacencyList(), getTheModel().getGraph().getListOfLabels()); // calculate FW shortest path algorthm
                
                if(getTheModel().getGraphAlgorithm().negativeCycle()) {                 // return true if detect negative cycle (distance[A][A] < 0).
                    throw new NegativeCycleException("Negative cycle detctd!");         // negative cycle in graph.
                }
                
                // Prepering data to print in 2d array.  First column is filled with vertices labels.
                String[] columns = new String[getTheModel().getGraph().getListOfLabels().length + 1];
                columns[0] = "\\";
                
                for(int i = 1; i < getTheModel().getGraph().getListOfLabels().length + 1; i++) {
                    columns[i] = getTheModel().getGraph().getListOfLabels()[i - 1];
                }
                
                getTheView().setFWMatrixData(getTheModel().getGraphAlgorithm().getDistanceData(), getTheModel().getGraphAlgorithm().getPathData(), columns);
            } catch(EmptyStringException ex) {
                getTheView().displayErrorMessage(ex.getErrorMessage());
            } catch(NegativeCycleException ex) {
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
    class EdgeListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            String startVertexLabel;
            String endVertexLabel;
            double weight;
            
            try{
                startVertexLabel = getTheView().getStartVertexFieldText();
                endVertexLabel = getTheView().getEndVertexFieldText();
                weight = getTheView().getWeightFieldText();
                
                if(startVertexLabel.isEmpty()) {
                    throw new EmptyStringException("Incorrect label for Start vertex!");    // empty string.
                }
                
                if(endVertexLabel.isEmpty()) {
                    throw new EmptyStringException("Incorrect label for End vertex!");      // empty string.
                }
                
                getTheModel().getGraph().addEdge(startVertexLabel, weight, endVertexLabel);
                getTheView().setVertexData(getTheModel().getGraph().getVertexData());
                getTheView().setEdgeData(getTheModel().getGraph().getEdgesData());
                getTheModel().getGraphAlgorithm().doAlgorithm(getTheModel().getGraph().getAdjacencyList(), getTheModel().getGraph().getListOfLabels()); // calculate FW shortest path algorthm
                
                if(getTheModel().getGraphAlgorithm().negativeCycle()) {                     // return true if detect negative cycle (distance[A][A] < 0).
                    //throw new NegativeCycleException("Negative cycle detctd!");             // negative cycle in graph.
                }
                
                // Prepering data to print in 2d array.  First column is filled with vertices labels.
                String[] columns = new String[getTheModel().getGraph().getListOfLabels().length + 1];
                columns[0] = "\\";
                
                for(int i = 1; i < getTheModel().getGraph().getListOfLabels().length + 1; i++) {
                    columns[i] = getTheModel().getGraph().getListOfLabels()[i - 1];
                }
                
                getTheView().setFWMatrixData(getTheModel().getGraphAlgorithm().getDistanceData(), getTheModel().getGraphAlgorithm().getPathData(), columns);
            } catch(EmptyStringException ex) {
                getTheView().displayErrorMessage(ex.getErrorMessage());
            } catch(IllegalArgumentException ex) {
                getTheView().displayErrorMessage("Incorrect weight!");
            }// catch(NegativeCycleException ex) {
              //  getTheView().displayErrorMessage(ex.getErrorMessage());
           // }
        }
    }
    
    /**
     * Listener for ResetButton.
     * If button was pressed, clear all data.
     */
    class ResetListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            
            try {
                getTheModel().getGraph().reset();
                getTheModel().getGraphAlgorithm().doAlgorithm(new LinkedList<>(), new String[]{});
                getTheView().setVertexData(getTheModel().getGraph().getVertexData());
                getTheView().setEdgeData(getTheModel().getGraph().getEdgesData());                
                getTheView().setFWMatrixData(getTheModel().getGraphAlgorithm().getDistanceData(), getTheModel().getGraphAlgorithm().getPathData(), getTheModel().getGraph().getListOfLabels());
                getTheView().setEndVertexFieldText("");
                getTheView().setStartVertexFieldText("");
                getTheView().setVertexFieldText("");
                getTheView().setWeightFieldText("");
            } catch(Exception ex) {
                getTheView().displayErrorMessage("Reset not completed!");
            }
        }
    }
}
