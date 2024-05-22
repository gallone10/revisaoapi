package br.com.fiap.revisaoapi.controller;
import br.com.fiap.revisaoapi.dto.CursoDTO;
import br.com.fiap.revisaoapi.model.Curso;
import br.com.fiap.revisaoapi.service.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/cursos", produces = {"application/json"})
@Tag(name = "api-curso")
public class CursoController {

    private final CursoService cursoService;

    @Autowired
    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @Operation(summary = "Retorna todos os cursos em páginas de 3")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum curso encontrado", content = {
                    @Content(schema = @Schema())
            })
    })
    @GetMapping
    public ResponseEntity<Page<CursoDTO>> findAll(Pageable pageable) {
        Page<CursoDTO> cursosDTO = cursoService.findAll(pageable);
        if (cursosDTO.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            for (CursoDTO cursoDTO : cursosDTO){
                Long id = cursoDTO.getId();
                cursoDTO.add(linkTo(methodOn(CursoController.class)
                        .findById(id)).withSelfRel());
            }
        }
        return ResponseEntity.ok(cursosDTO);
    }

    @Operation(summary = "Retorna um curso específico por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum curso encontrado para o id informado", content = {
                    @Content(schema = @Schema())
            })
    })
    @GetMapping("/{id}")
    public ResponseEntity<CursoDTO> findById(@PathVariable Long id) {
        CursoDTO cursoDTO = cursoService.findById(id);
        if (cursoDTO == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            cursoDTO.add(linkTo(methodOn(CursoController.class)
                    .findAll(null)).withRel("Lista de Cursos"));
        }
        return ResponseEntity.ok(cursoDTO);
    }

    @Operation(summary = "Grava um curso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Curso gravado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados", content = {
                    @Content(schema = @Schema())
            })
    })
    @PostMapping
    public ResponseEntity<Curso> save(@Valid @RequestBody Curso curso) {
        Curso cursoSalvo = cursoService.save(curso);
        return new ResponseEntity<>(cursoSalvo, HttpStatus.CREATED);
    }

    @Operation(summary = "Atualiza um curso com base no id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Curso atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados", content = {
                    @Content(schema = @Schema())
            })
    })
    @PutMapping("/{id}")
    public ResponseEntity<Curso> update(@PathVariable Long id, @Valid @RequestBody Curso curso) {
        Curso cursoSalvo = cursoService.update(id, curso);
        return new ResponseEntity<>(cursoSalvo, HttpStatus.CREATED);
    }

    @Operation(summary = "Exclui um curso com base no id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Curso excluído com sucesso", content = {
                    @Content(schema = @Schema())
            })
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cursoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}


