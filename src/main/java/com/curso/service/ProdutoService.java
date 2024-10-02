package com.curso.service;

import com.curso.domains.dtos.ProdutoDTO;
import com.curso.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepo;

    public List<ProdutoDTO> findAll(){
        //retorna uma lista de GrupoProdutoDRO
        return produtoRepo.findAll().stream()
                .map(obj -> new ProdutoDTO(obj))
                .collect(Collectors.toList());
    }
}
