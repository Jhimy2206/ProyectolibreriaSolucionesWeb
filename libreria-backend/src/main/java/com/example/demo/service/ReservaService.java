package com.example.demo.service;

import com.example.demo.entity.Reserva;
import com.example.demo.repository.ReservaRepository;
import com.example.demo.repository.LibroRepository;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private StockRepository stockRepository;

    public List<Reserva> listarTodas() {
        return reservaRepository.findAll();
    }

    public List<Reserva> listarPorUsuario(Long usuarioId) {
        return reservaRepository.findByUsuarioId(usuarioId);
    }

    public List<Reserva> listarPorLibro(Long libroId) {
        return reservaRepository.findByLibroId(libroId);
    }

    public Reserva crear(Reserva reserva) {
        if (!libroRepository.existsById(reserva.getLibro().getId())) {
            throw new RuntimeException("El libro no existe");
        }

        if (!usuarioRepository.existsById(reserva.getUsuario().getId())) {
            throw new RuntimeException("El usuario no existe");
        }

        var stock = stockRepository.findByLibroId(reserva.getLibro().getId());
        if (stock == null || stock.getDisponibles() <= 0) {
            throw new RuntimeException("No hay ejemplares disponibles para reservar");
        }

        reserva.setFechaReserva(LocalDateTime.now());
        reserva.setFechaExpiracion(LocalDateTime.now().plusDays(3));
        reserva.setEstado("PENDIENTE");

        stock.setDisponibles(stock.getDisponibles() - 1);
        stockRepository.save(stock);

        return reservaRepository.save(reserva);
    }

    public Reserva cancelar(Long id) {
        var reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        if (!"PENDIENTE".equals(reserva.getEstado())) {
            throw new RuntimeException("No se puede cancelar una reserva que no está pendiente");
        }

        var stock = stockRepository.findByLibroId(reserva.getLibro().getId());
        if (stock != null) {
            stock.setDisponibles(stock.getDisponibles() + 1);
            stockRepository.save(stock);
        }

        reserva.setEstado("CANCELADA");
        return reservaRepository.save(reserva);
    }

    public Optional<Reserva> obtenerPorId(Long id) {
        return reservaRepository.findById(id);
    }

    public void eliminar(Long id) {
        reservaRepository.deleteById(id);
    }
}