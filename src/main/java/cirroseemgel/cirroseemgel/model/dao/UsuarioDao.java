package cirroseemgel.cirroseemgel.model.dao;

import cirroseemgel.cirroseemgel.model.entities.Comentario;
import cirroseemgel.cirroseemgel.model.entities.Usuario;

import java.util.List;

public interface UsuarioDao {

    String insert(Usuario usuario);
    void update(Usuario usuario);
    void deleteById(String id);
    Usuario findById(String id);
    Usuario findByEmailAndPassword(String email, String password);
    List<Usuario> findAllUserAssinando();

}
