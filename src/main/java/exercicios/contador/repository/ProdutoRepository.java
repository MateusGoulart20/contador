package exercicios.contador.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import exercicios.contador.models.Produto;
import exercicios.contador.models.ProdutoCategoria;

public interface ProdutoRepository
extends JpaRepository<Produto, Long>{
        //@Query("SELECT p.categorias FROM Produto p JOIN FETCH p.categorias WHERE p.id = :id")
        @Query("SELECT c FROM Produto p JOIN p.categorias c WHERE p.id = :id")
        Optional<List<ProdutoCategoria>> findCategoriasById(@Param("id") Long id);

        @Query("SELECT p FROM Produto p JOIN FETCH p.categorias WHERE p.id = :id")
        Optional<Produto> findByIdWithProdutoCategorias(@Param("id") Long id);
}
