package cirroseemgel.cirroseemgel.model.mappers;

import cirroseemgel.cirroseemgel.model.entities.Texto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TextoMapper {

    public static Texto apply(ResultSet rs) throws SQLException {
        Texto texto = new Texto();
        texto.setId(rs.getString("id"));
        texto.setTitulo(rs.getString("titulo"));
        texto.setDescricao(rs.getString("descricao"));
        texto.setConteudo(rs.getString("conteudo"));
        texto.setUrlImagem(rs.getString("url_imagem"));
        texto.setNumeroVisualizacoes(rs.getInt("numero_visualizacoes"));
        texto.setDataPublicacao(rs.getTimestamp("data_publicacao").toLocalDateTime());
        return texto;
    }
}