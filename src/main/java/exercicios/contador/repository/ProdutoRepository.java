package exercicios.contador.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import exercicios.contador.models.Produto;
import exercicios.contador.models.ProdutoCategoria;
import exercicios.contador.models.Categoria;


public interface ProdutoRepository
extends JpaRepository<Produto, Long>{
        //@Query("SELECT p.categorias FROM Produto p JOIN FETCH p.categorias WHERE p.id = :id")
        //@Query("SELECT c FROM Produto p JOIN p.categorias c WHERE p.id = :id")
        //Optional<List<ProdutoCategoria>> findCategoriasById(@Param("id") Long id);

        @Query("SELECT p FROM Produto p JOIN FETCH p.categorias WHERE p.id = :id")
        Optional<Produto> findByIdWithProdutoCategorias(@Param("id") Long id);
        List<Produto> findByCategoriasCategoria(@Param("categoria") Categoria categoria);
        List<Produto> findByCategorias(@Param("produtoCategoria") ProdutoCategoria produtoCategoria);

        @Query("""
                SELECT cp.produto 
                FROM ProdutoCategoria cp 
                WHERE cp.categoria.id = :idCategoria
        """)
        List<Produto> findProdutosByCategoria(@Param("idCategoria") Long idCategoria);
        List<Produto> findByCategoriasCategoriaId(@Param("idCategoria") Long idCategoria);
        @Query("""
                SELECT cp.produto 
                FROM ProdutoCategoria cp 
                WHERE cp.categoria.id = :idCategoria
                ORDER BY cp.produto.preco ASC
        """)
        List<Produto> findProdutosByCategoriaPorPrecoAsc(@Param("idCategoria") Long idCategoria);
        List<Produto> findByCategoriasCategoriaIdOrderByPrecoAsc(@Param("idCategoria") Long idCategoria);
        @Query("""
                SELECT cp.produto 
                FROM ProdutoCategoria cp 
                WHERE cp.categoria.id = :idCategoria
                ORDER BY cp.produto.preco DESC
        """)
        List<Produto> findProdutosByCategoriaPorPrecoDesc(@Param("idCategoria") Long idCategoria);
        // feito certo
        List<Produto> findByCategoriasCategoriaIdOrderByPrecoDesc(@Param("idCategoria") Long idCategoria);
        List<Produto> findByNome(String nome);
        List<Produto> findByNomeContaining(String nome);
        List<Produto> findByPrecoGreaterThanOrderByPrecoDesc(double preco);
        List<Produto> findByPrecoLessThanOrderByPrecoAsc(double preco);
        Long countByCategoriasCategoriaNome(@Param("nomeCategoria") String nomeCategoria);
        Long countByPrecoLessThan(Double valor);
        Long countByPrecoGreaterThan(Double valor);

        List<Produto> findByPrecoLessThanOrNomeContainingIgnoreCase(Double valor, String t);

        List<Produto> findTop3ByOrderByPrecoDesc();

        List<Produto> findTop5ByCategoriasCategoriaNomeContainingIgnoreCaseOrderByPrecoAsc(String t);
        
        @Query("""
                SELECT p 
                FROM Produto p 
                WHERE p.preco >= :precoMinimo
        """)
        List<Produto> listarProdutosPrecoMinimoJPQL(Double precoMinimo);

        @Query("""
                SELECT p 
                FROM Produto p 
                ORDER BY p.preco ASC
        """)
        List<Produto> listarProdutosOrdenadosPrecoCrescenteJPQL();

         @Query("""
                SELECT p 
                FROM Produto p 
                ORDER BY p.preco DESC
        """)
        List<Produto> listarProdutosOrdenadosPrecoDerescenteJPQL();

        @Query("""
                SELECT p 
                FROM Produto p
                WHERE p.nome ILIKE :letra% 
        """)
        List<Produto> listarProdutosComecadosComLetraJPQL(String letra);
        @Query("""
                SELECT MAX(p.preco)
                FROM Produto p
                WHERE p.id IN (
                        SELECT pc.produto.id 
                        FROM ProdutoCategoria pc 
                        WHERE pc.categoria = :categoria
                )
        """)
        Double buscarProdutoComMaiorPrecoDaCategoriaJPQL(Categoria categoria);

        @Query("""
                SELECT COUNT(p)
                FROM Produto p
                WHERE p IN (
                        SELECT pc.produto 
                        FROM ProdutoCategoria pc 
                        WHERE pc.categoria = :categoria
                )
        """)
        Integer contarProdutoPorCategoriaJPQL(Categoria categoria);
        @Query("""
                SELECT p 
                FROM Produto p
                WHERE p.nome ILIKE %:nome% 
                OR p IN (
                        SELECT pc.produto 
                        FROM ProdutoCategoria pc 
                        WHERE pc.categoria = :categoria
                ) 
        """)
        List<Produto> listarProdutosPorCategoriaOuNomeJPQL(Categoria categoria, String nome);

        @NativeQuery("""
                SELECT * 
                FROM produto 
                ORDER BY valor DESC 
                LIMIT 5
        """)
        List<Produto> listarTop5ProdutosCarosNATIVE();
        @Query("""
                SELECT AVG(p.preco) 
                FROM Produto p
        """)
        Double mediaProdutosJPQL();
}
