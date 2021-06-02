package cirroseemgel.cirroseemgel.model.mappers;

import cirroseemgel.cirroseemgel.model.entities.Usuario;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioMapper {

    public Usuario apply(ResultSet rs) throws SQLException {
        Usuario user = new Usuario();
        user.setId(rs.getString("id"));
        user.setNome(rs.getString("nome"));
        user.setEmail(rs.getString("email"));
        user.setSenha(rs.getString("senha"));
        user.setEstaAssinando(rs.getBoolean("assinando"));
        return user;
    }
}
