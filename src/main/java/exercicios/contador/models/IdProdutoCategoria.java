package exercicios.contador.models;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class IdProdutoCategoria implements Serializable {
    private Produto produto;
    private Categoria categoria;

    // ainda por cima é possível colocar ManyToOne / OneToMany
}
