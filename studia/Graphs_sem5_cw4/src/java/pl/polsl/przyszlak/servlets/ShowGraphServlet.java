/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.przyszlak.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pl.polsl.przyszlak.model.GraphModel;

/**
 * ShowGraph Servlet
 * draw list of vertices and all edges
 * 
 * @author Sebastian Przyszlak
 * @version 1.0
 */
@WebServlet(name = "ShowGraph", urlPatterns = {"/ShowGraph"})
public class ShowGraphServlet extends HttpServlet {

    /**
     * WebDrawer to draw a HTML
     */
    private final WebDrawer webDrawer = new WebDrawer();

    /**
     * Graph model
     */
    private GraphModel model;

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param req servlet request
     * @param resp servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        HttpSession session = req.getSession();
        boolean loggedIn = false;
        Cookie[] cookie_jar = req.getCookies();

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
            resp.sendRedirect("/Graphs/login.html");
        }

        // Check to see if model exists, if not create new one
        model = (GraphModel) session.getAttribute("model");

        if (model == null) {
            model = new GraphModel();
            req.getSession().setAttribute("model", model);
        }

        // website
        try (PrintWriter out = resp.getWriter()) {
            webDrawer.headSection(out);
            webDrawer.startBodySection(out);
            out.println("<section class=\"table\">");
            out.println("<h2>Vertices list</h2>");
            out.println("<table>");
            out.println("<tr><th>Labels</th></tr>");

            for (String label : model.getGraph().getListOfLabels()) {
                out.println("<tr><td>" + label + "</td></tr>");
            }

            out.println("</table>");
            out.println("</section>");
            out.println("<section class=\"table\">");
            out.println("<h2>Edges list</h2>");
            out.println("<table>");
            out.println("<tr><th>Start vertex label</th><th>End vertex label</th><th>Weight</th></tr>");

            for (Object[] edge : model.getGraph().getEdgesData()) {
                out.println("<tr><td>" + edge[0] + "</td><td>" + edge[1] + "</td><td>" + edge[2] + "</td></tr>");
            }

            out.println("</table>");
            out.println("</section>");
            webDrawer.endBodySection(out);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param req servlet request
     * @param resp servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
