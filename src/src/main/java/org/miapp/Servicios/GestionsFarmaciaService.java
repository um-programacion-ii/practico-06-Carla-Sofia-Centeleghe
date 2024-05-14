package org.miapp.Servicios;

import org.miapp.DAO.FarmaciaDAO;
import org.miapp.DAO.RecetaDAO;
import org.miapp.Clases.Medicamento;
import org.miapp.Clases.Receta;
import org.miapp.Clases.Drogueria;

public class GestionFarmaciaService {
    private FarmaciaDAO farmaciaDAO;
    private RecetaDAO recetaDAO;
    private static final GestionFarmaciaService instance = new GestionFarmaciaService();

    private GestionFarmaciaService() {
        this.farmaciaDAO = new FarmaciaDAO();
        this.recetaDAO = new RecetaDAO();
    }

    public static GestionFarmaciaService getInstance() {
        return instance;
    }

    public void generarRecetaAleatoria(Receta receta) {
        recetaDAO.crearReceta(receta);
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
        return recetaDAO.listarTodasLasRecetas();
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
