package pl.polsl.przyszlak.model;

import java.util.Comparator;

/**
 * Comparator class for vertex.
 * @author Sebastian Przyszlak
 * @version 1.0.5
 */
public class SortVertex implements Comparator<Vertex> {
    
    /**
     * compare label of vertex.
     * @param a label of first vertex
     * @param b label of secound vertex
     * @return 0 if a is equal b, more than 0 if a is bigger b, less than 0 if b is bigger a
     */
    @Override
    public int compare(Vertex a, Vertex b) {
        return a.getLabel().compareTo(b.getLabel()); 
    } 
} 
