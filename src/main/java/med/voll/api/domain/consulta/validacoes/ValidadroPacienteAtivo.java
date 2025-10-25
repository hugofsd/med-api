package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidationException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.paciente.PacienteRepository;

public class ValidadroPacienteAtivo {

    private PacienteRepository repository;

    public void validar(DadosAgendamentoConsulta dados){
        var pacienteEstaAtivo = repository.findByAtivoById(dados.idPaciente());

        if(!pacienteEstaAtivo){
            throw  new ValidationException("Consulta não pode ser agendada com paciente excluido");
        }
    }
}
