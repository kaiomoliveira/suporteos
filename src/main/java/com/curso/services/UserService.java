package com.curso.services;

import com.curso.domains.User;
import com.curso.domains.dtos.UserDTO;
import com.curso.repositories.UserRepository;
import com.curso.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    public List<UserDTO> findAll(){
        //retorna uma lista de UserDTO
        return userRepo.findAll().stream()
                .map(obj -> new UserDTO(obj))
                .collect(Collectors.toList());
    }

    public User findbyId(Long id){
        Optional<User> obj = userRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id ));
    }

    public User findbyCpf(String cpf){
        Optional<User> obj = userRepo.findByCpf(cpf);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + cpf ));
    }

    public User findbyEmail(String email){
        Optional<User> obj = userRepo.findByEmail(email);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + email ));
    }

    public User create(UserDTO objDto){
        objDto.setId(null);
        ValidaPorCPFeEmail(objDto);
        User obj = new User(objDto);
        return userRepo.save(obj);
    }

    public User update(Long id, UserDTO objDto){
        objDto.setId(id);
        User oldObj = findbyId(id);
        oldObj = new User(objDto);
        return userRepo.save(oldObj);
    }

    public void delete(Long id){
        User obj = findbyId(id);
        if (obj.getServiceOrders().size() > 0){
            throw new DataIntegrityViolationException("Técnico não pode ser deletado! Possui ordens de serviço.");
        }
        userRepo.deleteById(id);
    }

    private void ValidaPorCPFeEmail(UserDTO objDto){
        Optional<User> obj = userRepo.findByCpf(objDto.getCpf());
        if(obj.isPresent() && obj.get().getId() != objDto.getId()){
            throw new ObjectNotFoundException("CPF já cadastrado no sistema!");
        }

        Optional<User> obj2 = userRepo.findByCpf(objDto.getEmail());
        if(obj2.isPresent() && obj2.get().getId() != objDto.getId()){
            throw new ObjectNotFoundException("Email já cadastrado no sistema!");
        }
    }
}
