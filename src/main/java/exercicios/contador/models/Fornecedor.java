package exercicios.contador.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="fornecedor")
public class Fornecedor {

    @Id
    @GeneratedValue
    private Long id;

    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Fornecedor(){}
    public Fornecedor(String nome){
        this.nome = nome;
    }

    @Override
    public String toString(){
        return "Fornecedor[nome="+nome+"]";
    }

    public String demo(){return "Fornecedor(id="+id+", nome="+nome+")";}

}
