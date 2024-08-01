package uz.lee.news_app.custom_responses.exceptions;

public class SourceIsNotExistException extends RuntimeException{
    public SourceIsNotExistException(String message) {
        super(message);
    }
}
