package br.com.votacao.exceptions.handler;

import br.com.votacao.exceptions.SecaoException;
import br.com.votacao.exceptions.VotacaoException;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerException {

    @ExceptionHandler(SecaoException.class)
    public ResponseEntity<ErrorMessage> catchHttpMessageNotReadableException(SecaoException e){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessage(e.getMessage()));
    }
    @ExceptionHandler(VotacaoException.class)
    public ResponseEntity<ErrorMessage> catchHttpMessageNotReadableException(VotacaoException e){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessage(e.getMessage()));
    }

}
