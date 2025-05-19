package exercicios.contador.models;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;

import exercicios.contador.ContadorApplication;
import exercicios.contador.repository.CategoriaRepository;
import exercicios.contador.repository.ProdutoRepository;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class Categoria {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String nome;
    
    public Categoria(){}
    public Categoria(String nome){
        this.nome = nome;
    }

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private List<ProdutoCategoria> produtos;

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<ProdutoCategoria> getProdutoCategoria() {
        if (!Hibernate.isInitialized(produtos)) {
            Optional<Categoria> trans = ContadorApplication.get()
                .getCategoriaRepository()
                .findByIdWithProdutoCategorias(this.id);
            if(!trans.isPresent()) return null;
            if(trans.get().produtos == null) return null;
            this.produtos = trans.get().getProdutoCategoria();
            return this.produtos;
        }
        return this.produtos;
    }
    public List<Produto> getProdutos(){
        List<ProdutoCategoria> trans = getProdutoCategoria();
        if(trans == null) return null;
        return trans.stream()
                .map(e -> e.getProduto())
                .toList();
    }

    public void setProdutos(List<ProdutoCategoria> produtos) {
        this.produtos = produtos;
    }

     // Método para adicionar a relação
     public void addProduto(ProdutoCategoria produtoCategoria) {
        //this.produtos = getProdutos();
        produtos.add(produtoCategoria);
        //produtoCategoria.setCategoria(this);
    }

    // Método para remover a relação
    public void removeProduto(ProdutoCategoria produtoCategoria) {
        //this.produtos = getProdutos();
        produtos.remove(produtoCategoria);
        //produtoCategoria.setCategoria(null);
    }

    private void refresh(){}

    @Override
    public String toString(){
        return 
            "Categoria{"
            +" nome="+nome
            +", produtos=["+((getProdutos() == null)
                    ? "null"
                    : getProdutos().stream()
                            .map(e -> e.demo())  // Converte cada produto para string
                            .collect(java.util.stream.Collectors.joining(", ")))  // Junta com vírgula
            +"]}";
    }
    public String demo(){
        return "Categoria(id="+id+", nome="+nome+")";
    }
}
