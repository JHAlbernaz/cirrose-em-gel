package cirroseemgel.cirroseemgel.model.dao.impl;

import cirroseemgel.cirroseemgel.model.dao.ComentarioDao;
import cirroseemgel.cirroseemgel.model.entities.Comentario;

import java.util.List;

public class ComentarioDaoJDBC implements ComentarioDao {

    @Override
    public void insert(Comentario comentario) {

    }

    @Override
    public void update(Comentario comentario) {

    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public List<Comentario> findByUsuarioId(String usuarioId) {
        return null;
    }

    @Override
    public List<Comentario> findByTextoId(String textoId) {
        return null;
    }
}
