package com.igor.autoestudo.services;

import com.igor.autoestudo.entity.Cursos;
import com.igor.autoestudo.entity.Users;
import com.igor.autoestudo.exception.CursoNaoEncontradoException;
import com.igor.autoestudo.exception.UsuarioNaoEncontradoException;
import com.igor.autoestudo.repository.CursosRepository;
import com.igor.autoestudo.repository.UsersRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService implements UserDetailsService {
    private final UsersRepository usersRepository;
    private final CursosRepository cursosRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsersService(UsersRepository userRepository, CursosRepository cursosRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = userRepository;
        this.cursosRepository = cursosRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //adiciona um novo usuario
    public Users addNew(@NotNull Users users) {
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
    public Users atualizar(Long id, @NotNull Users userAtualizado){
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

    // remover user
    public void remover(Long id) {
        Users user = usersRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(id));

        if (Boolean.TRUE.equals(user.getAtivo())) {
            throw new IllegalStateException("Não é possível remover um usuário ativo.");
        }

        usersRepository.deleteById(id);
    }

    //autentificar
    public Optional<Users> autenticar(String email, String senha) {
        Optional<Users> usuarioOpt = usersRepository.findByEmail(email);

        if (usuarioOpt.isPresent()) {
            Users usuario = usuarioOpt.get();
            if (passwordEncoder.matches(senha, usuario.getPassword())) {
                return Optional.of(usuario);
            }
        }

        return Optional.empty();
    }

    //associar usuario a um curso
    public Users associarCurso(Long usuarioId, Long cursoId) {
        Users user = usersRepository.findById(usuarioId)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));

        Cursos curso = cursosRepository.findById(cursoId)
                .orElseThrow(() -> new CursoNaoEncontradoException(cursoId));

        user.setCurso(curso);

        return usersRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usersRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o email: " + email));
    }
}
