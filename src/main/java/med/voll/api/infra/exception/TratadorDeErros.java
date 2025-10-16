package med.voll.api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErros {

    // Trata erro 404 para entidade não encontrada
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404() {
        return ResponseEntity.notFound().build();
    }

    // Trata erro 400 para campos inválidos (Bean Validation)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
    }

   // Trata erro de violação de integridade (ex: CPF/email duplicado)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity tratarErroDadosDuplicados(DataIntegrityViolationException ex) {
        var mensagem = "Dados já cadastrados no sistema. Verifique o CPF e o e-mail.";
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErroSimples(mensagem));
    }

    // DTO para formatar os erros de validação
    private record DadosErroValidacao(String campo, String mensagem) {
        public DadosErroValidacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }

    // DTO para erros mais simples
    private record ErroSimples(String mensagem) {}

}
