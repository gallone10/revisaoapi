package br.com.fiap.revisaoapi.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_curso")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título é obrigatório")
    @Size(min = 1, max = 100, message = "O título deve ter entre 1 e 100 caracteres")
    @Column(name = "titulo")
    private String titulo;

    @NotBlank(message = "A descrição é obrigatória")
    @Size(min = 10, max = 500, message = "A descrição deve ter entre 10 e 500 caracteres")
    @Column(name = "descricao")
    private String descricao;

    @NotBlank(message = "O instrutor é obrigatório")
    @Column(name = "instrutor")
    private String instrutor;

    @Min(value = 1, message = "A duração deve ser no mínimo 1 hora")
    @Column(name = "duracao")
    private int duracao;

    @NotBlank(message = "O nível é obrigatório")
    @Column(name = "nivel")
    private String nivel;

    @Min(value = 0, message = "O preço deve ser maior ou igual a zero")
    @Column(name = "preco")
    private double preco;

    public Curso() {
    }

    public Curso(Long id, String titulo, String descricao, String instrutor, int duracao, String nivel, double preco) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.instrutor = instrutor;
        this.duracao = duracao;
        this.nivel = nivel;
        this.preco = preco;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getInstrutor() {
        return instrutor;
    }

    public void setInstrutor(String instrutor) {
        this.instrutor = instrutor;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
