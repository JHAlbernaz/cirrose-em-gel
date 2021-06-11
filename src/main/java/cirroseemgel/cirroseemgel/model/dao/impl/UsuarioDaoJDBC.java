package cirroseemgel.cirroseemgel.model.dao.impl;

import cirroseemgel.cirroseemgel.db.DB;
import cirroseemgel.cirroseemgel.db.DbException;
import cirroseemgel.cirroseemgel.model.dao.UsuarioDao;
import cirroseemgel.cirroseemgel.model.entities.Usuario;
import cirroseemgel.cirroseemgel.model.mappers.UsuarioMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UsuarioDaoJDBC implements UsuarioDao {

    private Connection conn;

    public UsuarioDaoJDBC(Connection conn) {
        this.conn = conn;
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
            st.setString(1, UUID.randomUUID().toString());
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
        String[] fieldsDesired = {"id", "nome", "email", "assinando"};
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM USUARIO " +
                    "WHERE id = ?"
            );
            st.setString(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                return UsuarioMapper.apply(rs, fieldsDesired);
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
        String[] fieldsDesired = {"id", "nome", "email", "senha", "assinando"};
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM USUARIO " +
                            "WHERE email = ? and senha = ?"
            );
            st.setString(1, email);
            st.setString(2, password);
            rs = st.executeQuery();
            while (rs.next()) {
                return UsuarioMapper.apply(rs, fieldsDesired);
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
    public List<Usuario> findAllUserAssinando() {
        PreparedStatement st = null;
        ResultSet rs = null;
        String[] fieldsDesired = {"id", "nome", "email", "assinando"};
        try {
            st = conn.prepareStatement(
                    "SELECT id, nome, email, assinando FROM USUARIO " +
                            "WHERE assinando = ?"
            );
            st.setBoolean(1, true);
            rs = st.executeQuery();
            List<Usuario> usuariosAssinando = new ArrayList<>();
            while (rs.next()) {
                usuariosAssinando.add(UsuarioMapper.apply(rs, fieldsDesired));
            }
            return usuariosAssinando;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
}
