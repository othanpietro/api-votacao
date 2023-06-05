package br.com.votacao.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class SecaoException extends BaseException {

    private final String message;
    public SecaoException(String message) {
        super(HttpStatus.BAD_REQUEST);
        this.message = message;
    }
}
