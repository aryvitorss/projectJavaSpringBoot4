package med.voll.api.domain.validacoes.agendamento;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;

@Component
public class ValidadorHorarioComAntecedencia implements ValidadorAgendamentoDeConsulta {

    public void validar(DadosAgendamentoConsulta dados){

        var dataConsulta = dados.data();
        var agora = LocalDateTime.now();
        var diferencaEmMInutos = Duration.between(agora, dataConsulta).toMinutes();
        
        if(diferencaEmMInutos < 30){
            throw new ValidacaoException("Consulta deve seragendada com antecedência mínima de 30 minutos");
        }

    }

}
