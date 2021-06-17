package cirroseemgel.cirroseemgel.model.dao;

import cirroseemgel.cirroseemgel.db.DB;
import cirroseemgel.cirroseemgel.model.dao.impl.ComentarioDaoJDBC;
import cirroseemgel.cirroseemgel.model.dao.impl.CurtidaDaoJDBC;
import cirroseemgel.cirroseemgel.model.dao.impl.TextoDaoJDBC;
import cirroseemgel.cirroseemgel.model.dao.impl.UsuarioDaoJDBC;

public class DaoFactory {

    public static UsuarioDao createUsuarioDao() {
        return new UsuarioDaoJDBC(DB.getConnection());
    }

    public static ComentarioDao createComentarioDao() {
        return new ComentarioDaoJDBC(DB.getConnection());
    }

    public static TextoDao createTextDao() {
        return new TextoDaoJDBC(DB.getConnection());
    }

    public static CurtidaDao createCurtidaDao() {
        return new CurtidaDaoJDBC(DB.getConnection());
    }
}
