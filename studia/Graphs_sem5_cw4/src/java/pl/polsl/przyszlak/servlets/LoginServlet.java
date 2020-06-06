package pl.polsl.przyszlak.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pl.polsl.przyszlak.exceptions.EmptyStringException;

/**
 * Login servlet create cookie name
 *
 * password : admin
 * 
 * @author Sebastian Przyszlak
 * @version 1.0
 */
@WebServlet(urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    /**
     * WebDrawer to draw a HTML
     */
    private final WebDrawer webDrawer = new WebDrawer();

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param req servlet request
     * @param resp servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = resp.getWriter()) {

            webDrawer.headSection(out);
            webDrawer.startBodySectionForLogin(out);
            
            // Get parameter values
            try {
                String name = req.getParameter("name");
                String password = req.getParameter("password");

                if (name.length() == 0 || password.length() == 0) {
                    throw new EmptyStringException("empty string");
                }

                if (password.equals("admin")) {
                    Cookie ck = new Cookie("name", (String) URLEncoder.encode(name, "UTF-8"));
                    resp.addCookie(ck);
                    resp.sendRedirect("/Graphs/Graphs");

                } else {
                    webDrawer.exception(out, "wrong password!");
                }

                // Some data was not given - show error message
            } catch (EmptyStringException ex) {
                webDrawer.exception(out, ex.getErrorMessage());
            } catch (Exception ex) {
                webDrawer.exception(out, ex.getMessage());
            }

            webDrawer.endBodySection(out);
        }
    }

    /**
     * Handles the HTTP <code>GET</code> method.
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
