package com.curso.resources;

import com.curso.domains.Technician;
import com.curso.domains.dtos.TechnicianDTO;
import com.curso.services.TechnicianService;
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

@Tag(name = "Técnicos", description = "Gerenciamento dos técnicos")
@RestController
@RequestMapping(value = "/technician")
public class TechnicianResource {

    @Autowired
    private TechnicianService technicianService;

    @Operation(summary = "Listar técnicos", description = "Retorna uma lista de todos os técnicos cadastrados")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<TechnicianDTO>> findAll() {
        return ResponseEntity.ok().body(technicianService.findAll());
    }

    @Operation(summary = "Buscar técnico por ID", description = "Retorna um técnico específico pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Técnico encontrado"),
            @ApiResponse(responseCode = "404", description = "Técnico não encontrado")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<TechnicianDTO> findById(
            @Parameter(description = "ID do técnico") @PathVariable Long id) {
        Technician obj = this.technicianService.findbyId(id);
        return ResponseEntity.ok().body(new TechnicianDTO(obj));
    }

    @Operation(summary = "Buscar técnico por CPF", description = "Retorna um técnico a partir do CPF")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Técnico encontrado"),
            @ApiResponse(responseCode = "404", description = "Técnico não encontrado")
    })
    @GetMapping(value = "/cpf/{cpf}")
    public ResponseEntity<TechnicianDTO> findByCpf(
            @Parameter(description = "CPF do técnico") @PathVariable String cpf) {
        Technician obj = this.technicianService.findbyCpf(cpf);
        return ResponseEntity.ok().body(new TechnicianDTO(obj));
    }

    @Operation(summary = "Buscar técnico por E-mail", description = "Retorna um técnico a partir do E-mail")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Técnico encontrado"),
            @ApiResponse(responseCode = "404", description = "Técnico não encontrado")
    })
    @GetMapping(value = "/email/{email}")
    public ResponseEntity<TechnicianDTO> findByEmail(
            @Parameter(description = "E-mail do técnico") @PathVariable String email) {
        Technician obj = this.technicianService.findbyEmail(email);
        return ResponseEntity.ok().body(new TechnicianDTO(obj));
    }

    @Operation(summary = "Criar técnico", description = "Cadastra um novo técnico no sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Técnico criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<TechnicianDTO> create(@Valid @RequestBody TechnicianDTO objDto) {
        Technician technician = technicianService.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(technician.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Operation(summary = "Atualizar técnico", description = "Atualiza os dados de um técnico existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Técnico atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Técnico não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PutMapping(value = "/{id}")
    public ResponseEntity<TechnicianDTO> update(
            @Parameter(description = "ID do técnico") @PathVariable Long id,
            @Valid @RequestBody TechnicianDTO objDto) {
        Technician technician = technicianService.update(id, objDto);
        return ResponseEntity.ok().body(new TechnicianDTO(technician));
    }

    @Operation(summary = "Deletar técnico", description = "Remove um técnico a partir do ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Técnico deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Técnico não encontrado")
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID do técnico") @PathVariable Long id) {
        technicianService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
