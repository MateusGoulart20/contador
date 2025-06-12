package exercicios.contador.models;

import java.util.function.ToIntBiFunction;

public enum TipoArtista {
    SOLO("Solo"),
    DUPLA("Dupla"),
    BANDA("Banda");

    private String tipo;
    TipoArtista(String tipo){
        this.tipo = tipo;
    }

    public static TipoArtista fromString(String text){
        for (TipoArtista categoria : TipoArtista.values()) {
            if (categoria.tipo.toLowerCase().contains(text.toLowerCase())) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }
}
