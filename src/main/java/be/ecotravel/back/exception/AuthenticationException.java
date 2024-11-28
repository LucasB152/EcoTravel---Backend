package be.ecotravel.back.exception;

public class AuthenticationException extends Exception{
    public AuthenticationException(String message){
        super(message);
    }
}
