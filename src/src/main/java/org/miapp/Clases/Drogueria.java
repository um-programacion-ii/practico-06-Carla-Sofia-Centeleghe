package org.miapp.Clases;

import java.util.Map;
import java.util.Optional;
import java.util.Collections;


public class Drogueria {
    private final Map<Medicamento, Integer> stockMedicamentos;

    public Drogueria() {
        this.stockMedicamentos = Collections.emptyMap();
    }

    public Optional<Map<Medicamento, Integer>> getStockMedicamentos() {
        return Optional.ofNullable(stockMedicamentos);
    }

}
