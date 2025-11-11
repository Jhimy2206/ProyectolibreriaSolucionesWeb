package com.example.demo.service;

import com.example.demo.entity.Stock;
import com.example.demo.repository.StockRepository;
import com.example.demo.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private LibroRepository libroRepository;

    public List<Stock> listarTodos() {
        return stockRepository.findAll();
    }

    public Stock guardar(Stock stock) {
        if (!libroRepository.existsById(stock.getLibro().getId())) {
            throw new RuntimeException("El libro no existe");
        }

        if (stock.getCantidad() < 0) {
            throw new RuntimeException("La cantidad no puede ser negativa");
        }

        Stock stockExistente = stockRepository.findByLibroId(stock.getLibro().getId());
        if (stockExistente != null) {
            stockExistente.setCantidad(stock.getCantidad());
            stockExistente.setDisponibles(stock.getDisponibles());
            stockExistente.setMinimo(stock.getMinimo());
            return stockRepository.save(stockExistente);
        }

        return stockRepository.save(stock);
    }

    public Optional<Stock> obtenerPorId(Long id) {
        return stockRepository.findById(id);
    }

    public Stock obtenerPorLibro(Long libroId) {
        return stockRepository.findByLibroId(libroId);
    }

    public List<Stock> listarStockBajo() {
        return stockRepository.findByDisponiblesLessThanEqual(1);
    }

    public void eliminar(Long id) {
        stockRepository.deleteById(id);
    }
}