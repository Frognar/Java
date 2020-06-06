/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.lab.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import pl.polsl.lab.entity.EdgesData;
import pl.polsl.lab.entity.VerticesData;
import pl.polsl.przyszlak.model.GraphModel;

/**
 * Web services
 * @version 1.0
 * @author Sebastian Przyszlak
 */
@WebService(serviceName = "Graph")
public class Graph {

    @PersistenceUnit
    private EntityManagerFactory emf;
    @PersistenceContext(unitName = "GraphServicePU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;
    /**
     * make transaction with db and add object to DB
     * @param object - object to persist
     */
    public void persist(Object object) {
        try {
            utx.begin();
            em.persist(object);
            utx.commit();
        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Web service operation Adding new Vertex to DB
     * Add Vertex to DB
     * @param label - label of vertex
     * @return result msg [completed or not]
     */
    @WebMethod(operationName = "AddVertex")
    public String AddVertex(@WebParam(name = "label") String label) {
        VerticesData vertex;
        try {
            vertex = (VerticesData) em.createQuery("SELECT v FROM VerticesData v WHERE v.label LIKE :label").setParameter("label", label).getSingleResult();
        } catch (NoResultException e) {
            vertex = new VerticesData();
            vertex.setLabel(label);
            persist(vertex);
            return "Adding complete<br>";
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Vertex already in graph<br>";
    }

    /**
     * Web service operation
     * Add edge to DB
     * @param sLabel - start vertex label
     * @param eLabel - end vertex label
     * @param weight - weight of connection
     * @return result msg [complete or error]
     */
    @WebMethod(operationName = "AddEdge")
    public String AddEdge(@WebParam(name = "sLabel") String sLabel, @WebParam(name = "eLabel") String eLabel, @WebParam(name = "weight") double weight) {
        try {
            VerticesData s = null;
            VerticesData e = null;
            AddVertex(sLabel);
            AddVertex(eLabel);
            try {
                s = (VerticesData) em.createQuery("SELECT v FROM VerticesData v WHERE v.label LIKE :label").setParameter("label", sLabel).getSingleResult();
                e = (VerticesData) em.createQuery("SELECT v FROM VerticesData v WHERE v.label LIKE :label").setParameter("label", eLabel).getSingleResult();
            } catch (Exception ex) {
                return "Problem with finding vertices in DB<br>";
            }
            EdgesData edge = new EdgesData();
            edge.setStartVertex(s);
            edge.setEndVertex(e);
            edge.setWeight(weight);
            s.getEdgesDataList().add(edge);
            e.getEdgesDataList1().add(edge);
            utx.begin();
            em.merge(s);
            em.merge(e);
            em.persist(edge);
            utx.commit();
            return "Adding complete<br>";
        } catch (Exception ex) {
            return "Can't add this edge<br>";
        }
    }

    /**
     * Web service operation
     * Get data form DB
     * @return data from DB
     */
    @WebMethod(operationName = "returnGraph")
    public List returnGraph() {
        // Get data from DB
        List vertices = em.createQuery("select v from VerticesData v").getResultList();
        List edges = em.createQuery("select v from EdgesData v").getResultList();
        // Preparing result list
        List result = new ArrayList<String>();
        result.add("Vertices:<br>");
        for (int i = 0; i < vertices.size(); i++) {
            VerticesData vert = (VerticesData) vertices.get(i);
            result.add(vert.getLabel() + "<br>");
        }
        result.add("Edges:<br>");
        for (int i = 0; i < edges.size(); i++) {
            EdgesData edg = (EdgesData) edges.get(i);
            result.add(edg.getStartVertex().getLabel() + " -> " + edg.getEndVertex().getLabel() + " : " + edg.getWeight() + "<br>");
        }

        return result;
    }

    /**
     * Web service operation
     * Calculate FW algorithm
     * @return FW shortest path matrix
     */
    @WebMethod(operationName = "returnFW")
    public List returnFW() {
        List result = new ArrayList<>();
        // Get data from DB
        GraphModel model = new GraphModel();
        try {
            List vertices = em.createQuery("select v from VerticesData v").getResultList();
            for (Object v : vertices) {
                VerticesData vert = (VerticesData) v;
                model.getGraph().addVertex(vert.getLabel());
            }
            List edges = em.createQuery("select v from EdgesData v").getResultList();
            for (Object e : edges) {
                EdgesData edg = (EdgesData) e;
                model.getGraph().addEdge(edg.getStartVertex().getLabel(), edg.getWeight(), edg.getEndVertex().getLabel());
            }
        } catch (Exception ex) {
            result.add("Problem with reading data from DB<br>");
            return result;
        }
        
        //Calcluate FW
        model.getGraphAlgorithm().doAlgorithm(model.getGraph().getAdjacencyList(), model.getGraph().getListOfLabels());
        // Detect negative cycle
        if (model.getGraphAlgorithm().negativeCycle()) {
            result.add("NEGATIVE CYCLE<br>");
        } else {
            // Preparing result
            double[][] d = model.getGraphAlgorithm().getDistanceMatrix();
            String[][] p = model.getGraphAlgorithm().getPathMatrix();
            String[] v = model.getGraph().getListOfLabels();
            String temp = "<table border=\"1\"><tr><td>X</td>";
            for (String v1 : v) {
                temp += "<td>" + v1 + "</td>";
            }
            temp += "</tr>";
            result.add(temp);
            for (int i = 0; i < v.length; i++) {
                temp = "<tr><td>" + v[i] + "</td>";
                for (int j = 0; j < v.length; j++) {
                    temp += "<td>" + d[i][j] + " / " + p[i][j] + "</td>";
                }
                temp += "</tr>";
                result.add(temp);
            }
            result.add("</table>");
        }
        return result;
    }

    /**
     * Web service operation
     * Delete all data from DB
     * @return complete msg or error msg
     */
    @WebMethod(operationName = "resetGraph")
    public String resetGraph() {
        // Delete data from DB
        try {
            utx.begin();
            em.createQuery("DELETE FROM EdgesData e").executeUpdate();
            em.createQuery("DELETE FROM VerticesData v").executeUpdate();
            utx.commit();
        } catch (Exception e) {
            return e.getMessage() + "<br>";
        }
        return "Reset complete<br>";
    }
}
