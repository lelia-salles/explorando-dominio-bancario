package me.dio.service;

import me.dio.domain.model.User;

public interface UserService {

// a implementação está em UserServiceImpl    
 
public interface UserService {

    User findById(Long id);// retorna o usuário pelo id

    User create(User userToCreate);//cria o usuário
}