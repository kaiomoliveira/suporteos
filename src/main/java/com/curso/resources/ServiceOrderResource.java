package com.curso.resources;

import com.curso.domains.ServiceOrder;
import com.curso.domains.dtos.ServiceOrderDTO;
import com.curso.services.ServiceOrderService;
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
import java.util.UUID;

@Tag(name = "Ordens de Serviço", description = "Gerenciamento das ordens de serviço")
@RestController
@RequestMapping(value = "/serviceorder")
public class ServiceOrderResource {

    @Autowired
    private ServiceOrderService osService;

    @Operation(summary = "Listar ordens de serviço", description = "Retorna uma lista de todas as ordens de serviço cadastradas")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<ServiceOrderDTO>> findAll() {
        return ResponseEntity.ok().body(osService.findAll());
    }

    @Operation(summary = "Buscar ordem de serviço por ID", description = "Retorna uma ordem de serviço específica pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ordem encontrada"),
            @ApiResponse(responseCode = "404", description = "Ordem não encontrada")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<ServiceOrderDTO> findById(
            @Parameter(description = "ID da ordem de serviço") @PathVariable UUID id) {
        ServiceOrder obj = this.osService.findbyId(id);
        return ResponseEntity.ok().body(new ServiceOrderDTO(obj));
    }

    @Operation(summary = "Criar ordem de serviço", description = "Cadastra uma nova ordem de serviço no sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Ordem criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<ServiceOrderDTO> create(@Valid @RequestBody ServiceOrderDTO objDto) {
        ServiceOrder serviceOrder = osService.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(serviceOrder.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Operation(summary = "Atualizar ordem de serviço", description = "Atualiza os dados de uma ordem de serviço existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ordem atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ordem não encontrada"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PutMapping(value = "/{id}")
    public ResponseEntity<ServiceOrderDTO> update(
            @Parameter(description = "ID da ordem de serviço") @PathVariable UUID id,
            @Valid @RequestBody ServiceOrderDTO objDto) {
        ServiceOrder serviceOrder = osService.update(id, objDto);
        return ResponseEntity.ok().body(new ServiceOrderDTO(serviceOrder));
    }

}
