package pl.polsl.przyszlak.model;

import java.util.Comparator;

/**
 * Comparator class for vertex.
 * @author Sebastian Przyszlak
 * @version 1.0
 */
public class SortVertex implements Comparator<Vertex> {
    
    /**
     * compare label of vertex.
     * @param a label of first vertex
     * @param b label of secound vertex
     * @return 0 if a is equal b, 1 if a is bigger b, - 1 if b is bigger a
     */
    public int compare(Vertex a, Vertex b){ 
        return a.getLabel().compareTo(b.getLabel()); 
    } 
} 
