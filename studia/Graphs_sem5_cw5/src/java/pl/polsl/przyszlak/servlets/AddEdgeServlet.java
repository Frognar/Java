package pl.polsl.przyszlak.servlets;

import pl.polsl.przyszlak.entity.EdgeEntity;
import pl.polsl.przyszlak.entity.VertexEntity;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import pl.polsl.przyszlak.exceptions.EmptyStringException;
import pl.polsl.przyszlak.model.GraphModel;

/**
 * Add edge servlet
 *
 * @author Sebastian Przyszlak
 * @version 1.1
 */
@WebServlet(urlPatterns = {"/AddEdge"})
public class AddEdgeServlet extends HttpServlet {

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
                String startVertexLabel = req.getParameter("startVertexLabel");
                String endVertexLabel = req.getParameter("endVertexLabel");
                String weightStr = req.getParameter("weight");

                if (startVertexLabel == null || endVertexLabel == null || weightStr == null) {
                    throw new EmptyStringException("no parameters");
                } else if (startVertexLabel.length() == 0 || endVertexLabel.length() == 0) {
                    throw new EmptyStringException("empty string");
                }

                double weight = Double.parseDouble(weightStr);

                em = emf.createEntityManager();
                
                VertexEntity startVertex = null;
                VertexEntity endVertex = null;
                //Check if vertices are in DB
                try {
                    startVertex = (VertexEntity) em.createQuery("SELECT v FROM VertexEntity v WHERE v.label LIKE :label").setParameter("label", startVertexLabel).getSingleResult();
                } catch (Exception ex) {
                    webDrawer.exception(out, ex.getMessage());
                }
                try {
                    endVertex = (VertexEntity) em.createQuery("SELECT v FROM VertexEntity v WHERE v.label LIKE :label").setParameter("label", endVertexLabel).getSingleResult();
                } catch (Exception ex) {
                    webDrawer.exception(out, ex.getMessage());
                }

                //begin a transaction
                utx.begin();
                //create an em. 
                //Since the em is created inside a transaction, it is associsated with 
                //the transaction
                em = emf.createEntityManager();
                
                //if no, add them'
                if (startVertex == null) {
                    startVertex = new VertexEntity(startVertexLabel);
                    em.persist(startVertex);
                }
                if (endVertex == null) {
                    endVertex = new VertexEntity(endVertexLabel);
                    em.persist(endVertex);
                }

                //Create a vertex instance out of it
                EdgeEntity edgeEntity = new EdgeEntity(startVertex, endVertex, weight);
                startVertex.getEdgeStart().add(edgeEntity);
                endVertex.getEdgeEnd().add(edgeEntity);

                //merge vertices
                em.merge(startVertex);
                em.merge(endVertex);

                //persist the edgeEntity
                em.persist(edgeEntity);
                //commit transaction which will trigger the em to 
                //commit newly created entity into database
                utx.commit();

                // Add new edge
                model.getGraph().addEdge(startVertexLabel, weight, endVertexLabel);

                // And go to show graph page
                resp.sendRedirect("/Graphs/ShowGraph");

                // Some data was not given - show error message
            } catch (NumberFormatException | NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
                webDrawer.exception(out, ex.getMessage());
            } catch (EmptyStringException ex) {
                webDrawer.exception(out, ex.getErrorMessage());
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
