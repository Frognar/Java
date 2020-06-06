package pl.polsl.przyszlak.servlets;

import pl.polsl.przyszlak.entity.EdgeEntity;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.persistence.PersistenceUnit;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.annotation.Resource;

import pl.polsl.przyszlak.exceptions.EmptyStringException;
import pl.polsl.przyszlak.model.GraphModel;
import pl.polsl.przyszlak.entity.VertexEntity;
import java.util.List;

/**
 * AddVertex servlet adds new vertex to graph
 *
 * @author Sebastian Przyszlak
 * @version 1.1
 */
@WebServlet(name = "AddVertexServlet", urlPatterns = {"/AddVertex"})
public class AddVertexServlet extends HttpServlet {

    @PersistenceUnit
    //The emf corresponding to 
    private EntityManagerFactory emf;
    @Resource
    private UserTransaction utx;

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

            assert emf != null;  //Make sure injection went through correctly.
            EntityManager em = null;
            try {
                em = emf.createEntityManager();
                //query for all the vertices in database
                List vertices = em.createQuery("select v from VertexEntity v").getResultList();
                for (Object v : vertices) {
                    VertexEntity vert = (VertexEntity) v;
                    model.getGraph().addVertex(vert.getLabel());
                }

                //query for all the edges in database
                List edges = em.createQuery("select v from EdgeEntity v").getResultList();
                for (Object e : edges) {
                    EdgeEntity edg = (EdgeEntity) e;
                    model.getGraph().addEdge(edg.getStartVertex().getLabel(), edg.getWeight(), edg.getEndVertex().getLabel());
                }
            } catch (Exception ex) {
                throw new ServletException(ex);
            } finally {
                //close the em to release any resources held up by the persistebce provider
                if (em != null) {
                    em.close();
                }
            }

            session.setAttribute("model", model);
        }

        // website
        try (PrintWriter out = resp.getWriter()) {
            webDrawer.headSection(out);
            webDrawer.startBodySection(out);

            assert emf != null;  //Make sure injection went through correctly.
            EntityManager em = null;

            try {
                //Get the data from user's form
                String vertexLabel = req.getParameter("vertexLabel");

                if (vertexLabel == null) {
                    throw new EmptyStringException("no parameter");
                } else if (vertexLabel.length() == 0) {
                    throw new EmptyStringException("empty string");
                }

                //Create a vertex instance out of it
                VertexEntity vertexEntity = new VertexEntity(vertexLabel);
                //begin a transaction
                utx.begin();
                //create an em. 
                //Since the em is created inside a transaction, it is associsated with 
                //the transaction
                em = emf.createEntityManager();
                // check if the vertex is in DB
                boolean ve = false;

                for (String label : model.getGraph().getListOfLabels()) {
                    if (label.equals(vertexLabel)) {
                        ve = true;
                    }
                }
                // if no
                if (!ve) {
                    //persist the vertexEntity
                    em.persist(vertexEntity);
                }
                //commit transaction which will trigger the em to 
                //commit newly created entity into database
                utx.commit();
                // Add new vertex to model
                model.getGraph().addVertex(vertexLabel);

                // And go to show graph page
                resp.sendRedirect("/Graphs/ShowGraph");
                //Forward to ListPerson servlet to list persons along with the newly
                //created person above
                //req.getRequestDispatcher("/Graphs/ShowGraph").forward(req, resp);
                // Some data was not given - show error message
            } catch (NumberFormatException | NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
                webDrawer.exception(out, ex.getMessage());
            } catch (EmptyStringException ex) {
                webDrawer.exception(out, ex.getErrorMessage());
            } catch (Exception ex) {
                webDrawer.exception(out, ex.getMessage());
            } finally {
                //close the em to release any resources held up by the persistebce provider
                if (em != null) {
                    em.close();
                }
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doGet(req, resp);
    }
}
