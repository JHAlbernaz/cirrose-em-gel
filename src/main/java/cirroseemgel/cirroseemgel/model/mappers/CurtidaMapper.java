package cirroseemgel.cirroseemgel.model.mappers;

import cirroseemgel.cirroseemgel.model.entities.Curtida;
import cirroseemgel.cirroseemgel.model.entities.Texto;
import cirroseemgel.cirroseemgel.model.entities.Usuario;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CurtidaMapper {

    public static Curtida apply(ResultSet rs) throws SQLException {
        Curtida curtida = new Curtida();
        curtida.setId(rs.getString("id"));
        curtida.setData(rs.getTimestamp("data_curtida").toLocalDateTime());
        curtida.setAutor(new Usuario(rs.getString("id_usuario")));
        curtida.setTexto(new Texto(rs.getString("id_texto")));
        return curtida;
    }
}
