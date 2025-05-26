package com.curso.domains;

import com.curso.domains.dtos.UserDTO;
import com.curso.domains.enums.PersonType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
@PrimaryKeyJoinColumn(name = "person_id")  // Chave estrangeira para a tabela "persons"
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends Person{

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<ServiceOrder> serviceOrders = new ArrayList<>();

    public User(Long id, String firstName, String lastName, String cpf, String email, String password, List<ServiceOrder> serviceOrders) {
        super(id, firstName, lastName, cpf, email, password);
        this.serviceOrders = serviceOrders;
    }

    public User(UserDTO obj){
        this.id = obj.getId();
        this.firstName = obj.getFirstName();
        this.lastName = obj.getLastName();
        this.cpf = obj.getCpf();
        this.email = obj.getEmail();
        this.password = obj.getPassword();
        this.createAt = obj.getCreateAt();
        this.personType = obj.getPersonType().stream()
                .map(x -> x.getId()).collect(Collectors.toSet());
        addPersonType(PersonType.USER);
    }

    public User() {
        super();
    }

    public List<ServiceOrder> getServiceOrders() {
        return serviceOrders;
    }

    public void setServiceOrders(List<ServiceOrder> serviceOrders) {
        this.serviceOrders = serviceOrders;
    }
}
