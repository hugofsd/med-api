package med.voll.api.controller;

import org.springframework.web.bind.annotation.GetMapping; // Anotação para mapear requisições HTTP GET em métodos de manipulação específicos.
import org.springframework.web.bind.annotation.RequestMapping; // Anotação para mapear requisições web para classes e métodos de manipulação.
import org.springframework.web.bind.annotation.RestController; // Anotação que combina @Controller e @ResponseBody, marcando a classe como um controlador de API REST.

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    public String olaMundo(){
        System.out.println("OI");
        return "Hello World!!";
    }

}
