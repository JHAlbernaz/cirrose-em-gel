package cirroseemgel.cirroseemgel.model.dao.impl;

import cirroseemgel.cirroseemgel.db.DB;
import cirroseemgel.cirroseemgel.db.DbException;
import cirroseemgel.cirroseemgel.model.dao.ComentarioDao;
import cirroseemgel.cirroseemgel.model.entities.Comentario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public class ComentarioDaoJDBC implements ComentarioDao {

    private Connection conn;

    public ComentarioDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Comentario comentario) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO COMENTARIO " +
                            "(id, conteudo, data_comentario, id_texto, id_usuario) " +
                            "VALUES " +
                            "(?, ?, ?, ?, ?)"
            );
            st.setString(1, UUID.randomUUID().toString());
            st.setString(2, comentario.getConteudo());
            st.setTimestamp(2, Timestamp.valueOf(comentario.getData()));
            st.setString(3, comentario.getTexto().getId());
            st.setString(4, comentario.getAutor().getId());

            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Comentario inserido com sucesso!");
            } else {
                throw new DbException("Unexpected error! No rows affected!");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Comentario comentario) {

    }

    @Override
    public void deleteById(String id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("DELETE FROM COMENTARIO WHERE id = ?");
            st.setString(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
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
