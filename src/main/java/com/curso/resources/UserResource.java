package com.curso.resources;

import com.curso.domains.User;
import com.curso.domains.dtos.UserDTO;
import com.curso.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(name = "Usuários", description = "Gerenciamento dos usuários")
@RestController
@RequestMapping(value = "/user")
public class UserResource {

    @Autowired
    private UserService userService;

    @Operation(summary = "Listar usuários", description = "Retorna uma lista de todos os usuários cadastrados")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        return ResponseEntity.ok().body(userService.findAll());
    }

    @Operation(summary = "Buscar usuário por ID", description = "Retorna um usuário específico pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(
            @Parameter(description = "ID do usuário") @PathVariable Long id) {
        User obj = this.userService.findbyId(id);
        return ResponseEntity.ok().body(new UserDTO(obj));
    }

    @Operation(summary = "Buscar usuário por CPF", description = "Retorna um usuário a partir do CPF")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping(value = "/cpf/{cpf}")
    public ResponseEntity<UserDTO> findByCpf(
            @Parameter(description = "CPF do usuário") @PathVariable String cpf) {
        User obj = this.userService.findbyCpf(cpf);
        return ResponseEntity.ok().body(new UserDTO(obj));
    }

    @Operation(summary = "Buscar usuário por E-mail", description = "Retorna um usuário a partir do E-mail")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping(value = "/email/{email}")
    public ResponseEntity<UserDTO> findByEmail(
            @Parameter(description = "E-mail do usuário") @PathVariable String email) {
        User obj = this.userService.findbyEmail(email);
        return ResponseEntity.ok().body(new UserDTO(obj));
    }

    @Operation(summary = "Criar novo usuário", description = "Cadastra um novo usuário no sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO objDto) {
        User user = userService.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Operation(summary = "Atualizar usuário", description = "Atualiza os dados de um usuário existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTO> update(
            @Parameter(description = "ID do usuário") @PathVariable Long id,
            @Valid @RequestBody UserDTO objDto) {
        User user = userService.update(id, objDto);
        return ResponseEntity.ok().body(new UserDTO(user));
    }

    @Operation(summary = "Deletar usuário", description = "Remove um usuário a partir do ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID do usuário") @PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
