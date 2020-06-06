package pl.polsl.przyszlak.controller;

/**
 * Exception for negative cycle.
 * @author Seastian Przyszlak
 * @version 1.0
 */
public class NegativeCycleException extends Exception {
    private String errorMessage;
    
    /**
     * Class constructor.
     * Sets errorMessage.
     * @param errorMessage error text 
     */
    public NegativeCycleException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
}