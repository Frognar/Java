package pl.polsl.przyszlak.client;

import java.io.*;

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
     * Input
     */
    private BufferedReader reader;

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

        System.out.println("from server: " + protocol);
        return protocol;
    }

    /**
     * Send data to client
     *
     * @param protocol - requset sended to server
     */
    public void sendData(String protocol) {
        System.out.println("to server: " + protocol);
        getWriter().println(protocol);
    }

    /**
     * Read data from server, check if syntax is correct, and send request and
     * data to client
     *
     * @param protocol - message from server
     * @return array of object with request in first cell, and data in others
     */
    public Object[][] communication(String protocol) {
        String[] request = protocol.split("::");
        Object[][] data = new Object[][]{};

        if (request.length == 4
                && request[0].equalsIgnoreCase("STR")
                && request[1].equalsIgnoreCase("EXC")
                && request[3].equalsIgnoreCase("END")) {
            data = new Object[1][2];
            data[0][0] = "EXC";
            data[0][1] = request[2];

        } else if (request.length == 4
                && request[0].equalsIgnoreCase("STR")
                && request[1].equalsIgnoreCase("REQ")
                && request[2].equalsIgnoreCase("NOTREC")
                && request[3].equalsIgnoreCase("END")) {
            data = new Object[1][1];
            data[0][0] = "NOTREC";

        } else if (request.length == 4
                && request[0].equalsIgnoreCase("STR")
                && request[1].equalsIgnoreCase("BYE")
                && request[2].equalsIgnoreCase("OK")
                && request[3].equalsIgnoreCase("END")) {
            data = new Object[1][1];
            data[0][0] = "BYE";

        } else if (request.length == 5
                && request[0].equalsIgnoreCase("STR")
                && request[1].equalsIgnoreCase("DATA")
                && request[4].equalsIgnoreCase("END")) {
            data = new Object[1][1];
            data[0][0] = "EMPTY";

        } else if (request.length > 5
                && request[0].equalsIgnoreCase("STR")
                && request[1].equalsIgnoreCase("DATA")
                && request[request.length - 1].equalsIgnoreCase("END")) {

            try {
                int cellInRow = Integer.parseInt(request[2]);
                int numberOfEdges = Integer.parseInt(request[3]);
                data = new Object[(cellInRow + 1) * 2 + 1 + numberOfEdges][cellInRow]; // row 0 - leabels, rows from 1 to cellInRow + 1 - path, rows from cellInRow + 2 to cellInRow*2+1 - distance, last - info about data
                String[] row;

                for (int i = 0; i < (cellInRow + 1) * 2 + numberOfEdges - 1; i++) {
                    row = request[i + 4].split("@");
                    data[i] = row;
                }

                data[data.length - 2][0] = request[3];
                data[data.length - 1][0] = "DATA";
                return data;

            } catch (NumberFormatException ex) {
                data = new Object[1][2];
                data[0][0] = "EXC";
                data[0][1] = "Wrong row length (not a number)";
                return data;
            }

        }
        return data;
    }
}
