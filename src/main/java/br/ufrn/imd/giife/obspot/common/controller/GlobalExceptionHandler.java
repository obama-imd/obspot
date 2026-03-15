package br.ufrn.imd.giife.obspot.common.controller;

import br.ufrn.imd.giife.obspot.common.controller.dto.ResponseDTO;
import br.ufrn.imd.giife.obspot.common.service.exception.EntityAlreadyExistsException;
import br.ufrn.imd.giife.obspot.common.service.exception.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseDTO> handleEntityNotFoundException(EntityNotFoundException ex) {
        log.warn(ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        new ResponseDTO(
                                HttpStatus.BAD_REQUEST.value(),
                                "Entidade não encontrada!"
                        )
                );
    }

    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<ResponseDTO> handleEntityAlreadyExistsException(EntityAlreadyExistsException ex) {
        log.warn(ex.getMessage());

        return  ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                    new ResponseDTO(
                            HttpStatus.BAD_REQUEST.value(),
                            "Entidade já existe!"
                    )
                );
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ResponseDTO> handleGenericException(Throwable ex) {
        log.error(ex.getMessage(), ex);

        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        new ResponseDTO(
                                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                "Erro no servidor"
                        )
                );
    }

}
