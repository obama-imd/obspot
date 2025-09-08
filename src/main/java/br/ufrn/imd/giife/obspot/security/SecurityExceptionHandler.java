package br.ufrn.imd.giife.obspot.security;

import br.ufrn.imd.giife.obspot.common.controller.dto.ResponseDTO;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "br.ufrn.imd.giife.obspot.security")
public class SecurityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(SecurityExceptionHandler.class);
    private static final String INVALID_LOGIN_MESSAGE = "Login inválido!";


    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ResponseDTO> handleSignatureException(SignatureException ex) {
        log.warn("A assinatura do token JWT é inválida: {}", ex.getMessage());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(
                        new ResponseDTO(
                                HttpStatus.UNAUTHORIZED.value(),
                                "Login não autorizado!"
                        )
                );
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<ResponseDTO> handleMalformedJwtException(MalformedJwtException ex) {
        log.warn("Token JWT inválido: {}", ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        new ResponseDTO(
                                HttpStatus.BAD_REQUEST.value(),
                                INVALID_LOGIN_MESSAGE
                        )
                );
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ResponseDTO> handleExpiredJwtException(ExpiredJwtException ex) {
        log.warn("Token JWT expirado: {}", ex.getMessage());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(
                        new ResponseDTO(
                                HttpStatus.UNAUTHORIZED.value(),
                                "Login expirado!"
                        )
                );
    }

    @ExceptionHandler(UnsupportedJwtException.class)
    public ResponseEntity<ResponseDTO> handleUnsupportedJwtException(UnsupportedJwtException ex) {
        log.warn("Token JWT não suportado: {}", ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        new ResponseDTO(
                                HttpStatus.BAD_REQUEST.value(),
                                INVALID_LOGIN_MESSAGE
                        )
                );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseDTO> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.warn("Token JWT claims string está vazia: {}", ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        new ResponseDTO(
                                HttpStatus.BAD_REQUEST.value(),
                                INVALID_LOGIN_MESSAGE
                        )
                );
    }
}
