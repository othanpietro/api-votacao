package br.com.votacao.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public abstract class BaseException extends RuntimeException{

    private final HttpStatus status;

    public BaseException(HttpStatus status) { this.status = status; }

    public BaseException(Throwable e, HttpStatus status){
        super(e);
        this.status = status;
    }


}
