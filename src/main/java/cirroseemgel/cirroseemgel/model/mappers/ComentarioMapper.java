package cirroseemgel.cirroseemgel.model.mappers;

import cirroseemgel.cirroseemgel.model.entities.Comentario;
import cirroseemgel.cirroseemgel.model.entities.Texto;
import cirroseemgel.cirroseemgel.model.entities.Usuario;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ComentarioMapper {

    public static Comentario apply(ResultSet rs) throws SQLException {
        Comentario comentario = new Comentario();
        Usuario usuario = new Usuario();
        usuario.setId(rs.getString("id_usuario"));
        usuario.setNome(rs.getString("nome_usuario"));
        comentario.setAutor(usuario);
        comentario.setId(rs.getString("id"));
        comentario.setData(rs.getTimestamp("data_comentario").toLocalDateTime());
        comentario.setConteudo(rs.getString("conteudo"));
        comentario.setTexto(new Texto(rs.getString("id_texto")));
        return comentario;
    }
}