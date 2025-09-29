package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.paciente.DadosCadastroPaciente; // Importa o DTO para cadastro de pacientes.
import med.voll.api.paciente.Paciente;
import med.voll.api.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping; // Anotação para mapear requisições HTTP POST.
import org.springframework.web.bind.annotation.RequestBody; // Anotação que indica que um parâmetro de método deve ser vinculado ao corpo da requisição web.
import org.springframework.web.bind.annotation.RequestMapping; // Anotação para mapear requisições web.
import org.springframework.web.bind.annotation.RestController; // Anotação para criar um controlador de API REST.

@RestController
@RequestMapping("paciente")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

     @PostMapping
     @Transactional
     public void cadastrar(@RequestBody @Valid DadosCadastroPaciente dados){
         repository.save(new Paciente(dados));
     }

}
