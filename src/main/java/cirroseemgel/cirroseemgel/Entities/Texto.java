package cirroseemgel.cirroseemgel.Entities;

import java.time.LocalDate;

public class Texto {

    private String id;

    private String titulo;

    private String descricao;

    private String conteudo;

    private String urlImagem;

    private int numeroVisualizacoes;

    private LocalDate dataPublicacao;

    public Texto(String id, String titulo, String descricao, String conteudo, String urlImagem, int numeroVisualizacoes, LocalDate dataPublicacao) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.conteudo = conteudo;
        this.urlImagem = urlImagem;
        this.numeroVisualizacoes = numeroVisualizacoes;
        this.dataPublicacao = dataPublicacao;
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

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }
}
