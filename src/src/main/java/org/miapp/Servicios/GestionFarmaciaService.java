package org.miapp.Servicios;

import lombok.Getter;
import lombok.Setter;
import org.miapp.DAO.FarmaciaDAO;
import org.miapp.DAO.RecetaDAO;
import org.miapp.Clases.Medicamento;
import org.miapp.Clases.Receta;
import org.miapp.Clases.Drogueria;

import java.util.List;

public class GestionFarmaciaService {
    private final FarmaciaDAO farmaciaDAO;
    private final RecetaDAO recetaDAO;
    @Getter
    @Setter

    public static GestionFarmaciaService instance = new GestionFarmaciaService();

    private GestionFarmaciaService() {
        this.farmaciaDAO = new FarmaciaDAO();
        this.recetaDAO = new RecetaDAO();
    }


    public int obtenerStockMedicamento(Medicamento medicamento) {
        return farmaciaDAO.obtenerStockMedicamento(medicamento);
    }

    public void actualizarStockMedicamento(Medicamento medicamento, int cantidad) {
        farmaciaDAO.actualizarStockMedicamento(medicamento, cantidad);
    }

    public List<Medicamento> obtenerMedicamentosPorCategoria(String categoria) {
        return farmaciaDAO.obtenerMedicamentosPorCategoria(categoria);
    }

    public Receta generarRecetaEspecifica(Receta recetaEspecifica) {
        return recetaDAO.crearReceta(recetaEspecifica);
    }

    public List<Receta> listarTodasLasRecetas() {
        return recetaDAO.actualizarReceta();
    }

    public void asociarRecetaADrogueria(Receta receta, Drogueria drogueria) {
        recetaDAO.asociarRecetaADrogueria(receta, drogueria);
    }

    public void generarRecetaAleatoria(Receta receta) {
        if (Math.random() < 0.5) {  // 50% de probabilidad de obtener una receta
            recetaDAO.crearReceta(receta);
        } else {
            System.out.println("El paciente no ha recibido una receta en esta visita.");
        }
    }
}
