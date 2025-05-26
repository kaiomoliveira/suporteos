package com.curso.services;

import com.curso.domains.*;
import com.curso.domains.enums.OrderPriority;
import com.curso.domains.enums.OrderStatus;
import com.curso.domains.enums.Status;
import com.curso.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class DBService {

    @Autowired
    private GrupoProdutoRepository grupoProdutoRepo;

    @Autowired
    private ProdutoRepository produtoRepo;

    @Autowired
    private TechnicianRepository technicianRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ServiceOrderRepository osRepo;

    public void initDB() {
        GrupoProduto grupo01 = new GrupoProduto(null, "Limpeza", Status.ATIVO);
        GrupoProduto grupo02 = new GrupoProduto(null, "Alimentação", Status.ATIVO);

        Produto produto01 = new Produto(null, "1111", "Coca-cola", new BigDecimal("100"), new BigDecimal("3.5"),
                LocalDate.now(), grupo02, Status.ATIVO);
        Produto produto02 = new Produto(null, "2222", "Guarana Antartica", new BigDecimal("200"), new BigDecimal("3.0"),
                LocalDate.now(), grupo02, Status.ATIVO);
        Produto produto03 = new Produto(null, "3333", "Detergente Limpol", new BigDecimal("300"), new BigDecimal("4.5"),
                LocalDate.now(), grupo01, Status.ATIVO);
        Produto produto04 = new Produto(null, "4444", "Sabão em pó OMO", new BigDecimal("400"), new BigDecimal("15.5"),
                LocalDate.now(), grupo01, Status.ATIVO);

        grupoProdutoRepo.save(grupo01);
        grupoProdutoRepo.save(grupo02);
        produtoRepo.save(produto01);
        produtoRepo.save(produto02);
        produtoRepo.save(produto03);
        produtoRepo.save(produto04);

        Technician tec1 = new Technician(
                null,
                "Jefferson",
                "Passerini",
                "89308024000",
                "jefferson.passerini@gmail.com",
                "123",
                null);

        User user01 = new User(
                null,
                "Joao",
                "Alberto",
                "02569095099",
                "joao.alberto@gmail.com",
                "123",
                null);

        User user02 = new User(
                null,
                "Jose",
                "Felipe",
                "02569095044",
                "jose.felipe@gmail.com",
                "123",
                null);

        ServiceOrder os01 = new ServiceOrder(
                null,
                "test",
                "OS test",
                OrderPriority.HIGH,
                OrderStatus.OPEN,
                tec1,
                user02);

        technicianRepo.save(tec1);
        userRepo.save(user01);
        userRepo.save(user02);
        osRepo.save(os01);

    }

}
