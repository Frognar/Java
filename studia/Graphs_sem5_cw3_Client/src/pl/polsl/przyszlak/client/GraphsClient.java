package pl.polsl.przyszlak.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.net.*;
import java.util.Properties;
import java.util.Scanner;
import pl.polsl.przyszlak.view.GraphView;

/**
 * Client
 * @author Sebastian Przyszlak
 * @version 1.0
 */
public class GraphsClient {

    /**
     * Client socket
     */
    private Socket clientSocket;

    /**
     * input
     */
    private PrintWriter writer;

    /**
     * output
     */
    private BufferedReader reader;

    /**
     * Communicator between client and server
     */
    private Communicator communicator;

    /**
     * The view
     */
    private static GraphView theGraphView;

    /**
     * @return theGraphView
     */
    public GraphView getGraphView() {
        return GraphsClient.theGraphView;
    }

    /**
     * @param theGraphView theGrapHView to set the graph view
     */
    public void setGraphView(GraphView theGraphView) {
        GraphsClient.theGraphView = theGraphView;
    }

    /**
     * @param communicator to set the communicator
     */
    public void setCommunicator(Communicator communicator) {
        this.communicator = communicator;
    }

    /**
     * @return communicator
     */
    public Communicator getCommunicator() {
        return this.communicator;
    }

    public void betterCommunication(String protocol) {

        communicator.sendData(protocol);

        String answer;
        answer = communicator.readData();
        Object[][] data = communicator.communication(answer);

        if (data[0][0].equals("EXC")) {
            this.getGraphView().displayErrorMessage((String) data[0][1]);
            if (data[0][1].equals("Negative cycle")) { // to show client negative cycle
                answer = communicator.readData();
                data = communicator.communication(answer);
                Object[][] vertexData = new Object[data[0].length][1];
                String[] columns = new String[data[0].length];
                Object[][] path = new Object[data[0].length][data[0].length];
                Object[][] dist = new Object[data[0].length][data[0].length];
                int numberOfEdges = Integer.parseInt(data[data.length - 2][0].toString());
                Object[][] edges = new Object[numberOfEdges][3];

                for (int i = 0; i < data[0].length; i++) {
                    vertexData[i][0] = data[0][i];
                    columns[i] = data[0][i].toString();
                    path[i] = data[i + 1];
                    dist[i] = data[i + 1 + data[0].length];
                }

                for (int i = 0; i < numberOfEdges; i++) {
                    edges[i][0] = data[data.length - 2 - numberOfEdges + i][0];
                    edges[i][1] = data[data.length - 2 - numberOfEdges + i][1];
                    edges[i][2] = data[data.length - 2 - numberOfEdges + i][2];
                }
                getGraphView().setVertexData(vertexData);
                getGraphView().setFWMatrixData(dist, path, columns);
                getGraphView().setEdgeData(edges);
            }

        } else if (data[0][0].equals("NOTREC")) {
            System.out.println("com: request not recognized");

        } else if (data[0][0].equals("BYE")) {
            System.out.println("com: connection ended");

        } else if (data[0][0].equals("EMPTY")) {
            System.out.println("com: empty graph");
            getGraphView().setVertexData(new Object[][]{});
            getGraphView().setEdgeData(new Object[][]{});
            getGraphView().setFWMatrixData(new Object[][]{}, new Object[][]{}, new String[]{""});

        } else if (data[data.length - 1][0].equals("DATA")) {
            Object[][] vertexData = new Object[data[0].length][1];
            String[] columns = new String[data[0].length];
            Object[][] path = new Object[data[0].length][data[0].length];
            Object[][] dist = new Object[data[0].length][data[0].length];
            int numberOfEdges = Integer.parseInt(data[data.length - 2][0].toString());
            Object[][] edges = new Object[numberOfEdges][3];

            for (int i = 0; i < data[0].length; i++) {
                vertexData[i][0] = data[0][i];
                columns[i] = data[0][i].toString();
                path[i] = data[i + 1];
                dist[i] = data[i + 1 + data[0].length];
            }

            for (int i = 0; i < numberOfEdges; i++) {
                edges[i][0] = data[data.length - 2 - numberOfEdges + i][0];
                edges[i][1] = data[data.length - 2 - numberOfEdges + i][1];
                edges[i][2] = data[data.length - 2 - numberOfEdges + i][2];
            }
            getGraphView().setVertexData(vertexData);
            getGraphView().setFWMatrixData(dist, path, columns);
            getGraphView().setEdgeData(edges);
        }
    }

    /**
     * Create connection to the server
     *
     * @param ip - ip address of th server
     * @param port - port throught, whitch we connect to th server
     */
    public void startConnection(String ip, int port) {
        try {
            clientSocket = new Socket(ip, port);
            GraphsClient.theGraphView.addWindowListener(new MyWindowListener());
            GraphsClient.theGraphView.addVertexListener(new VertexListener());
            GraphsClient.theGraphView.addEdgeListener(new EdgeListener());
            GraphsClient.theGraphView.resetListener(new ResetListener());

            OutputStream output = clientSocket.getOutputStream();
            writer = new PrintWriter(output, true);

            InputStream input = clientSocket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));
            communicator = new Communicator(reader, writer);

            this.betterCommunication("STR::DRAW::END"); // get all data at the beggining of connection
            String text;

            do {
                Scanner sinput = new Scanner(System.in);
                text = sinput.next();
                this.betterCommunication(text);

            } while (!text.equalsIgnoreCase("STR::BYE::END"));

        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());

        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }

    /**
     * Close the connection
     */
    public void stopConnection() {
        try {
            if (reader != null) {
                reader.close();
            }
            if (writer != null) {
                writer.close();
            }
            if (clientSocket != null) {
                clientSocket.close();
            }

        } catch (IOException ex) {
            getGraphView().displayErrorMessage(ex.getMessage());
        }
    }

    /**
     * Main function
     *
     * @param args - list of arguments - no needed
     */
    public static void main(String[] args) {
        GraphsClient.theGraphView = new GraphView();
        GraphsClient.theGraphView.setVisible(true);
        GraphsClient client = new GraphsClient();

        Properties properties = new Properties();

        try (FileInputStream in = new FileInputStream("config.properties")) {
            properties.load(in);
            System.out.println("ip = " + properties.getProperty("ip"));
            System.out.println("port = " + properties.getProperty("port"));

            try {
                client.startConnection(properties.getProperty("ip"),
                        Integer.parseInt(properties.getProperty("port")));
                client.stopConnection();

            } catch (NumberFormatException ex) {
                System.err.println(ex.getMessage());
            }

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     * Listener for AddVertexButton. If button was pressed, adds given vertex if
     * label was given by user. Throw exception if text field is empty.
     */
    //*
    class VertexListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            betterCommunication("STR::ADDV::" + getGraphView().getVertexFieldText() + "::END");
        }
    }

    /**
     * Listener for AddEdgeButton. If button was pressed, adds edge and needed
     * vertex. Throw exception if any text field is empty and|or weight is not
     * number value.
     */
    class EdgeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            betterCommunication("STR::ADDE::" 
                    + getGraphView().getStartVertexFieldText() + "::" 
                    + getGraphView().getEndVertexFieldText() + "::" 
                    + getGraphView().getWeightFieldText() + "::END");
        }
    }

    /**
     * Listener for ResetButton. If button was pressed, clear all data.
     */
    class ResetListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            betterCommunication("STR::RST::END");
        }
    }

    class MyWindowListener implements WindowListener {

        @Override
        public void windowClosing(WindowEvent arg0) {
            betterCommunication("STR::BYE::END");
            stopConnection();
            System.exit(0);
        }

        public void windowOpened(WindowEvent arg0) {
        }

        public void windowClosed(WindowEvent arg0) {
        }

        public void windowIconified(WindowEvent arg0) {
        }

        public void windowDeiconified(WindowEvent arg0) {
        }

        public void windowActivated(WindowEvent arg0) {
        }

        public void windowDeactivated(WindowEvent arg0) {
        }

    }
}
