package pl.polsl.przyszlak.view;

import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * View in MVC structure.
 * Contain all GUI data.
 * All components as fields, labels, table, etc. was generated in design panel (initComponents method).
 * @author Sebastian Przyszzlak
 * @version 1.2
 */
public class GraphView extends JFrame{
    
    private javax.swing.JButton addEdgeButton;
    private javax.swing.JTextField addEdgeEndField;
    private javax.swing.JLabel addEdgeEndLabel;
    private javax.swing.JTextField addEdgeStartField;
    private javax.swing.JLabel addEdgeStartLabel;
    private javax.swing.JTextField addEdgeWeightField;
    private javax.swing.JLabel addEdgeWeightLabel;
    private javax.swing.JButton addVertexButton;
    private javax.swing.JLabel addVertexLabel;
    private javax.swing.JTextField addVertxField;
    private javax.swing.JTable distanceMatrix;
    private javax.swing.JScrollPane distanceMatrixPane;
    private javax.swing.JTable edgeList;
    private javax.swing.JScrollPane edgeListPane;
    private javax.swing.JTable pathMatrix;
    private javax.swing.JScrollPane pathMatrixPane;
    private javax.swing.JButton resetButton;
    private javax.swing.JTable vertexList;
    private javax.swing.JScrollPane vertexListPane;

    /**
     * Creates new form GraphView.
     * Sets headers in tables.
     */
    public GraphView() {
        initComponents();
        setData();
    }
        
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */             
    private void initComponents() {

        distanceMatrixPane = new javax.swing.JScrollPane();
        distanceMatrix = new javax.swing.JTable();
        pathMatrixPane = new javax.swing.JScrollPane();
        pathMatrix = new javax.swing.JTable();
        addVertexLabel = new javax.swing.JLabel();
        addVertxField = new javax.swing.JTextField();
        addVertexButton = new javax.swing.JButton();
        addEdgeStartLabel = new javax.swing.JLabel();
        addEdgeEndLabel = new javax.swing.JLabel();
        addEdgeWeightLabel = new javax.swing.JLabel();
        addEdgeStartField = new javax.swing.JTextField();
        addEdgeEndField = new javax.swing.JTextField();
        addEdgeWeightField = new javax.swing.JTextField();
        addEdgeButton = new javax.swing.JButton();
        vertexListPane = new javax.swing.JScrollPane();
        vertexList = new javax.swing.JTable();
        edgeListPane = new javax.swing.JScrollPane();
        edgeList = new javax.swing.JTable();
        resetButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        distanceMatrixPane.setPreferredSize(new java.awt.Dimension(452, 452));

        distanceMatrix.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        distanceMatrix.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        distanceMatrix.setAutoscrolls(false);
        distanceMatrix.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        distanceMatrix.setFocusable(false);
        distanceMatrix.setMinimumSize(new java.awt.Dimension(135, 0));
        distanceMatrix.setRowSelectionAllowed(false);
        distanceMatrix.getTableHeader().setResizingAllowed(false);
        distanceMatrix.getTableHeader().setReorderingAllowed(false);
        distanceMatrixPane.setViewportView(distanceMatrix);

        pathMatrix.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        pathMatrix.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        pathMatrix.setFocusable(false);
        pathMatrix.setRowSelectionAllowed(false);
        pathMatrixPane.setViewportView(pathMatrix);

        addVertexLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        addVertexLabel.setLabelFor(addVertxField);
        addVertexLabel.setText("Vertex label");

        addVertexButton.setText("Add");

        addEdgeStartLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        addEdgeStartLabel.setLabelFor(addEdgeStartField);
        addEdgeStartLabel.setText("Start vertex label");

        addEdgeEndLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        addEdgeEndLabel.setLabelFor(addEdgeEndField);
        addEdgeEndLabel.setText("End vertex label");

        addEdgeWeightLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        addEdgeWeightLabel.setLabelFor(addEdgeWeightField);
        addEdgeWeightLabel.setText("Weight");

        addEdgeButton.setText("Add");

        vertexList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        vertexList.setFocusable(false);
        vertexList.setRowSelectionAllowed(false);
        vertexListPane.setViewportView(vertexList);

        edgeList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        edgeList.setFocusable(false);
        edgeList.setRowSelectionAllowed(false);
        edgeListPane.setViewportView(edgeList);

        resetButton.setText("Reset");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(addVertexLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addVertxField, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addVertexButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addComponent(vertexListPane, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(addEdgeEndLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(addEdgeStartLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(addEdgeWeightLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(5, 5, 5)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(addEdgeEndField, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(addEdgeWeightField, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(addEdgeStartField, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(addEdgeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(resetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edgeListPane, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(distanceMatrixPane, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pathMatrixPane, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(vertexListPane, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edgeListPane, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(addVertxField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(addVertexLabel))
                                .addGap(10, 10, 10)
                                .addComponent(addVertexButton))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(addEdgeStartLabel)
                                    .addComponent(addEdgeStartField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(5, 5, 5)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(addEdgeEndLabel)
                                    .addComponent(addEdgeEndField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(5, 5, 5)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(addEdgeWeightLabel)
                                    .addComponent(addEdgeWeightField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(10, 10, 10)
                        .addComponent(addEdgeButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(resetButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(distanceMatrixPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(pathMatrixPane, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        addEdgeEndField.getAccessibleContext().setAccessibleDescription("");

        pack();
    }
    
    /**
     * Sets recived data in table with veritces label.
     * @param vertexData Data about vertices
     */
    public void setVertexData(Object[][] vertexData) {
        DefaultTableModel vertexModel = (DefaultTableModel) vertexList.getModel();
        vertexModel.setDataVector(vertexData,  new String[]{"Vertex"});
    }
    
    /**
     * Sets recived data in table with endges information.
     * @param edgeData Data about edges (start vertex, end vertex, weight)
     */
    public void setEdgeData(Object[][] edgeData) {
        DefaultTableModel edgeModel = (DefaultTableModel) edgeList.getModel();
        edgeModel.setDataVector(edgeData, new String[]{"Start", "End", "Weight"});
    }
    
    /**
     * Sets starting data (empty) and headers to tables.
     */
    private void setData() {
        DefaultTableModel vertexModel = (DefaultTableModel) vertexList.getModel();
        DefaultTableModel edgeModel = (DefaultTableModel) edgeList.getModel();
        DefaultTableModel distanceMatrixModel = (DefaultTableModel) distanceMatrix.getModel();
        DefaultTableModel pathMatrixModel = (DefaultTableModel) pathMatrix.getModel();
        
        vertexModel.setDataVector(new Object[][]{}, new String[]{"Vertex"});
        edgeModel.setDataVector(new Object[][]{}, new String[]{"Start", "End", "Weight"});
        distanceMatrixModel.setDataVector(new Object[][]{}, new String[]{""});
        pathMatrixModel.setDataVector(new Object[][]{}, new String[]{""});
    }
    
    /**
     * Sets recived data in tables for FW algorithm.
     * @param distanceData  Data about distance between vertices
     * @param pathData      Data about path between vertivec
     * @param columns       List of vertices label to set as first column in tables.
     */
    public void setFWMatrixData(Object[][] distanceData, Object[][] pathData, String[] columns) {
        DefaultTableModel distanceMatrixModel = (DefaultTableModel) distanceMatrix.getModel();
        distanceMatrixModel.setDataVector(distanceData, columns);
        
        DefaultTableModel pathMatrixModel = (DefaultTableModel) pathMatrix.getModel();
        pathMatrixModel.setDataVector(pathData, columns);
    }

    /**
     * Get access to text from field.
     * @return text from field
     */
    public String getVertexFieldText() {
        return this.addVertxField.getText();
    }
    
    /**
     * Set text in field.
     * @param text text to set in the field
     */
    public void setVertexFieldText(String text) {
        this.addVertxField.setText(text);
    }

    /**
     * Get access to text from fied.
     * @return text from field
     */
    public String getStartVertexFieldText() {
        return this.addEdgeStartField.getText();
    }
    
    /**
     * Set text in field.
     * @param text text to set in the field
     */
    public void setStartVertexFieldText(String text) {
        this.addEdgeStartField.setText(text);
    }

    /**
     * Get access to text from fied.
     * @return text from field
     */
    public String getEndVertexFieldText() {
        return this.addEdgeEndField.getText();
    }
    
    /**
     * Set text in field.
     * @param text text to set in the field
     */
    public void setEndVertexFieldText(String text) {
        this.addEdgeEndField.setText(text);
    }

    /**
     * Get access to text from fied.
     * @return text from field
     */
    public double getWeightFieldText() {
        return Double.parseDouble(this.addEdgeWeightField.getText());
    }
    
    /**
     * Set text in field.
     * @param text text to set in the field
     */
    public void setWeightFieldText(String text) {
        this.addEdgeWeightField.setText(text);
    }
    
    /**
     * Connect listener to button, to get information about beeing pressd.
     * @param listenForAddVertexButton listener for addVertexButton
     */
    public void addVertexListener(ActionListener listenForAddVertexButton) {
        addVertexButton.addActionListener(listenForAddVertexButton);
    }
    
    /**
     * Connect listener to button, to get information about beeing pressd.
     * @param listenForAddEdgeButton listener for addEdgeButton
     */
    public void addEdgeListener(ActionListener listenForAddEdgeButton) {
        addEdgeButton.addActionListener(listenForAddEdgeButton);
    }
    
    /**
     * Connect listener to button, to get information about beeing pressd.
     * @param listenForResetButton listener for resetButton
     */
    public void resetListener(ActionListener listenForResetButton) {
        resetButton.addActionListener(listenForResetButton);
    }
    
    /**
     * Popup window with given error message.
     * @param errorMessage text to display
     */
    public void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage);
    }
}
