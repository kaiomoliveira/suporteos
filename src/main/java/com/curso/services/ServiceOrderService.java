package com.curso.services;

import com.curso.domains.ServiceOrder;
import com.curso.domains.Technician;
import com.curso.domains.User;
import com.curso.domains.dtos.ServiceOrderDTO;
import com.curso.domains.enums.OrderPriority;
import com.curso.domains.enums.OrderStatus;
import com.curso.repositories.ServiceOrderRepository;
import com.curso.repositories.TechnicianRepository;
import com.curso.repositories.UserRepository;
import com.curso.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ServiceOrderService {

    @Autowired
    private TechnicianRepository technicianRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ServiceOrderRepository serviceOrderRepo;
    @Autowired
    private TechnicianService technicianService;
    @Autowired
    private UserService userService;

    public List<ServiceOrderDTO> findAll(){
        //retorna uma lista de ServiceOrderDTO
        return serviceOrderRepo.findAll().stream()
                .map(obj -> new ServiceOrderDTO(obj))
                .collect(Collectors.toList());
    }

    public ServiceOrder findbyId(UUID id){
        Optional<ServiceOrder> obj = serviceOrderRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id ));
    }

    public ServiceOrder newServiceOrder(ServiceOrderDTO obj){
        Technician tech = technicianService.findbyId(obj.getTechnician());
        User user = userService.findbyId(obj.getUser());

        ServiceOrder os = new ServiceOrder();
        if (obj.getId() != null){
            os.setId(obj.getId());
        }

        if (obj.getOrderStatus().equals(2)){
            os.setEndDate(LocalDate.now());
        }

        os.setTechnician(tech);
        os.setUser(user);
        os.setOrderStatus(OrderStatus.toEnum(obj.getOrderStatus()));
        os.setOrderPriority(OrderPriority.toEnum(obj.getOrderPriority()));
        os.setTitleOS(obj.getTitleOS());
        os.setDescription(obj.getDescription());

        return os;
    }

    public ServiceOrder create(ServiceOrderDTO objDto){
        return serviceOrderRepo.save(newServiceOrder(objDto));
    }

    public ServiceOrder update(UUID id, ServiceOrderDTO objDto){
        objDto.setId(id);
        ServiceOrder oldObj = findbyId(id);
        oldObj = newServiceOrder(objDto);
        return serviceOrderRepo.save(oldObj);
    }

}
