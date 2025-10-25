package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidationException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;

import java.time.DayOfWeek;

public class ValidadorHorarioFuncionamentoClinica {


    public void validar (DadosAgendamentoConsulta dados){

     var dataConsulta = dados.data();

     var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
     var antesDaAberturaDaClinica = dataConsulta.getHour() <7;
     var depoisDoEncerramentoDaClinica = dataConsulta.getHour() >18;

     if(domingo || antesDaAberturaDaClinica || depoisDoEncerramentoDaClinica ){
         throw new ValidationException("Consulta fora do Horario de funcionamento da clinica");
     }
    }

}
