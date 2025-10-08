package med.voll.api.medico;

import jakarta.persistence.*; // Fornece as anotações e interfaces para a persistência de dados (JPA).
import jakarta.validation.Valid;
import lombok.AllArgsConstructor; // Gera um construtor com todos os campos da classe.
import lombok.EqualsAndHashCode; // Gera os métodos equals e hashCode.
import lombok.Getter; // Gera os métodos getters para todos os campos.
import lombok.NoArgsConstructor; // Gera um construtor sem argumentos.
import med.voll.api.endereco.Endereco; // Importa a classe Endereco, que será incorporada nesta entidade.

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;
    private String nome;
    private String email;
    private String crm;
    private String telefone;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    public Medico(DadosCadastroMedico dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.crm = dados.crm();
        this.telefone = dados.telefone();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizarInformacoes(DadosAtualizacaoMedico dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null) {
            this.endereco.atualizarInformacoes(dados.endereco());
        }

    }
}
;