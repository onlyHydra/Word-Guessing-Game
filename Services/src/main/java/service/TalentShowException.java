package service;

public class TalentShowException extends RuntimeException{
    public TalentShowException() {
    }

    public TalentShowException(String message) {
        super(message);
    }

    public TalentShowException(String message, Throwable cause) {
        super(message, cause);
    }
}