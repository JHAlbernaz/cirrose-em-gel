package cirroseemgel.cirroseemgel.model.dao;

import cirroseemgel.cirroseemgel.db.DB;
import cirroseemgel.cirroseemgel.model.dao.impl.ComentarioDaoJDBC;
import cirroseemgel.cirroseemgel.model.dao.impl.CurtidaDaoJDBC;
import cirroseemgel.cirroseemgel.model.dao.impl.TextoDaoJDBC;
import cirroseemgel.cirroseemgel.model.dao.impl.UsuarioDaoJDBC;

public class DaoFactory {

    public static UsuarioDao crateUsuarioDao() {
        return new UsuarioDaoJDBC(DB.getConnection());
    }

    public static ComentarioDao crateComentarioDao() {
        return new ComentarioDaoJDBC(DB.getConnection());
    }

    public static TextoDao crateTextoDao() {
        return new TextoDaoJDBC(DB.getConnection());
    }

    public static CurtidaDao crateCurtidaDao() {
        return new CurtidaDaoJDBC(DB.getConnection());
    }
}
