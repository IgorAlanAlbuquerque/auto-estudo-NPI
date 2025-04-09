package com.igor.autoestudo.services;

import com.igor.autoestudo.entity.Users;
import com.igor.autoestudo.exception.UsuarioNaoEncontradoException;
import com.igor.autoestudo.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsersService(UsersRepository userRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //adiciona um novo usuario
    public Users addNew(Users users) {
        if (usersRepository.findByEmail(users.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Já existe um usuário com esse e-mail.");
        }
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        return usersRepository.save(users);
    }

    //lista todos os usuarios
    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    //buscar por ID
    public Optional<Users> buscarPorId(Long id){
        return usersRepository.findById(id);
    }

    //Atualizar usuario
    public Users atualizar(Long id, Users userAtualizado){
        Users userExistente = usersRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException(id));
        userExistente.setName(userAtualizado.getName());
        userExistente.setEmail(userAtualizado.getEmail());
        userExistente.setAtivo(userAtualizado.getAtivo());
        //so atualiza senha se for diferente
        if (!userAtualizado.getPassword().isBlank() &&
                !passwordEncoder.matches(userAtualizado.getPassword(), userExistente.getPassword())) {
            userExistente.setPassword(passwordEncoder.encode(userAtualizado.getPassword()));
        }
        return usersRepository.save(userExistente);
    }
}
