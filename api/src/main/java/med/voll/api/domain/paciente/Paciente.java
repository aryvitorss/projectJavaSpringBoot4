package med.voll.api.domain.paciente;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.endereco.Endereco;

@EqualsAndHashCode(of="id")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="Paciente")
@Table(name="pacientes")
public class Paciente {
   
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String cpf;
    private String telefone;

    @Embedded
    Endereco endereco;

    private Boolean ativo;

    public Paciente(DadosCadastroPaciente dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.cpf = dados.cpf();
        this.endereco = new Endereco(dados.endereco());
    }
    
    public void atualizarInformacoes(@Valid DadosAtualizacaoPaciente dados) {

        if(dados.nome() != null){
            this.nome = dados.nome();
            }
        if(dados.email() != null){    
                this.email = dados.email();
            }
        if(dados.cpf() != null){
            this.cpf = dados.cpf();
            }
        if(dados.telefone() != null){    
                this.telefone = dados.telefone();
            }
	}

    public void excluir() {
        this.ativo = false;
    }

}
