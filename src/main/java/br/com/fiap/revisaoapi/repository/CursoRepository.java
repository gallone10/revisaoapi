package br.com.fiap.revisaoapi.repository;
import br.com.fiap.revisaoapi.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}


