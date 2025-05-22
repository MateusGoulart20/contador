package exercicios.contador.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import exercicios.contador.models.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {}

