package exercicios.contador.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import exercicios.contador.models.Categoria;
import exercicios.contador.models.Produto;
import exercicios.contador.models.ProdutoCategoria;

public interface CategoriaRepository 
extends JpaRepository<Categoria, Long>{
        @Query("SELECT c FROM Categoria c JOIN FETCH c.produtos WHERE c.id = :id")
        Optional<List<ProdutoCategoria>> findProdutosById(@Param("id") Long id);

        @Query("SELECT c FROM Categoria c JOIN FETCH c.produtos WHERE c.id = :id")
        Optional<Categoria> findByIdWithProdutoCategorias(@Param("id") Long id);
        
        
        List<Categoria> findByProdutosProduto(Produto produto);
        List<Categoria> findByProdutos(ProdutoCategoria produtoCategoria);
        
        @Query("""
                SELECT c 
                FROM Categoria c 
                WHERE c IN (
                       SELECT pc.categoria 
                       FROM ProdutoCategoria pc
                       GROUP BY pc.categoria 
                       HAVING COUNT(pc.produto) > 10
                ) 
        """)
        List<Categoria> listarCategoriasComMaisDe10JPQL();
}
