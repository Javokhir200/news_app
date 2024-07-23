package uz.lee.news_app.exceptions;

public class SourceAlreadyExistException extends RuntimeException{

    public SourceAlreadyExistException(String message) {
        super(message);
    }
}
