package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidationException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;

public class ValidadorMedicoComOutraConsultaNoMesmoHorario {
    private ConsultaRepository repository;

    public void validar (DadosAgendamentoConsulta dados){
        var medicoPoossuiOutraConsulta = repository.existsByMedicoIdAndData(dados.idMedico(), dados.data());
        if(medicoPoossuiOutraConsulta){
            throw new ValidationException("Medico já possui outra consulta agendada nesse mesmo horário");
        }
    }
}
