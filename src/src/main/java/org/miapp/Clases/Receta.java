package org.miapp.Clases;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
public class Receta {
    private int id;
    private List<Medicamento> medicamentos;

}
