package pl.polsl.przyszlak.test.model;

import org.junit.Test;
import pl.polsl.przyszlak.model.Edge;
import pl.polsl.przyszlak.model.Vertex;
import static org.junit.Assert.*;

/**
 * Test for Vertex class.
 * @author Sebastian Przyszlak
 * @version 1.0
 */
public class VertexTest {

    /**
     * Test of addEdge method, of class Vertex.
     */
    @Test
    public void testAddEdge() {
        Vertex vertex = new Vertex();
        
        vertex.addEdge(new Edge(12,"A"));
        vertex.addEdge(new Edge(1.4335,"B"));
        vertex.addEdge(new Edge(-2,"F"));
        vertex.addEdge(new Edge(2,"G"));
        vertex.addEdge(new Edge(422,"2"));
        vertex.addEdge(new Edge(-122,"Skok"));
        
        boolean[] expResult = new boolean[] {true, true, true, true, true, true};
        boolean[] result = new boolean[] {false, false, false, false, false, false};
        
        for(Edge edg : vertex.getEdges()) {
            if(edg.getEndVertexLabel().equals("A") && edg.getWeight() == 12.d) {
                result[0] = true;
            } else if(edg.getEndVertexLabel().equals("B") && edg.getWeight() == 1.4335d) {
                result[1] = true;
            } else if(edg.getEndVertexLabel().equals("F") && edg.getWeight() == -2.d) {
                result[2] = true;
            } else if(edg.getEndVertexLabel().equals("G") && edg.getWeight() == 2.d) {
                result[3] = true;
            } else if(edg.getEndVertexLabel().equals("2") && edg.getWeight() == 422.d) {
                result[4] = true;
            } else if(edg.getEndVertexLabel().equals("Skok") && edg.getWeight() == -122.d) {
                result[5] = true;
            }
        }
        
        assertArrayEquals(expResult,result);
    }
}