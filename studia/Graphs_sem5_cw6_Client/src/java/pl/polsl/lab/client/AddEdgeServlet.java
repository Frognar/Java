/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.lab.client;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceRef;
import pl.polsl.lab.service.Graph_Service;

/**
 * Add Edge servlet send request to add edge
 *
 * @version 1.0
 * @author Sebastian Przyszlak
 */
@WebServlet(name = "AddEdgeServlet", urlPatterns = {"/AddEdgeServlet"})
public class AddEdgeServlet extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/GraphService/Graph.wsdl")
    private Graph_Service service;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession();
        boolean loggedIn = false;
        Cookie[] cookie_jar = request.getCookies();

        // Check to see if any cookies exists
        if (cookie_jar != null) {
            for (Cookie aCookie : cookie_jar) {
                if (aCookie.getName().equals("name")) {
                    loggedIn = true;
                }
            }
        }

        // if not logged in, go to loggin site
        if (!loggedIn) {
            response.sendRedirect("login.html");
        }

        String sLabel = request.getParameter("sLabel");
        String eLabel = request.getParameter("eLabel");
        String sWeight = request.getParameter("weight");
        double weight;
        String err = "";
        if (sLabel.equals("")) {
            err += "start vertex label empty!<br>";
        }
        if (eLabel.equals("")) {
            err += "end vertex label empty!<br>";
        }
        try {
            weight = Double.parseDouble(sWeight);
            if (err.equals("")) {
                err = addEdge(sLabel, eLabel, weight);
                err += "<br>";
            }
        } catch (NumberFormatException e) {
            err += "Wrong weight parameter!<br>";
        }

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddEdgeServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddEdgeServlet at " + request.getContextPath() + "</h1>");
            out.println(err);
            out.println("<a href = \"index.html\">Back</a>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private String addEdge(java.lang.String sLabel, java.lang.String eLabel, double weight) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        pl.polsl.lab.service.Graph port = service.getGraphPort();
        return port.addEdge(sLabel, eLabel, weight);
    }

}
