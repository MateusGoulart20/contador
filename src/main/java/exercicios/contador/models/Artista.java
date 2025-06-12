package exercicios.contador.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class Artista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    @Enumerated(EnumType.STRING)
    private TipoArtista tipo;

    @OneToMany(mappedBy = "artista")
    private List<Musica> musicas = new ArrayList<>();

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public TipoArtista getTipo() {
        return tipo;
    }
    public void setTipo(TipoArtista tipo) {
        this.tipo = tipo;
    }

    Artista(){}
    public Artista(
        String nome,
        TipoArtista tipo
    ){
        this.nome = nome;
        this.tipo = tipo;
    }

    @Override
    public String toString(){
        return "Artista{nome="+nome+", tipo="+tipo+"}";
    }
}
