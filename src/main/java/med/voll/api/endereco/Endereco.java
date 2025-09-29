package med.voll.api.endereco;

import jakarta.persistence.Embeddable; // Especifica que a classe Endereco pode ser incorporada como parte de outra entidade.
import lombok.AllArgsConstructor; // Gera um construtor com todos os campos da classe.
import lombok.Getter; // Gera os métodos getters para todos os campos.
import lombok.NoArgsConstructor; // Gera um construtor sem argumentos.

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;

    public Endereco(DadosEndereco endereco) {
        this.logradouro = endereco.logradouro();
        this.bairro = endereco.bairro();
        this.cep = endereco.cep();
        this.numero = endereco.numero();
        this.complemento = endereco.complemento();
        this.cidade = endereco.cidade();
        this.uf = endereco.uf();
    }
}
