package med.voll.api.controller;

import jakarta.validation.Valid; // Anotação para acionar a validação de um objeto aninhado.
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired; // Anotação para injeção de dependência automática.
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
        System.out.println("teste");
        repository.save(new Medico(dados));
    }

    // Se não for informado parametros o padrão é 2 registros por pagina ordenando por nome,
    @GetMapping
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 2, sort = {"nome"}) Pageable paginacao){
        return repository.findAll(paginacao).map(DadosListagemMedico::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados){
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
    }




}
