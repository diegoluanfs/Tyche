package com.tyche.services;

import com.tyche.domain.user.User;
import com.tyche.domain.user.UserType;
import com.tyche.dtos.UserDTO;
import com.tyche.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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

    public User createUser(UserDTO data){
        User newUser = new User(data);
        System.out.println("newUser: " + newUser);
        this.saveUser(newUser);
        return newUser;
    }

    public List<User> getAllUsers(){
        return this.repository.findAll();
    }

    public Optional<User> findById(long id){
        return this.repository.findById(id);
    }

    public void saveUser(User user){
        this.repository.save(user);
    }
}
