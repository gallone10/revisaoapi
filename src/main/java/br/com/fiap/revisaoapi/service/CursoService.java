package br.com.fiap.revisaoapi.service;

import br.com.fiap.revisaoapi.dto.CursoDTO;
import br.com.fiap.revisaoapi.model.Curso;
import br.com.fiap.revisaoapi.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;

    @Autowired
    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public Page<CursoDTO> findAll(Pageable pageable) {
        Page<Curso> cursos = cursoRepository.findAll(pageable);
        return cursos.map(this::convertToDTO);
    }

    public CursoDTO findById(Long id) {
        Curso curso = cursoRepository.findById(id).orElse(null);
        return (curso != null) ? convertToDTO(curso) : null;
    }

    public Curso save(Curso curso) {
        return cursoRepository.save(curso);
    }

    public Curso update(Long id, Curso curso) {
        if (cursoRepository.existsById(id)) {
            curso.setId(id);
            return cursoRepository.save(curso);
        }
        return null;
    }

    public void delete(Long id) {
        cursoRepository.deleteById(id);
    }

    private CursoDTO convertToDTO(Curso curso) {
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
