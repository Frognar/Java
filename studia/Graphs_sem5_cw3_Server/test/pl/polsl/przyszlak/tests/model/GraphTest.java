package pl.polsl.przyszlak.tests.model;

import java.util.LinkedList;
import org.junit.Test;
import static org.junit.Assert.*;
import pl.polsl.przyszlak.model.Edge;
import pl.polsl.przyszlak.model.Graph;
import pl.polsl.przyszlak.model.Vertex;

/**
 * Test for Graph class.
 * @author Sebastian Przyszlak
 * @version 1.0
 */
public class GraphTest {
    
    public GraphTest() {
    }

    /**
     * Test of findVertexIndex method, of class Graph.
     */
    @Test
    public void testFindVertexIndex() {
        Graph graph = new Graph();
        
        graph.getAdjacencyList().add(new Vertex("a"));
        graph.getAdjacencyList().add(new Vertex("c"));
        graph.getAdjacencyList().add(new Vertex("b"));
        
        int expResult = 0;
        int result = graph.findVertexIndex("a");
        assertEquals(expResult, result);
        
        expResult = 2;
        result = graph.findVertexIndex("b");
        assertEquals(expResult, result);
        
        expResult = 1;
        result = graph.findVertexIndex("c");
        assertEquals(expResult, result);
        
        expResult = -1;
        result = graph.findVertexIndex("x");
        assertEquals(expResult,result);
        
        graph.getAdjacencyList().add(new Vertex("x"));
        expResult = 3;
        result = graph.findVertexIndex("x");
        assertEquals(expResult,result);
        
        graph.getAdjacencyList().add(new Vertex("o"));
        expResult = 3;
        result = graph.findVertexIndex("x");
        assertEquals(expResult,result);
    }

    /**
     * Test of addVertex method, of class Graph.
     */
    @Test
    public void testAddVertex() {
        Graph graph = new Graph();
        String[] labels = new String[]{"","b","z","Zabrze"};
        boolean[] added = new boolean[]{false, false, false, false};
        boolean[] expResult = new boolean[]{true, true, true, true};
        graph.addVertex(labels);
        
        for(Vertex vert : graph.getAdjacencyList()) {
            if(vert.getLabel().equals(labels[0])) {
                added[0] = true;
            } else if(vert.getLabel().equals(labels[1])) {
                added[1] = true;
            } else if(vert.getLabel().equals(labels[2])) {
                added[2] = true;
            } else if(vert.getLabel().equals(labels[3])) {
                added[3] = true;
            }
        }
        assertArrayEquals(expResult, added);
    }

    /**
     * Test of addEdge method, of class Graph.
     */
    @Test
    public void testAddEdge() {
        Graph graph = new Graph();
        graph.addEdge("A", 127.52, "B");
        graph.addEdge("A", 12.5, "A");
        graph.addEdge("X", 132.51, "F");
        graph.addEdge("D", -212.5, "X");
        
        boolean[] added = new boolean[]{false,false,false,false};
        boolean[] expResault = new boolean[]{true,true,true,true};
        
        for(Vertex vert : graph.getAdjacencyList()) {
            if(vert.getLabel().equals("A")) {
                for(Edge edg : vert.getEdges()) {
                    if(edg.getEndVertexLabel().equals("B") && edg.getWeight() == 127.52){
                        added[0] = true;
                    } else if(edg.getEndVertexLabel().equals("A") && edg.getWeight() == 12.5){
                        added[1] = true;
                    }
                }
            } else if(vert.getLabel().equals("X")) {
                for(Edge edg : vert.getEdges()) {
                    if(edg.getEndVertexLabel().equals("F") && edg.getWeight() == 132.51){
                        added[2] = true;
                    }
                }
            } else if(vert.getLabel().equals("D")) {
                for(Edge edg : vert.getEdges()) {
                    if(edg.getEndVertexLabel().equals("X") && edg.getWeight() == -212.5){
                        added[3] = true;
                    }
                }
            }
        }
        
        assertArrayEquals(expResault, added);
    }

    /**
     * Test of getListOfLabels method, of class Graph.
     */
    @Test
    public void testGetListOfLabels() {
        Graph graph = new Graph();
        
        
        String[] expResult = new String[] {"A","B","ZABRZE","Kowal","Home","Dino"};
        
        for(String label : expResult) {
            graph.getAdjacencyList().add(new Vertex(label));
        }
        
        String[] result = graph.getListOfLabels();
        assertArrayEquals(expResult, result);
        
        
        expResult = new String[] {};
        graph.getAdjacencyList().clear();
        
        for(String label : expResult) {
            graph.getAdjacencyList().add(new Vertex(label));
        }
        
        result = graph.getListOfLabels();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of getNumberOfEdges method, of class Graph.
     */
    @Test
    public void testGetNumberOfEdges() {
        Graph graph = new Graph();
        
        graph.getAdjacencyList().add(new Vertex("A"));
        graph.getAdjacencyList().add(new Vertex("B"));
        graph.getAdjacencyList().add(new Vertex("C"));
        graph.getAdjacencyList().add(new Vertex("D"));
        
        graph.getAdjacencyList().get(0).getEdges().add(new Edge(12,"B"));
        graph.getAdjacencyList().get(0).getEdges().add(new Edge(122,"D"));
        graph.getAdjacencyList().get(1).getEdges().add(new Edge(132,"C"));
        graph.getAdjacencyList().get(3).getEdges().add(new Edge(-12,"A"));
        
        int expResult = 4;
        int result = graph.getNumberOfEdges();
        assertEquals(expResult, result);
        
        graph.getAdjacencyList().get(2).getEdges().add(new Edge(2,"B"));
        graph.getAdjacencyList().get(2).getEdges().add(new Edge(-2,"A"));
        
        expResult = 6;
        result = graph.getNumberOfEdges();
        assertEquals(expResult, result);
        
        for(Vertex vert : graph.getAdjacencyList()) {
            vert.getEdges().clear();
        }
        
        expResult = 0;
        result = graph.getNumberOfEdges();
        assertEquals(expResult, result);
    }

    /**
     * Test of getVertexData method, of class Graph.
     */
    @Test
    public void testGetVertexData() {
        Graph graph = new Graph();
        
        graph.getAdjacencyList().add(new Vertex("A"));
        graph.getAdjacencyList().add(new Vertex("B"));
        graph.getAdjacencyList().add(new Vertex("C"));
        graph.getAdjacencyList().add(new Vertex("D"));
        
        Object[][] expResult = new Object[][] {
            {"A"},
            {"B"},
            {"C"},
            {"D"}
        };
        Object[][] result = graph.getVertexData();
        assertArrayEquals(expResult, result);
        
        graph.getAdjacencyList().add(new Vertex("F"));
        expResult = new Object[][] {
            {"A"},
            {"B"},
            {"C"},
            {"D"},
            {"F"}
        };
        result = graph.getVertexData();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of getEdgesData method, of class Graph.
     */
    @Test
    public void testGetEdgesData() {
        Graph graph = new Graph();
        
        graph.getAdjacencyList().add(new Vertex("A"));
        graph.getAdjacencyList().add(new Vertex("B"));
        graph.getAdjacencyList().add(new Vertex("C"));
        graph.getAdjacencyList().add(new Vertex("D"));
        
        graph.getAdjacencyList().get(0).getEdges().add(new Edge(12,"B"));
        graph.getAdjacencyList().get(0).getEdges().add(new Edge(122,"D"));
        graph.getAdjacencyList().get(1).getEdges().add(new Edge(132,"C"));
        graph.getAdjacencyList().get(3).getEdges().add(new Edge(-12,"A"));
        
        Object[][] expResult = new Object[][] {
            {"A","B",12.d},
            {"A","D",122.d},
            {"B","C",132.d},
            {"D","A",-12.d}
        };
        Object[][] result = graph.getEdgesData();
        assertArrayEquals(expResult, result);
        
        graph.getAdjacencyList().get(2).getEdges().add(new Edge(-2,"A"));
        graph.getAdjacencyList().get(2).getEdges().add(new Edge(2,"B"));
        
        expResult = new Object[][] {
            {"A","B",12.d},
            {"A","D",122.d},
            {"B","C",132.d},
            {"C","A",-2.d},
            {"C","B",2.d},
            {"D","A",-12.d}
        };
        result = graph.getEdgesData();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of reset method, of class Graph.
     */
    @Test
    public void testReset() {
        Graph graph = new Graph();
        
        graph.getAdjacencyList().add(new Vertex("A"));
        graph.getAdjacencyList().add(new Vertex("B"));
        graph.getAdjacencyList().add(new Vertex("C"));
        graph.getAdjacencyList().add(new Vertex("D"));
        
        graph.getAdjacencyList().get(0).getEdges().add(new Edge(12,"B"));
        graph.getAdjacencyList().get(0).getEdges().add(new Edge(122,"D"));
        graph.getAdjacencyList().get(1).getEdges().add(new Edge(132,"C"));
        graph.getAdjacencyList().get(3).getEdges().add(new Edge(-12,"A"));
        
        graph.reset();
        
        assertEquals(new LinkedList<>(), graph.getAdjacencyList());
    }
    
}
