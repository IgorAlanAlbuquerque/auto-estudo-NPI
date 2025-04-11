package com.igor.autoestudo.services;

import com.igor.autoestudo.entity.Cursos;
import com.igor.autoestudo.entity.Users;
import com.igor.autoestudo.exception.CursoNaoEncontradoException;
import com.igor.autoestudo.repository.CursosRepository;
import com.igor.autoestudo.repository.UsersRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursosService {
    private final CursosRepository cursosRepository;
    private final UsersRepository usersRepository;

    @Autowired
    public CursosService(CursosRepository cursosRepository, UsersRepository usersRepository) {
        this.cursosRepository = cursosRepository;
        this.usersRepository = usersRepository;
    }

    //adicionar curso
    public Cursos cadastrar(@NotNull Cursos curso) {
        if (curso.getName() == null || curso.getName().isBlank()) {
            throw new IllegalArgumentException("O nome do curso é obrigatório.");
        }
        if (curso.getSigla() == null || curso.getSigla().length() != 2) {
            throw new IllegalArgumentException("A sigla deve conter exatamente 2 caracteres.");
        }
        if (cursosRepository.findByName(curso.getName()).isPresent()) {
            throw new IllegalArgumentException("Já existe um curso com esse nome.");
        }
        return cursosRepository.save(curso);
    }

    //atualizar curso
    public Cursos atualizar(Long id, @NotNull Cursos cursoAtualizado){
        Cursos cursoExistente = cursosRepository.findById(id).orElseThrow(() -> new CursoNaoEncontradoException(id));

        cursoExistente.setName(cursoAtualizado.getName());
        cursoExistente.setSigla(cursoAtualizado.getSigla());
        cursoExistente.setTurno(cursoAtualizado.getTurno());

        return cursosRepository.save(cursoExistente);
    }

    //listar todos os cursos
    public List<Cursos> listarTodos(){
        return cursosRepository.findAll();
    }

    //buscar curso por ID
    public Optional<Cursos> buscarPorId(Long id){
        return cursosRepository.findById(id);
    }

    //excluir curso
    public void excluir(Long id){
        cursosRepository.findById(id)
                .orElseThrow(() -> new CursoNaoEncontradoException(id));

        List<Users> usuarios = usersRepository.findByCursoId(id);
        for (Users u : usuarios) {
            u.setCurso(null); // remove a associação
            usersRepository.save(u);
        }

        cursosRepository.deleteById(id);
    }
}
