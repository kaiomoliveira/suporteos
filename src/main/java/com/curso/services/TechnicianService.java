package com.curso.services;

import com.curso.domains.Technician;
import com.curso.domains.dtos.TechnicianDTO;
import com.curso.repositories.TechnicianRepository;
import com.curso.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TechnicianService {
    @Autowired
    private TechnicianRepository technicianRepo;

    public List<TechnicianDTO> findAll(){
        //retorna uma lista de TechnicianDTO
        return technicianRepo.findAll().stream()
                .map(obj -> new TechnicianDTO(obj))
                .collect(Collectors.toList());
    }

    public Technician findbyId(Long id){
        Optional<Technician> obj = technicianRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id ));
    }

    public Technician findbyCpf(String cpf){
        Optional<Technician> obj = technicianRepo.findByCpf(cpf);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + cpf ));
    }

    public Technician findbyEmail(String email){
        Optional<Technician> obj = technicianRepo.findByEmail(email);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + email ));
    }

    public Technician create(TechnicianDTO objDto){
        objDto.setId(null);
        ValidaPorCPFeEmail(objDto);
        Technician obj = new Technician(objDto);
        return technicianRepo.save(obj);
    }

    public Technician update(Long id, TechnicianDTO objDto){
        objDto.setId(id);
        Technician oldObj = findbyId(id);
        oldObj = new Technician(objDto);
        return technicianRepo.save(oldObj);
    }

    public void delete(Long id){
        Technician obj = findbyId(id);
        if (obj.getServiceOrders().size() > 0){
            throw new DataIntegrityViolationException("Técnico não pode ser deletado! Possui ordens de serviço.");
        }
        technicianRepo.deleteById(id);
    }

    private void ValidaPorCPFeEmail(TechnicianDTO objDto){
        Optional<Technician> obj = technicianRepo.findByCpf(objDto.getCpf());
        if(obj.isPresent() && obj.get().getId() != objDto.getId()){
            throw new ObjectNotFoundException("CPF já cadastrado no sistema!");
        }

        Optional<Technician> obj2 = technicianRepo.findByCpf(objDto.getEmail());
        if(obj2.isPresent() && obj2.get().getId() != objDto.getId()){
            throw new ObjectNotFoundException("Email já cadastrado no sistema!");
        }
    }
}
