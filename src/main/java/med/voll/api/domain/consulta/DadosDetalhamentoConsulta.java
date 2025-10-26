package med.voll.api.domain.consulta;

import med.voll.api.domain.consulta.model.Consulta;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta(
        Long id,
        Long idMedico,
        Long idPaciente,
        LocalDateTime date) {

    public DadosDetalhamentoConsulta(Consulta consulta) {
    this(
            consulta.getId(),
            consulta.getMedico().getId(),
            consulta.getPaciente().getId(),
            consulta.getData());
    }
}
