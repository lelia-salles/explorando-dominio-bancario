package me.dio.service.impl;

import me.dio.domain.model.User;
import me.dio.domain.repository.UserRepository;
import me.dio.service.UserService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

/*
Essa implementação atua como intermediária entre a camada de apresentação (controladores) e a camada de persistência (repositórios). Ela aplica regras de negócio simples, como evitar duplicidades de contas ao criar um usuário, e também delega a persistência ao repositório. A abordagem segue boas práticas como:
- Separação de responsabilidades.
- Uso de exceções para lidar com cenários inesperados.
- Validações antes de interagir com o banco de dados.
*/ 
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public User create(User userToCreate) {
        if (userRepository.existsByAccountNumber(userToCreate.getAccount().getNumber())) {
            throw new IllegalArgumentException("This Account number already exists.");
        }
        return userRepository.save(userToCreate);
    }
}