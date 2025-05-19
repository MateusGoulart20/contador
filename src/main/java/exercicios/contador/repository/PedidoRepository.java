package exercicios.contador.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import exercicios.contador.models.Pedido;

public interface PedidoRepository 
        extends JpaRepository<Pedido, Long> {}
