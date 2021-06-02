package cirroseemgel.cirroseemgel.model.entities;

public class Usuario {

    private String id;

    private String nome;

    private String email;

    private String senha;

    private boolean estaAssinando;

    public Usuario(String id, String nome, String email, String senha, boolean estaAssinando) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.estaAssinando = estaAssinando;
    }

    public Usuario() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isEstaAssinando() {
        return estaAssinando;
    }

    public void setEstaAssinando(boolean estaAssinando) {
        this.estaAssinando = estaAssinando;
    }
}
