package pl.polsl.przyszlak.server;

import java.io.*;
import java.net.*;
import java.util.LinkedList;
import java.util.Properties;
import pl.polsl.przyszlak.model.GraphModel;

/**
 * Server
 *
 * @author Sebastian Przyszlak
 * @version 1.0
 */
public class GraphsServer {

    /**
     * Graph model
     */
    private GraphModel theModel;

    /**
     * Server socket
     */
    private ServerSocket serverSocket;

    /**
     * input
     */
    BufferedReader reader;

    /**
     * output
     */
    PrintWriter writer;

    /**
     * @return the theModel
     */
    public GraphModel getTheModel() {
        return this.theModel;
    }

    /**
     * @param theModel the theModel to set
     */
    public void setTheModel(GraphModel theModel) {
        this.theModel = theModel;
    }

    /**
     * Create string with all FWalgorithm data
     *
     * @return data about vertex, distance and path
     */
    private String createDataProtocol() {
        String[] labels = getTheModel().getGraph().getListOfLabels();
        String[][] path = getTheModel().getGraphAlgorithm().getPathMatrix();
        double[][] distance = getTheModel().getGraphAlgorithm().getDistanceMatrix();
        Object[][] edges = getTheModel().getGraph().getEdgesData();
        int numberOfEdges = getTheModel().getGraph().getNumberOfEdges();

        String data = "STR::DATA::";

        if (labels != null && path != null && distance != null && edges != null) {
            data += labels.length + "::" + numberOfEdges + "::";
            for (int i = 0; i < labels.length; i++) {   // add all vertices label to data[][]
                data += labels[i];
                if (i < labels.length - 1) {
                    data += "@";
                } else {
                    data += "::";
                }
            }

            for (String[] row : path) { // add all next vertex in path to data[][]
                for (int i = 0; i < row.length; i++) {
                    if (row[i].equals("")) {
                        data += " ";
                    } else {
                        data += row[i];
                    }
                    if (i < row.length - 1) {
                        data += "@";
                    } else {
                        data += "::";
                    }
                }
            }

            for (double[] row : distance) { // add all distance of path to data[][]
                for (int i = 0; i < row.length; i++) {
                    data += row[i];
                    if (i < row.length - 1) {
                        data += "@";
                    } else {
                        data += "::";
                    }
                }
            }

            for (Object[] row : edges) {    // add all edges to data[][]
                for (int i = 0; i < row.length; i++) {
                    data += row[i];
                    if (i < row.length - 1) {
                        data += "@";
                    } else {
                        data += "::";
                    }
                }
            }
        } else {
            data += "0::0::";
        }
        data += "END";
        return data;
    }

    /**
     * Communication between server and clients
     *
     * @param reader - input from clients
     * @param writer - output to clients
     */
    private void betterCommunication(BufferedReader reader, PrintWriter writer) {

        Communicator communicator = new Communicator(reader, writer);
        String protocol;

        do {
            protocol = communicator.readData();
            Object[] data = communicator.communication(protocol);

            if (data[0].equals("ADDV")) {
                //communicator.sendData("STR::ADDV::OK::END");
                String vertexLabel = data[1].toString();
                this.getTheModel().getGraph().addVertex(vertexLabel);
                this.getTheModel().getGraphAlgorithm().doAlgorithm(
                        this.getTheModel().getGraph().getAdjacencyList(),
                        this.getTheModel().getGraph().getListOfLabels());

                if (this.getTheModel().getGraphAlgorithm().negativeCycle()) {
                    communicator.sendData("STR::EXC::Negative cycle::END");
                    communicator.sendData(this.createDataProtocol());

                } else {
                    communicator.sendData(this.createDataProtocol());
                }
            } else if (data[0].equals("ADDE")) {
                //communicator.sendData("STR::ADDE::OK::END");
                String startVertexLabel = data[1].toString();
                String endVertexLabel = data[2].toString();
                double weight = (double) data[3];
                this.getTheModel().getGraph().addEdge(startVertexLabel, weight, endVertexLabel);
                this.getTheModel().getGraphAlgorithm().doAlgorithm(
                        this.getTheModel().getGraph().getAdjacencyList(),
                        this.getTheModel().getGraph().getListOfLabels());

                if (this.getTheModel().getGraphAlgorithm().negativeCycle()) {
                    communicator.sendData("STR::EXC::Negative cycle::END");
                    communicator.sendData(this.createDataProtocol());

                } else {
                    communicator.sendData(this.createDataProtocol());
                }

            } else if (data[0].equals("DRAW")) {
                //communicator.sendData("STR::DRAW::OK::END");
                communicator.sendData(this.createDataProtocol());

            } else if (data[0].equals("RST")) {
                //communicator.sendData("STR::RST::OK::END");
                this.getTheModel().getGraph().reset();
                this.getTheModel().getGraphAlgorithm().doAlgorithm(
                        new LinkedList<>(),
                        new String[0]);
                communicator.sendData(this.createDataProtocol());

            } else if (data[0].equals("EXC")) {
                communicator.sendData("STR::EXC::" + data[1] + "::END");

            } else if (data[0].equals("NOTREC")) {
                communicator.sendData("STR::REQ::NOTREC::END");

            } else if (data[0].equals("BYE")) {
                communicator.sendData("STR::BYE::OK::END");

            } else if (data[0].equals("HELP")) {    // this is only fo putty
                communicator.sendData("Aveliable commands:");
                communicator.sendData("STR::ADDV::l::END - add vertex with label l");
                communicator.sendData("STR::ADDE::l1::l2::w::END - add edge between vertex l1 and l2 with weight w");
                communicator.sendData("STR::RES::END - delete the graph");
                communicator.sendData("STR::DRAW::END - draw the graph");
                communicator.sendData("STR::BYE::END - end connection !important");
                communicator.sendData("HELP - this help menu");
            }

        } while (!protocol.equalsIgnoreCase("STR::BYE::END")); // end of communication

    }

    /**
     * starts server
     *
     * @param port - port to connect with server
     */
    private void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server is listening on port " + port);
            while (true) {

                try (Socket socket = serverSocket.accept()) {
                    System.out.println("New client connected");

                    InputStream input = socket.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(input));

                    OutputStream output = socket.getOutputStream();
                    writer = new PrintWriter(output, true);

                    betterCommunication(reader, writer);
                }

                System.out.println("Client disconected");
            }

        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
        }
    }

    /**
     * Close server
     */
    private void stop() {
        try {
            reader.close();
            writer.close();
            serverSocket.close();

        } catch (IOException ex) {

        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        GraphsServer graphServer = new GraphsServer();

        graphServer.setTheModel(new GraphModel());

        Properties properties = new Properties();

        try (FileInputStream in = new FileInputStream("config.properties")) {
            properties.load(in);
            System.out.println("port=" + properties.getProperty("port"));

            try {
                graphServer.start(Integer.parseInt(properties.getProperty("port")));
                graphServer.stop();

            } catch (NumberFormatException ex) {
                System.err.println(ex.getMessage());
            }

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
