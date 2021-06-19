package cirroseemgel.cirroseemgel.model.dao.impl;

import cirroseemgel.cirroseemgel.db.DB;
import cirroseemgel.cirroseemgel.db.DbException;
import cirroseemgel.cirroseemgel.model.dao.TextoDao;
import cirroseemgel.cirroseemgel.model.entities.Texto;
import cirroseemgel.cirroseemgel.model.mappers.TextoMapper;
import cirroseemgel.cirroseemgel.service.PublishNewTextScreenService;

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
                    "INSERT INTO TEXTO " +
                    "(id, titulo, descricao, conteudo, numero_visualizacoes, data_publicacao) " +
                    "VALUES " +
                    "(?, ?, ?, ?, ?, ?)"
            );
            String id = UUID.randomUUID().toString();
            st.setString(1, id);
            st.setString(2, texto.getTitulo());
            st.setString(3, texto.getDescricao());
            st.setString(4, texto.getConteudo());
            st.setInt(5, texto.getNumeroVisualizacoes());
            st.setTimestamp(6, Timestamp.valueOf(texto.getDataPublicacao()));

            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                PublishNewTextScreenService.showTextAddOrUpdatedOrDeletedMessage("Adicionado");
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
                    "SET titulo = ?, descricao = ?, conteudo = ?, numero_visualizacoes = ?, data_publicacao = ? " +
                    "WHERE id = ?"
            );
            st.setString(1, texto.getTitulo());
            st.setString(2, texto.getDescricao());
            st.setString(3, texto.getConteudo());
            st.setInt(4, texto.getNumeroVisualizacoes());
            st.setTimestamp(5, Timestamp.valueOf(texto.getDataPublicacao()));
            st.setString(6, texto.getId());
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

            st = conn.prepareStatement("DELETE FROM TEXTO WHERE id = ?");
            st.setString(1, id);
            st.executeUpdate();

        } catch (SQLException e) {

            throw new DbException(e.getMessage());

        } finally {

            DB.closeStatement(st);
        }
    }

    @Override
    public Texto findById(String id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        String[] fieldsDesired = {"id", "titulo", "descricao", "conteudo", "numero_visualizacoes", "data_publicacao"};
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM TEXTO " +
                            "WHERE id = ?"
            );
            st.setString(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                return TextoMapper.apply(rs, fieldsDesired);
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
    public List<Texto> findLatestTexts(int numberOfTextsWanted) {
        PreparedStatement st = null;
        ResultSet rs = null;
        String[] fieldsDesired = {"id", "titulo", "descricao", "data_publicacao", "numero_visualizacoes"};
        try {
            st = conn.prepareStatement(
                    "SELECT id, titulo, descricao, data_publicacao, numero_visualizacoes FROM TEXTO " +
                    "ORDER BY data_publicacao DESC " +
                    "LIMIT ?"
            );
            st.setInt(1, numberOfTextsWanted);
            List<Texto> textos = new ArrayList<>();
            rs = st.executeQuery();
            while (rs.next()) {
                textos.add(TextoMapper.apply(rs, fieldsDesired));
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
    public void addVisualizacao(Texto texto) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("UPDATE TEXTO " +
                    "SET numero_visualizacoes = ? " +
                    "WHERE id = ?"
            );
            st.setInt(1, texto.getNumeroVisualizacoes());
            st.setString(2, texto.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }
}
