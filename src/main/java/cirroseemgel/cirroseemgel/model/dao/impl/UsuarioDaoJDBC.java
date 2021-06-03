package cirroseemgel.cirroseemgel.model.dao.impl;

import cirroseemgel.cirroseemgel.db.DB;
import cirroseemgel.cirroseemgel.db.DbException;
import cirroseemgel.cirroseemgel.model.dao.UsuarioDao;
import cirroseemgel.cirroseemgel.model.entities.Comentario;
import cirroseemgel.cirroseemgel.model.entities.Usuario;
import cirroseemgel.cirroseemgel.model.mappers.UsuarioMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDaoJDBC implements UsuarioDao {

    private Connection conn;
    private UsuarioMapper userMapper;

    public UsuarioDaoJDBC(Connection conn) {
        this.conn = conn;
        this.userMapper = new UsuarioMapper();
    }

    @Override
    public void insert(Usuario usuario) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO USUARIO " +
                    "(id, nome, email, senha, assinando) " +
                    "VALUES " +
                    "(?, ?, ?, ?, ?)"
            );
            st.setString(1, usuario.getId());
            st.setString(2, usuario.getNome());
            st.setString(3, usuario.getEmail());
            st.setString(4, usuario.getSenha());
            st.setBoolean(5, usuario.isEstaAssinando());

            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Usuario inserted sucessfully!");
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
    public void update(Usuario usuario) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("UPDATE USUARIO " +
                    "SET nome = ?, senha = ?, assinando = ? " +
                    "WHERE id = ?"
            );
            st.setString(1, usuario.getNome());
            st.setString(2, usuario.getSenha());
            st.setBoolean(3, usuario.isEstaAssinando());
            st.setString(4, usuario.getId());
            st.executeUpdate();
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
            st = conn.prepareStatement("DELETE FROM USUARIO WHERE id = ?");
            st.setString(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Usuario findById(String id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM USUARIO " +
                    "WHERE id = ?"
            );
            st.setString(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                return userMapper.apply(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public Usuario findByEmailAndPassword(String email, String password) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM USUARIO " +
                            "WHERE email = ? and senha = ?"
            );
            st.setString(1, email);
            st.setString(2, password);
            rs = st.executeQuery();
            while (rs.next()) {
                return userMapper.apply(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
}
