package pl.polsl.przyszlak.test.model;

import org.junit.Test;
import pl.polsl.przyszlak.model.SortVertex;
import pl.polsl.przyszlak.model.Vertex;
import static org.junit.Assert.*;

/**
 * Test for SortVertex class.
 * @author Sebastian Przyszlak
 * @version 1.0
 */
public class SortVertexTest {
    
    public SortVertexTest() {
    }

    /**
     * Test of compare method, of class SortVertex.
     */
    @Test
    public void testCompare() {
        SortVertex sortVertex = new SortVertex();
        
        int expResult = 'A' - 'B';
        int result = sortVertex.compare(new Vertex("A"), new Vertex("B"));
        assertEquals(expResult, result);
        
        expResult = 'B' - 'A';
        result = sortVertex.compare(new Vertex("B"), new Vertex("A"));
        assertEquals(expResult, result);
        
        expResult = 'A' - 'A';
        result = sortVertex.compare(new Vertex("A"), new Vertex("A"));
        assertEquals(expResult, result);
        
        expResult = ('z' - 'Z');
        result = sortVertex.compare(new Vertex("zabrze"), new Vertex("Zabrze"));
        assertEquals(expResult, result);
        
        expResult = ('b' - 'B');
        result = sortVertex.compare(new Vertex("zabrze"), new Vertex("zaBrze"));
        assertEquals(expResult, result);
        
        expResult = ('Z' - 'G');
        result = sortVertex.compare(new Vertex("Zabrze"), new Vertex("Gliwice"));
        assertEquals(expResult, result);
    }
    
}
