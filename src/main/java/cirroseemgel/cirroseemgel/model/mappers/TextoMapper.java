package cirroseemgel.cirroseemgel.model.mappers;

import cirroseemgel.cirroseemgel.model.entities.Texto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class TextoMapper {

    public static Texto apply(ResultSet rs, String[] fields) throws SQLException {
        Texto texto = new Texto();

        if (Arrays.stream(fields).anyMatch(field -> field == "id")) {
            texto.setId(rs.getString("id"));
        }
        if (Arrays.stream(fields).anyMatch(field -> field == "titulo")) {
            texto.setTitulo(rs.getString("titulo"));
        }
        if (Arrays.stream(fields).anyMatch(field -> field == "descricao")) {
            texto.setDescricao(rs.getString("descricao"));
        }
        if (Arrays.stream(fields).anyMatch(field -> field == "conteudo")) {
            texto.setDescricao(rs.getString("conteudo"));
        }
        if (Arrays.stream(fields).anyMatch(field -> field == "numero_visualizacoes")) {
            texto.setNumeroVisualizacoes(rs.getInt("numero_visualizacoes"));
        }
        if (Arrays.stream(fields).anyMatch(field -> field == "data_publicacao")) {
            texto.setDataPublicacao(rs.getTimestamp("data_publicacao").toLocalDateTime());
        }
        return texto;
    }
}