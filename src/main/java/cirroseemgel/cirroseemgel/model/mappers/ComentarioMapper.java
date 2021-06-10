package cirroseemgel.cirroseemgel.model.mappers;

import cirroseemgel.cirroseemgel.model.entities.Comentario;
import cirroseemgel.cirroseemgel.model.entities.Texto;
import cirroseemgel.cirroseemgel.model.entities.Usuario;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ComentarioMapper {

    public static Comentario apply(ResultSet rs) throws SQLException {
        Comentario comentario = new Comentario();
        comentario.setId(rs.getString("id"));
        comentario.setData(rs.getTimestamp("data_curtida").toLocalDateTime());
        comentario.setConteudo(rs.getString("conteudo"));
        comentario.setAutor(new Usuario(rs.getString("id_usuario")));
        comentario.setTexto(new Texto(rs.getString("id_texto")));
        return comentario;
    }
}