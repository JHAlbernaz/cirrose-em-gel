package cirroseemgel.cirroseemgel.model.dao.impl;

import cirroseemgel.cirroseemgel.model.dao.CurtidaDao;
import cirroseemgel.cirroseemgel.model.entities.Comentario;
import cirroseemgel.cirroseemgel.model.entities.Curtida;

import java.sql.Connection;
import java.util.List;

public class CurtidaDaoJDBC implements CurtidaDao {

    private Connection conn;

    public CurtidaDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Curtida curtida) {

    }

    @Override
    public void update(Curtida curtida) {

    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public List<Curtida> findByUsuarioId(String usuarioId) {
        return null;
    }

    @Override
    public List<Curtida> findByTextoId(String textoId) {
        return null;
    }
}