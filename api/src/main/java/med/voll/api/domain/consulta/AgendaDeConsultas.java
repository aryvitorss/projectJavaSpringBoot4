package med.voll.api.domain.consulta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.domain.validacoes.agendamento.ValidadorAgendamentoDeConsulta;
import med.voll.api.domain.validacoes.cancelamento.ValidadorCancelamentoDeConsulta;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorCancelamentoDeConsulta> validadoresCancelamento;

    @Autowired
    private List<ValidadorAgendamentoDeConsulta> validadores;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados){

        if(!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoException("Id do paciente informado não existe!");
        }

        if(dados.idMedico() != null){
            if(!medicoRepository.existsById(dados.idMedico())){
                throw new ValidacaoException("Id do medico informado não existe!");
            }
        }else{throw new ValidacaoException("Id do medico informado não existe!");}

        validadores.forEach(v-> v.validar(dados));

        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var medico = escolherMedico(dados);

        if(medico == null){
            throw new ValidacaoException("Não existe médico disponivél nessa data!");
        }

        var consulta = new Consulta(null, medico, paciente, dados.data(), null);
                consultaRepository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);

        }

        public void cancelar(DadosCancelamentoConsulta dados) {
            if (!consultaRepository.existsById(dados.idConsulta())) {
                throw new ValidacaoException("Id da consulta informado não existe!");
            }
        
            validadoresCancelamento.forEach(v -> v.validar(dados));    

            var consulta = consultaRepository.getReferenceById(dados.idConsulta());
            consulta.cancelar(dados.motivo());
        }

        private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        
            if(dados.idMedico() != null){
                return medicoRepository.getReferenceById(dados.idMedico());
            }

            if(dados.especialidade() == null){
                throw new ValidacaoException("Especialidade é obrigatória quando médico não for edibido!");
            }

            return medicoRepository.escolherMedicoLivreNaData(dados.especialidade(),dados.data());
        }    
}
