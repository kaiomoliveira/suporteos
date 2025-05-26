package com.curso.domains.dtos;

import com.curso.domains.GrupoProduto;
import com.curso.domains.Technician;
import com.curso.domains.enums.PersonType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.FetchType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TechnicianDTO {

    protected Long id;

    @NotNull(message = "O campo nome não pode ser nulo!")
    @NotBlank(message = "O campo nome não pode estar vazio!")
    protected String firstName;

    @NotNull(message = "O campo sobrenome não pode ser nulo!")
    @NotBlank(message = "O campo sobrenome não pode estar vazio!")
    protected String lastName;

    @NotNull(message = "O campo cpf não pode ser nulo!")
    @CPF
    protected String cpf;

    @NotNull(message = "O campo email não pode ser nulo!")
    @NotBlank(message = "O campo email não pode estar vazio!")
    protected String email;

    @NotNull(message = "O campo password não pode ser nulo!")
    @NotBlank(message = "O campo password não pode estar vazio!")
    protected String password;

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate createAt = LocalDate.now();

    protected Set<Integer> personType = new HashSet<>();

    public TechnicianDTO() {
    }

    public TechnicianDTO(Technician obj) {
        this.id = obj.getId();
        this.firstName = obj.getFirstName();
        this.lastName = obj.getLastName();
        this.cpf = obj.getCpf();
        this.email = obj.getEmail();
        this.password = obj.getPassword();
        this.createAt = obj.getCreateAt();
        this.personType.stream().map(PersonType::toEnum).collect(Collectors.toSet());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    public Set<PersonType> getPersonType() {
        return personType == null ? Collections.emptySet() :
                personType.stream().map(PersonType::toEnum).collect(Collectors.toSet());
    }

    public void setPersonType(PersonType personType) {
        this.personType.add(personType.getId());
    }
}
