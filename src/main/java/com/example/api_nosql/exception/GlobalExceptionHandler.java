package com.example.api_nosql.exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Map<String, Object>> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex) {
        return buildResponse(
                HttpStatus.NOT_FOUND,
                "Nenhum registro foi encontrado com o ID informado. "
                        + "Verifique se o identificador está correto antes de tentar novamente."
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return buildResponse(
                HttpStatus.BAD_REQUEST,
                "O corpo da requisição está inválido ou mal formatado. "
                        + "Certifique-se de enviar um JSON válido com os campos esperados. "
                        + "Erro técnico: " + ex.getMostSpecificCause().getMessage()
        );
    }

    @ExceptionHandler(IlegalStatusChange.class)
    public ResponseEntity<Map<String, Object>> handleIlegalDataException(IlegalStatusChange ex) {
        return buildResponse(
                HttpStatus.BAD_REQUEST,
                "Alteração de status inválida: " + ex.getMessage()
                        + ". Essa operação não é permitida para o estado atual do recurso."
        );
    }

    @ExceptionHandler(ExistingMatch.class)
    public ResponseEntity<Map<String, Object>> handleExistingMatch(ExistingMatch ex) {
        return buildResponse(
                HttpStatus.BAD_REQUEST,
                "Ocorreu um conflito ao tentar criar o registro: " + ex.getMessage()
                        + ". Já existe um registro ativo com as mesmas informações."
        );
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> handleDataIntegrity(DataIntegrityViolationException ex) {
        return buildResponse(
                HttpStatus.CONFLICT,
                "Violação de integridade dos dados detectada. "
                        + "Isso pode ocorrer quando há duplicidade de chaves únicas, valores nulos indevidos ou restrições do banco de dados. "
                        + "Detalhes técnicos: " + ex.getMostSpecificCause().getMessage()
        );
    }

    @ExceptionHandler(JsonMappingException.class)
    public ResponseEntity<Map<String, Object>> handleJsonMapping(JsonMappingException ex) {
        return buildResponse(
                HttpStatus.BAD_REQUEST,
                "Erro ao processar o corpo JSON da requisição. "
                        + "Verifique se os campos enviados correspondem aos tipos esperados pelo servidor. "
                        + "Campo com erro: " + (ex.getPathReference() != null ? ex.getPathReference() : "desconhecido")
                        + ". Detalhes técnicos: " + ex.getOriginalMessage()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException ex) {
        StringBuilder message = new StringBuilder("Erro de validação encontrado nos seguintes campos: ");
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            message.append(String.format("[%s: %s] ", error.getField(), error.getDefaultMessage()));
        }
        message.append("Verifique os valores informados e tente novamente.");
        return buildResponse(HttpStatus.BAD_REQUEST, message.toString());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, Object>> handleConstraintViolationException(ConstraintViolationException ex) {
        StringBuilder message = new StringBuilder("Erro de validação: ");
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            message.append(String.format("[%s: %s] ", violation.getPropertyPath(), violation.getMessage()));
        }
        message.append("Verifique os parâmetros informados na requisição.");
        return buildResponse(HttpStatus.BAD_REQUEST, message.toString());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneric(Exception ex) {
        return buildResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Erro interno inesperado: " + ex.getMessage()
                        + ". Se o problema persistir, entre em contato com o suporte técnico."
        );
    }

    private ResponseEntity<Map<String, Object>> buildResponse(HttpStatus status, String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        body.put("timestamp", System.currentTimeMillis());
        return ResponseEntity.status(status).body(body);
    }
}
