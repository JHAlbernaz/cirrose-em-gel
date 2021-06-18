package cirroseemgel.cirroseemgel.model.dao.impl;

import cirroseemgel.cirroseemgel.db.DB;
import cirroseemgel.cirroseemgel.db.DbException;
import cirroseemgel.cirroseemgel.model.dao.ComentarioDao;
import cirroseemgel.cirroseemgel.model.entities.Comentario;
import cirroseemgel.cirroseemgel.model.mappers.ComentarioMapper;
import cirroseemgel.cirroseemgel.service.CommentsScreenService;

import java.sql.*;
import java.util.ArrayList;
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
            st.setTimestamp(3, Timestamp.valueOf(comentario.getData()));
            st.setString(4, comentario.getTexto().getId());
            st.setString(5, comentario.getAutor().getId());

            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                CommentsScreenService.commentAddedOrUpdatedOrDeletedSucessfully("Adicionado");
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
    public void deleteById(String id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("DELETE FROM COMENTARIO WHERE id = ?");
            st.setString(1, id);
            st.executeUpdate();
            CommentsScreenService.commentAddedOrUpdatedOrDeletedSucessfully("Deletado");
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public List<Comentario> findByUsuarioId(String usuarioId) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM COMENTARIO " +
                            "WHERE id_usuario = ?"
            );
            st.setString(1, usuarioId);
            List<Comentario> comentarios = new ArrayList<>();
            rs = st.executeQuery();
            while (rs.next()) {
                comentarios.add(ComentarioMapper.apply(rs));
            }
            return comentarios;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Comentario> findByTextoId(String textoId) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT c.id AS id, " +
                    "c.conteudo AS conteudo, " +
                    "c.id_texto AS id_texto, " +
                    "c.id_usuario AS id_usuario, " +
                    "c.data_comentario AS data_comentario, " +
                    "u.nome AS nome_usuario " +
                    "FROM comentario c " +
                    "JOIN usuario u " +
                    "ON u.id = c.id_usuario " +
                    "WHERE c.id_texto = ?"
            );
            st.setString(1, textoId);
            List<Comentario> comentarios = new ArrayList<>();
            rs = st.executeQuery();
            while (rs.next()) {
                comentarios.add(ComentarioMapper.apply(rs));
            }
            return comentarios;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public int getNumberOfComentariosByTextoId(String textoId) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT COUNT(*) AS numero_comentarios FROM COMENTARIO " +
                            "WHERE id_texto = ?"
            );
            st.setString(1, textoId);
            rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt("numero_comentarios");
            }
            return 0;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public void updateCommentContent(Comentario comment) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE COMENTARIO " +
                            "SET conteudo = ? " +
                            "WHERE id = ?"
            );
            st.setString(1, comment.getConteudo());
            st.setString(2, comment.getId());
            st.executeUpdate();
            CommentsScreenService.commentAddedOrUpdatedOrDeletedSucessfully("Atualizado");
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteUserComments(String userId) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("DELETE FROM COMENTARIO WHERE id_usuario = ?");
            st.setString(1, userId);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteTextComments(String textId) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("DELETE FROM COMENTARIO WHERE id_texto = ?");
            st.setString(1, textId);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }
}
