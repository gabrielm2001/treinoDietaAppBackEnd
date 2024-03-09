package treinoDieta.api.infra.security.exceptions;

import org.aspectj.bridge.IMessage;

public class ValidacaoException extends RuntimeException{
    public ValidacaoException(String message){
        super(message);
    }
}
