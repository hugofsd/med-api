package med.voll.api.domain.consulta;

import med.voll.api.domain.ValidationException;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaDeConsultas {

    @Autowired
     private  ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public void agendar (DadosAgendamentoConsulta dados){

        if(medicoRepository.existsById(dados.idPaciente())){
            throw new ValidationException("Id do paciente informado não existe!");
        }

        if(dados.idPaciente() != null && medicoRepository.existsById(dados.idMedico())){
            throw new ValidationException("Id do medico informado não existe!");
        }

        var paciente = pacienteRepository.findById(dados.idPaciente()).get();
        var medico = escolherMedico(dados);
        var consulta = new Consulta(null, medico, paciente, dados.data());
        consultaRepository.save(consulta);
     }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {

    }
}
