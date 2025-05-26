package com.curso.resources;

import com.curso.domains.GrupoProduto;
import com.curso.domains.dtos.GrupoProdutoDTO;
import com.curso.services.GrupoProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(name = "Grupos de Produtos", description = "Gerenciamento dos grupos de produtos")
@RestController
@RequestMapping(value = "/grupoproduto")
public class GrupoProdutoResource {

    @Autowired
    private GrupoProdutoService grupoProdutoService;

    @Operation(summary = "Listar grupos de produtos", description = "Retorna uma lista de todos os grupos de produtos cadastrados")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<GrupoProdutoDTO>> findAll() {
        return ResponseEntity.ok(grupoProdutoService.findAll());
    }

    @Operation(summary = "Buscar grupo de produto por ID", description = "Retorna um grupo de produto específico pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Grupo encontrado"),
            @ApiResponse(responseCode = "404", description = "Grupo não encontrado")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<GrupoProdutoDTO> findById(
            @Parameter(description = "ID do grupo de produto") @PathVariable Integer id) {
        GrupoProduto obj = this.grupoProdutoService.findbyId(id);
        return ResponseEntity.ok().body(new GrupoProdutoDTO(obj));
    }

    @Operation(summary = "Criar grupo de produto", description = "Cadastra um novo grupo de produto no sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Grupo criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<GrupoProdutoDTO> create(@RequestBody GrupoProdutoDTO dto) {
        GrupoProduto grupoProduto = grupoProdutoService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(grupoProduto.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
