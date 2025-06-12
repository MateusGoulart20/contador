package exercicios.contador.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Musica {
    @Id
    @GeneratedValue(strategy =  GenerationType.SEQUENCE)
    private Long id;

    private String nome;

    @ManyToOne
    private Artista autor;

    Musica(){}
    public Musica(
        String nome,
        Artista autor
    ){
        this.nome = nome;
        this.autor = autor;
    }

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}
    public Artista getAutor() {return autor;}
    public void setAutor(Artista autor) {this.autor = autor;}

    @Override
    public String toString(){
        return "Musica{nome="+nome+", artista="+autor+"}";
    }
}
