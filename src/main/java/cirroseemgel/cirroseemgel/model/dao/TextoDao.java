package cirroseemgel.cirroseemgel.model.dao;

import cirroseemgel.cirroseemgel.model.entities.Comentario;
import cirroseemgel.cirroseemgel.model.entities.Texto;

import java.util.List;

public interface TextoDao {

    void insert(Texto texto);
    void update(Texto texto);
    void deleteById(String id);
    Texto findById(String id);
    List<Texto> findAll();
    List<Texto> findLatestTexts(int numberOfTextsWanted);
    void addVisualizacao(Texto texto);

}
