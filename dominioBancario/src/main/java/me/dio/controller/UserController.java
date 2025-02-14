package me.dio.controller;

import me.dio.domain.model.User;
import me.dio.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users") // define um caminho para o recurso para expor o endpoint
public class UserController {

    private final UserService userService; 

    public UserController(UserService userService) {
        this.userService = userService;
    }

// a boa prática aqui seria criar DTOs específicos para expor os endpoints para não expor informações sensíveis à quem consumir a API
/**
 * Este controlador REST é responsável por gerenciar o recurso "User" (usuário). 
 * Ele fornece dois endpoints principais:
 * 
 * - O método GET "/users/{id}" permite buscar um usuário pelo seu ID. 
 *   O ID é extraído da URL por meio da anotação @PathVariable e, em seguida, 
 *   o UserService é chamado para encontrar o usuário correspondente. 
 *   O usuário encontrado é retornado no corpo da resposta com status HTTP 200 (OK).
 * 
 * - O método POST "/users" permite criar um novo usuário. 
 *   Os dados do novo usuário são enviados no corpo da requisição e mapeados 
 *   automaticamente para um objeto do tipo User por meio da anotação @RequestBody.
 *   O UserService é chamado para salvar o novo usuário, e a URI do recurso recém-criado 
 *   é gerada usando o ServletUriComponentsBuilder. A resposta retorna o usuário criado, 
 *   com status HTTP 201 (Created) e o cabeçalho "Location" contendo a URI do novo recurso.
 */

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
       var user = userService.findById(id);
       return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User userToCreate) {
        var userCreated = userService.create(userToCreate);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userCreated.getId())
                .toUri();
        return ResponseEntity.created(location).body(userCreated);
    }
}