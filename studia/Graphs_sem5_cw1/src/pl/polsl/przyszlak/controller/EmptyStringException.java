package pl.polsl.przyszlak.controller;

/**
 * Exception for empty string.
 * @author Sebastian Przyszlak
 * @version 1.0
 */
public class EmptyStringException extends Exception {
    private String errorMessage;
    
    /**
     * Class constructor.
     * Sets errorMessage.
     * @param errorMessage error text 
     */
    public EmptyStringException(String errorMessage) {
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