package cirroseemgel.cirroseemgel.Entities;

import java.time.LocalDate;

public class Comentario {

    private String id;

    private LocalDate data;

    private String conteudo;

    private Texto texto;

    private Usuario autor;

    public Comentario(String id, LocalDate data, String conteudo, Texto texto, Usuario autor) {
        this.id = id;
        this.data = data;
        this.conteudo = conteudo;
        this.texto = texto;
        this.autor = autor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Texto getTexto() {
        return texto;
    }

    public void setTexto(Texto texto) {
        this.texto = texto;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }
}
