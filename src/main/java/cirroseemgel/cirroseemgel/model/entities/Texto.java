package cirroseemgel.cirroseemgel.model.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Texto {

    private String id;

    private String titulo;

    private String descricao;

    private String conteudo;

    private String urlImagem;

    private int numeroVisualizacoes;

    private LocalDateTime dataPublicacao;

    public Texto(String id, String titulo, String descricao, String conteudo, String urlImagem, int numeroVisualizacoes, LocalDateTime dataPublicacao) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.conteudo = conteudo;
        this.urlImagem = urlImagem;
        this.numeroVisualizacoes = numeroVisualizacoes;
        this.dataPublicacao = dataPublicacao;
    }

    public Texto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public int getNumeroVisualizacoes() {
        return numeroVisualizacoes;
    }

    public void setNumeroVisualizacoes(int numeroVisualizacoes) {
        this.numeroVisualizacoes = numeroVisualizacoes;
    }

    public LocalDateTime getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDateTime dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }
}
