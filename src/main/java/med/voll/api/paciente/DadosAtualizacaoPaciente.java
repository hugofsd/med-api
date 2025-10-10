package med.voll.api.paciente;

import jakarta.persistence.Embedded;
import jakarta.validation.constraints.NotNull;
import med.voll.api.endereco.DadosEndereco;
import med.voll.api.endereco.Endereco;

public record DadosAtualizacaoPaciente(
        @NotNull
        Long id,
        String nome,
        String email,
        String cpf,
        String telefone,
        DadosEndereco endereco
) {


}
