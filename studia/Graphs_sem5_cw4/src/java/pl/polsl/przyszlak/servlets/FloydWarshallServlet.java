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
import pl.polsl.przyszlak.exceptions.NegativeCycleException;
import pl.polsl.przyszlak.model.GraphModel;

/**
 * Floyd Warshall servlet
 * calculate FW shortest path algorithm and draw distance matrix
 * 
 * @author Sebastian Przyszlak
 * @version 1.0
 */
@WebServlet(urlPatterns = {"/FloydWarshall"})
public class FloydWarshallServlet extends HttpServlet {

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

            try {
                model.getGraphAlgorithm().doAlgorithm(model.getGraph().getAdjacencyList(), model.getGraph().getListOfLabels());

                if (model.getGraphAlgorithm().negativeCycle()) {
                    throw new NegativeCycleException("Negative cycle");
                }

                Object[][] distance = model.getGraphAlgorithm().getDistanceData();
                Object[][] path = model.getGraphAlgorithm().getPathData();
                String[] labels = model.getGraph().getListOfLabels();

                if (distance != null && path != null && labels != null) {
                    if (labels.length > 0) {
                        out.println("<h3 class=\"logo\">Floyd Warshall shortest past algorithm calculated</h3>");
                        out.println("<table style=\"margin-left: auto; margin-right: auto;\">");
                        out.println("<tr>");
                        out.println("<th>\\</th>");

                        for (String label : labels) {
                            out.println("<th>" + label + "</th>");
                        }

                        out.println("</tr>");

                        for (int i = 0; i < distance.length; i++) {
                            out.println("<tr><td>" + labels[i] + "</td>");

                            for (int j = 1; j < distance[i].length; j++) {
                                out.println("<td>" + distance[i][j] + " / " + path[i][j] + "</td>");
                            }

                            out.println("</tr>");
                        }

                        out.println("</table>");

                    } else {
                        out.println("<h3 class=\"logo\">Graph is empty</h3>");
                    }
                }

                // Negative cycle in graph - show error message
            } catch (NegativeCycleException ex) {
                webDrawer.exception(out, ex.getErrorMessage());
            }

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
