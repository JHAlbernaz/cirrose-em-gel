package cirroseemgel.cirroseemgel.model.mappers;

import cirroseemgel.cirroseemgel.model.entities.Usuario;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class UsuarioMapper {

    public static Usuario apply(ResultSet rs, String[] fields) throws SQLException {
        Usuario user = new Usuario();
        if (Arrays.stream(fields).anyMatch(field -> field == "id")) {
            user.setId(rs.getString("id"));
        }
        if (Arrays.stream(fields).anyMatch(field -> field == "nome")) {
            user.setNome(rs.getString("nome"));
        }
        if (Arrays.stream(fields).anyMatch(field -> field == "email")) {
            user.setEmail(rs.getString("email"));
        }
        if (Arrays.stream(fields).anyMatch(field -> field == "senha")) {
            user.setSenha(rs.getString("senha"));
        }
        if (Arrays.stream(fields).anyMatch(field -> field == "assinando")) {
            user.setEstaAssinando(rs.getBoolean("assinando"));
        }
        return user;
    }
}
