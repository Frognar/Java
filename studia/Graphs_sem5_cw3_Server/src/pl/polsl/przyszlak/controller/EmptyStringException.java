package pl.polsl.przyszlak.controller;

/**
 * Exception for empty string.
 * @author Sebastian Przyszlak
 * @version 1.1
 */
public class EmptyStringException extends Exception {
    
    /** Error message to diplay */
    private String errorMessage;

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
    
    @Override
    public String getMessage(){
        return this.getErrorMessage();
    }
    /**
     * Class constructor.
     * Sets errorMessage.
     * @param errorMessage error text 
     */
    public EmptyStringException(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}