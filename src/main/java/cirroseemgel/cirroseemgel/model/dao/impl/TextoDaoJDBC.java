package cirroseemgel.cirroseemgel.model.dao.impl;

import cirroseemgel.cirroseemgel.db.DB;
import cirroseemgel.cirroseemgel.db.DbException;
import cirroseemgel.cirroseemgel.model.dao.TextoDao;
import cirroseemgel.cirroseemgel.model.entities.Comentario;
import cirroseemgel.cirroseemgel.model.entities.Texto;
import cirroseemgel.cirroseemgel.model.mappers.TextoMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TextoDaoJDBC implements TextoDao {

    private Connection conn;

    public TextoDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Texto texto) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO USUARIO " +
                    "(id, titulo, descricao, conteudo, url_imagem, numero_visualizacoes, data_publicacao) " +
                    "VALUES " +
                    "(?, ?, ?, ?, ?, ?, ?)"
            );
            String id = UUID.randomUUID().toString();
            st.setString(1, id);
            st.setString(2, texto.getTitulo());
            st.setString(3, texto.getDescricao());
            st.setString(4, texto.getConteudo());
            st.setString(5, texto.getUrlImagem());
            st.setInt(6, texto.getNumeroVisualizacoes());
            st.setTimestamp(7, Timestamp.valueOf(texto.getDataPublicacao()));

            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Texto inserted sucessfully!");
                texto.setId(id);
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
    public void update(Texto texto) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("UPDATE TEXTO " +
                    "SET titulo = ?, descricao = ?, conteudo = ?, imagem_url = ?, numeroVisualizacoes = ?, data_publicacao = ? " +
                    "WHERE id = ?"
            );
            st.setString(1, texto.getTitulo());
            st.setString(2, texto.getDescricao());
            st.setString(3, texto.getConteudo());
            st.setString(4, texto.getUrlImagem());
            st.setInt(5, texto.getNumeroVisualizacoes());
            st.setTimestamp(6, Timestamp.valueOf(texto.getDataPublicacao()));
            st.setString(7, texto.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public Texto findById(String id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM TEXTO " +
                            "WHERE id = ?"
            );
            st.setString(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                return TextoMapper.apply(rs);
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
    public List<Texto> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM TEXTO"
            );
            List<Texto> textos = new ArrayList<>();
            rs = st.executeQuery();
            while (rs.next()) {
                textos.add(TextoMapper.apply(rs));
            }
            return textos;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Texto> findLatestTexts(int numberOfTextsWanted) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM TEXTO " +
                    "ORDER BY data_publicacao ASC " +
                    "LIMIT ?"
            );
            st.setInt(1, numberOfTextsWanted);
            List<Texto> textos = new ArrayList<>();
            rs = st.executeQuery();
            while (rs.next()) {
                textos.add(TextoMapper.apply(rs));
            }
            return textos;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
}
