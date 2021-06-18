package cirroseemgel.cirroseemgel.model.dao;

import cirroseemgel.cirroseemgel.model.entities.Comentario;
import cirroseemgel.cirroseemgel.model.entities.Curtida;

import java.util.List;

public interface ComentarioDao {

    void insert(Comentario comentario);
    void deleteById(String id);
    List<Comentario> findByUsuarioId(String usuarioId);
    List<Comentario> findByTextoId(String textoId);
    int getNumberOfComentariosByTextoId(String textoId);
    void updateCommentContent(Comentario comment);

}
