package cirroseemgel.cirroseemgel.model.dao.impl;

import cirroseemgel.cirroseemgel.db.DB;
import cirroseemgel.cirroseemgel.db.DbException;
import cirroseemgel.cirroseemgel.model.dao.CurtidaDao;
import cirroseemgel.cirroseemgel.model.entities.Curtida;
import cirroseemgel.cirroseemgel.model.mappers.CurtidaMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CurtidaDaoJDBC implements CurtidaDao {

    private Connection conn;

    public CurtidaDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Curtida curtida) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO CURTIDA " +
                            "(id, data_curtida, id_texto, id_usuario) " +
                            "VALUES " +
                            "(?, ?, ?, ?)"
            );
            st.setString(1, UUID.randomUUID().toString());
            st.setTimestamp(2, Timestamp.valueOf(curtida.getData()));
            st.setString(3, curtida.getTexto().getId());
            st.setString(4, curtida.getAutor().getId());

            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Texto curtido com sucesso!");
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
            st = conn.prepareStatement("DELETE FROM CURTIDA WHERE id = ?");
            st.setString(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public List<Curtida> findByUsuarioId(String usuarioId) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM CURTIDA " +
                            "WHERE id_usuario = ?"
            );
            st.setString(1, usuarioId);
            List<Curtida> curtida = new ArrayList<>();
            rs = st.executeQuery();
            while (rs.next()) {
                curtida.add(CurtidaMapper.apply(rs));
            }
            return curtida;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Curtida> findByTextoId(String textoId) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM CURTIDA " +
                            "WHERE id_texto = ?"
            );
            st.setString(1, textoId);
            List<Curtida> curtida = new ArrayList<>();
            rs = st.executeQuery();
            while (rs.next()) {
                curtida.add(CurtidaMapper.apply(rs));
            }
            return curtida;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public int getNumberOfCurtidasByTextoId(String textoId) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT COUNT(*) AS numero_curtidas FROM CURTIDA " +
                            "WHERE id_texto = ?"
            );
            st.setString(1, textoId);
            rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt("numero_curtidas");
            }
            return 0;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
}
