package pl.polsl.przyszlak.test.model;

import java.util.List;
import java.util.LinkedList;
import org.junit.Test;
import pl.polsl.przyszlak.model.Edge;
import pl.polsl.przyszlak.model.FloydWarshallAlgorithm;
import pl.polsl.przyszlak.model.Vertex;
import static org.junit.Assert.*;

/**
 * Test for FloydWarshallAlgorithm class.
 * @author Sebastian Przyszlak
 * @version 1.0
 */
public class FloydWarshallAlgorithmTest {

    /**
     * Test of inicjalization method, of class FloydWarshallAlgorithm.
     */
    @Test
    public void testDoAlgorithm() {        
        FloydWarshallAlgorithm fwAlgorithm;
        fwAlgorithm = new FloydWarshallAlgorithm();
        
        // Empty string
        String[] list = new String[]{""};
        List<Vertex> adjacencyList = new LinkedList<>();
        adjacencyList.add(new Vertex(""));
        fwAlgorithm.doAlgorithm(adjacencyList, list);
        
        assertEquals("0. Wrong length of listOfLabels", fwAlgorithm.getListOfLabels().length, list.length, 0);
        assertEquals("0. Wrong size of distanceMatrix", fwAlgorithm.getDistanceMatrix().length,  list.length, 0);
        for(double[] distance : fwAlgorithm.getDistanceMatrix()) {
            assertEquals("0. Wrong size of distanceMatrix", distance.length,  list.length, 0);
        }
        assertEquals("0. Wrong size of pathMatrix", fwAlgorithm.getPathMatrix().length,  list.length, 0);
        for(String[] path : fwAlgorithm.getPathMatrix()) {
            assertEquals("0. Wrong size of pathMatrix", path.length,  list.length, 0);
        }
        assertArrayEquals("0. Collections values ​​in both arrays are not identical!", fwAlgorithm.getListOfLabels(), list);
      
        // Empty list
        list = new String[]{};
        adjacencyList = new LinkedList<>();
        fwAlgorithm.doAlgorithm(adjacencyList, list);
        
        assertEquals("1. Wrong length of listOfLabels", fwAlgorithm.getListOfLabels().length, list.length, 0);
        assertEquals("1. Wrong size of distanceMatrix", fwAlgorithm.getDistanceMatrix().length,  list.length, 0);
        for(double[] distance : fwAlgorithm.getDistanceMatrix()) {
            assertEquals("1. Wrong size of distanceMatrix", distance.length,  list.length, 0);
        }
        assertEquals("1. Wrong size of pathMatrix", fwAlgorithm.getPathMatrix().length,  list.length, 0);
        for(String[] path : fwAlgorithm.getPathMatrix()) {
            assertEquals("1. Wrong size of pathMatrix", path.length,  list.length, 0);
        }
        assertArrayEquals("1. Collections values ​​in both arrays are not identical!", fwAlgorithm.getListOfLabels(), list);
        
        // Empty list 2
        list = new String[0];
        adjacencyList = new LinkedList<>();
        fwAlgorithm.doAlgorithm(adjacencyList, list);
        
        assertEquals("2. Wrong length of listOfLabels", fwAlgorithm.getListOfLabels().length, list.length, 0);
        assertEquals("2. Wrong size of distanceMatrix", fwAlgorithm.getDistanceMatrix().length,  list.length, 0);
        for(double[] distance : fwAlgorithm.getDistanceMatrix()) {
            assertEquals("2. Wrong size of distanceMatrix", distance.length,  list.length, 0);
        }
        assertEquals("2. Wrong size of pathMatrix", fwAlgorithm.getPathMatrix().length,  list.length, 0);
        for(String[] path : fwAlgorithm.getPathMatrix()) {
            assertEquals("2. Wrong size of pathMatrix", path.length,  list.length, 0);
        }
        assertArrayEquals("2. Collections values ​​in both arrays are not identical!", fwAlgorithm.getListOfLabels(), list);
      
        // List with some strings
        list = new String[]{"A","B","C","4","Zabrze", ""};
        adjacencyList = new LinkedList<>();
        adjacencyList.add(new Vertex("A"));
        adjacencyList.add(new Vertex("B"));
        adjacencyList.add(new Vertex("C"));
        adjacencyList.add(new Vertex("4"));
        adjacencyList.add(new Vertex("Zabrze"));
        adjacencyList.add(new Vertex(""));
        fwAlgorithm.doAlgorithm(adjacencyList, list);
        
        assertEquals("3. Wrong length of listOfLabels", fwAlgorithm.getListOfLabels().length, list.length, 0);
        assertEquals("3. Wrong size of distanceMatrix", fwAlgorithm.getDistanceMatrix().length,  list.length, 0);
        for(double[] distance : fwAlgorithm.getDistanceMatrix()) {
            assertEquals("3. Wrong size of distanceMatrix", distance.length,  list.length, 0);
        }
        assertEquals("3. Wrong size of pathMatrix", fwAlgorithm.getPathMatrix().length,  list.length, 0);
        for(String[] path : fwAlgorithm.getPathMatrix()) {
            assertEquals("3. Wrong size of pathMatrix", path.length,  list.length, 0);
        }
        assertArrayEquals("3. Collections values ​​in both arrays are not identical!", fwAlgorithm.getListOfLabels(), list);
    }
    
    /**
     * Test of negativeCycle method, of class FloydWarshallAlgorithm.
     */
    @Test
    public void testNegativeCycle() {        
        FloydWarshallAlgorithm fwAlgorithm;
        fwAlgorithm = new FloydWarshallAlgorithm();
        
        // Empty
        String[] list = new String[]{};
        List<Vertex> adjacencyList = new LinkedList<>();
        fwAlgorithm.doAlgorithm(adjacencyList, list);
        
        boolean expResult = false;
        boolean result = fwAlgorithm.negativeCycle();
        assertEquals(expResult, result);
        
        // without edges
        list = new String[]{"A", "B", "C"};
        adjacencyList = new LinkedList<>();
        adjacencyList.add(new Vertex("A"));
        adjacencyList.add(new Vertex("B"));
        adjacencyList.add(new Vertex("C"));
        fwAlgorithm.doAlgorithm(adjacencyList, list);
        expResult = false;
        result = fwAlgorithm.negativeCycle();
        assertEquals(expResult, result);
        
        // with edges, no negative cycle expected
        list = new String[]{"A", "B", "C"};
        adjacencyList = new LinkedList<>();
        adjacencyList.add(new Vertex("A"));
        adjacencyList.add(new Vertex("B"));
        adjacencyList.add(new Vertex("C"));
        adjacencyList.get(0).addEdge(new Edge(20.0f,"B"));      // A -> B 20
        adjacencyList.get(0).addEdge(new Edge(3.0f,"C"));       // A -> C 3
        adjacencyList.get(1).addEdge(new Edge(9.0f,"C"));       // B -> C 9
        adjacencyList.get(1).addEdge(new Edge(2.5f,"A"));       // B -> A 2.5
        adjacencyList.get(2).addEdge(new Edge(1.0f,"B"));       // C -> B 1     // A -> C -> B 4
        fwAlgorithm.doAlgorithm(adjacencyList, list);
        expResult = false;
        result = fwAlgorithm.negativeCycle();
        assertEquals(expResult, result);
        
        // with edges, negative cycle expected
        list = new String[]{"A", "B", "C"};
        adjacencyList = new LinkedList<>();
        adjacencyList.add(new Vertex("A"));
        adjacencyList.add(new Vertex("B"));
        adjacencyList.add(new Vertex("C"));
        adjacencyList.get(0).addEdge(new Edge(-20.0f,"B"));     // A -> B -20
        adjacencyList.get(0).addEdge(new Edge(3.0f,"C"));       // A -> C 3
        adjacencyList.get(1).addEdge(new Edge(9.0f,"C"));       // B -> C 9
        adjacencyList.get(1).addEdge(new Edge(2.5f,"A"));       // B -> A 2.5   // A -> B -> A < 0
        adjacencyList.get(2).addEdge(new Edge(1.0f,"B"));       // C -> B 1
        fwAlgorithm.doAlgorithm(adjacencyList, list);
        expResult = true;
        result = fwAlgorithm.negativeCycle();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getDistanceData method, of class FloydWarshallAlgorithm.
     */
    @Test
    public void testGetDistanceData() {
        FloydWarshallAlgorithm fwAlgorithm;
        fwAlgorithm = new FloydWarshallAlgorithm();
        
        // Empty
        String[] list = new String[]{};
        List<Vertex> adjacencyList = new LinkedList<>();
        fwAlgorithm.doAlgorithm(adjacencyList, list);
        Object[][] expResult = new Object[][]{};
        Object[][] result = fwAlgorithm.getDistanceData();
        assertArrayEquals(expResult, result);
        
        // without edges
        list = new String[]{"A", "B", "C"};
        adjacencyList = new LinkedList<>();
        adjacencyList.add(new Vertex("A"));
        adjacencyList.add(new Vertex("B"));
        adjacencyList.add(new Vertex("C"));
        fwAlgorithm.doAlgorithm(adjacencyList, list);
        expResult = new Object[3][4];
        expResult[0][0] = "A |";
        expResult[0][1] = 0.0;
        expResult[0][2] = Double.POSITIVE_INFINITY;
        expResult[0][3] = Double.POSITIVE_INFINITY;
        expResult[1][0] = "B |";
        expResult[1][1] = Double.POSITIVE_INFINITY;
        expResult[1][2] = 0.0;
        expResult[1][3] = Double.POSITIVE_INFINITY;
        expResult[2][0] = "C |";
        expResult[2][1] = Double.POSITIVE_INFINITY;
        expResult[2][2] = Double.POSITIVE_INFINITY;
        expResult[2][3] = 0.0;
        result = fwAlgorithm.getDistanceData();
        assertArrayEquals(expResult, result);
        
        // with edges, no negative cycle expected
        list = new String[]{"A", "B", "C"};
        adjacencyList = new LinkedList<>();
        adjacencyList.add(new Vertex("A"));
        adjacencyList.add(new Vertex("B"));
        adjacencyList.add(new Vertex("C"));
        adjacencyList.get(0).addEdge(new Edge(20.0f,"B"));      // A -> B 20
        adjacencyList.get(0).addEdge(new Edge(3.0f,"C"));       // A -> C 3
        adjacencyList.get(1).addEdge(new Edge(9.0f,"C"));       // B -> C 9
        adjacencyList.get(1).addEdge(new Edge(2.5f,"A"));       // B -> A 2.5   // B -> A -> C 5.5
        adjacencyList.get(2).addEdge(new Edge(1.0f,"B"));       // C -> B 1     // A -> C -> B 4    // C->B->A 3.5
        fwAlgorithm.doAlgorithm(adjacencyList, list);
        expResult = new Object[3][4];
        expResult[0][0] = "A |";
        expResult[0][1] = 0.0;
        expResult[0][2] = 4.0;
        expResult[0][3] = 3.0;
        expResult[1][0] = "B |";
        expResult[1][1] = 2.5;
        expResult[1][2] = 0.0;
        expResult[1][3] = 5.5;
        expResult[2][0] = "C |";
        expResult[2][1] = 3.5;
        expResult[2][2] = 1.0;
        expResult[2][3] = 0.0;
        result = fwAlgorithm.getDistanceData();
        assertArrayEquals(expResult, result);
        
        // with edges, no negative cycle expected
        list = new String[]{"A", "B", "C", "D", "E"};
        adjacencyList = new LinkedList<>();
        adjacencyList.add(new Vertex("A"));
        adjacencyList.add(new Vertex("B"));
        adjacencyList.add(new Vertex("C"));
        adjacencyList.add(new Vertex("D"));
        adjacencyList.add(new Vertex("E"));
        adjacencyList.get(0).addEdge(new Edge(1.f,"B"));
        adjacencyList.get(1).addEdge(new Edge(2.f,"C"));
        adjacencyList.get(1).addEdge(new Edge(7.f,"E"));
        adjacencyList.get(2).addEdge(new Edge(-1.f,"B"));
        adjacencyList.get(2).addEdge(new Edge(3.f,"D"));
        adjacencyList.get(3).addEdge(new Edge(2.f,"C"));
        adjacencyList.get(4).addEdge(new Edge(2.f,"D"));
        adjacencyList.get(4).addEdge(new Edge(3.f,"A"));
        fwAlgorithm.doAlgorithm(adjacencyList, list);
        expResult = new Object[5][6];
        expResult[0][0] = "A |";
        expResult[0][1] = 0.d;
        expResult[0][2] = 1.d;
        expResult[0][3] = 3.d;
        expResult[0][4] = 6.d;
        expResult[0][5] = 8.d;
        expResult[1][0] = "B |";
        expResult[1][1] = 10.d;
        expResult[1][2] = 0.d;
        expResult[1][3] = 2.d;
        expResult[1][4] = 5.d;
        expResult[1][5] = 7.d;
        expResult[2][0] = "C |";
        expResult[2][1] = 9.d;
        expResult[2][2] = -1.d;
        expResult[2][3] = 0.d;
        expResult[2][4] = 3.d;
        expResult[2][5] = 6.d;
        expResult[3][0] = "D |";
        expResult[3][1] = 11.d;
        expResult[3][2] = 1.d;
        expResult[3][3] = 2.d;
        expResult[3][4] = 0.d;
        expResult[3][5] = 8.d;
        expResult[4][0] = "E |";
        expResult[4][1] = 3.d;
        expResult[4][2] = 3.d;
        expResult[4][3] = 4.d;
        expResult[4][4] = 2.d;
        expResult[4][5] = 0.d;
        result = fwAlgorithm.getDistanceData();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of getPathData method, of class FloydWarshallAlgorithm.
     */
    @Test
    public void testGetPathData() {
        FloydWarshallAlgorithm fwAlgorithm;
        fwAlgorithm = new FloydWarshallAlgorithm();
        
        // Empty
        String[] list = new String[]{};
        List<Vertex> adjacencyList = new LinkedList<>();
        fwAlgorithm.doAlgorithm(adjacencyList, list);
        Object[][] expResult = new Object[][]{};
        Object[][] result = fwAlgorithm.getDistanceData();
        assertArrayEquals(expResult, result);
        
        // without edges
        list = new String[]{"A", "B", "C"};
        adjacencyList = new LinkedList<>();
        adjacencyList.add(new Vertex("A"));
        adjacencyList.add(new Vertex("B"));
        adjacencyList.add(new Vertex("C"));
        fwAlgorithm.doAlgorithm(adjacencyList, list);
        expResult = new Object[3][4];
        expResult[0][0] = "A |";
        expResult[0][1] = "A";
        expResult[0][2] = "";
        expResult[0][3] = "";
        expResult[1][0] = "B |";
        expResult[1][1] = "";
        expResult[1][2] = "B";
        expResult[1][3] = "";
        expResult[2][0] = "C |";
        expResult[2][1] = "";
        expResult[2][2] = "";
        expResult[2][3] = "C";
        result = fwAlgorithm.getPathData();
        assertArrayEquals(expResult, result);
        
        // with edges, no negative cycle expected
        list = new String[]{"A", "B", "C"};
        adjacencyList = new LinkedList<>();
        adjacencyList.add(new Vertex("A"));
        adjacencyList.add(new Vertex("B"));
        adjacencyList.add(new Vertex("C"));
        adjacencyList.get(0).addEdge(new Edge(20.0f,"B"));      // A -> B 20
        adjacencyList.get(0).addEdge(new Edge(3.0f,"C"));       // A -> C 3
        adjacencyList.get(1).addEdge(new Edge(9.0f,"C"));       // B -> C 9
        adjacencyList.get(1).addEdge(new Edge(2.5f,"A"));       // B -> A 2.5   // B -> A -> C 5.5
        adjacencyList.get(2).addEdge(new Edge(1.0f,"B"));       // C -> B 1     // A -> C -> B 4    // C->B->A 3.5
        fwAlgorithm.doAlgorithm(adjacencyList, list);
        expResult = new Object[3][4];
        expResult[0][0] = "A |";
            expResult[0][1] = "A";
            expResult[0][2] = "C";
            expResult[0][3] = "C";
        expResult[1][0] = "B |";
            expResult[1][1] = "A";
            expResult[1][2] = "B";
            expResult[1][3] = "A";
        expResult[2][0] = "C |";
            expResult[2][1] = "B";
            expResult[2][2] = "B";
            expResult[2][3] = "C";
        result = fwAlgorithm.getPathData();
        assertArrayEquals(expResult, result);
        
        // with edges, no negative cycle expected
        list = new String[]{"A", "B", "C", "D", "E"};
        adjacencyList = new LinkedList<>();
        adjacencyList.add(new Vertex("A"));
        adjacencyList.add(new Vertex("B"));
        adjacencyList.add(new Vertex("C"));
        adjacencyList.add(new Vertex("D"));
        adjacencyList.add(new Vertex("E"));
        adjacencyList.get(0).addEdge(new Edge(1.f,"B"));
        adjacencyList.get(1).addEdge(new Edge(2.f,"C"));
        adjacencyList.get(1).addEdge(new Edge(7.f,"E"));
        adjacencyList.get(2).addEdge(new Edge(-1.f,"B"));
        adjacencyList.get(2).addEdge(new Edge(3.f,"D"));
        adjacencyList.get(3).addEdge(new Edge(2.f,"C"));
        adjacencyList.get(4).addEdge(new Edge(2.f,"D"));
        adjacencyList.get(4).addEdge(new Edge(3.f,"A"));
        fwAlgorithm.doAlgorithm(adjacencyList, list);
        expResult = new Object[5][6];
        expResult[0][0] = "A |";
            expResult[0][1] = "A";//A-A
            expResult[0][2] = "B";//A-B
            expResult[0][3] = "B";//A-C
            expResult[0][4] = "B";//A-D
            expResult[0][5] = "B";//A-E
        expResult[1][0] = "B |";
            expResult[1][1] = "E";//B-A
            expResult[1][2] = "B";//B-B
            expResult[1][3] = "C";//B-C
            expResult[1][4] = "C";//B-D
            expResult[1][5] = "E";//B-E
        expResult[2][0] = "C |";
            expResult[2][1] = "B";//C-A
            expResult[2][2] = "B";//C-B
            expResult[2][3] = "C";//C-C
            expResult[2][4] = "D";//C-D
            expResult[2][5] = "B";//C-E
        expResult[3][0] = "D |";
            expResult[3][1] = "C";//D-A
            expResult[3][2] = "C";//D-B
            expResult[3][3] = "C";//D-C
            expResult[3][4] = "D";//D-D
            expResult[3][5] = "C";//D-E
        expResult[4][0] = "E |";
            expResult[4][1] = "A";//E-A
            expResult[4][2] = "D";//E-B
            expResult[4][3] = "D";//E-C
            expResult[4][4] = "D";//E-D
            expResult[4][5] = "E";//E-E
        result = fwAlgorithm.getPathData();
        assertArrayEquals(expResult, result);
    }
}