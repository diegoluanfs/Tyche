package com.tyche.services;

import com.tyche.domain.user.User;
import com.tyche.domain.user.UserType;
import com.tyche.dtos.RespUserDTO;
import com.tyche.dtos.UserCreateDTO;
import com.tyche.dtos.UserUpdateDTO;
import com.tyche.dtos.UserStatusUpdateDTO;
import com.tyche.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception{
        if(sender.getUserType() != UserType.MERCHANT){
            throw new Exception("Usuário do tipo lojista não está autorizado a realizar transação");
        }

        if(sender.getBalance().compareTo(amount) < 0){
            throw new Exception("Saldo insuficiente");
        }
    }

    public User findUserById(Long id) throws Exception{
        return this.repository.findUserById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
    }

    public User createUser(UserCreateDTO data){
        User newUser = new User(data);
        this.saveUser(newUser);
        return newUser;
    }

    public User updateUser(UserUpdateDTO data) {
        // Busca o usuário existente pelo ID
        User existingUser = this.repository.findById(data.id())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Atualiza os campos modificáveis
        existingUser.setFirstName(data.firstName());
        existingUser.setLastName(data.lastName());
        existingUser.setPassword(data.password());

        // Salva o usuário atualizado
        this.repository.save(existingUser);

        return existingUser;
    }

    public User userStatusUpdate(UserStatusUpdateDTO data) {
        // Busca o usuário existente pelo ID
        User existingUser = this.repository.findById(data.id())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Atualiza os campos modificáveis
        existingUser.setStatus(data.status());

        // Salva o usuário atualizado
        this.repository.save(existingUser);

        return existingUser;
    }

    public List<RespUserDTO> getAllUsers(){
        List<User> users = this.repository.findAll();
        List<RespUserDTO> respUserDTOs = new ArrayList<>();

        for (User user : users) {
            RespUserDTO dto = new RespUserDTO(
                    user.getId(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getBalance(),
                    user.getUserType(),
                    user.getStatus()
            );
            respUserDTOs.add(dto);
        }
        System.out.println("respUserDTOs: " + respUserDTOs);
        return respUserDTOs;
    }

    public Optional<User> findById(long id){
        return this.repository.findById(id);
    }

    public void saveUser(User user){
        this.repository.save(user);
    }
}
