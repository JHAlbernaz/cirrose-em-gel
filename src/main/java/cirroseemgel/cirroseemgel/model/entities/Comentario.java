package cirroseemgel.cirroseemgel.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Comentario {

    private String id;

    private LocalDateTime data;

    private String conteudo;

    private Texto texto;

    private Usuario autor;

}
