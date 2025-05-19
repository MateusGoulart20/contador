package exercicios.contador.models;

import exercicios.contador.ContadorApplication;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;

@Entity
@IdClass(IdProdutoCategoria.class)
public class ProdutoCategoria {
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    private Produto produto;
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    private Categoria categoria;

    private String observacao;

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public String toString(){
        return "ProdutoCategoria{"
                +"produto='" + produto.demo() + '\''
                +", categoria='" + categoria.demo()+ '\''
                +", observacao='" + observacao+ '\''
                +'}';            
    }
}
