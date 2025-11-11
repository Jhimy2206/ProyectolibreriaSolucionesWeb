package com.example.demo.controller;

import com.example.demo.entity.Stock;
import com.example.demo.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/stocks")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping
    public List<Stock> listarTodos() {
        return stockService.listarTodos();
    }

    @PostMapping
    public ResponseEntity<Stock> crear(@RequestBody Stock stock) {
        try {
            return ResponseEntity.ok(stockService.guardar(stock));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stock> obtenerPorId(@PathVariable Long id) {
        return stockService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/libro/{libroId}")
    public ResponseEntity<Stock> obtenerPorLibro(@PathVariable Long libroId) {
        Stock stock = stockService.obtenerPorLibro(libroId);
        if (stock == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(stock);
    }

    @GetMapping("/bajo")
    public List<Stock> listarStockBajo() {
        return stockService.listarStockBajo();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Stock> actualizar(@PathVariable Long id, @RequestBody Stock stock) {
        try {
            stock.setId(id);
            return ResponseEntity.ok(stockService.guardar(stock));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        stockService.eliminar(id);
        return ResponseEntity.ok().build();
    }
}