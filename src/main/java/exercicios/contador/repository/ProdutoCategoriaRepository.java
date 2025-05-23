package exercicios.contador.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import exercicios.contador.models.Categoria;
import exercicios.contador.models.IdProdutoCategoria;
import exercicios.contador.models.Produto;
import exercicios.contador.models.ProdutoCategoria;

public interface ProdutoCategoriaRepository 
extends JpaRepository<ProdutoCategoria, IdProdutoCategoria>{
            List<ProdutoCategoria> findByCategoria(@Param("categoria") Categoria categoria);
            List<ProdutoCategoria> findByProduto(@Param("produto") Produto produto);
}
