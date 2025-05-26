package com.curso.domains.dtos;

import com.curso.domains.ServiceOrder;
import com.curso.domains.Technician;
import com.curso.domains.enums.PersonType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class ServiceOrderDTO {

    private UUID id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate starDate = LocalDate.now();

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate endDate = LocalDate.now();

    @NotNull(message = "O campo Titulo não pode ser nulo!")
    private String titleOS;

    @NotNull(message = "O campo descrição não pode ser nulo!")
    private String description;

    @NotNull(message = "O campo Prioridade não pode ser nulo!")
    private Integer orderPriority;

    @NotNull(message = "O campo Status não pode ser nulo!")
    private Integer orderStatus;

    @NotNull(message = "O campo Técnico não pode ser nulo!")
    private Long technician;

    @NotNull(message = "O campo Usuário não pode ser nulo!")
    private Long user;
    private String nameTechnician;
    private String nameUser;

    public ServiceOrderDTO() {
    }

    public ServiceOrderDTO(UUID id, LocalDate starDate, LocalDate endDate, String titleOS, String description, Integer orderPriority, Integer orderStatus, Long technician, Long user, String nameTechnician, String nameUser) {
        this.id = id;
        this.starDate = starDate;
        this.endDate = endDate;
        this.titleOS = titleOS;
        this.description = description;
        this.orderPriority = orderPriority;
        this.orderStatus = orderStatus;
        this.technician = technician;
        this.user = user;
        this.nameTechnician = nameTechnician;
        this.nameUser = nameUser;
    }

    public ServiceOrderDTO(ServiceOrder serviceOrder){
        this.id = serviceOrder.getId();
        this.starDate = serviceOrder.getStarDate();
        this.endDate = serviceOrder.getEndDate();
        this.titleOS = serviceOrder.getTitleOS();
        this.description = serviceOrder.getDescription();
        this.orderPriority = serviceOrder.getOrderPriority().getId();
        this.orderStatus = serviceOrder.getOrderStatus().getId();
        this.technician = serviceOrder.getTechnician().getId();
        this.user = serviceOrder.getUser().getId();
        this.nameTechnician = serviceOrder.getTechnician().getFirstName() + " " + serviceOrder.getTechnician().getLastName();
        this.nameUser = serviceOrder.getUser().getFirstName() + " " + serviceOrder.getUser().getLastName();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getStarDate() {
        return starDate;
    }

    public void setStarDate(LocalDate starDate) {
        this.starDate = starDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getTitleOS() {
        return titleOS;
    }

    public void setTitleOS(String titleOS) {
        this.titleOS = titleOS;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOrderPriority() {
        return orderPriority;
    }

    public void setOrderPriority(Integer orderPriority) {
        this.orderPriority = orderPriority;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getTechnician() {
        return technician;
    }

    public void setTechnician(Long technician) {
        this.technician = technician;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public String getNameTechnician() {
        return nameTechnician;
    }

    public void setNameTechnician(String nameTechnician) {
        this.nameTechnician = nameTechnician;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }
}
