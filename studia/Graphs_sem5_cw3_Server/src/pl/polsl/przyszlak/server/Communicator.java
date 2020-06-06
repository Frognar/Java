package pl.polsl.przyszlak.server;

import java.io.*;
import pl.polsl.przyszlak.controller.EmptyStringException;

/**
 * Communicator
 * @author Sebastian Przyszlak
 * @version 1.0
 */
public final class Communicator {

    /**
     * Output
     */
    private PrintWriter writer;

    /**
     * @return writer
     */
    public PrintWriter getWriter() {
        return this.writer;
    }

    /**
     * @param writer the out to set
     */
    public void setWriter(PrintWriter writer) {
        this.writer = writer;
    }
    /**
     * Input
     */
    private BufferedReader reader;

    /**
     * @return reader
     */
    public BufferedReader getRaader() {
        return this.reader;
    }

    /**
     * @param reader the in to set
     */
    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }

    /**
     * Class constructor
     *
     * @param reader - input from client
     * @param writer - output to client
     */
    Communicator(BufferedReader reader, PrintWriter writer) {
        setReader(reader);
        setWriter(writer);
    }

    /**
     * Read request from client
     *
     * @return protocol - clients request
     */
    public String readData() {
        String protocol;

        try {
            protocol = getRaader().readLine();

        } catch (IOException ex) {
            protocol = "STR::EXC::" + ex.getMessage() + "::END";
        }

        return protocol;
    }

    /**
     * Send data to client
     *
     * @param protocol - message for client
     */
    public void sendData(String protocol) {
        getWriter().println(protocol);
    }

    /**
     * Communication between server and clients
     *
     * @param protocol - message from client
     * @return orders for server
     */
    public Object[] communication(String protocol) {
        System.out.println("Client: " + protocol);

        if (protocol.equalsIgnoreCase("HELP")) {
            Object[] data = {"HELP"};
            return data;
        }

        String[] request = protocol.split("::");
        Object[] data = {};

        if (request.length == 4
                && request[0].equalsIgnoreCase("STR")
                && request[1].equalsIgnoreCase("ADDV")
                && request[3].equalsIgnoreCase("END")) {

            try {
                String vertexLabel = request[2];

                if (vertexLabel.equals("")) {
                    throw new EmptyStringException("Empty vertex label");  // empty string.
                }

                data = new Object[2];
                data[0] = "ADDV";
                data[1] = vertexLabel;
                System.out.println("STR::ADDV::OK::END");
                //writer.println("Server: " + "STR::ADDV::OK::END");

            } catch (EmptyStringException ex) {
                data = new Object[2];
                data[0] = "EXC";
                data[1] = ex.getMessage();
                System.out.println("STR::EXC::" + ex.getMessage() + "::END");
                //writer.println("Server: " + "STR::EXC::" + ex.getMessage() + "::END");
            }

        } else if (request.length == 6
                && request[0].equalsIgnoreCase("STR")
                && request[1].equalsIgnoreCase("ADDE")
                && request[5].equalsIgnoreCase("END")) {

            try {
                String startVertexLabel = request[2];
                String endVertexLabel = request[3];

                if (startVertexLabel.equals("") || endVertexLabel.equals("")) {
                    throw new EmptyStringException("Empty vertex label");  // empty string.
                }
                try {
                    double weight = Double.parseDouble(request[4]);
                    data = new Object[4];
                    data[0] = "ADDE";
                    data[1] = startVertexLabel;
                    data[2] = endVertexLabel;
                    data[3] = weight;
                    System.out.println("STR::ADDE::OK::END");
                    //writer.println("Server: " + "STR::ADDE::OK::END");
                } catch (NumberFormatException ex) {
                    data = new Object[2];
                    data[0] = "EXC";
                    data[1] = ex.getMessage();
                    System.out.println("STR::EXC" + data[1] + "::END");
                    //writer.println("Server: " + "STR::EXC" + data[1] + "::END");
                }

            } catch (NumberFormatException | EmptyStringException ex) {
                data = new Object[2];
                data[0] = "EXC";
                data[1] = ex.getMessage();
                System.out.println("STR::EXC::" + ex.getMessage() + "::END");
                //writer.println("Server: " + "STR::EXC::" + ex.getMessage() + "::END");
            }

        } else if (request.length == 3
                && request[0].equalsIgnoreCase("STR")
                && request[1].equalsIgnoreCase("DRAW")
                && request[2].equalsIgnoreCase("END")) {

            data = new Object[1];
            data[0] = "DRAW";
            System.out.println("STR::DRAW::OK::END");
            //writer.println("Server: " + "STR::DRAW::OK::END");

        } else if (request.length == 3
                && request[0].equalsIgnoreCase("STR")
                && request[1].equalsIgnoreCase("RST")
                && request[2].equalsIgnoreCase("END")) {

            data = new Object[1];
            data[0] = "RST";
            System.out.println("STR::RST::OK::END");
            //writer.println("Server: " + "STR::RST::OK::END");

        } else if (request.length == 3
                && request[0].equalsIgnoreCase("STR")
                && request[1].equalsIgnoreCase("BYE")
                && request[2].equalsIgnoreCase("END")) {

            data = new Object[1];
            data[0] = "BYE";
            System.out.println("STR::BYE::OK::END");
            //writer.println("Server: " + "STR::BYE::OK::END");

        } else {
            data = new Object[1];
            data[0] = "NOTREC";
            System.out.println("STR::REQ::NOTREC::END");
            //writer.println("Server: " + "STR::REQ::NOTREC::END");
        }

        return data;
    }
}
