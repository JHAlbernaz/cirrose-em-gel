package cirroseemgel.cirroseemgel.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Usuario {

    private String id;

    private String nome;

    private String email;

    private String senha;

    private boolean estaAssinando;

    public Usuario(String id) {
        this.id = id;
    }
}
