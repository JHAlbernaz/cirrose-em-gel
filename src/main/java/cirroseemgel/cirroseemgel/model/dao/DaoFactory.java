package cirroseemgel.cirroseemgel.model.dao;

import cirroseemgel.cirroseemgel.model.dao.impl.ComentarioDaoJDBC;
import cirroseemgel.cirroseemgel.model.dao.impl.CurtidaDaoJDBC;
import cirroseemgel.cirroseemgel.model.dao.impl.TextoDaoJDBC;
import cirroseemgel.cirroseemgel.model.dao.impl.UsuarioDaoJDBC;

public class DaoFactory {

    public static UsuarioDao crateUsuarioDao() {
        return new UsuarioDaoJDBC();
    }

    public static ComentarioDao crateComentarioDao() {
        return new ComentarioDaoJDBC();
    }

    public static TextoDao crateTextoDao() {
        return new TextoDaoJDBC();
    }

    public static CurtidaDao crateCurtidaDao() {
        return new CurtidaDaoJDBC();
    }
}
