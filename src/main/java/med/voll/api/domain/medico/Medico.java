package med.voll.api.domain.medico;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.endereco.Endereco;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String crm;
    private String telefone;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    private Boolean ativo;

    public Medico(DadosCadastroMedico dados) {
        if (dados != null) {  // Adiciona verificação de nulo
            this.nome = dados.nome();
            this.ativo = true;
            this.email = dados.email();
            this.crm = dados.crm();
            this.telefone = dados.telefone();
            this.especialidade = dados.especialidade();
            this.endereco = new Endereco(dados.endereco());
        }
    }

    public void atualizarInformacoes(@Valid DadosAtualizarMedico dadosAtualizarMedico) {
        if (dadosAtualizarMedico.nome() != null) {
            this.nome = dadosAtualizarMedico.nome();
        }
        if(dadosAtualizarMedico.telefone() != null) {
            this.telefone = dadosAtualizarMedico.telefone();
        }
        if(dadosAtualizarMedico.endereco() != null) {
            this.endereco.atualizarInformacoes(dadosAtualizarMedico.endereco());
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}