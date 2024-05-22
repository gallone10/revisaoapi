package br.com.fiap.revisaoapi.service;

import br.com.fiap.revisaoapi.dto.CursoDTO;
import br.com.fiap.revisaoapi.model.Curso;
import br.com.fiap.revisaoapi.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;
    private static final Pageable customPageable = PageRequest.of(0, 3, Sort.by("titulo").ascending());

    @Autowired
    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public Page<CursoDTO> findAllCursos() {
        return cursoRepository.findAll(customPageable).map(this::toDTO);
    }

    public CursoDTO findCursoById(Long id) {
        Optional<Curso> curso = cursoRepository.findById(id);
        return curso.map(this::toDTO).orElse(null);
    }

    public Curso saveCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    public Curso updateCurso(Long id, Curso curso) {
        Optional<Curso> cursoOptional = cursoRepository.findById(id);
        if (cursoOptional.isPresent()) {
            Curso cursoAtualizado = cursoOptional.get();
            cursoAtualizado.setTitulo(curso.getTitulo());
            cursoAtualizado.setDescricao(curso.getDescricao());
            cursoAtualizado.setInstrutor(curso.getInstrutor());
            cursoAtualizado.setDuracao(curso.getDuracao());
            cursoAtualizado.setNivel(curso.getNivel());
            cursoAtualizado.setPreco(curso.getPreco());
            curso = cursoRepository.save(cursoAtualizado);
            return curso;
        }
        return null;
    }

    public void deleteCurso(Long id) {
        cursoRepository.deleteById(id);
    }

    private CursoDTO toDTO(Curso curso) {
        CursoDTO cursoDTO = new CursoDTO();
        cursoDTO.setId(curso.getId());
        cursoDTO.setTitulo(curso.getTitulo());
        cursoDTO.setDescricao(curso.getDescricao());
        cursoDTO.setInstrutor(curso.getInstrutor());
        cursoDTO.setDuracao(curso.getDuracao());
        cursoDTO.setNivel(curso.getNivel());
        cursoDTO.setPreco(curso.getPreco());
        return cursoDTO;
    }
}


