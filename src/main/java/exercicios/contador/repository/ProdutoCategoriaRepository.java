package exercicios.contador.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import exercicios.contador.models.IdProdutoCategoria;
import exercicios.contador.models.ProdutoCategoria;

public interface ProdutoCategoriaRepository 
extends JpaRepository<ProdutoCategoria, IdProdutoCategoria>{

}
