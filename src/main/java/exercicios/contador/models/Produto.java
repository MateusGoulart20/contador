package exercicios.contador.models;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import exercicios.contador.ContadorApplication;
import exercicios.contador.repository.ProdutoRepository;
import org.hibernate.Hibernate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="produto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nome;
    
    @Column(name= "valor")
    private double preco;
    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<ProdutoCategoria> categorias;

    //private String categoria;

    public Produto(){}

    public Produto(
        String nome
        , double preco
        //, String categoria
    ) {
        this.nome = nome;
        this.preco = preco;
        //this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getPreco() {
        return preco;
    }

    public void setCategorias(List<ProdutoCategoria> categorias) {
        this.categorias = categorias;
    }

    /*//*/

    

    public List<ProdutoCategoria> getProdutoCategoria() {
        if (!Hibernate.isInitialized(categorias)) {
            Optional<Produto> trans = ContadorApplication.get()
                    .getProdutoRepository()
                    .findByIdWithProdutoCategorias(this.id);
            if(!trans.isPresent()) return null;
            if(trans.get().categorias == null) return null;
            this.categorias = trans.get().getProdutoCategoria();
            return this.categorias;
            /*/
            Hibernate.initialize(this);
            /*/
        }
        return categorias;
    }
    public List<Categoria> getCategorias(){
        List<ProdutoCategoria> trans =  getProdutoCategoria();
        if(trans == null) return null;
        return trans.stream()
                .map(e -> e.getCategoria())
                .toList();
    }

    /*/
    public List<ProdutoCategoria> getCategoria() {
        return categorias;
        //         produtoRepository.findByIdWithCategorias(id)
        //    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }
    /*/

    // Método para adicionar a relação
    public void addCategoria(ProdutoCategoria produtoCategoria) {
        //this.categorias=getCategorias();
        categorias.add(produtoCategoria);
        //produtoCategoria.setProduto(this);
    }

    // Método para remover a relação
    public void removeCategoria(ProdutoCategoria produtoCategoria) {
        //this.categorias=getCategorias();
        categorias.remove(produtoCategoria);
        //produtoCategoria.setProduto(null);
    }



    @Override
    public String toString() {
        return "Produto{"
                +"nome='" + nome + '\''
                +", preco=" + preco
                +", categoria=[" + ((getCategorias() == null)
                        ? "null"
                        : getCategorias().stream()
                                .map(e -> e.demo()) 
                                .collect(java.util.stream.Collectors.joining(", ")) )
                +"]}";
    }

    public String demo(){
        return "Produto(id="+id+", nome="+nome+")";
    }
}
