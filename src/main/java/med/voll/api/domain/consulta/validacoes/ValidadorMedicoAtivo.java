package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidationException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidadorMedicoAtivo {

    private MedicoRepository repository;

    public void validar (DadosAgendamentoConsulta dados){
        if(dados.idMedico() == null){
            return;
        }

        var medicoEstaAtivo = repository.findByAtivoById(dados.idMedico());

        if (!medicoEstaAtivo){
            throw new ValidationException("O medico selecionado não está ativo");
        }
    }



}
