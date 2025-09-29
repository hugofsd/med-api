package med.voll.api.medico;

import jakarta.validation.Valid; // Anotação para acionar a validação de um objeto aninhado.
import jakarta.validation.constraints.Email; // Anotação que valida se o campo é um endereço de e-mail bem formado.
import jakarta.validation.constraints.NotBlank; // Anotação que garante que o campo não seja nulo e nem contenha apenas espaços em branco.
import jakarta.validation.constraints.NotNull; // Anotação que valida que o campo não é nulo.
import jakarta.validation.constraints.Pattern; // Anotação que valida se o campo corresponde a uma expressão regular.
import med.voll.api.endereco.DadosEndereco; // Importa o DTO para os dados de endereço.

public record DadosCadastroMedico(
        @NotNull
        @NotBlank
        String nome,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,

        @NotNull
        Especialidade especialidade,

        @NotBlank
        String telefone,

        @NotNull
        @Valid
        DadosEndereco endereco) {
}
