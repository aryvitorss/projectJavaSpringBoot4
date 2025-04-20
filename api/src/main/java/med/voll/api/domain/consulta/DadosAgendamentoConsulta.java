package med.voll.api.domain.consulta;

import java.time.LocalDateTime;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.medico.Especialidade;

public record DadosAgendamentoConsulta(
    Long idMedico,
    
    @NotNull
    Long idPaciente,

    @NotNull
    @Future
    LocalDateTime data,
    
    @Enumerated(EnumType.STRING)
    Especialidade especialidade) {

} 