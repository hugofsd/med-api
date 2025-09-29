package med.voll.api.controller;

import jakarta.validation.Valid; // Anotação para acionar a validação de um objeto aninhado.
import med.voll.api.medico.DadosCadastroMedico; // Importa o DTO para cadastro de médicos.
import med.voll.api.medico.DadosListagemMedico;
import med.voll.api.medico.Medico; // Importa a entidade Medico.
import med.voll.api.medico.MedicoRepository; // Importa o repositório para a entidade Medico.
import org.springframework.beans.factory.annotation.Autowired; // Anotação para injeção de dependência automática.
import org.springframework.transaction.annotation.Transactional; // Anotação para demarcar um método ou classe como transacional.
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados){
        repository.save(new Medico(dados));
    }

    @GetMapping
    public List<DadosListagemMedico> listar(){
        return repository.findAll().stream().map(DadosListagemMedico::new).toList();
    }
}
