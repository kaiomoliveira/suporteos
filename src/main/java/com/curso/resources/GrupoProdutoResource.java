package com.curso.resources;

import com.curso.domains.dtos.GrupoProdutoDTO;
import com.curso.service.GrupoProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/grupoproduto")
public class GrupoProdutoResource {

    @Autowired
    private GrupoProdutoService grupoProdutoService;

    @GetMapping
    public ResponseEntity<List<GrupoProdutoDTO>> findAll(){
        return ResponseEntity.ok(grupoProdutoService.findAll());
    }
}
