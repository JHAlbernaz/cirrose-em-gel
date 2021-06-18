package cirroseemgel.cirroseemgel.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Texto {

    private String id;

    private String titulo;

    private String descricao;

    private String conteudo;

    private int numeroVisualizacoes;

    private LocalDateTime dataPublicacao;

    public Texto(String id) {
        this.id = id;
    }

    public Texto(String titulo, String descricao, String conteudo, LocalDateTime dataPublicacao) {

        this.titulo = titulo;
        this.descricao = descricao;
        this.conteudo = conteudo;
        this.numeroVisualizacoes = 0;
        this.dataPublicacao = dataPublicacao;

    }

    public void addVisualizacao() {
        numeroVisualizacoes++;
    }
}
