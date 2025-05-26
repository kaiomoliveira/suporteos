package com.curso.services;

import com.curso.domains.Produto;
import com.curso.domains.dtos.ProdutoDTO;
import com.curso.repositories.ProdutoRepository;
import com.curso.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepo;

    public List<ProdutoDTO> findAll(){
        //retorna uma lista de GrupoProdutoDTO
        return produtoRepo.findAll().stream()
                .map(obj -> new ProdutoDTO(obj))
                .collect(Collectors.toList());
    }

    public Produto findbyId(Long id){
        Optional<Produto> obj = produtoRepo.findById(id);
        return obj.orElse(null);
    }

    public Produto findbyCodigoBarra(String codigoBarra){
        Optional<Produto> obj = produtoRepo.findByCodigoBarra(codigoBarra);
        return obj.orElse(null);
    }

    public Produto create(ProdutoDTO dto){
        dto.setIdProduto(null);
        Produto obj = new Produto(dto);
        return produtoRepo.save(obj);
    }
}
