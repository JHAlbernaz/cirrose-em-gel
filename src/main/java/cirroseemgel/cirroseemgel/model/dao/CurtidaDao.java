package cirroseemgel.cirroseemgel.model.dao;

import cirroseemgel.cirroseemgel.model.entities.Comentario;
import cirroseemgel.cirroseemgel.model.entities.Curtida;

import java.util.List;

public interface CurtidaDao {

    void insert(Curtida curtida);
    void deleteById(String id);
    List<Curtida> findByUsuarioId(String usuarioId);
    List<Curtida> findByTextoId(String textoId);
    int getNumberOfCurtidasByTextoId(String textoId);

}
