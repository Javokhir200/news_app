package uz.lee.news_app.exceptions;

public class SourceIsNotExistException extends RuntimeException{
    public SourceIsNotExistException(String message) {
        super(message);
    }
}
