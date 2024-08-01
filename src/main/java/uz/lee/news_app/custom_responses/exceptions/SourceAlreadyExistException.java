package uz.lee.news_app.custom_responses.exceptions;

public class SourceAlreadyExistException extends RuntimeException{

    public SourceAlreadyExistException(String message) {
        super(message);
    }
}
