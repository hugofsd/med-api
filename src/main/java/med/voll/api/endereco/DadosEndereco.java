package med.voll.api.endereco;

import jakarta.validation.constraints.NotBlank; // Anotação que garante que o campo não seja nulo e nem contenha apenas espaços em branco.
import jakarta.validation.constraints.Pattern; // Anotação que valida se o campo corresponde a uma expressão regular.

public record DadosEndereco(

        @NotBlank
        String logradouro,

        @NotBlank
        String bairro,

        @NotBlank
        @Pattern(regexp = "\\d{8}")
        String cep,

        @NotBlank
        String cidade,

        @NotBlank
        String uf,

        String complemento,

        String numero
) {
}
