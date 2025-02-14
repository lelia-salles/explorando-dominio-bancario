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

    private final UserRepository userRepository; // injeção de dependência: userRepository é passado para a classe via seu construtor, em vez de ser criado diretamente dentro dela. Isso facilita a testabilidade, promove a reutilização de código e separa as responsabilidades, seguindo boas práticas de design.

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new); // O método findById busca um usuário pelo ID usando o repositório. Se o usuário não for encontrado, lança uma exceção NoSuchElementException, garantindo que valores nulos não sejam retornados.
    }

    @Override
    public User create(User userToCreate) {
        if (userRepository.existsByAccountNumber(userToCreate.getAccount().getNumber())) {
            throw new IllegalArgumentException("This Account number already exists."); // O método create cria um novo usuário. Ele verifica se já existe uma conta com o mesmo número usando existsByAccountNumber. Se existir, lança uma exceção IllegalArgumentException. Caso contrário, salva o usuário no banco com o método save.
        }
        return userRepository.save(userToCreate);
    }
}