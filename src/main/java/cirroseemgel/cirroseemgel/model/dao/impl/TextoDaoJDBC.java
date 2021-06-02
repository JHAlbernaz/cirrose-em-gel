package cirroseemgel.cirroseemgel.model.dao.impl;

import cirroseemgel.cirroseemgel.model.dao.TextoDao;
import cirroseemgel.cirroseemgel.model.entities.Comentario;
import cirroseemgel.cirroseemgel.model.entities.Texto;

import java.sql.Connection;
import java.util.List;

public class TextoDaoJDBC implements TextoDao {

    private Connection conn;

    public TextoDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Texto texto) {

    }

    @Override
    public void update(Texto texto) {

    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public Texto findById(String id) {
        return null;
    }

    @Override
    public List<Texto> findAll() {
        return null;
    }

    @Override
    public List<Texto> findLatestTexts() {
        return null;
    }
}
